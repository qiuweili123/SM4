package com.java.basic;

//final修饰基本数据类型变量时，只能赋值一次,再赋重值也不行，变量的值是不可改变的,修饰引用数据类型对象时对象的地址是
//不可变的，但对象中的成员属性可变。
public class AboutFinal {
    //定义属性,默认、显示初始化都不赋值，而在构造方法中赋值。
    final int var;
    //{this.age = 5;}加上该句将报错

    //无参构造
    public AboutFinal() {
        var = 0;
        System.out.println("无参构造 var = " + var);
    }

    //有参构造
    public AboutFinal(int var) {
        this.var = var;
        System.out.println("有参构造 var = " + var);
    }

}	