import com.thread.ReadWriteLockDemo1;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class TestReadWriteLock {

    @Test
    public void testReadWriteLockTest() throws Exception {
        final ReadWriteLockDemo1 readWriteLockTest = new ReadWriteLockDemo1();
        // 新建任务1，调用lockTest的addValue方法
        Runnable task_1 = new Runnable() {
            public void run() {
                readWriteLockTest.addValue(55.55);
            }
        };
        // 新建任务2，调用lockTest的getValue方法
        Runnable task_2 = new Runnable() {
            public void run() {
                System.out.println(System.currentTimeMillis() + "info: " + readWriteLockTest.getInfo());
            }
        };
        // 新建任务执行服务
        ExecutorService cachedService_1 = Executors.newCachedThreadPool();
        Future future_1 = null;
        // 同时执行5个任务，其中前2个任务是task_1，后两个任务是task_2
        for (int i = 0; i < 2; i++) {
            future_1 = cachedService_1.submit(task_1);
        }
        for (int i = 0; i < 2; i++) {
            future_1 = cachedService_1.submit(task_2);
        }
        // 最后一个任务是task_1
        future_1 = cachedService_1.submit(task_1);
        // 这5个任务的执行顺序应该是：
        // 第一个task_1先执行，第二个task_1再执行；这是因为不能同时写，所以必须等。
        // 然后2个task_2同时执行；这是因为在写的时候，就不能读，所以都等待写结束，
        // 又因为可以同时读，所以它们同时执行
        // 最后一个task_1再执行。这是因为在读的时候，也不能写，所以必须等待读结束后，才能写。

        // 等待最后一个task_2被执行完
        future_1.get();
        cachedService_1.shutdownNow();
    }


}
