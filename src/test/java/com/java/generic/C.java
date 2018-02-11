/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: C.java
 * @Package com.java.generic
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年11月21日 下午11:18:40
 * @version
 */
package com.java.generic;

import java.util.Map;

/**
 * @author lenovo
 * @create time:2016年11月21日下午11:18:40
 * @Description:TODO(这里用一句话描述这个类的作用) 这种设计基于泛型方法的方式获取类的势力的实际类型
 */
public class C<T> extends D {
    private T entity;

  /*  public C(T entity) {
        this.entity = entity;
    }*/

    public T getEntity() {
        return entity;
    }

    public void save(T t) {
        System.out.println("##" + t.getClass());
        if (t instanceof Map) {
            System.out.println("i am instacnce map");
        }
        super.save();
    }
}
