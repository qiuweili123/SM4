package com.java.jdk8.test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrToStrTest {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};

        String str1 = Arrays.stream(arr).boxed().map(i -> i.toString()) //必须将普通数组 boxed才能 在 map 里面 toString
                .collect(Collectors.joining(""));
        System.out.println(str1);

        String str2 = Arrays.stream(arr).boxed().map(i -> i.toString()).reduce("，", String::concat);
        System.out.println(str2);

        String str3 = Arrays.stream(arr).boxed().map(Object :: toString).reduce("-", String::concat); // 方法引用Object：：toString
        System.out.println(str3);

    }
}
