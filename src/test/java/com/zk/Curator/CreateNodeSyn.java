/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: CreateNodeSyn.java
 * @Package com.zk.Curator
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年3月20日 上午10:16:44
 * @version
 */
package com.zk.Curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @author liqiuwei
 * @create time:2016年3月20日上午10:16:44
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class CreateNodeSyn implements RetryPolicy {
    private static CuratorFramework client;
    private static int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        // 1. RetryPolicy retryPolicy=new ExponentialBackoffRetry(1000,
        // 3);//重试的基本的时间是1s，以后重试的时间会越来越长，但最多不能超过3次
        // 2. RetryPolicy retryPolicy=new RetryNTimes(3,
        // 1000);//最大重试3次，两次之间的间隔为1s
        RetryPolicy retryPolicy = new RetryUntilElapsed(3000, 1000);// 最大重试时间为3秒，两次间隔为1s
        // 1.第一种创建方式
        // client=CuratorFrameworkFactory.newClient("10.150.150.80:2181", 5000,
        // 5000, retryPolicy);
        // 2. 第二种创建方式 fluent风格的创建方式
        client = CuratorFrameworkFactory.builder().connectString("10.150.150.80:2181,10.150.150.80:2182,10.150.150.80:2183").connectionTimeoutMs(1000).sessionTimeoutMs(500).retryPolicy(retryPolicy).build();
        client.start();
        createNode();

        // deleteNode();
        // getChildNodeInfo();
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static void createNode() {
        try {
            StringBuffer sb = new StringBuffer();
            byte[] allocation1 = new byte[_1MB / 1024];

            String path = "";
            for (int i = 0; i < 10240; i++) {
                path = client.create().creatingParentsIfNeeded()// 如果父节点不存在会自动创建
                        .withMode(CreateMode.EPHEMERAL).forPath("/CuratorNode/node" + i, allocation1);
            }
            System.out.println("path=" + path);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void deleteNode() throws Exception {
        client.delete().guaranteed()// 表示如果删除异常会重试删除直到删除完成
                .deletingChildrenIfNeeded()// 如果有子节点先删除子节点
                // .withVersion(0)//增加版本验证
                .forPath("/CuratorNode");
        System.out.println("delete end");
    }

    public static void getChildNodeInfo() throws Exception {

        Stat stat = new Stat();
        Thread.sleep(2000);
        List<String> nodes = client.getChildren().storingStatIn(stat)// 将状态信息存储到stat对象中
                .forPath("/CuratorNode");
        long start = System.currentTimeMillis();

        for (String str : nodes) {

            System.out.println("##" + str);
        }
        System.out.println("###end=" + ((System.currentTimeMillis() - start) / 1000));
    }

    /**
     * <p>
     * Title: allowRetry
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param arg0 重试次数
     * @param arg1 重试的间隔时间
     * @param arg2 重试完成后执行休眠
     * @return
     * @see RetryPolicy#allowRetry(int, long, * RetrySleeper)
     */
    @Override
    public boolean allowRetry(int arg0, long arg1, RetrySleeper arg2) {
        // TODO Auto-generated method stub
        return false;
    }
}
