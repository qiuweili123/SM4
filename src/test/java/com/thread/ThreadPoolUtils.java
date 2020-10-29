package com.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolUtils {

    private ThreadPoolExecutor pool = null;

    static Integer num = 0;

    /**
     * 获取对象
     *
     * @param corePoolSize  核心线程数量
     * @param poolName      线程池名称
     * @param maxThreadSize 最大线程数
     * @param maxTaskSize   最大阻塞任务数
     * @return
     */
    public ThreadPoolExecutor getNewInstance(int corePoolSize, int maxThreadSize, String poolName, int maxTaskSize) {

        pool = createPool(corePoolSize, maxThreadSize, poolName, maxTaskSize);

        if (pool == null) {
            throw new NullPointerException();
        } else {
            return pool;
        }
    }

    /**
     * 创建线程池
     *
     * @param corePoolSize  核心线程数量
     * @param poolName      线程池名称
     * @param maxThreadSize 最大线程数
     * @param maxTaskSize   最大阻塞任务数
     * @return
     */
    private ThreadPoolExecutor createPool(int corePoolSize, int maxThreadSize, String poolName, int maxTaskSize) {

        return new ThreadPoolExecutor(corePoolSize, maxThreadSize, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(maxTaskSize),
                new CustomThreadFactory(poolName),
                new RejectedExecutionHandlerImpl());
    }

    /**
     * 线程工厂
     * 给线程命名
     */
    private class CustomThreadFactory implements ThreadFactory {

        private final String poolName;

        public CustomThreadFactory(String poolName) {
            this.poolName = poolName;
        }

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String nowThreadName = "";
            nowThreadName = poolName + count.addAndGet(1);
            t.setName(nowThreadName);
            return t;
        }
    }

    /**
     * 自定义拒绝策略
     */
    private class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolUtils().getNewInstance(3, 8, "test-", 5);
        for (int i = 0; i < 20; i++) {
            System.out.println("提交第" + i + "个任务");
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(10);
                    synchronized (num) {
                        System.out.println("数字为：" + num++);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("提交第" + i + "个任务成功");
        }
        System.out.println("结束");
    }
}
