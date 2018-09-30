package com.javapatterns.singleton;

import scala.App;

public class EnumSingletonTest {



    public static void main(String[] args) {
        System.out.println(AppleEnumSingleton.Singleton.GREEN.getFruit()==AppleEnumSingleton.Singleton.GREEN.getFruit());

        Person green = AppleEnumSingleton.PersonExtAction.GREEN;
        green.eat();

    }
}
