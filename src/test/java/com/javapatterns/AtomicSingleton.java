package com.javapatterns;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicSingleton {

    private static AtomicReference<AtomicSingleton> atomicReference = new AtomicReference<AtomicSingleton>();

    // test how constructor get into by new AtomicSingleton statement
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private AtomicSingleton() {
        int i = atomicInteger.get();
        atomicInteger.addAndGet(i++);
    }

    public static AtomicSingleton getInstance() {
        AtomicSingleton atomicSingleton = atomicReference.get();
        if (null == atomicSingleton) {
            synchronized (AtomicSingleton.class) {
                // double checking the AtomicSingleton whether initial, ensure
                // not new AtomicSingleton twice
                // to waste the system resource
                if (null != (atomicSingleton = atomicReference.get())) {
                    return atomicSingleton;
                }

                // ensure the locate the memory, construct and other instructs
                // is atomic,
                // whatever the implementation of the JVM
                atomicReference.getAndSet(new AtomicSingleton());
                // atomicReference.compareAndSet(null, new AtomicSingleton());
                atomicSingleton = atomicReference.get();
            }
        }
        return atomicSingleton;
    }

    public static void main(String[] args) {
        System.out.println(AtomicSingleton.getInstance());
    }
}