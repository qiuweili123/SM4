package com.java.basic;

/**
 * 关于java中参数传递只存在值传递的证明
 *
 * @author liqiuwei
 * @version $Id: AboutQuote.java, v 0.1 2017年4月8日 下午7:53:05 liqiuwei Exp $
 */
public class AboutQuote {
    public static void main(String args[]) {
        Demo d1 = new Demo(1);
        Demo d2 = new Demo(2);
        System.out.println(d1.a);
        System.out.println(d2.a);
        function(d1, d2);
        System.out.println(d1.a);
        System.out.println(d2.a);
    }

    /**
     * 调用function()前后程序输出的都是1、2，此程序试图通过调用function()交换d1和d2但是没有成功，为什么呢？
     * 因为d1和d2是值传递，function()中的d1和d2是main()函数中d1和d2的副本，调用完function()不会对main()
     * 中的变量产生影响。
     *
     * @param d1
     * @param d2
     */
    private static void function(Demo d1, Demo d2) {
        Demo temp;
        temp = d1;
        d1 = d2;
        d2 = temp;
    }

    static class Demo {
        int a;

        public Demo(int a) {
            this.a = a;
        }
    }
}
