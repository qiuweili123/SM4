/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: JedisClusterTest.java
 * @Package com.redis.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: ard-liqiuwei
 * @date: 2017年2月21日 下午5:05:56
 * @version
 */
package com.redis.test;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.util.JedisClusterCRC16;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ard-liqiuwei
 * @create time:2017年2月21日下午5:05:56
 * @Description:TODO(这里用一句话描述这个类的作用)
 */

public class JedisClusterTest {

    /**
     * @Description:TODO(这里用一句话描述这个方法的作用)
     * @return: void
     * @author:ard-liqiuwei 2017年2月21日下午5:09:50
     * @update1:updater:lenovo updatetime:2017年2月21日下午5:09:50 测试结轮： 2.如果某个master
     * 挂了，比如7001, 集群依然可用，会存放到对应的salve 7004 上去。
     * 3.如果master -slave 都挂了，会导致 整个集群不可用，异常，因此最好配有M-S
     * 的结构 4.我默认配置，有rdb 和 aof 持久化，因此master
     * 挂了，重启，数据可以从salve 上恢复 5.存放的key 会根据返回的位置，放在不同的slot
     * 上，实现均衡
     */
    @Test
    public void testCluster() {
        String key = "2";
        // 这东西 可以直接看到key 的分片数，就能知道放哪个 节点
        System.out.println(JedisClusterCRC16.getSlot(key));
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7000));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7001));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7002));
        // 3个master 节点
        JedisCluster jc = new JedisCluster(jedisClusterNodes);

        System.out.println(jc.get(key));
        jc.setnx(key, "bar");
        String value = jc.get(key);
        System.out.println(value);

    }
}
