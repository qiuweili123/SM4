/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: GenericSingleton.java
 * @Package com.javapatterns
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年11月20日 下午9:39:54
 * @version
 */
package com.javapatterns;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lenovo
 * @create time:2016年11月20日下午9:39:54
 * @Description:通配符(?)赋值的类型不确定的时，赋值类型确定时用T.通配符？只能用于读
 */
public class GenericSingleton {
    private final static Object lock = new Object();
    // Class<? extends Single>
    private static ConcurrentHashMap<Class<? extends Single>, Single> singleMap = new ConcurrentHashMap<>();
    ;

    public static <T extends Single> Single getInstance(Class<? extends Single> class1) throws Exception {
        T instance;
        if (singleMap.containsKey(class1)) {
            instance = (T) singleMap.get(class1);
        } else {
            /*
             * 第一种实现 synchronized (lock) { if (singleMap.containsKey(class1)) {
			 * return (T) singleMap.get(class1); }
			 */

			/*
             * instance = (T) class1.newInstance(); if
			 * (instance.setSingle(instance)) { singleMap.put(class1, instance);
			 * } else { instance = (T) singleMap.get(class1); }
			 */

            instance = (T) singleMap.get(class1);
            if (instance == null) {
                instance = (T) class1.newInstance();
                T ins = (T) singleMap.putIfAbsent(class1, instance);
                if (ins != null) {
                    instance = ins;
                }
            }
            return instance;

        }

        return instance;

    }
}
