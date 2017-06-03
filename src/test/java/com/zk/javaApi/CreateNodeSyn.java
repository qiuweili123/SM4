/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: CreateNodeSyn.java
 * @Package com.zk.javaApi
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年3月19日 上午11:09:25
 * @version
 */
package com.zk.javaApi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author liqiuwei
 * @create time:2016年3月19日上午11:09:25
 * @Description:同步的方式创建节点，同步和异步的方式的区别在于是否有返回值
 */
public class CreateNodeSyn implements Watcher {
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("10.150.150.80:2181/nodeTest", 5000, new CreateNodeSyn());//watcher 初始化事件监听器
        System.out.println("starting=" + zooKeeper.getState());
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * <p>Title: process</p>
     * <p>Description:业务处理只能在proces中处理 </p>
     *
     * @param event
     * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
     */
    @Override
    public void process(WatchedEvent event) {
        System.out.println("eventState=" + event.getState());
        if (event.getState() == KeeperState.SyncConnected) {
            createNode();

        } else if (event.getState() == KeeperState.Expired) {
            try {
                System.out.println("reconnnection");
                zooKeeper = new ZooKeeper("10.150.150.80:2181", 5000, new CreateNodeSyn());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("其他");
        }


    }

    /**
     * @Description:Ids.OPEN_ACL_UNSAFE ACL所有人都有权限
     * createMode：创建的节点是临时节点还是持久节点，是否有序
     * @return: void
     * @author:liqiuwei 2016年3月19日下午12:13:35
     * @update1:updater:liqiuwei updatetime:2016年3月19日下午12:13:35 TODO(描述修改内容)
     */
    public void createNode() {
        try {
            //String path=zooKeeper.create("/zkTest", "cesh".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL );//创建临时节点
// 	         String  path=zooKeeper.create("/node1", "cesh".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL );
// 		System.out.println("##path=="+path);
//			 System.out.println("create node");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
