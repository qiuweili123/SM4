package com.fastjson.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FastJsonObjectTest {
    public static void main(String[] args) {
        String data = "{\"uid\":\"1581108693131087\",\"condition\":\"121 > 1\",\"context\":\"[orderTestCommonErrorCount:121]\",\"project\":\"shop-hk\",\"trigger\":\"order_exsist_error_rule\",\"message\":\"10分钟内出现报警1次\"}";

        Map map = new LinkedHashMap();

        HashMap map1 = JSONObject.parseObject(data, HashMap.class);
        String platCode = "ali";
        String action = "warn";

        map.put("@type", platCode);
        map.put("action", action);
        map.putAll(map1);
        System.out.println(JSONObject.toJSONString(map));

        Map<String, Map<String, Integer>> mapMap = new HashMap<>();
        Map<String, Integer> subMap1 = new HashMap<>();
        subMap1.put("start", 1);
        subMap1.put("end", 1);
        mapMap.put("users", subMap1);
        System.out.println("eee" + JSON.toJSONString(mapMap));



    }
}
