package com.sh.utils;


public class StringUtils {
    public static String dquote(final String str) {
        System.out.println("str=="+str);
        return CharConstant.DOT + str + CharConstant.DOT;
    }
}
