package com.javapatterns;

import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractSingleton<T> {
    private final AtomicReference<T> ref = new AtomicReference<T>();

    public T get() {
        T ret = ref.get();
        if (ret == null) {
            /*
             * try { Thread.sleep(300); } catch (InterruptedException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */
            // 第一种方式

            T obj = newObj();
            boolean rets = ref.compareAndSet(null, obj);
            // System.out.println(obj + "#rets==" + rets + "##name==" +
            // Thread.currentThread().getName());
            if (rets) {
                return obj;
            } else {
                return ref.get();
            }

            // 第二种实现方式
            /*
			 * synchronized (this) { if (ref.get() == null) { ret = newObj();
			 * ref.set(ret); } else { ret = ref.get(); } }
			 */

        }
        return ret;
    }

    protected abstract T newObj();
}