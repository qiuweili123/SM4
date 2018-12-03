package com.spring.scan.test.spring;

import com.spring.scan.test.ScanClass1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2017/7/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from AppConfig and TestConfig
@Configuration
@ComponentScan(basePackages = {"com.spring.scan.test"})
@EnableAspectJAutoProxy
@ContextConfiguration(classes = {ScanTest.class})
public class ScanTest {
    @Resource
    private User user;
    @Resource
    private ScanClass1 class1;
    @Test
    public void testRes() {
        System.out.println("##==" + user);
        user.setMoney(10L);
        System.out.println("##"+class1);
    }

}
