package com.fastjson.test;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(seeAlso = {Dog.class, Cat.class})
public class Animal {
}
