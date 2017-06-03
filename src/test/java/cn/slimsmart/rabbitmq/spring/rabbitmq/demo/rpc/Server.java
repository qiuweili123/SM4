package cn.slimsmart.rabbitmq.spring.rabbitmq.demo.rpc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("conf/applicationContext-rabbitmq-rpc-server.xml");
    }
}
