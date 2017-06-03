package com.zk.javaApi;


import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;


public class GetChildrenSync implements Watcher {


    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        zooKeeper = new ZooKeeper("10.150.150.80:2181", 5000, new GetChildrenSync());
        System.out.println(zooKeeper.getState().toString());

        Thread.sleep(Integer.MAX_VALUE);


    }

    private void doSomething(ZooKeeper zooKeeper) {

        try {

            List<String> children = zooKeeper.getChildren("/nodeTest", true);//第二参数表示是否一直监控此节点变化
            System.out.println(children);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @Override
    public void process(WatchedEvent event) {
        // TODO Auto-generated method stub
        System.out.println("##event.getType()==" + event.getState() + "##" + event.getType());
        if (event.getState() == KeeperState.SyncConnected) {
            //在事件处理函数中（WatchedEvent）包含三个要素zk的连接状态、事件类型、事件相关的节点。 当程序启动后，获取事件类型、获取的节点都是空的，保证都dosomething执行一次

            if (event.getType() == EventType.None && null == event.getPath()) {
                System.out.println("无节点");
                doSomething(zooKeeper);
            } else {
                if (event.getType() == EventType.NodeChildrenChanged) {
                    try {
                        System.out.println(zooKeeper.getChildren(event.getPath(), true));
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
