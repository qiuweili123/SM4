/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: NettyServer.java
 * @Package com.netty.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年6月13日 下午9:52:21
 * @version
 */
package com.netty.test;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liqiuwei
 * @create time:2016年6月13日下午9:52:21
 * @Description:TODO(这里用一句话描述这个类的作用) org.jboss.netty包为netty3的包
 * io.netty为netty4的包
 */
public class NettyServer {
    public static void main(String[] args) {

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        ExecutorService bossPool = Executors.newSingleThreadExecutor();//轮训线程Executors相当于Arrays的工具类
        ExecutorService workerPool = Executors.newFixedThreadPool(10);//工作线程池
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(bossPool, workerPool));//设置niosocket工厂
        //设置管道工厂
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {//管道的主要作用是增加数据的过滤器,理解为装有很多的过滤器
                ChannelPipeline pipeline = Channels.pipeline();//过滤器分为上行过滤器和下行过滤器

                pipeline.addLast("deencoder", new StringDecoder());//增加对上行消息过滤器
                pipeline.addLast("encoder", new StringEncoder());//增加下行消息过滤器
                pipeline.addLast("helloHandler", new HelloHandler());
                return pipeline;
            }
        });

        serverBootstrap.bind(new InetSocketAddress(7001));//绑定服务端口
        System.out.println("服务已经启动 7001");
    }
}
