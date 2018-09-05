package com.java.beans;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

/**
 * Created by lenovo on 2018/1/4.
 */
public class TestConvertArray {

    public static void main(String[] args) {
        String str = "1";
        Integer integer = new Integer(str);

        Long aLong = new Long(str);
        BigDecimal decimal = new BigDecimal("1.0");
        System.out.println(decimal + "##" + aLong + "##" + integer);
     /*   Object[] obj = new String[] {"t1","t2"};
        String[] str1   = (String[])obj;
        System.out.println(str1.length);*/


        System.out.println("##" + NumberUtils.createInteger("1"));
    }

    public static <T> T[] toConvert(Class<T> clazz, String... arr) {
        return null;
    }
}
