package com.rabbitmq.spring.remoting;

import com.rabbitmq.client.*;
import com.rabbitmq.spring.ExchangeType;
import com.rabbitmq.spring.InvalidRoutingKeyException;
import com.rabbitmq.spring.channel.RabbitChannelFactory;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.remoting.support.RemoteInvocation;
import org.springframework.remoting.support.RemoteInvocationBasedExporter;
import org.springframework.remoting.support.RemoteInvocationResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class RabbitInvokerServiceExporter extends RemoteInvocationBasedExporter implements InitializingBean, DisposableBean, ShutdownListener {

    private final Log log = LogFactory.getLog(RabbitInvokerServiceExporter.class);

    private RabbitChannelFactory channelFactory;
    private String exchange;
    private ExchangeType exchangeType;
    private String queueName;
    private String routingKey;

    private Object proxy;
    private List<RpcServer> rpcServerPool;
    private int poolsize = 1;

    public void afterPropertiesSet() {

        // 检查exchange type类型不能为fanout
        if (exchangeType.equals(ExchangeType.FANOUT)) {
            throw new InvalidRoutingKeyException(String.format("Exchange type %s not allowed for service exporter", exchangeType));
        }

        exchangeType.validateRoutingKey(routingKey);
        // 调用org.springframework.remoting.support.RemoteExporter的getProxyForService()，得到代理对象
        proxy = getProxyForService();
        // 初始化rpcServer池
        rpcServerPool = new ArrayList<RpcServer>(poolsize);
        // 初始化RpcServer，并开始接收请求
        startRpcServer();
    }

    // 初始化RpcServer，并开始接收请求
    private void startRpcServer() {
        try {
            // 创建临时的channel，用来定义queue，exchange，并进行bind
            // 这里有两个用处：
            // 1：在服务端也定义queue，避免因为先开服务端而出现queue没被定义的错误
            // 2：这里先用一个channel定义一下qeueue，后面的for循环里面就不用每个都去定义了
            log.info("Creating channel and rpc server");
            Channel tmpChannel = channelFactory.createChannel();
            tmpChannel.getConnection().addShutdownListener(this);
            //自动删除非持久队列
            tmpChannel.queueDeclare(queueName, false, false, true, null);
            if (exchange != null) {
                tmpChannel.exchangeDeclare(exchange, exchangeType.toString());
                tmpChannel.queueBind(queueName, exchange, routingKey);
            }
            // 创建poolsize个RpcServer，每个RpcServer使用一个单独的channel，并且分别使用单独的线程去接收请求，提升接收速度
            for (int i = 1; i <= poolsize; i++) {
                try {
                    // 每次都创建一个新的channel，因为一个channel在多个线程中使用是会有问题的(官方文档和channel的JavaDoc上是这样说的)
                    Channel channel = channelFactory.createChannel();

                    log.info(String.format("Starting rpc server %d on exchange [%s(%s)] - queue [%s] - routingKey [%s]", i, exchange, exchangeType, queueName, routingKey));
                    // 使用当前的channel创建一个RpcServer去处理请求
                    final RpcServer rpcServer = createRpcServer(channel);
                    rpcServerPool.add(rpcServer);
                    // 创建一个线程让当前的RpcServer去处理请求
                    Runnable main = new Runnable() {
                        public void run() {
                            try {
                                // rpcServer开始处理请求
                                throw rpcServer.mainloop();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    };
                    // 线程开始
                    new Thread(main).start();
                } catch (IOException e) {
                    log.warn("Unable to create rpc server", e);
                }
            }
        } catch (Exception e) {
            log.error("Unexpected error trying to start rpc servers", e);
        }
    }

    // 创建RpcServer对象
    private RpcServer createRpcServer(Channel channel) throws IOException {
        return new RpcServer(channel, queueName) {

            // 重写处理接收到的消息的方法
            @Override
            public byte[] handleCall(byte[] requestBody, AMQP.BasicProperties replyProperties) {
                // 因为在客户端调用方法的时候，是将客户端调用的方法的信息封装成一个RemoteInvocation对象，然后序列化成一个byte数据再使用RpcClient发送到服务端的
                // 所以在这里(服务端接收消息)，将消息(requestBody)反序列化成RemoteInvocation对象
                RemoteInvocation invocation = (RemoteInvocation) SerializationUtils.deserialize(requestBody);
                //根据RemoteInvocation的信息，服务端使用代理对象执行相应的方法，并得到执行结果
                RemoteInvocationResult result = invokeAndCreateResult(invocation, proxy);
                // 将执行结果序列化为byte数据，然后返回给客户端
                return SerializationUtils.serialize(result);
            }
        };
    }

    public void setChannelFactory(RabbitChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
    }

    @Required
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Object getProxy() {
        return proxy;
    }

    public void destroy() throws Exception {
        clearRpcServers();
    }

    // 清除所有的RpcServer
    private void clearRpcServers() {
        if (log.isInfoEnabled()) {
            log.info(format("Closing %d rpc servers", rpcServerPool.size()));
        }

        for (RpcServer rpcServer : rpcServerPool) {
            try {
                // 中止处理请求
                rpcServer.terminateMainloop();
                rpcServer.close();
            } catch (Exception e) {
                log.warn("Error termination rpcserver loop", e);
            }
        }
        rpcServerPool.clear();
        if (log.isInfoEnabled()) {
            log.info("Rpc servers closed");
        }

    }

    public void shutdownCompleted(ShutdownSignalException cause) {
        if (log.isInfoEnabled()) {
            log.info(String.format("Channel connection lost for reason [%s]", cause.getReason()));
            log.info(String.format("Reference [%s]", cause.getReference()));
        }

        if (cause.isInitiatedByApplication()) {
            if (log.isInfoEnabled()) {
                log.info("Sutdown initiated by application");
            }
        } else if (cause.isHardError()) {
            log.error("Shutdown is a hard error, trying to restart the RPC server...");
            startRpcServer();
        }
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Required
    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public void setPoolsize(int poolsize) {
        this.poolsize = poolsize;
    }

    @Required
    public void setExchangeType(ExchangeType exchangeType) {
        this.exchangeType = exchangeType;
    }
}
