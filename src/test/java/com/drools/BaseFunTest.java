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
    public void testFrom(){

        KieSession session = getKieSession("String_From");
        Map<String, Object> map = new HashMap<>();

        map.put("code","101");
        execute(session, map);
        System.out.println("##map=="+map);

    }
    @Test
    public void testIN(){

        KieSession session = getKieSession("String_IN");
        Map<String, Object> map = new HashMap<>();

        map.put("code","10");
        execute(session, map);
        System.out.println("##map=="+map);

    }
}
