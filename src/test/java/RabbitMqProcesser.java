import com.sh.model.Student;
import com.sh.processor.MyMessagePostProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/applicationContext-rabbitmq.xml"})
public class RabbitMqProcesser {

    @Resource
    private AmqpTemplate amqpTemplate;

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("conf/applicationContext-rabbitmq.xml");
    }

    @Test
    public void testSend() throws Exception {
//		for(int j=0;j<10;j++){
//			System.out.println("第"+j+"send message");
//			Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setName("word" + i);
            sendReliable("queue.one", "word" + i);
            student.setName("hello" + i);
            //sendsAndReceive("queue_two_key", student);
            //amqpTemplate.convertAndSend("queue_two_key", "hello"+i);
            //MessageProperties
            //	amqpTemplate.convertSendAndReceive(message);//rpc访问方式的实现

        }
        //}

        // amqpTemplate.convertAndSend("queue_one_key", "hello word");

    }

    @Test
    public void testRecivceve() throws Exception {
        //System.out.println(amqpTemplate.receive("queue_one").getBody()+"##"+amqpTemplate.receive("queue_one").getMessageProperties().getReceivedRoutingKey());
    }

    public <T> void sendReliable(String routeKey, T message) {
        //实现将message通过json转换&将对象发送
        //convertAndSend(String exchange, String routingKey,s Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData)
        amqpTemplate.convertAndSend(routeKey, message, new MyMessagePostProcessor());
        // amqpTemplate.convertAndSend("my-mq-exchange",routeKey,"sdsds", new MyMessagePostProcessor());
        System.out.println("message==" + message);
    }

    public void sendsAndReceive(String routeKey, Student student) {
        //实现将message通过json转换&将对象发送
        //convertAndSend(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData)
        //同步等待队列返回结果
        Student student1 = (Student) amqpTemplate.convertSendAndReceive(routeKey, student, new MyMessagePostProcessor());
        System.out.println("##" + student1);
    }
}
