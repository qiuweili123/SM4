package com.thread;

import java.util.concurrent.atomic.LongAdder;

public class LongAddrTest {
    private static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {
        longAdder.add(50);
        System.out.println("##" + longAdder.longValue());
    }
}
