/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: FastjsonTest.java
 * @Package com.fastjson.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2017年5月15日 上午10:27:17
 * @version
 */
package com.fastjson.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.logging.log4j.util.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liqiuwei
 * @create time:2017年5月15日上午10:27:17
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class FastjsonTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", "lisi");
        Cat cat = new Cat();
        cat.catName = "帽";
        Dog dog = new Dog();
        dog.dogName = "帽";
        Map<String, Object> objMap = Maps.newHashMap();
        objMap.put("dog", dog);
        objMap.put("cat", cat);
        String[] keys = new String[]{"dog", "cat"};
        Object[] objs = new Object[]{dog, cat};

        StringBuilder newStr = new StringBuilder();

        for (int i = 0; i < keys.length; i++) {
            newStr.append(",").append(Strings.dquote(keys[i])).append(":").append(JSON.toJSONString(objs[i]));
        }
        newStr.deleteCharAt(0);

        String test = JSON.toJSONString(map);
        StringBuilder stringBuilder = new StringBuilder(test);


        stringBuilder.insert(1, newStr + ",");
        System.out.println("test::" + stringBuilder);

        System.out.println(JSON.toJSONString(1L));
    }

}
