package com.spring.utils;


import org.springframework.util.StringUtils;

/**
 * Created by lenovo on 2018/2/11.
 */
public class TestStringUtils {
    public static void main(String[] args) {
        String str = "127.0.0.1:9200";
        System.out.println(StringUtils.hasLength(str));
        String[] arry = org.apache.commons.lang3.StringUtils.split(str, ",");
        for (int i = 0; i < arry.length; i++) {
            System.out.println(arry[i]);
        }
    }
}
