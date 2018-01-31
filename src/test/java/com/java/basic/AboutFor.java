/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: AboutFor.java
 * @Package com.java.basic
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2015年11月23日 下午3:46:52
 * @version
 */
package com.java.basic;

/**
 * @author liqiuwei
 * @create time:2015年11月23日下午3:46:52
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class AboutFor {
    private static String[] strs;

    public static void main(String[] args) {
        int i = 0;
        for (; i < 10; i++) {
            System.out.println("##" + i);
        }
        int flag = 2;
        System.out.println("##" + (flag |= 1));

       /* for (String str : strs) {
            System.out.println("str==" + str);
        }
        */
          flag = 2;
        switch (flag){
            case 2:
                System.out.println("2222===");
                System.out.println("2222++++");
                break;
            case 3:
                System.out.println("3===");
                System.out.println("3----");
        }

    }

}
