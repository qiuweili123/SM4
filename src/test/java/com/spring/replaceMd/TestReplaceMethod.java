package com.spring.replaceMd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 17-6-5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/application-replaceMethod.xml")
public class TestReplaceMethod {
    @Resource
    private MvcService mvcService;

    @Test
    public void testGetTime() {
        System.out.println("end::" + mvcService.getTime());
    }
}

