package com.java.jdk8.test.lamda;

/**
 * Created by lenovo on 2017/6/27.
 * 函数式编程接口,可以没有实现累，用lamda表达式实现即可
 */
//@FunctionalInterface
public interface Func extends NonFunc {
    void aoo(int a, int b);

    default void foo() {
        System.out.println("foo");
    }

    default void coo() {
        System.out.println("coo");
    }
}
