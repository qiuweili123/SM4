/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: FastDateFormatTest.java
 * @Package com.java.date
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年4月23日 下午2:17:51
 * @version
 */
package com.java.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * http://lilongfei1030.blog.163.com/blog/static/860152820136260822266/
 *
 * @author liqiuwei
 * @create time:2016年4月23日下午2:17:51
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class FastDateFormatTest {
    public static final Logger loger = LoggerFactory.getLogger(FastDateFormatTest.class);
    //线程数
    private static final int THREAD_NUM = 50;
    // 客户端数
    private static final int CLIENT_NUM = 100;
    private static final Logger loger = getDefaultLogger();
    private static int failCount = 0;

    public static void main(String[] args) {
//	FastDateFormat fdate=FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
//	System.out.println("##"+fdate.format(new Date()));

    }

    private static Logger getDefaultLogger() {
        return Logger.getLogger("Businesslog");
    }

    @Override
    public void setUp() throws Exception {
        // TODO: 实现测试前的初始化工作
    }

    @Override
    public void tearDown() throws Exception {
        // 实现测试完成后的垃圾回收、测试结果统计等工作
        loger.info("访问数:" + CLIENT_NUM);
        loger.info("并发数:" + THREAD_NUM);
        loger.info("断言失败数:" + failCount);
    }

    @Test
    public void test() {
        // 得到一个可复用线程的线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 信号量
        final Semaphore semp = new Semaphore(THREAD_NUM);
        for (int index = 0; index < CLIENT_NUM; index++) {
            final int no = index;
            Runnable run = new Runnable() {
                public void run() {
                    // 获取一个准入许可
                    try {
                        semp.acquire();
                        // doFormatTest(no);
                        doParseTest();
                        // 释放一个许可
                        semp.release();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            };
            // 在线程池中执行一个任务
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }

    /**
     * @Description:
     */
    private void doParseTest() {
        try {
            DateUtil.parse("2013-07-25");
        } catch (Throwable e) {
            failCount++;
            e.printStackTrace();
        }
    }

    /**
     * @param no
     * @Description: 测试时需要修改一下日期，如：当前日期为2013-07-25，明天为2013-07-26
     */
    private void doFormatTest(int no) {
        try {
            if (no % 2 == 0) {
                String today = DateUtil.formatDate(new Date());
                assertTrue("ERROR TODAY IS:" + today, "2013-07-25".equals(today));
            } else {
                String tomorrow = DateUtil.formatDate(new Date(new Date().getTime() + 1000 * 60 * 60 * 24));
                assertTrue("ERROR TOMORROW IS:" + tomorrow, "2013-07-26".equals(tomorrow));
            }
        } catch (Throwable e) {
            loger.error(e);
            failCount++;
        }
    }
}
