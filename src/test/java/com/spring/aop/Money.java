package com.spring.aop;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by lenovo on 2017/7/6.
 */
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface Money {
    String name() default "";
}
