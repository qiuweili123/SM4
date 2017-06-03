package com.javapatterns.abstractfactory;

public class Factory {
    public static Fruit createFruit(Class fruit) throws Exception {
        return (Fruit) fruit.newInstance();
    }
}
