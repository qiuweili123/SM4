package com.javapatterns;

public class StringImmutable {

    /**
     * 常量池(constant pool)指的是在编译期被确定，并被保存在已编译的.class文件中的一些数据。
     * 它包括了关于类、方法、接口等中的常量，也包括字符串常量。
     * new的方式都是在执行期创建变量的引用，所以只能执行期确定对象引用
     *
     * @param args
     * @author inth-liqiuwei
     * @date 2015年8月15日 下午3:59:12
     */
    public static void main(String[] args) {

        String a = "ab";
        String bb = "b";
        String b = "a" + bb;//="a"+new String("b");
        System.out.println(a == b);

        String str = new String("abc");//不会将abc放到常量池中
        String str2 = "abc";
        String str3 = "ab" + "c";//String 是不变的，所以会形成多个变量对象，分别为ab、c、abc
        //str=str.intern();//new的方式将创建的对象放到strings pool常量池中，常量池每个基本类型都有
        System.out.println("##" + (str == str2));//在heap中判断对对象的引用是否相同
        System.out.println("##" + (str2 == str3));
        String str4 = new String("abc");
        System.out.println("##1=" + (str2 == str2.intern()));
        System.out.println("##1=" + (str3.intern() == str2.intern()));
    }

    /**
     * 重要***
     * 虽然两个语句都是返回一个String对象的引用，但是jvm对两者的处理方式是不一样的。对于第一种，jvm会马上在heap中创建一个String对象，
     * 然后将该对象的引用返回给用户。对于第二种，jvm首先会在内部维护的strings pool中通过String的 equals 方法查找是对象池中是否存放有该String对象，
     * 如果有，则返回已有的String对象给用户，而不会在heap中重新创建一个新的String对象；如果对象池中没有该String对象，jvm则在heap中创建新的String对象，
     * 将其引用返回给用户，同时将该引用添加至strings pool中。
     * 注意：使用第一种方法创建对象时，jvm是不会主动把该对象放到strings pool里面的，除非程序调用 String的intern方法
     * String str="a”;会自动调用intern（）方法
     * <p>
     * 常量池中存放的是池中对象引用地址和字符
     * new 的对象都在堆中；常量的方式都在常量的对象池中
     *
     * @author inth-liqiuwei
     * @date 2015年8月15日 下午5:08:48
     */
    public void Demo1() {
        String str1 = new String("abc");
        String str2 = "abc";
        //通过上面的描述这里在heap中会产生两个对象
    }

    /**
     * s0还是常量池中”kvill”的引用，s1因为无法在编译期确定，所以是运行时创
     * 建的新对象”kvill”的引用，s2因为有后半部分new String(“ill”)所以也无法在编译
     * 期确定，所以也是一个新创建对象”kvill”的引用;
     *
     * @author inth-liqiuwei
     * @date 2015年8月15日 下午4:16:51
     */
    public void Demo2() {

        String s0 = "kvill";
        String s1 = new String("kvill");
        String s2 = "kv" + new String("ill");
        System.out.println(s0 == s1);
        System.out.println(s0 == s2);
        System.out.println(s1 == s2);
    }

    /**
     * 在于.class文件中的常量池，在运行期被JVM装载，并且可以扩充。String的intern()方法就是扩充常量池的一个方法；
     * 当一个String实例str调用intern()方法时，Java查找常量池中是否有相同Unicode的字符串常量，
     * 如果有，则返回其的引用，如果没有，则在常量池中增加一个Unicode等于str的字符串并返回它的引用；
     *
     * @author inth-liqiuwei
     * @date 2015年8月15日 下午4:20:29
     */
    public void Demo3() {
        String s0 = "kvill";
        String s1 = new String("kvill");
        String s2 = new String("kvill");
        System.out.println(s0 == s1);
        System.out.println("**");
        s1.intern(); //说明s1.intern()返回的是常量池中”kvill”的引用
        s2 = s2.intern(); //把常量池中"kvill"的引用赋给s2
        System.out.println(s0 == s1);
        System.out.println(s0 == s1.intern());
        System.out.println(s0 == s2);
    }

    /**
     * 一定要区分
     * String a=new String("s");为堆对象的引用
     * String a="s";为常量对象池的应用
     * String a=a.intren();返回的是对常量池的引用
     *
     * @author inth-liqiuwei
     * @date 2015年8月15日 下午4:46:28
     */
    public void Demo4() {
        String s1 = new String("kvill");
        String s2 = s1.intern();
        System.out.println(s1 == s1.intern());  //对常量对象池的引用和对对象的引用是不相等的
        System.out.println(s1 + " " + s2);
        System.out.println(s2 == s1.intern());
    }
}
