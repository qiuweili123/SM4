package com.java.reflection;

import java.lang.reflect.Field;

/**
 * Created by lenovo on 2017/11/17.
 */
public class AboutGetFiledValue {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User();
        user.setId(124L);
        System.out.println("###" + getFiledVlaue(user, "id"));
    }

    public static <T> Object getFiledVlaue(T obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
