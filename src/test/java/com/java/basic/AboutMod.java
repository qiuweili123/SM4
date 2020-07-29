/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: AboutMod.java
 * @Package com.java.basic
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年3月2日 下午1:16:10
 * @version
 */
package com.java.basic;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author liqiuwei
 * @create time:2016年3月2日下午1:16:10
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class AboutMod {
    public static void main(String[] args) {
        try {
            //System.out.println(10%10+"##"+10/0);
            System.out.println(2 % 2 + "##" + 2 / 2);
            int num = 3;
            int idx = 1;
            List<Integer> list = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i % num) + "##" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e.pr);
        }

    }

}
