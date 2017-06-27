package com.java.jdk8.test.repeatable;

/**
 * Created by lenovo on 2017/6/27.
 */

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Filters.class)
/**
 * ，这里有个使用@Repeatable( Filters.class )注解的注解类Filter，Filters仅仅是Filter注解的数组
 */
public @interface Filter {
    String value();
}
