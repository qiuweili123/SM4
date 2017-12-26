package com.javapatterns.decorator;

public class Hat extends AbstractClothes {

    private String color;

    public Hat(AbstractPerson abstractPerson, String color) {
        super(abstractPerson);
        this.color = color;
    }

    @Override
    public void show() {
        super.show();
        say();
    }

    public void say() {
        System.out.println("我展示一个帽子" + color);
    }

    public void setColor(String color) {
        this.color = color;
    }
}