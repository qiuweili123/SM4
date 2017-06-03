/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TestMonitorConsumer.java
 * @Package com.test.kafka
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2015年12月3日 下午5:11:36
 * @version
 */
package com.test.kafka;

import kafka.tools.ConsumerOffsetChecker;

/**
 * @author liqiuwei
 * @create time:2015年12月3日下午5:11:36
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class TestMonitorConsumer {
    public static void main(String[] args) {
        //group-1是消费者的group名称,可以在zk中
        String[] arr = new String[]{"--topic=lqwtest05", "--zookeeper=zk01.n.lemall.com:2181,zk02.n.lemall.com:2181,zk03.n.lemall.com:2181,zk04.n.lemall.com:2181,zk05.n.lemall.com:2181/kafka", "--group=test_group"};
        ConsumerOffsetChecker.main(arr);

    }
}
