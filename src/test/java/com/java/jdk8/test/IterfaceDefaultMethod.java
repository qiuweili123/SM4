package com.java.jdk8.test;

public interface IterfaceDefaultMethod {

    public void methodA(String arg);

    public default void methodB() {
        System.out.println("################");
        methodA("inteterface hello");
        System.out.println("#1111111111111111111##");
    }

    public default String methodC() {
        System.out.println("################");
        methodA("inteterface hello");
        System.out.println("#1111111111111111111##");
        return "";
    }

}
