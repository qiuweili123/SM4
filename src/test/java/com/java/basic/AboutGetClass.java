/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: AboutGetClass.java
 * @Package com.java.basic
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: ard-liqiuwei
 * @date: 2017年2月8日 上午10:04:00
 * @version
 */
package com.java.basic;

/**
 * @author ard-liqiuwei
 * @create time:2017年2月8日上午10:04:00
 * @Description: 两者最直接的区别就是，getClass（）是一个类的实例所具备的方法，而class（）方法是一个类的方法。
 * 另外getClass（）是在运行时才确定的，而class（）方法是在编译时就确定了
 * 当出现继承和多态时，getclass和class方法的确实有所不同，因为getclass是在运行时确定的，
 */
public class AboutGetClass {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        A ab = new B();
        System.out.println(a.getClass() + " " + A.class);
        System.out.println(b.getClass() + " " + B.class);
        System.out.println(ab.getClass());
        ab = a;
        System.out.println(ab.getClass());

    }
}

class A {
    public void func() {

    }
}

class B extends A {

}
