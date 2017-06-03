package com.javapatterns.abstractfactory;


public class RedOrange extends Orange {
    public String sayClassName() {
        System.out.println("red orange");
        return "red orange";
    }
}
