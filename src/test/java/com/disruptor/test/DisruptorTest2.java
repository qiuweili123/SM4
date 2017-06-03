package com.disruptor.test;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

/**
 * @author liqiuwei
 * @create time:2016年5月16日下午4:14:02
 * @Description:使用WorkerPool辅助创建消费者
 */
public class DisruptorTest2 {
    public static void main(String[] args) throws InterruptedException {
        int BUFFER_SIZE = 1024;
        int THREAD_NUMBERS = 4;
        EventFactory<TradeTransaction> eventFactory = new EventFactory<TradeTransaction>() {
            public TradeTransaction newInstance() {
                return new TradeTransaction();
            }
        };


        Disruptor<TradeTransaction> disruptor = new Disruptor<TradeTransaction>(new EventFactory<TradeTransaction>() {
            @Override
            public TradeTransaction newInstance() {
                return new TradeTransaction();
            }
        }, BUFFER_SIZE, new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread("myThread run");
                return null;
            }
        }, ProducerType.SINGLE, new BusySpinWaitStrategy());

        disruptor.setDefaultExceptionHandler(new IgnoreExceptionHandler());
        disruptor.handleEventsWithWorkerPool(new TradeTransactionInDBHandler("worker-1"), new TradeTransactionInDBHandler("worker-2"));//一条消息只被一个handler处理。
        RingBuffer<TradeTransaction> ringBuffer = disruptor.start();
        //生产者发送消息
        CountDownLatch latch = new CountDownLatch(1);
        //生产者准备  
        TradeTransactionPublisher publisher = new TradeTransactionPublisher(latch, disruptor);
        new Thread(publisher).start();
        latch.await();//等待生产者完事.  
        System.out.println(disruptor.getBufferSize());
        disruptor.shutdown();  //关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；


    }
}  