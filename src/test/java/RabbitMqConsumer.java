import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RabbitMqConsumer {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("conf/applicationContext-rabbitmq.xml");
    }
}
