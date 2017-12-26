package com.spring.scan.test.spring;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by lenovo on 2017/7/8.
 */
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
@Component
public @interface NSEntity {
}
