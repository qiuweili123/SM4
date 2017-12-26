package com.spring.scan.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Set;

/**
 * test for customer 1 BeanFactoryPostProcessor after bean factory is
 * created,scan and modify bean definition 2 BeanDefinition , bean class , if a
 * basic class, auto ,else if a factory bean ,create by factory bean 3
 * FactoryBean , create bean 4 Scan ,basic scan
 *
 * @author wcong<wc19920415@gmail.com>
 * @since 16/1/22
 */
@Configuration
public class CustomizeScanTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(CustomizeScanTest.class);
        annotationConfigApplicationContext.refresh();

        ScanClass1 injectClass = annotationConfigApplicationContext.getBean(ScanClass1.class);
        injectClass.print("moon");

	/*
     * PathTest pathTest =
	 * annotationConfigApplicationContext.getBean(PathTest.class);
	 * pathTest.say();
	 */

    }

    @Component
    public static class BeanScannerConfigurer implements BeanFactoryPostProcessor, ApplicationContextAware {

        private ApplicationContext applicationContext;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            Scanner scanner = new Scanner((BeanDefinitionRegistry) beanFactory);
            scanner.setResourceLoader(this.applicationContext);
            scanner.scan("com.spring.scan.test");
        }
    }

    public final static class Scanner extends ClassPathBeanDefinitionScanner {

        public Scanner(BeanDefinitionRegistry registry) {
            super(registry);
        }

        @Override
        public void registerDefaultFilters() {
            this.addIncludeFilter(new AnnotationTypeFilter(CustomizeComponent.class));
            // this.addIncludeFilter(new AnnotationTypeFilter(Path.class));
        }

        @Override
        public Set<BeanDefinitionHolder> doScan(String... basePackages) {
            Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
            for (BeanDefinitionHolder holder : beanDefinitions) {
                GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
                System.out.println("calssname==" + definition.getBeanClassName());
                definition.getPropertyValues().add("innerClassName", definition.getBeanClassName());
                definition.setBeanClass(FactoryBeanTest.class);
            }
            return beanDefinitions;
        }

        @Override
        public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return super.isCandidateComponent(beanDefinition) && beanDefinition.getMetadata().hasAnnotation(CustomizeComponent.class.getName());
        }

    }

    public static class FactoryBeanTest<T> implements InitializingBean, FactoryBean<T> {

        private String innerClassName;

        public void setInnerClassName(String innerClassName) {
            this.innerClassName = innerClassName;
        }

        @Override
        public T getObject() throws Exception {
            System.out.println("##innerClassName==" + innerClassName);
            Class innerClass = Class.forName(innerClassName);
            if (innerClass.isInterface()) {
                return (T) InterfaceProxy.newInstance(innerClass);
            } else {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(innerClass);
                enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
                enhancer.setCallback(new MethodInterceptorImpl());
                return (T) enhancer.create();
            }
        }

        @Override
        public Class<?> getObjectType() {
            try {
                return Class.forName(innerClassName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public boolean isSingleton() {
            return true;
        }

        @Override
        public void afterPropertiesSet() throws Exception {

        }
    }

    public static class InterfaceProxy implements InvocationHandler {

        public static <T> T newInstance(Class<T> innerInterface) {
            ClassLoader classLoader = innerInterface.getClassLoader();
            Class[] interfaces = new Class[]{innerInterface};
            System.out.println("##==" + innerInterface);
            InterfaceProxy proxy = new InterfaceProxy();
            return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("ObjectProxy execute:" + method.getName());
            return method.invoke(proxy, args);
        }
    }

    public static class MethodInterceptorImpl implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            System.out.println(Arrays.toString(objects) + "##" + methodProxy.getSignature().getName() + "MethodInterceptorImpl:" + method.getName());
            return methodProxy.invokeSuper(o, new Object[]{objects[0] + " ::new name"});
        }
    }

}