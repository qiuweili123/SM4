package com.aviator.test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class AviatorTest {
    static {
        try {
            //   AviatorEvaluator.addInstanceFunctions("str", String.class);
            AviatorEvaluator.addStaticFunctions("StrUtil", StringUtils.class);
            AviatorEvaluator.addStaticFunctions("MapUtil", MapUtil.class);
            Stream.of(AvatorFuncConstant.FuncEnum.values()).forEach(funcEnum -> AviatorEvaluator.addFunction(funcEnum.getFunction()));
        } catch (Exception e) {

        }
    }

    /**
     * 测试对象嵌套
     *
     * @throws Exception
     */
    @Test
    public void testEvalArray() throws Exception {
        String exps = "evalArray(objs1,'obj.a>10 && obj.b<20')";
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("a", 21);
        map.put("b", 15);
        list.add(map);
        map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 16);
        list.add(map);

        boolean ret = (Boolean) AviatorEvaluator.execute(exps, ImmutableMap.of("objs1", list));
        System.out.println(ret);
    }

    @Test
    public void testFunctions() throws IOException {
        //AviatorEvaluator.getInstance().setOption(Options.CAPTURE_FUNCTION_ARGS,true);
        //缓存模式加载
        Expression exp = AviatorEvaluator.getInstance().compileScript("aviator/functions.av", true);
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("a", 21);
        map.put("b", 15);
        list.add(map);
        map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 16);
        list.add(map);

        Map paramMap = new HashMap();
        paramMap.put("expres", "obj.a>10 && obj.b<20");
        paramMap.put("objs", list);

        Object result = exp.execute(exp.newEnv("fun", "matchOnce", "paramMap", paramMap));

        System.out.println("result: " + result);
    }


    @Test
    public void testConvertParam() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("sysCode", "9b694a4fc4c2b144");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("activtyGroupId", 1240);
        dataMap.put("isNew", 0);

        map.put("extInfo", dataMap);
        map.put("bdInfo", ImmutableMap.of("client_ip", "114.233.121.250"));
        Map<String, Object> pramMap = new HashMap<>();


        List<Map> activityList = new ArrayList<>();
        Map<String, Object> activityMap = new HashMap<>();
        activityMap.put("activityId", 21);
        activityMap.put("activityName", "领券1");
        activityMap.put("mount", 100.2);
        activityList.add(activityMap);

        activityMap = new HashMap<>();
        activityMap.put("activityId", 22);
        activityMap.put("activityName", "领券2");
        activityMap.put("mount", 200.2);

        activityList.add(activityMap);


        pramMap.put("paramMap", map);
        pramMap.put("activityList", activityList);

        // Object p = AviatorEvaluator.execute("let list=seq.list();println(paramMap.extInfo);map(activityList,lambda(objMap) -> {println(objMap);for x in paramMap.extInfo {println(x.key+x.value);} seq.add(list,objMap);} end); println(list)", pramMap,true);

        Object p = AviatorEvaluator.execute("let list=seq.list();println(paramMap.extInfo); for activity in activityList {}", pramMap, true);

        System.out.println("ret:" + p);
    }

    @Test
    public void testConvertParamFunctions() throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("sysCode", "9b694a4fc4c2b144");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("activtyGroupId", 1240);
        dataMap.put("isNew", 0);

        map.put("extInfo", dataMap);
        map.put("bdInfo", ImmutableMap.of("client_ip", "114.233.121.250"));
        Map<String, Object> pramMap = new HashMap<>();


        List<Map> activityList = new ArrayList<>();
        Map<String, Object> activityMap = new HashMap<>();
        activityMap.put("activityId", 21);
        activityMap.put("activityName", "领券1");
        activityMap.put("mount", 100.2);
        activityList.add(activityMap);

        activityMap = new HashMap<>();
        activityMap.put("activityId", 22);
        activityMap.put("activityName", "领券2");
        activityMap.put("mount", 200.2);

        activityList.add(activityMap);


        pramMap.put("paramMap", map);
        pramMap.put("activityList", activityList);
        //缓存模式加载
        Expression exp = AviatorEvaluator.getInstance().compileScript("aviator/functions.av", true);
        Object result = exp.execute(exp.newEnv("fun", "convertParam", "paramMap", pramMap));
    }





}
