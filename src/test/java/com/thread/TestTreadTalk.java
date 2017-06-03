package com.thread;

import java.util.concurrent.LinkedBlockingQueue;

public class TestTreadTalk {
    public static void main(String[] args) {
        final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(3);

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        queue.add(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("从子线程提交的任务!!thread.name=" + Thread.currentThread().getName());
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        while (true) {
            try {
                Runnable r = queue.take();
                System.out.println("主线程执行子线程命令: " + Thread.currentThread().getName());
                r.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
