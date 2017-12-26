package com.javapatterns.decorator;

/**
 * Created by lenovo on 2017/12/17.
 */
public abstract class AbsDecotator implements IDecorator {

    private IDecorator decorator;

    public AbsDecotator(IDecorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public void show() {
        decorator.show();
    }
}
