/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: ThreadLocalPattern.java
 * @Package com.javapatterns
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: ard-liqiuwei
 * @date: 2017年2月8日 下午5:40:08
 * @version
 */
package com.javapatterns;

/**
 * @author ard-liqiuwei
 * @create time:2017年2月8日下午5:40:08
 * @Description:ThreadLocal设计模式
 */
public class ThreadLocalPattern {
    public static void main(String[] args) {
        ThreadLocalThread1 testThread1 = new ThreadLocalThread1();
        ThreadLocalThread1 testThread2 = new ThreadLocalThread1();
        ThreadLocalThread1 testThread3 = new ThreadLocalThread1();
        testThread1.start();
        testThread2.start();
        testThread3.start();
    }
}
