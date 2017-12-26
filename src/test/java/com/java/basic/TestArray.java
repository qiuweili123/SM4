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

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

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
        List<String> list = Lists.newArrayList();
        System.out.println(list.get(1));

    }
}
