package com.java.jdk8.test.map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


        Map<String, Object> o1 = Maps.newHashMap();
        o1.put("01", "01");

        List<Map> list1 = Lists.newArrayList();
        list1.stream().forEach(m -> {
            System.out.println(m);
        });
        list1.add(o1);
        list1.stream().forEach(map -> {
            Map map1 = new HashMap();
            map1.put("03", "03");
            map.putAll(map1);
        });

       /* list1.stream().map(map -> {
            Map map1=new HashMap();
            map1.put("03","03");
            map.putAll(map1);
            return map;
        }).*/
        System.out.println(list1);

/*        for(int i=0;i<10;i++){
            if (i==2){
                return;
            }
            System.out.println(i);
        }*/

        Integer[] ints = new Integer[]{1, 2, 3, 5};
        Arrays.stream(ints).forEach(i -> {
            if (i == 3) {
                return;
            }
            System.out.println("sdsd" + i);
        });
    }

}
