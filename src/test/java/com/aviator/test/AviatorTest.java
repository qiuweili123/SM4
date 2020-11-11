package com.aviator.test;

import com.google.common.collect.ImmutableMap;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
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

}
