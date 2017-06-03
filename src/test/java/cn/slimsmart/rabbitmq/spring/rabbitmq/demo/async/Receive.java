package cn.slimsmart.rabbitmq.spring.rabbitmq.demo.async;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Receive {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("conf/applicationContext-rabbitmq-async-receive.xml");
    }
}
