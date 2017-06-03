package com.javapatterns;

import com.javapatterns.abstractfactory.Fruit;
import com.javapatterns.abstractfactory.GreenApple;
import com.javapatterns.abstractfactory.RedApple;

/**
 * 枚举抽象方法使用
 * 满足即是单例也是用工厂方法动态创建实例
 * 使用枚举结合工厂方法，创建工厂
 *
 * @author liqiuwei
 * @create time:2015年11月6日上午11:11:37
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public enum FruitFactoryEnum {
    REDAPPLE {
        @Override
        public Fruit createFruit() {
            System.out.println("创建red");
            return new RedApple();
        }
    }, GREENAPPLE {
        @Override
        public Fruit createFruit() {
            System.out.println("创建green");
            return new GreenApple();
        }
    };

    public abstract Fruit createFruit();
}