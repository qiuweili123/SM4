package com.fastjson.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;

public class BeanSeralizerTest {

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.dogName = "dog1001";
        dog.CNName = "cdnName";
        List<Module> modules = new ArrayList<>();
        AgeModule ageModule = new AgeModule();
        ageModule.ageModuleName = "doge_age_module";

        SexModule sexModule = new SexModule();
        sexModule.sexModuleName = "sex_module_name";
        modules.add(sexModule);
        modules.add(ageModule);

        dog.modules = modules;
        String text = JSON.toJSONString(dog, SerializerFeature.WriteClassName);
        System.out.println("text==" + text);
    }

}
