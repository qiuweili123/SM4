/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TestProxy.java
 * @Package com.javapatterns.proxy
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年11月27日 下午9:14:19
 * @version
 */
package com.javapatterns.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * @author lenovo
 * @create time:2016年11月27日下午9:14:19
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class TestProxy {

    public static void main(String[] args) throws NoSuchMethodException {
        Subject subject = new RealSubject();
        SubjectHandler subjectHandler = new SubjectHandler(subject);
        Subject proxy = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), subjectHandler);
        for (Field field : proxy.getClass().getFields()) {
            System.out.println(field.getName());
        }
        System.out.println(proxy.getClass().getFields().length + "##" + proxy.getClass().getMethod("toString"));
        proxy.doSomething();

    }

}
