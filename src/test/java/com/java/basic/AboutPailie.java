package com.java.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/2/28.
 */
public class AboutPailie {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        for (int i = 0; i < list.size(); i++) {
            String str1 = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                System.out.println(str1 + "-" + list.get(j));
            }
        }
    }
}
