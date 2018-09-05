package com.guava.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*
*
验证结果和预期一致：
1.在缓存还没初始化的时候，client-1最新获得了load锁，进行load操作，在进行load的期间，其他client也到达进入load过程，阻塞，等待client-1释放锁，再依次获得锁。最终只load by client-1。
2.当超过了refreshAfterWrite设定的时间之内没有访问，需要进行refresh，client-5进行 refresh，在这个过程中，其他client并没有获得锁，而是直接查询旧值，直到refresh后才得到新值，过渡平滑。
3.在超过了expireAfterWrite设定的时间内没有访问，main线程在访问的时候，值已经过期，需要进行load操作，而不会得到旧值。
* */
public class CacheTest {


    private static final int CONCURRENT_NUM = 10;//并发数

    private volatile static int value = 1;

    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000)
            .expireAfterWrite(5, TimeUnit.SECONDS)// 缓存过期时间和redis缓存一样
            .refreshAfterWrite(1, TimeUnit.SECONDS)// 给定时间内没有被读/写访问，则回收。
            .build(new CacheLoader<String, String>() {
                       public String load(String key) throws InterruptedException {
                           System.out.println("load by " + Thread.currentThread().getName());
                           return createValue(key);
                       }


                       @Override
                       public ListenableFuture<String> reload(String key, String oldValue)
                               throws Exception {
                           System.out.println("reload by " + Thread.currentThread().getName());
                           return Futures.immediateFuture(createValue(key));
                       }
                   }
            );

    //创建value
    private static String createValue(String key) throws InterruptedException {
        Thread.sleep(1000L);//让当前线程sleep 1秒，是为了测试load和reload时候的并发特性
        return String.valueOf(value++);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CyclicBarrier barrier = new CyclicBarrier(CONCURRENT_NUM);
        CountDownLatch latch = new CountDownLatch(CONCURRENT_NUM);
        for (int i = 0; i < CONCURRENT_NUM; i++) {
            final ClientRunnable runnable = new ClientRunnable(barrier, latch);
            Thread thread = new Thread(runnable, "client-" + i);
            thread.start();
        }

        //测试一段时间不访问后是否执行expire而不是refresh
        latch.await();
        Thread.sleep(5100L);
        System.out.println("\n超过expire时间未读之后...");
        System.out.println(Thread.currentThread().getName() + ",val:" + cache.get("key"));
    }

    static class ClientRunnable implements Runnable {

        CyclicBarrier barrier;
        CountDownLatch latch;

        public ClientRunnable(CyclicBarrier barrier, CountDownLatch latch) {
            this.barrier = barrier;
            this.latch = latch;
        }

        public void run() {
            try {
                barrier.await();
                Thread.sleep((long) (Math.random() * 4000));//每个client随机睡眠，为了充分测试refresh和load
                System.out.println(Thread.currentThread().getName() + ",val:" + cache.get("key"));
                latch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
