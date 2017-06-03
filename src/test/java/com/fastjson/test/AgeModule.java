package com.fastjson.test;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(typeName = "AgeModule")
public class AgeModule implements Module {
    public String ageModuleName;


    // @JSONField(serialize=false)
    // public String dogSex;
}