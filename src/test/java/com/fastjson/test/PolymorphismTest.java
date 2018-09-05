/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: Polymorphism.java
 * @Package com.fastjson.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年6月13日 上午11:16:51
 * @version
 */
package com.fastjson.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liqiuwei
 * @create time:2016年6月13日上午11:16:51
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class PolymorphismTest {

    /**
     * @param args
     * @Description:fastjson的多态测试
     * @return: void
     * @author:liqiuwei 2016年6月13日上午11:16:51
     * @update1:updater:liqiuwei updatetime:2016年6月13日上午11:16:51 TODO(描述修改内容)
     */
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        Dog dog = new Dog();
        dog.dogName = "dog1001";
        dog.CNName = "cdnName";
        AgeModule ageModule = new AgeModule();
        ageModule.ageModuleName = "doge_age_module";

        SexModule sexModule = new SexModule();
        sexModule.sexModuleName = "sex_module_name";
        List<Module> modules = new ArrayList<>();
        modules.add(sexModule);
        modules.add(ageModule);

        dog.modules = modules;

        //dog.dogSex="sex";
        Cat cat = new Cat();
        cat.catName = "cat2001";
        animals.add(dog);
        animals.add(cat);

        String text = JSON.toJSONString(animals, SerializerFeature.WriteClassName);
        System.out.println("text==" + text);
        String tString = "[{\"@type\":\"dog\",\"CNName\":\"cdnName\",\"cNName\":\"cdnName\",\"dogName\":\"dog1001\",\"modules\":[{\"@type\":\"com.fastjson.test.SexModule\",\"sexModuleName\":\"sex_module_name\"},{\"@type\":\"AgeModule\",\"ageModuleName\":\"doge_age_module\"}]},{\"@type\":\"cat\",\"catName\":\"cat2001\"}]";
        // SexModul使用@type注解
        //   String tString = "[{\"@type\":\"dog\",\"dogName\":\"dog1001\",\"modules\":[{\"@type\":\"SexModule\",\"sexModuleName\":\"sex_module_name\"},{\"@type\":\"AgeModule\",\"ageModuleName\":\"doge_age_module\"}]},{\"@type\":\"cat\",\"catName\":\"cat2001\"}]";
        List<Animal> list = JSON.parseArray(tString, Animal.class);
        System.out.println("##list==" + list);

        for (Animal animal : list) {
            System.out.println("namdddddde=="+animal.getClass().getSimpleName());
            if (animal instanceof Dog) {
                Dog dog1 = (Dog) animal;
                System.out.println("dogName####" + dog1.dogName + "###");

                for (Module module : dog1.modules) {
                    if (module instanceof SexModule) {
                        SexModule module1 = (SexModule) module;
                        System.out.println("SexModule####" + module1.sexModuleName);
                    }

                }
            }
            if (animal instanceof Cat) {
                Cat cat1 = (Cat) animal;
                System.out.println("catName####" + cat1.catName);
            }
        }

    }

}
