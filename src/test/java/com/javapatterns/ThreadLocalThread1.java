package com.javapatterns;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalThread1 extends Thread {
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Thread[" + Thread.currentThread().getName() + "],counter=##" + Counter.getNextCounter());
        }
        Map<String, String> map = new HashMap<>();
    }

    public void name() {

    }
}