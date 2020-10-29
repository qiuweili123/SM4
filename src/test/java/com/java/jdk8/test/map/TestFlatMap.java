package com.java.jdk8.test.map;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestFlatMap {
    public static void main(String[] args) {
        List<Integer> list = (List<Integer>) Stream.of(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(8, 9, 10, 11, 12))
                //flatMap将多个小数据流拉平，将小数据流合并为大的数据流
                .flatMap(test -> test.stream()).collect(Collectors.toList());

        for (int i = 0, length = list.size(); i < length; i++) {
            System.out.println(list.get(i));
        }


    }
}

