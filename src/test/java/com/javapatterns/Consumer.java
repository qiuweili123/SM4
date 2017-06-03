package com.javapatterns;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    private BlockingQueue<ImmutableData> queueBlockingQueue;

    public Consumer(BlockingQueue<ImmutableData> queueBlockingQueue) {

        this.queueBlockingQueue = queueBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ImmutableData data = queueBlockingQueue.poll(3, TimeUnit.SECONDS);
                System.out.println("start 取出data=" + data);
                if (data != null) {
                    System.out.println("poll 取出" + data);
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }

    }

}
