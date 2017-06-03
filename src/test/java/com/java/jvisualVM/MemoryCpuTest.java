package com.java.jvisualVM;

import java.util.concurrent.TimeUnit;

/**
 * @author liqiuwei
 * @create time:2015年11月11日下午2:24:23
 * @Description:TODO(这里用一句话描述这个类的作用) CPU 性能分析的主要目的是统计函数的调用情况及执行时间，或者更简单的情况就是统计应用程序的 CPU 使用情况
 */
public class MemoryCpuTest {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("start----");
        TimeUnit.SECONDS.sleep(10);
        cpuFix();
        System.out.println("start--end--");
    }


    /**
     * cpu 运行固定百分比
     *
     * @throws InterruptedException
     */
    public static void cpuFix() throws InterruptedException {
        // 80%的占有率
        int busyTime = 8;
        // 20%的占有率
        int idelTime = 2;
        // 开始时间
        long startTime = 0;

        while (true) {
            // 开始时间
            startTime = System.currentTimeMillis();
            
            /*
             * 运行时间
             */
            while (System.currentTimeMillis() - startTime < busyTime) {
                ;
            }

            // 休息时间
            TimeUnit.MILLISECONDS.sleep(idelTime);
        }
    }
}