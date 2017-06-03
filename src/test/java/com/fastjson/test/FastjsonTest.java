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
        String test = JSON.toJSONString(map);
        System.out.println("test::" + test);
    }

}
