package com.javapatterns.singleton;

import com.javapatterns.abstractfactory.Fruit;
import com.javapatterns.abstractfactory.GreenApple;
import com.javapatterns.abstractfactory.RedApple;

public class AppleEnumSingleton {

    enum Singleton {
        RED(new RedApple()), GREEN(new GreenApple());
        private Fruit fruit;

        Singleton(Fruit fruit) {
            this.fruit = fruit;
        }
        public Fruit getFruit() {
            return fruit;
        }

    }

    /**
     * 可以在一个枚举类中实现多种行为的组合,实现某个类的功能增强 或者共同行为
     */
    enum PersonExtAction implements  Person{
        //friut为单例
        RED(new RedApple()){
            @Override
            void showInfo() {
                System.out.println(":::ew"+this.getFruit().sayClassName());
            }
        }, GREEN(new GreenApple()){
            @Override
            void showInfo() {
                System.out.println(":::"+this.getFruit().sayClassName());
            }
        };

        private Fruit fruit;

        PersonExtAction(Fruit fruit){
            this.fruit=fruit;
        }

        public Fruit getFruit() {
            return fruit;
        }

        @Override
        public void eat() {
            System.out.println("befor eat:"+this.name());
            System.out.println(this.fruit.sayClassName());
            System.out.println("after eat");
            showInfo();
        }

        @Override
        public void sleep() {

        }
        abstract  void showInfo();
    }

}
