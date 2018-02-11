package com.java.annotation;


import java.util.Objects;

/**
 * Created by lenovo on 2018/2/8.
 */
public class Fruit {
    private FruitColor.Color color;

    public Fruit() {

        FruitColor fruitColor = this.getClass().getAnnotation(FruitColor.class);
        if (Objects.isNull(fruitColor)) {
            throw new RuntimeException("没有");
        }

        System.out.println("##" + fruitColor.value());
        // AnnotationUtils.getValue()
    }
}
