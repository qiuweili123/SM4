package com.js;

import java.util.List;
import java.util.Map;

//接口中的方法签名必须与要执行的JavaScript方法一致
public interface Jsbean {
    // List<Map> excute(Object object);
    List<Map> excute(List list);

    int add(int a, int b);
}
