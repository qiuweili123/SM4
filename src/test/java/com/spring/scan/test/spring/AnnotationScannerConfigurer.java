package com.spring.scan.test.spring;

import com.spring.aop.Money;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by lenovo on 2017/7/8.
 */

/**
 * 扫描注解添加服务到缓存以供判断时候为对外开放service
 */
@Component
@Lazy(true)
public class AnnotationScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        boolean user = beanDefinitionRegistry.containsBeanDefinition("user");
        BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition("user");

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory postProcessBeanFactory) throws BeansException {

        Map<String, Object> map = postProcessBeanFactory.getBeansWithAnnotation(NSEntity.class);

        for (String key : map.keySet()) {
            //cache.add(key);
            System.out.println("beanName= " + key + "##" + map.get(key));

            Class<?> clazz = map.get(key).getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Money money = field.getAnnotation(Money.class);
                if (money != null) {
                    StringBuilder sb = new StringBuilder(field.getName());
                    sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                    String mName = "set" + sb.toString();
                    try {
                        Method declaredMethod = clazz.getDeclaredMethod(mName, new Class[]{Long.class});
                        declaredMethod.invoke(map.get(key), new Long[]{1L});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(mName + " fild Name ##" + field.getName());
                }
            }

        }

    }
}
