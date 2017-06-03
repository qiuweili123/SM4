package com.fastjson.test;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(typeName = "cat")
public class Cat extends Animal {
    public String catName;
}