import com.javapatterns.Consumer;
import com.javapatterns.ImmutableData;
import com.javapatterns.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 测试生产和消费者模式同时使用不变模式
 *
 * @author inth-liqiuwei
 * @version V1.0
 * @ClassName: TestProConsumer
 * @Description: TODO
 * @date 2015年8月15日 下午8:48:31
 */
public class TestProConsumer {
    public static void main(String[] args) {
        BlockingQueue<ImmutableData> queueBlockingQueue = new ArrayBlockingQueue<ImmutableData>(1);
        Producer producer = new Producer(queueBlockingQueue, "thread1");
        Thread threadProducer = new Thread(producer, "生产者1");
        threadProducer.start();

//		BlockingQueue<ImmutableData> queueBlockingQueue2=new ArrayBlockingQueue<ImmutableData>(1);
//		Producer producer2=new Producer(queueBlockingQueue2,"thread2"); 
//        Thread threadProducer2=new Thread(producer2,"生产者2"); 
//		threadProducer2.start();
//		

        Thread consumerThread = new Thread(new Consumer(queueBlockingQueue));
        consumerThread.start();
    }
}
