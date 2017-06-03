package com.javapatterns.abstractfactory;


public class GreenApple extends Apple {
    public String sayClassName() {
        System.out.println("red apple");
        return "red apple";
    }
}
