package com.java.jvisualVM;

import java.util.concurrent.TimeUnit;

/**
 * @author liqiuwei
 * @create time:2015年11月11日下午2:35:15
 * @Description:线程调优 Java 语言能够很好的实现多线程应用程序。
 * 当我们对一个多线程应用程序进行调试或者开发后期做性能调优的时候，往往需要了解当前程序中所有线程的运行状态，是否有死锁、热锁等情况的发生，从而分析系统可能存在的问题。
 */
public class MyThread extends Thread {

    public MyThread(String name) {
        System.out.println("start........" + name);
    }

    public static void main(String[] args) throws Exception {
        TimeUnit.SECONDS.sleep(10);
        System.out.println("start........");
        MyThread mt1 = new MyThread("Thread a");
        MyThread mt2 = new MyThread("Thread b");

        mt1.setName("My-Thread-1 ");
        mt2.setName("My-Thread-2 ");

        mt1.start();
        mt2.start();
    }

    public void run() {

        while (true) {

        }
    }


}