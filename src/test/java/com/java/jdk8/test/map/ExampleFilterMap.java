package com.java.jdk8.test.map;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.stream.Collectors;

public class ExampleFilterMap {
    private static Map<Integer, String> map_ = Maps.newHashMap();

    static {
        map_.put(1, "linode.com");
        map_.put(2, "heroku.com");
        map_.put(3, "digitalocean.com");
        map_.put(4, "aws.amazon.com");
    }

    public static void main(String[] args) {
        //before java iterator map
        String result = null;
        for (Map.Entry<Integer, String> entry : map_.entrySet()) {
            if ("heroku.com".equals(entry.getValue())) {
                result = entry.getValue();
            }
        }

        map_.forEach((key, value) -> System.out.println("key:" + key + "," + "value:" + value));

        System.out.println("Before Java 8 :" + result);
        //Java8 Map->Stream->Filter->String

        result = map_.entrySet().stream().
                filter(map -> "heroku.com".equals(map.getValue()))
                .map(map -> map.getValue())
                .collect(Collectors.joining());
        System.out.println("Java 8 :" + result);
        Map<Integer, String> collect = map_.entrySet().stream()
                .filter(c -> c.getKey() == 2)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        System.out.println(collect);
    }
}