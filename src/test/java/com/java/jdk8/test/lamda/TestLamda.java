package com.java.jdk8.test.lamda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2017/6/27
 * Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中），或者把代码看成数据
 * 一个lambda可以由用逗号分隔的参数列表、–>符号与函数体三部分表示.
 */
public class TestLamda {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(a -> System.out.println(a));
        System.out.println("------------------------");
        list.forEach(a -> {
            System.out.println(a);
            System.out.println(a);
        });
        System.out.println("----------调用局部变量--------------");
        String separator = ",";
        list.forEach(a -> {
            String b = a + separator;
            System.out.println(b);
        });
        System.out.println("----------函数式编程接口Functional--------------");
        Functional functional = (x, y) -> {
            System.out.println("xx+" + x + "yy" + y);
            return x + y;
        };
        System.out.println("----------函数式编程接口--------------");
        functional.m1("100", "200");
        System.out.println("----------函数式编程接口--------------");
        func((a, b) -> System.out.println("-ddd--" + (a + b)), 1, 2);
    }

    public static void func(Func func, int a, int b) {
        System.out.println("insert func----");
        func.aoo(a, b);
    }
}
