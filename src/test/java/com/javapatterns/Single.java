/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: Single.java
 * @Package com.javapatterns
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年11月20日 下午9:43:37
 * @version
 */
package com.javapatterns;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lenovo
 * @create time:2016年11月20日下午9:43:37
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public abstract class Single<T> {
    private final AtomicReference<T> ref = new AtomicReference<T>();

    protected T getSingle() {
        return ref.get();
    }

    protected boolean setSingle(T instance) {
        if (ref.compareAndSet(null, instance)) {
            return true;
        }
        return false;

    }
}
