/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: CreateNodeSyn.java
 * @Package com.zk.zkclient
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年3月20日 上午9:15:00
 * @version
 */
package com.zk.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

import java.util.List;

/**
 * @author liqiuwei
 * @create time:2016年3月20日上午9:15:00
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class CreateNodeSyn implements IZkChildListener {
    private static ZkClient zkClient;
    private static int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        //等同于调用源生API的，“sd”.getBytes,new SerializableSerializer() 会在节点存储序列化的byte数组，BytesPushThroughSerializer存储为源生byte数组
        //zkClient=new ZkClient("10.150.150.80:2181", 5000, 5000,new SerializableSerializer());
        zkClient = new ZkClient("10.150.150.80:2181", 5000, 10000, new BytesPushThroughSerializer());
        //zkClient.createPersistent("/zkClientTest","zkClient");
        //zkClient.createEphemeral("/zkClientTest/node1", "zkClientNode1".getBytes());
        // zkClient.subscribeChildChanges("/zkClientTest", new CreateNodeSyn());
        Thread.sleep(Integer.MAX_VALUE);
        System.out.println("##cout");


    }

    public static void createNode() {
        try {
            StringBuffer sb = new StringBuffer();
            byte[] allocation1 = new byte[_1MB / 1024];

            String path = "";
            for (int i = 0; i < 10240; i++) {
                zkClient.createEphemeral("/zkClientTest/node" + i, allocation1);
            }
            System.out.println("path=" + path);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * <p>Title: handleChildChange</p>
     * <p>Description: </p>
     *
     * @param parentPath
     * @param currentChilds
     * @throws Exception
     * @see IZkChildListener#handleChildChange(String, List)
     */
    @Override
    public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
        System.out.println("##parentPath==" + parentPath + "#" + currentChilds);

    }

}
