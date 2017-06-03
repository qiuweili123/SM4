/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: SubjectHandler.java
 * @Package com.javapatterns.proxy
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年11月27日 下午9:05:12
 * @version
 */
package com.javapatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lenovo
 * @create time:2016年11月27日下午9:05:12
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class SubjectHandler implements InvocationHandler {
    private Subject subject;

    public SubjectHandler(Subject subject) {
        this.subject = subject;
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     * @throws Throwable
     * @see InvocationHandler#invoke(Object, * Method, Object[])
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("being...");
        Object object = method.invoke(subject, args);
        System.out.println("end...");
        return object;
    }

}
