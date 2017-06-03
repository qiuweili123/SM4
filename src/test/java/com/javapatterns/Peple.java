/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: Peple.java
 * @Package com.javapatterns
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年11月20日 下午10:18:47
 * @version
 */
package com.javapatterns;

/**
 * @author lenovo
 * @create time:2016年11月20日下午10:18:47
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class Peple extends Single<Peple> {
    private String name;

    private Peple() {
        name = "2222";
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
