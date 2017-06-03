/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: FruitName.java
 * @Package com.java.annotation
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年7月26日 上午11:37:57
 * @version
 */
package com.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lenovo
 * @create time:2016年7月26日上午11:37:57
 * @Description:TODO(这里用一句话描述这个类的作用)
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitName {
    String value() default "";

    //public enum FontColor{ BULE,RED,GREEN};
}
