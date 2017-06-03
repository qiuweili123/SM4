package cn.slimsmart.rabbitmq.spring.rabbitmq.demo.rpc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("conf/applicationContext-rabbitmq-rpc-client.xml");
        TestService testService = (TestService) context.getBean("testService");
        System.out.println(testService.say(" hello  Tom"));
    }
}
