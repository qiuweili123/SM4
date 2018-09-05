package com.js;

import com.alibaba.fastjson.JSON;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSTest {

    public static void main(String[] args) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            List<Map> list = new ArrayList<>();
            Map map = new HashMap();
            map.put("id", 1);
            map.put("name", "张三");
            list.add(map);
            Map map1 = new HashMap();
            map1.put("id", 2);
            map1.put("name", "lisi");
            list.add(map1);
            engine.eval("function excute(obj){ print(typeof(obj)); " +
                    "var list=[]; var objN=obj;" +
                    "for(var i in objN){" +
                    "var objn={};objn.idn=objN[i].id;objn.namen=objN[i].name;" +
                    "print(objN[i].id+'==ss='+objN[i].name);" +
                    "list.push(JSON.stringify(objn));}" +
                    "print(objn);return list;}");
            engine.eval("function add(a,b){   return a+b}");

            //取得调用接口
            Invocable jsInvoke = (Invocable) engine;
            Jsbean jsbean = jsInvoke.getInterface(Jsbean.class);
            //   Map result1 = jsInvoke.invokeFunction("convert", new Object[] { list },Map.class);
            System.out.println(JSON.toJSONString(list));
            System.out.println("###########ret==" + jsbean.excute(list));
            System.out.println("###########ret==" + jsbean.add(1, 3));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
