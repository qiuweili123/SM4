/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: AboutRandom.java
 * @Package com.java.basic
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年7月15日 上午11:32:43
 * @version
 */
package com.java.basic;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

/**
 * @author lenovo
 * @create time:2016年7月15日上午11:32:43
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class AboutRandom {
    public static void main(String[] args) {
        System.out.println(getRandNum(1, 999999));
        //  System.out.println( RandomStringUtils.random(6));

        System.out.println(RandomUtils.nextInt(100000, 999999));
        Random random = new Random();
        System.out.println(random.nextInt() + "##ramdom==" + (random.nextInt(10)));
    }

    /**
     * 短信验证码
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandNum(int min, int max) {
        int randNum = min + (int) (Math.random() * ((max - min) + 1));
        return randNum;
    }

}
