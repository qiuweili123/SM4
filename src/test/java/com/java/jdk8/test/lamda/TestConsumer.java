package com.java.jdk8.test.lamda;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by lenovo on 2017/6/27.
 * Consumer为系统默认的Functionnal
 */

public class TestConsumer {
    public static void main(String[] args) {
        //<!--Consumer接受一个泛型参数，不需要返回值的函数接口-->
        Consumer<Integer> consumer = System.out::println;
        consumer.accept(100);
        //<!--Supplier接受一个泛型参数，需要返回值的函数接口-->
        Supplier<Integer> supplier = () -> {
            return 100 + 1000;
        };
        Integer c = supplier.get();
        System.out.println("cc##" + c);
    }
}
