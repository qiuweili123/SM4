/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TestArray.java
 * @Package com.java.basic
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年11月18日 上午10:48:37
 * @version
 */
package com.java.basic;

import java.util.Arrays;

/**
 * @author liqiuwei
 * @create time:2016年11月18日上午10:48:37
 * @Description:打印数组
 */
public class TestArray {

    public static void main(String[] args) {
        String[] strs = new String[]{"test04", "test10", "test03"};
        String str = Arrays.toString(strs);
        System.out.println(str + "==" + str.indexOf("test03"));

    }
}
