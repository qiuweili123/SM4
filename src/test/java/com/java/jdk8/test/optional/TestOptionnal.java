package com.java.jdk8.test.optional;

import com.commons.test.User;

import java.util.Optional;

/**
 * Created by lenovo on 2017/6/27.
 * 它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测
 */
public class TestOptionnal {
    public static void main(String[] args) {
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("Full Name is set? " + fullName.isPresent());
        System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
        System.out.println(fullName.orElse(null));
           User user=null;
        User u2 = Optional.ofNullable(user).orElseGet(() -> {
           User u = new User();
            u.setAge(30);
            return u;
        });
        System.out.println("##"+u2.getAge());
        //当u2不为空时执行特定操作
         Optional.ofNullable(u2).ifPresent(u->{u.setName("zhangsan");});
        System.out.println(u2.getName());
    }
}
