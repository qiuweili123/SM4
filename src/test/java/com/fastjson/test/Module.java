package com.fastjson.test;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(seeAlso = {SexModule.class, AgeModule.class})
public interface Module {


    // @JSONField(serialize=false)
    // public String dogSex;
}