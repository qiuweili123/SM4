/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: Apple.java
 * @Package com.java.annotation
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年7月26日 上午11:40:07
 * @version
 */
package com.java.annotation;

import static com.java.annotation.FruitColor.Color;

/**
 * @author lenovo
 * @create time:2016年7月26日上午11:40:07
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class Apple {
    @FruitName("Apple")
    private String appleName;

    @FruitColor(Color.RED)
    private String color;
}
