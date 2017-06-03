import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.perf4j.StopWatch;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: testKafka.java
 * @Package
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2015年12月2日 下午4:25:19
 * @version
 */

/**
 * @author liqiuwei
 * @create time:2015年12月2日下午4:25:19
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class testKafka {
    private static final StopWatch stopWatch = new StopWatch();

    public static void main(String[] args) {
        long events = 1000L;
        Random rnd = new Random();
        try {
            Properties props = new Properties();
            props.put("metadata.broker.list", "10.150.170.98:9092");
            props.put("serializer.class", "kafka.serializer.StringEncoder"); //默认字符串编码消息
            //props.put("partitioner.class", "example.producer.SimplePartitioner");
            props.put("request.required.acks", "1");
            props.put("producer.type", "sync");


            ProducerConfig config = new ProducerConfig(props);

            long start = System.currentTimeMillis();
            Producer<String, String> producer = new Producer<String, String>(config);
            stopWatch.lap("开始1");
            for (long nEvents = 0; nEvents < events; nEvents++) {
                long runtime = new Date().getTime();
                String ip = "192.168.2." + rnd.nextInt(255);
                String msg = runtime + ",www.example.com," + ip;
                KeyedMessage<String, String> data = new KeyedMessage<String, String>("lqwtest04", ip, msg);
                producer.send(data);

            }
            System.out.println("##" + (System.currentTimeMillis() - start));

            stopWatch.lap("结束");
            producer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
