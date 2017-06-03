/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TestSet.java
 * @Package com.java.basic
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年10月31日 上午9:37:58
 * @version
 */
package com.java.basic;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author liqiuwei
 * @create time:2016年10月31日上午9:37:58
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class TestSet {
    public static void main(String[] args) {
        Set<String> testSet = new HashSet<>();
        testSet.add("a");
        testReference(testSet);
        System.out.println(testSet);
    }

    public static void testContans() {
        Set<String> set = new TreeSet();
        set.add("5");
        set.add("1");
        set.add("10");
        System.out.println(set);

        for (String str : set) {
            System.out.println("##str==" + str);
        }
        System.out.println("##" + set.contains("101"));
    }

    public static void testReference(Set<String> strSet) {
        strSet.add("b");
    }
}
