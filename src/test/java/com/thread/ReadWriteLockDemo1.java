package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock内置两个Lock，一个是读的Lock，一个是写的Lock。
 * 多个线程可同时得到读的Lock，但只有一个线程能得到写的Lock，
 * 而且写的Lock被锁定后，任何线程都不能得到Lock。ReadWriteLock提供的方法有：
 * readLock(): 返回一个读的lock
 * writeLock(): 返回一个写的lock, 此lock是排他的。
 * ReadWriteLockTest很适合处理类似文件的读写操作。
 * 读的时候可以同时读，但不能写；写的时候既不能同时写也不能读。
 */
public class ReadWriteLockDemo1 {
    // 锁
    ReadWriteLock lock = new ReentrantReadWriteLock();
    // 值
    double value = 0d;
    int addtimes = 0;

    /**
     * 增加value的值，不允许多个线程同时进入该方法
     */
    public void addValue(double v) {
        // 得到writeLock并锁定
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        System.out.println("ReadWriteLockTest to addValue: " + v + "   " + System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        try {
            // 做写的工作
            this.value += v;
            this.addtimes++;
        } finally {
            // 释放writeLock锁
            writeLock.unlock();
        }
    }

    /**
     * 获得信息。当有线程在调用addValue方法时，getInfo得到的信息可能是不正确的。
     * 所以，也必须保证该方法在被调用时，没有方法在调用addValue方法。
     */
    public String getInfo() {
        // 得到readLock并锁定
        Lock readLock = lock.readLock();
        readLock.lock();
        System.out.println("ReadWriteLockTest to getInfo   " + System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        try {
            // 做读的工作
            return this.value + " : " + this.addtimes;
        } finally {
            // 释放readLock
            readLock.unlock();
        }
    }
}
 
    