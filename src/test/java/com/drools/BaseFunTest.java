package com.drools;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kie.api.runtime.KieSession;

import java.util.*;

public class BaseFunTest extends BaseTest {

    @Test
    public void testHello() {
        KieSession session = getKieSession("HelloWord");
        Map<String, Object> map = new HashMap<>();
        execute(session, map);
    }

    @Test
    public void testContains(){

        KieSession session = getKieSession("String_Contains");
        Map<String, Object> map = new HashMap<>();
        map.put("code","101");
        execute(session, map);
        System.out.println("##map=="+map);

    }
@Test
    public void testStringFrom(){

        KieSession session = getKieSession("String_From");
        Map<String, Object> map = new HashMap<>();

        map.put("code","10");
        execute(session, map);
        System.out.println("##map=="+map);

    }
    @Test
    public  void testListForm(){
        List<Map> list=new ArrayList<>();
        Map<String,Object> m1=new HashMap<>(),m2=new HashMap<>(),m3=new HashMap<>();
        m1.put("name","zhangsan");
        m1.put("age","23");

        m2.put("name","lisi");
        m2.put("age","list");
        m3.put("name","zhangsan");
        m3.put("age","86");

        list.add(m1);
        list.add(m2);
        list.add(m3);
        KieSession session = getKieSession("From");
        Map<String, Object> map = new HashMap<>();
        execute(session,list);

    }
    @Test
    public void testStringIN(){

        KieSession session = getKieSession("String_IN");
        Map<String, Object> map = new HashMap<>();

        map.put("code","10");
        execute(session, map);
        System.out.println("##map=="+map);

    }

    @Test
    public void testStr(){
    Map<String,String> map=new HashMap<>();
        map.put("code","1001");
        map.put("name","lisijing");
        KieSession session = getKieSession("Str");
        session.setGlobal("codes","101,102,103");
        execute(session, map);

    }
}
