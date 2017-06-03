/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: PepleSingeton.java
 * @Package com.javapatterns
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年11月18日 下午10:00:54
 * @version
 */
package com.javapatterns;

/**
 * @author liqiuwei
 * @create time:2016年11月18日下午10:00:54
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class PepleSingeton {
    public static final AbstractSingleton<PepleSingeton> INSTACE = new AbstractSingleton<PepleSingeton>() {
        @Override
        protected PepleSingeton newObj() {
            // TODO Auto-generated method stub
            return new PepleSingeton();
        }
    };

    private PepleSingeton() {
    }

}
