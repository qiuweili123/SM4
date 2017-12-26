package com.javapatterns.decorator;

/**
 * Created by lenovo on 2017/12/17.
 */
public class Compont implements IDecorator {
    @Override
    public void show() {
        System.out.println("compont");
    }
}
