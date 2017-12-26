package com.java.jdk8.test.lamda;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2017/6/27
 * 将Lambda可以看做是一种特殊的匿名内部类，此内部类为接口的实现
 * Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中），或者把代码看成数据
 * 一个lambda可以由用逗号分隔的参数列表、–>符号与函数体三部分表示.
 *
 * Lambda表达式是匿名内部类的一种简化，因此它可以取代匿名内部类的作用。

 【Lambda表达式与匿名内部类的 相同点】

 1.Lambda表达式和匿名内部类一样，都可以直接访问"effectively final"的局部变量，以及外部类的成员变量（包括实力变量和类变量）

 2.Lambda表达式创建的对象和匿名内部类创建的对象一样，都可以直接调用从接口中继承的默认方法。
 */
public class TestLamda {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(a -> System.out.println(a));
        System.out.println("------------------------");
        list.forEach(a -> {
            System.out.println(a);
            System.out.println(a);
        });
        System.out.println("----------调用局部变量--------------");
        String separator = ",";
        list.forEach(a -> {
            String b = a + separator;
            if (b.equals("")) {

            }
            System.out.println(b);
        });
        System.out.println("----------函数式编程接口Functional，可以看作是匿名内部类的变种--------------");
        Functional functional = (x, y) -> {
            System.out.println("xx+" + x + "yy" + y);
            return x + y;
        };
        System.out.println("----------函数式编程接口--------------");
        functional.m1("100", "200");
        System.out.println("----------函数式编程接口--------------");
        //func((a, b) -> System.out.println("-ddd--" + (a + b)), 1, 2);
        functinal((a, b) -> {
            return a + b;
        }, "hello", "word", "kekeiekk");
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4);
        //此种写法更容易理解
        int addvalue = 100;
        Func2 f2 = (i) -> {
            return getNewValue(i, 100);
        };
        functinal2(list1, f2);
    }


    public static void func(Func func, int a, int b) {
        System.out.println("insert func----");
        func.aoo(a, b);
    }

    public static int getNewValue(int i, int addValue) {
        return i + 100;
    }

    public static void functinal(Functional functional, String a, String b, String c) {
        System.out.println(functional.m1(a, b) + "###");
        //System.out.println(functional.m2(a,b,c)+"###");
        functional.defaultMethod(a, b);
    }

    public static void functinal2(List<Integer> list, Func2 functional) {
        System.out.println(functional);
        list.forEach(integer -> {
            System.out.println(functional.print(integer));
        });
    }

}
