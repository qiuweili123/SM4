package com.java.generic;

import com.sh.hibernate.dao.support.GenericsUtils;
import org.apache.commons.lang.StringUtils;

public class Generic<T, T2, T3> {

    private T entity;
    private T2 entity2;
    private T3 entity3;

    public Generic() {

    }

    public Generic(T obj, T2 obj2, T3 obj3) {
        this.entity = obj;
        this.entity2 = obj2;
        this.entity3 = obj3;
        this.entity = (T) GenericsUtils.getSuperClassGenricType(getClass());
    }

    public T2 getEntity2() {
        return entity2;
    }

    public void setEntity2(T2 entity2) {
        this.entity2 = entity2;
    }

    public T3 getEntity3() {
        return entity3;
    }

    public void setEntity3(T3 entity3) {
        this.entity3 = entity3;
    }

    public T getEntity() {
        return entity;
    }

    /**
     * 泛型方法
     *
     * @param @param en    设定文件
     * @return void    返回类型
     * @throws
     * @Title: showInfo
     * @Description: TODO
     */


    public <E> void showInfo(E... en) {
        for (E e : en) {

            System.out.println("#dd#" + StringUtils.uncapitalize(e.getClass().getSimpleName()) + "warrper==" + (e.getClass().getClassLoader() == null));
        }

    }

    public <G, E> void showInfo2(String str, Class<G> classd, E e) {
        System.out.println("ddddd" + classd.getClass());
    }

    public void showTypeName() {
        System.out.println("##calssname==" + entity.getClass());
        System.out.println("##calssname==" + entity2.getClass());
    }

    public void showTypeNameStr(String str) {
        System.out.println("##calssname==" + entity.getClass());
        System.out.println("##calssname==" + entity2.getClass());
    }

    public <T> T getT(T instance) {

        return instance;
    }
}
