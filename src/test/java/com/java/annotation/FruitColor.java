package com.java.annotation;

import java.lang.annotation.*;

/**
 * 水果颜色注解
 *
 * @author peida
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    Color value();

    /**
     * 颜色属性
     *
     * @return
     */
    Color fruitColor() default Color.GREEN;

    ;

    /**
     * 颜色枚举
     *
     * @author peida
     */
    public enum Color {
        BULE, RED, GREEN
    }

}