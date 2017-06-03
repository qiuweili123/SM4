package com.test.kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestConsumer {
    private final ConsumerConnector consumer;
    private final String topic;
    private ExecutorService executor;

    public TestConsumer(String a_zookeeper, String a_groupId, String a_topic) {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
        this.topic = a_topic;
    }

    private static ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();
        props.put("zookeeper.connect", "zk01.n.lemall.com:2181,zk02.n.lemall.com:2181,zk03.n.lemall.com:2181,zk04.n.lemall.com:2181,zk05.n.lemall.com:2180/kafka");
        props.put("group.id", "test_group");
        props.put("zookeeper.session.timeout.ms", "60000");
        props.put("zookeeper.sync.time.ms", "2000");
        props.put("auto.commit.interval.ms", "1000");
        props.put("fetch.min.bytes", "65536000");
        return new ConsumerConfig(props);
    }

    public static void main(String[] args) {

        String topic = "lqwtest05";
        int threads = 12;
        for (int i = 0; i < 10; i++) {

            TestConsumer example = new TestConsumer("a", "a", topic);
            example.run(threads);
            example.shutdown();
        }

        try {
            System.out.println("##休眠");
            Thread.sleep(1000);
        } catch (InterruptedException ie) {

        }

    }

    public void shutdown() {
        if (consumer != null) consumer.shutdown();
        if (executor != null) executor.shutdown();
    }

    public void run(int a_numThreads) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(a_numThreads));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        // 启动所有线程
        executor = Executors.newFixedThreadPool(a_numThreads);

        // 开始消费消息
        int threadNumber = 0;
        for (final KafkaStream stream : streams) {
            executor.submit(new ConsumerTest(stream, threadNumber));

            threadNumber++;
        }
    }
}  