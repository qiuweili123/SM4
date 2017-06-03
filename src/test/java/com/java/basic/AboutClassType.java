package com.java.basic;

import com.sh.model.Student;

import java.util.Map;

public class AboutClassType {
    public static void main(String[] args) throws Exception {
        System.out.println((char) 65);
        System.out.println(isWrapClass(Long.class));
        System.out.println(isWrapClass(Integer.class));
        System.out.println(isWrapClass(String.class));
        System.out.println(isWrapClass(Map.class));
        System.out.println(isWrapClass(Student.class));
    }

    public static boolean isWrapClass(Class clz) {
        try {
            return clz.getClassLoader() == null;

        } catch (Exception e) {
            return false;
        }
    }
}
