package com.javapatterns.singleton;

/**
 * DCL(DubleCheckedLock)单例模式
 */
public class Singleton {
    private volatile static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {//锁细化  
            synchronized (Singleton.class) {
                if (instance == null) {//多线程环境下还需要再次判空  
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}  