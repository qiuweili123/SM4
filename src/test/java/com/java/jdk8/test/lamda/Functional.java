package com.java.jdk8.test.lamda;

/**
 * Created by lenovo on 2017/6/27.
 * 函数式接口就是一个具有一个方法的普通接口。像这样的接口，可以被隐式转换为lambda表达式
 *
 * @FunctionalInterface避免编程接口的脆弱性
 */

public interface Functional {
    String m1(String key, String key2);

    //默认方法与静态方法并不影响函数式接口的契约，可以任意使用,单不能出现同样的方法
    // void  m2(String key,String key2);
    default void defaultMethod() {
        System.out.println("###");
    }
}
