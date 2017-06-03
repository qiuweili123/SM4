package com.fastjson.test;

import com.alibaba.fastjson.annotation.JSONType;

import java.util.List;

@JSONType(typeName = "dog")
public class Dog extends Animal {
    public String dogName;
    public String CNName;
    public List<Module> modules;

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getCNName() {
        return CNName;
    }

    public void setCNName(String cNName) {
        CNName = cNName;
    }
    // @JSONField(serialize=false)
    // public String dogSex;
}