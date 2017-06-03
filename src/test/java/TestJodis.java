import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.junit.Test;
import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.List;

public class TestJodis {
    @Test
    public void testJodis() throws Exception {
        try {

            JedisPoolConfig config = new JedisPoolConfig();
            //config.settsetMaxActive(500);
            config.setMinIdle(50);
            config.setMaxIdle(1000);
            config.setMaxTotal(5000);
            config.setMaxWaitMillis(30000);
            //config.setMaxWait(1000);
            config.setTestOnBorrow(false);

            JedisResourcePool jedisPool = new RoundRobinJedisPool("10.1.2.116:2181", 300000, "/zk/codis/db_erp_codis_cluster/proxy", config);

            System.out.println("##jedisPool==" + jedisPool.getResource());
            Jedis jedis = jedisPool.getResource();
            Long startTimeLong = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                jedis.set("h:" + i, "word" + i);

            }
            System.out.println("##" + (System.currentTimeMillis() - startTimeLong) / 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetJodis() throws Exception {
        JedisResourcePool jedisPool = new RoundRobinJedisPool("10.1.2.116:2181", 3000, "/zk/codis/db_erp_codis_cluster/proxy", new JedisPoolConfig());

        System.out.println("##jedisPool==" + jedisPool.getResource());
        //System.out.println("##jedisPool=="+jedisPool.getResource().info());
        Jedis jedis = jedisPool.getResource();
        System.out.println("##" + jedis.get("h:1000"));
    }


//分布式直连异步调用

    @Test
    public void test6shardpipelined() {
        List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("10.1.2.119", 19000), new JedisShardInfo("10.1.2.120", 19000), new JedisShardInfo("10.1.2.121", 19000));

        ShardedJedis sharding = new ShardedJedis(shards);

        ShardedJedisPipeline pipeline = sharding.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            pipeline.set("sp" + i, "p" + i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined@Sharing SET: " + ((end - start) / 1000.0) + " seconds");

        sharding.disconnect();
    }

    /**
     * 分布式连接池同步调用
     *
     * @author inth-liqiuwei
     * @date 2015年8月12日 上午10:53:46
     */
    @Test
    public void test7shardSimplePool() {
        List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("10.1.2.119", 19000), new JedisShardInfo("10.1.2.120", 19000), new JedisShardInfo("10.1.2.121", 19000));

        ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);

        ShardedJedis one = pool.getResource();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String result = one.set("spn" + i, "n" + i);
        }
        long end = System.currentTimeMillis();
        pool.returnResource(one);
        System.out.println("Simple@Pool SET: " + ((end - start) / 1000.0) + " seconds");

        pool.destroy();
    }


    /**
     * 八、分布式连接池异步调用
     *
     * @author inth-liqiuwei
     * @date 2015年8月12日 上午11:02:36
     */
    @Test
    public void test8shardPipelinedPool() {
        List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("10.1.2.119", 19000), new JedisShardInfo("10.1.2.120", 19000), new JedisShardInfo("10.1.2.121", 19000));

        ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);

        ShardedJedis one = pool.getResource();

        ShardedJedisPipeline pipeline = one.pipelined();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            pipeline.set("sppn" + i, "n" + i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        pool.returnResource(one);
        System.out.println("Pipelined@Pool SET: " + ((end - start) / 1000.0) + " seconds");
        pool.destroy();
        pool.close();
    }

    @Test
    public void testSetJodis() throws Exception {
        JedisResourcePool jedisPool = new RoundRobinJedisPool("10.1.2.116:2181", 30000, "/zk/codis/db_erp_codis_cluster/proxy", new JedisPoolConfig());

        System.out.println("##jedisPool==" + jedisPool.getResource());
        //System.out.println("##jedisPool=="+jedisPool.getResource().info());
        Jedis jedis = jedisPool.getResource();
        Pipeline pipeline = jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            pipeline.set("HG" + i, "n" + i);
        }
        List<Object> lusList = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined@Pool SET: " + ((end - start) / 1000.0) + " seconds");
        jedis.disconnect();

    }

    @Test
    public void testConnctZookPeer() throws Exception {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString("localhost:2181").connectionTimeoutMs(10000);
        builder.namespace("zk").retryPolicy(new RetryNTimes(10000, 1000));
        CuratorFramework client = builder.build();
        System.out.println("start connection----");
        client.start();

        // 异步地删除一个节点
        client.create().inBackground().forPath("/test01", "test02".getBytes());
        String str = new String(client.getData().forPath("/test01"));
        System.out.println("##" + str);
    }
    /**
     * 多线程插入数据
     * @author inth-liqiuwei
     * @date 2015年8月12日 下午1:33:11
     * @param args
     */
/*public static void main(String[] args) {
    ExecutorService pool = Executors.newCachedThreadPool();
	long start = System.currentTimeMillis(); 
	for (int i = 0; i < 25; i++) {
		pool.execute(new Runnable() {
			
			@Override
			public void run() {
				JedisResourcePool jedisPool = new RoundRobinJedisPool("10.1.2.116:2181", 30000, "/zk/codis/db_erp_codis_cluster/proxy", new JedisPoolConfig());
				for (int i = 0; i < 30000; i++) {
					try (Jedis jedis = jedisPool.getResource()) {
					    jedis.set("foo"+i, "bar"+i);
					    String value = jedis.get("foo"+i);
					    //System.out.println(value);
					}
//					try {
//						Thread.sleep(1);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				}
				try {
					jedisPool.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	 long end = System.currentTimeMillis(); 
	 System.out.println("Pipelined@Pool SET: " + ((end - start)/1000.0) + " seconds");
}*/

}
