package com.java.jdk8.test.repeatable;

import org.springframework.context.annotation.PropertySource;

/**
 * Created by lenovo on 2017/6/27.
 * 可重复注解可以实现一个注解在一个方法使用多次
 * PropertySource就是spring的原生应用
 */
@PropertySource("")
@PropertySource("ss")
public class TestRepeatable {
    public static void main(String[] args) {
        //反射射相关的API提供了新的函数getAnnotationsByType()来返回重复注解的类型
        for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
            System.out.println(filter.value());
        }
    }
}
