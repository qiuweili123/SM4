package com.commons.test;

import org.apache.commons.chain.impl.ContextBase;

/**
 * Created by lenovo on 2018/1/2.
 */
public class SellVehicleContext extends ContextBase {

    private Integer age;

    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
