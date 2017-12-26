package com.java.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 关于java中参数传递只存在值传递的证明
 *
 * @author liqiuwei
 * @version $Id: AboutQuote.java, v 0.1 2017年4月8日 下午7:53:05 liqiuwei Exp $
 */
public class AboutQuote {
    public static void main(String args[]) {
        Demo d1 = new Demo(1);
        Demo d2 = new Demo(2);
        System.out.println(d1.a);
        System.out.println(d2.a);
        function(d1, d2);
        System.out.println(d1.a);
        System.out.println(d2.a);

        //Map的put不是put一个对象，而是put一个对象的引用！该对象更新后，Map里面被put的引用随着改变。
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> params = new HashMap<String, String>();
        map.put("params", params);
        params.put("key", "123");
        System.out.println(map);

        Demo d3 = new Demo(1);
        System.out.println("brfore##" + d3.getA());
        // setDemo(d3);
        System.out.println(d3.getA());

       /* String key = "str_123";
        Long kLong = Long.valueOf(key);
        System.out.println("##kLong=" + kLong);*/
        DemoInter inter = (demo) -> getDemo(demo);
        setDemo(d3, inter);
        d3 = inter.getNewDemo(d3);

        System.out.println(d3.getA());

    }

    public static Demo getDemo(Demo demo) {
        demo = new Demo(123);
        demo.setA(456);
        return demo;
    }

    public static void setDemo(Demo demo, DemoInter inter) {
        inter.getNewDemo(demo);

    }

    /**
     * 调用function()前后程序输出的都是1、2，此程序试图通过调用function()交换d1和d2但是没有成功，为什么呢？
     * 因为d1和d2是值传递，function()中的d1和d2是main()函数中d1和d2的副本，调用完function()不会对main()
     * 中的变量产生影响。
     *
     * @param d1
     * @param d2
     */
    private static void function(Demo d1, Demo d2) {
        Demo temp;
        temp = d1;
        d1 = d2;
        d2 = temp;
    }

    static class Demo {
        private Integer a;

        public Demo(int a) {
            this.a = a;
        }

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }
    }

    /**
     * 通过引用循环创建树
     */
    public static void testMap() {
        Set<String> set = new TreeSet<>();
        set.add("自由住熊");
        //  set.add("自由网");

        Map sensitiveWordMap = new HashMap<>(set.size());
        for (String keyWord : set) {
            Map nowMap = sensitiveWordMap;
            System.out.println(keyWord + "kk" + nowMap);
            for (Character c : keyWord.toCharArray()) {
                Object obj = nowMap.get(c);
                if (obj == null) {
                    Map<String, Object> childMap = new HashMap<>();
                    System.out.println("11##" + nowMap);
                    childMap.put("isEnd", "false");
                    nowMap.put(c, childMap);
                    nowMap = childMap;
                    System.out.println(c + "c##" + sensitiveWordMap + "##" + nowMap);

                } else {
                    System.out.println("##" + c);
                    nowMap = (Map) obj;
                }
            }
            nowMap.put("isEnd", "true");
        }
        System.out.println("end##" + sensitiveWordMap);
    }

    interface DemoInter {
        Demo getNewDemo(Demo demo);
    }

}
