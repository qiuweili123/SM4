package com.javapatterns.decorator;

/**
 * Created by lenovo on 2017/12/17.
 */
public class Test {
    public static void main(String[] args) {
        BDecorator b = new BDecorator(new ADecorator(new Compont()));
        b.show();

        //创建被装饰者
        Me me = new Me("liming");

        //裸体的人被装饰了帽子 ，具有了展示帽子的能力
        Hat hat = new Hat(me, "red");

        // 带了帽子的人被装饰了鞋子，具有了展示鞋子的本领
        Shoes shoes = new Shoes(hat);
        hat.setColor("green");
        Shoes shoes2 = new Shoes(hat);
        shoes.show();
    }
}
