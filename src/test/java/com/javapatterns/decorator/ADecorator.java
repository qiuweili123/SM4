package com.javapatterns.decorator;

/**
 * Created by lenovo on 2017/12/17.
 */
public class ADecorator extends AbsDecotator {

    public ADecorator(IDecorator decorator) {
        super(decorator);
    }

    @Override
    public void show() {
        super.show();
        System.out.println("ADecorator");
    }
}
