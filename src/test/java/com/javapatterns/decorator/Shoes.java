package com.javapatterns.decorator;

public class Shoes extends AbstractClothes {

    public Shoes(AbstractPerson abstractPerson) {
        super(abstractPerson);
    }

    @Override
    public void show() {
        super.show();
        say();
    }

    public void say() {
        System.out.println("我展示一双鞋子");
    }
}