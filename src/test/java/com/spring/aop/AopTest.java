package com.spring.aop;

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
@ComponentScan(basePackages = {"com.spring.aop"})
@EnableAspectJAutoProxy
@ContextConfiguration(classes = {AopTest.class})
public class AopTest {
    @Resource
    private User user;

    @Test
    public void testRes() {
        System.out.println("##==" + user);
        user.setMoney(10L);

    }

}
