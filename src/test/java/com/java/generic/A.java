package com.java.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class A<T> {
    Class<T> clazz;

    void doGetClass() {
        System.out.println("this.getClass()==" + this.getClass().getSimpleName());
        Type genType = this.getClass().getGenericSuperclass();
        System.out.println("#genType=" + genType);
        if (!(genType instanceof ParameterizedType)) {
            this.clazz = (Class<T>) Object.class;
        }
        System.out.println("#clazz=" + this.clazz);
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        this.clazz = (Class<T>) params[0];
        System.out.println(this.clazz.getSimpleName());
    }
}