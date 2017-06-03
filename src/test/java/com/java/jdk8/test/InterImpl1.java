package com.java.jdk8.test;

public class InterImpl1 implements IterfaceDefaultMethod {

    public static void main(String[] args) {
        InterImpl1 impl1 = new InterImpl1();
        impl1.methodB();
    }

    @Override
    public void methodA(String arg) {
        System.out.println("kkaakkkk==" + arg);
    }

}
