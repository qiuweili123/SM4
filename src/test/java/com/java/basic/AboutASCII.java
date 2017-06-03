/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: AboutASCII.java
 * @Package com.java.basic
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年3月26日 上午11:52:12
 * @version
 */
package com.java.basic;

/**
 * @author liqiuwei
 * @create time:2016年3月26日上午11:52:12
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class AboutASCII {
    public static void main(String[] args) {
        String str = "abcd";
        int sum = 0;
        for (char c : str.toCharArray()) {

            System.out.println(c + "@##" + (int) c);
        }
    }
}
