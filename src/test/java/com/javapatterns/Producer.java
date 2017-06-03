package com.javapatterns;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private static AtomicInteger count = new AtomicInteger(0);//原子性数据操作
    private Boolean isRuning = true;
    private String threadName;
    private BlockingQueue<ImmutableData> queueBlockingQueue;

    public Producer(BlockingQueue<ImmutableData> queueBlockingQueue, String threadName) {
        this.queueBlockingQueue = queueBlockingQueue;
        this.threadName = threadName;
    }

    @Override
    public void run() {

        ImmutableData data = null;
        Random r = new Random();
        System.out.println(Thread.currentThread().getName() + "start producer==" + Thread.currentThread().getId());

        try {
            while (isRuning) {
                Thread.sleep(r.nextInt(1000));
                data = new ImmutableData(count.incrementAndGet());
                System.out.println(Thread.currentThread().getName() + "##" + threadName + "Start put in quene" + data);
                if (queueBlockingQueue.offer(data, 10, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + "puted in quene" + data);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();

        }

    }

    public void stop() {
        this.isRuning = false;
    }


}
