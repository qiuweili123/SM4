package com.javapatterns;

public class Counter {
    // 新建一个静态的ThreadLocal变量，并通过get方法将其变为一个可访问的对象
    private static final ThreadLocal<Integer> counterContext = new ThreadLocal<Integer>() {
  /*      protected synchronized Integer initialValue() {
            return 0;
        }*/

        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    // 通过静态的get方法访问ThreadLocal中存储的值
    public static Integer get() {

        return counterContext.get();
    }

    // 通过静态的set方法将变量值设置到ThreadLocal中
    public static void set(Integer value) {
        counterContext.set(value);
    }

    // 封装业务逻辑，操作存储于ThreadLocal中的变量
    public static Integer getNextCounter() {
        // System.out.println("##counterContext==" + counterContext);
        counterContext.set(counterContext.get() + 1);
        return counterContext.get();
    }
}