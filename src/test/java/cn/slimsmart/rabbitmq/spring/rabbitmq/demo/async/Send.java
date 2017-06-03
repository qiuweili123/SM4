package cn.slimsmart.rabbitmq.spring.rabbitmq.demo.async;

import com.rabbitmq.spring.template.ASyncRabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Send {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("conf/applicationContext-rabbitmq-async-send.xml");
            ASyncRabbitTemplate amqpTemplate = context.getBean(ASyncRabbitTemplate.class);
            System.out.println("33=" + (amqpTemplate == null));
            for (int i = 0; i < 10; i++) {
                amqpTemplate.send(System.currentTimeMillis() + "dfdfdtest spring async=>" + i);
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
