package com.java.jdk8.test.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lenovo on 2018/1/24.
 */
public class List2map {
    public static void main(String[] args) {
        User u1 = new User(1l, "zhangsan");

        User u2 = new User(2l, "lisi");

        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);

        Map<Long, String> stringMap = list.stream().collect(Collectors.toMap(User::getId, User::getName));
    }

}
