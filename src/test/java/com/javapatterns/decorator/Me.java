package com.javapatterns.decorator;

public class Me implements AbstractPerson {

    private String name;

    public Me(String name) {
        this.name = name;
    }

    @Override
    public void show() {
        System.out.println(name + "什么都没穿，我展示的是裸体");
    }

}