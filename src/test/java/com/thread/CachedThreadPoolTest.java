package com.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CachedThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        int i = 0;
        while (true) {
            //for (int i = 0; i < 100; i++) {
            //  String id=UUID.nameUUIDFromBytes((+"").getBytes()).toString();
            String id = "thread-" + i;
            tpe.execute(new MyThread(id));
            // }
            i++;

            System.out.println("##" + tpe.isTerminated());

            if (tpe.getActiveCount() == 0) {
                System.out.println("当前线程数为:" + tpe.getPoolSize() + "###" + tpe.getActiveCount() + "##" + tpe.getCompletedTaskCount());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
