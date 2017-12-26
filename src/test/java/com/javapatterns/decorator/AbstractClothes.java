package com.javapatterns.decorator;

public abstract class AbstractClothes implements AbstractPerson {

    AbstractPerson abstractPerson;

    public AbstractClothes(AbstractPerson abstractPerson) {
        this.abstractPerson = abstractPerson;
    }

    @Override
    public void show() {
        abstractPerson.show();
    }

}