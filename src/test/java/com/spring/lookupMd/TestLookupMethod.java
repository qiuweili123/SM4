package com.spring.lookupMd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 17-6-5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/application-lookupMethod.xml")
public class TestLookupMethod {
    @Resource
    private HpServiceImpl hpService;

    @Test
    public void testLookUp() {
        for (int i = 0; i <3 ; i++) {
            System.out.println(hpService+"###" + hpService.getHp());
        }


    }
}
