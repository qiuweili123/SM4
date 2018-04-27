package com.drools;

import org.junit.Test;
import org.kie.api.runtime.KieSession;

import java.util.HashMap;
import java.util.Map;

public class BaseFunTest extends BaseTest {

    @Test
    public void testHello() {
        KieSession session = getKieSession("HelloWord");
        Map<String, Object> map = new HashMap<>();
        execute(session, map);
    }

}
