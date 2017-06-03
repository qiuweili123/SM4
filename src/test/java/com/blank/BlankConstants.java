package com.blank;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class BlankConstants {
    //号码队列封装
    private static Map<CustomerType, AtomicInteger> nmMap = new HashMap<CustomerType, AtomicInteger>();
    //排号队列封装
    private static Map<CustomerType, BlockingQueue<Integer>> queueMap = new HashMap<CustomerType, BlockingQueue<Integer>>();
    // 呼叫应答模拟队列
    private static BlockingQueue<Semaphore> crq = new LinkedBlockingQueue<Semaphore>();

    /**
     * 初始化队列信息
     * @author inth-liqiuwei
     * @date 2015年7月19日 下午2:03:25
     */
    static {
        nmMap.put(CustomerType.COMMON, new AtomicInteger(1));
        nmMap.put(CustomerType.EXPRESS, new AtomicInteger(1));
        nmMap.put(CustomerType.VIP, new AtomicInteger(1));

        queueMap.put(CustomerType.COMMON, new LinkedBlockingQueue<Integer>());
        queueMap.put(CustomerType.EXPRESS, new LinkedBlockingQueue<Integer>());
        queueMap.put(CustomerType.VIP, new LinkedBlockingQueue<Integer>());

    }

    public static Map<CustomerType, AtomicInteger> getNmMap() {
        return nmMap;
    }

    public static void setNmMap(Map<CustomerType, AtomicInteger> nmMap) {
        BlankConstants.nmMap = nmMap;
    }

    public static Map<CustomerType, BlockingQueue<Integer>> getQueueMap() {
        return queueMap;
    }

    public static void setQueueMap(Map<CustomerType, BlockingQueue<Integer>> queueMap) {
        BlankConstants.queueMap = queueMap;
    }

    public static BlockingQueue<Semaphore> getCrq() {
        return crq;
    }

    public static void setCrq(BlockingQueue<Semaphore> crq) {
        BlankConstants.crq = crq;
    }

}
