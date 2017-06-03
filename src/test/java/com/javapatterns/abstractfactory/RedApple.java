package com.javapatterns.abstractfactory;


public class RedApple extends Apple {
    public String sayClassName() {
        System.out.println("red apple");
        return "red apple";
    }
}
