package com.javapatterns.decorator;

/**
 * Created by lenovo on 2017/12/17.
 */
public class BDecorator extends AbsDecotator {


    public BDecorator(IDecorator decorator) {
        super(decorator);
    }

    @Override
    public void show() {
        super.show();
        System.out.println("show b");
    }
}
