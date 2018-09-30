package com.java.jdk8.test;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class ListToaArrTest {
//方法引用分为三种，方法引用通过一对双冒号:: 来表示，方法引用是一种函数式接口的另一种书写方式
//
//
//静态方法引用，通过类名::静态方法名， 如 Integer::parseInt
//实例方法引用，通过实例对象::实例方法，如 str::substring
//构造方法引用，通过类名::new， 如 User::new
//

    public static void main(String[] args) {
        List<String> list=Lists.newArrayList("1","d","f");
        List<String> list2=Lists.newArrayList();
        list.forEach(list2::add);
        System.out.println("list2::"+list2);
    }
}
