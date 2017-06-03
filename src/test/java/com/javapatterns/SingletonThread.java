/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: SingletonThread.java
 * @Package com.javapatterns
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年11月18日 下午11:54:42
 * @version
 */
package com.javapatterns;

/**
 * @author liqiuwei
 * @create time:2016年11月18日下午11:54:42
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class SingletonThread extends Thread {
    /**
     * <p>
     * Title: run
     * </p>
     * <p>
     * Description:单例的非安全性测试
     * </p>
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            // System.out.println( Thread.currentThread().getName() +
            // "#########" + GenericSingleton.getInstance(Peple.class));
            System.out.println(Thread.currentThread().getName() + "#########" + PepleSingeton.INSTACE.get());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println(PepleSingeton.INSTACE.get().hashCode());

    }
}
