package com.spring.spel;

import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

/**
 * @author Comsys-Administrator
 * @ClassName: SpELValue
 * @Description: Spring3.0引入了Spring表达式语言（SpEL）。其中有两个可用的变量 “systemProperties” 和 “systemEnvironment”。
 * SpEL使我们能够在运行时访问自定义Bean或系统Bean的信息（后期绑定）。
 * 可以通过JSR-303@value注解字段或利用XML的<bean ... value=”” />选项作用于Bean字段（或属性）。
 * @date 2015年4月19日 上午10:06:32
 */
public class SpELValue {

    @Value("#{systemProperties['user.language']}")
    public static String language;
    /**
     * @Fields systempProperties : systemProperties为系统级环境变量
     */
    @Value("#{systemProperties}")
    private Properties systempProperties;

}
