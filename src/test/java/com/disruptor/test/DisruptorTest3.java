package com.disruptor.test;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liqiuwei
 * @create time:2016年5月16日下午4:28:37
 * @Description:假设如下场景： 1、交易网关收到交易(P1)把交易数据发到RingBuffer中，
 * 2、负责处理增值业务的消费者C1和负责数据存储的消费者C2负责处理交易
 * 3、负责发送JMS消息的消费者C3在C1和C2处理完成后再进行处理。
 */
public class DisruptorTest3 {
    public static void main(String[] args) throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("my-thread-%s").daemon(true).build();

        int bufferSize = 32;  // // RingBuffer 大小，必须是 2 的 N 次方；
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //这个构造函数参数，相信你在了解上面2个demo之后就看下就明白了，不解释了~  
        Disruptor<TradeTransaction> disruptor = new Disruptor<TradeTransaction>(new EventFactory<TradeTransaction>() {
            @Override
            public TradeTransaction newInstance() {
                return new TradeTransaction();
            }
        }, bufferSize, threadFactory, ProducerType.SINGLE, new BusySpinWaitStrategy());

disruptor.setDefaultExceptionHandler(new DisruptorExceptionHandler("memo"));
        System.out.println("##==" + threadFactory.getThreadCount());
        //使用disruptor创建消费者组C1,C2  handleEventsWith每个BatchEvenProcessor有一个Sequence，一个消息必然会被每一个BatchEvenProcessor消费。
        EventHandler<TradeTransaction>  vas=new TradeTransactionVasConsumer();
        EventHandler<TradeTransaction>  db1= new TradeTransactionInDBHandler();
        EventHandler<TradeTransaction>  db2= new TradeTransactionInDBHandler();

        EventHandlerGroup<TradeTransaction> handlerGroup = disruptor.handleEventsWith(vas,db1,db2);
        // disruptor.handleEventsWithWorkerPool(new TradeTransactionInDBHandler("worker-1"),new TradeTransactionInDBHandler("worker-2"));//一条消息只被一个handler处理。
        disruptor.after(vas,db1,db2);
        TradeTransactionJMSNotifyHandler jmsConsumer = new TradeTransactionJMSNotifyHandler();
        //声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3  
        handlerGroup.then(jmsConsumer);


        disruptor.start();//启动  
        CountDownLatch latch = new CountDownLatch(1);
        //生产者准备  
        executor.submit(new TradeTransactionPublisher(latch, disruptor));
        latch.await();//等待生产者完事.  
        System.out.println(disruptor.getBufferSize());
        disruptor.shutdown();  //关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
        executor.shutdown();  //关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown 时不会自动关闭；
        System.out.println("##==" + threadFactory.getThreadCount());
        System.out.println("总耗时:" + (System.currentTimeMillis() - beginTime));
    }
}  