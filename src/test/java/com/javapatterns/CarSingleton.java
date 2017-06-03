/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: CarSingleton.java
 * @Package com.javapatterns
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年11月18日 下午9:56:07
 * @version
 */
package com.javapatterns;

/**
 * @author liqiuwei
 * @create time:2016年11月18日下午9:56:07
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class CarSingleton {
    public static final AbstractSingleton<CarSingleton> INSTACE = new AbstractSingleton<CarSingleton>() {
        @Override
        protected CarSingleton newObj() {
            return new CarSingleton();
        }

    };

    private CarSingleton() {

    }
}
