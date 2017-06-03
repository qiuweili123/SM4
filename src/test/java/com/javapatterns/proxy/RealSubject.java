/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: RealSubject.java
 * @Package com.javapatterns.proxy
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年11月27日 下午9:01:09
 * @version
 */
package com.javapatterns.proxy;

/**
 * @author lenovo
 * @create time:2016年11月27日下午9:01:09
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class RealSubject implements Subject {

    /**
     * <p>
     * Title: doSomething
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @see Subject#doSomething()
     */
    @Override
    public void doSomething() {
        System.out.println("real subject");
    }

}
