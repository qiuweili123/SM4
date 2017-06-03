package com.blank;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 银行窗口服务类
 *
 * @author inth-liqiuwei
 * @version V1.0
 * @ClassName: Service
 * @Description: TODO
 * @date 2015年7月19日 下午2:20:08
 */
public class Service implements Runnable {
    /**
     * @Fields types :此窗口支持的服务类行
     */
    private CustomerType[] types;
    /**
     * @Fields windowName : 服务窗口名称
     */
    private String windowName;

    // 信号量媒介--一次只能服务一人
    private Semaphore sem = new Semaphore(1);

    public Service(CustomerType[] types, String windowName) {
        this.types = types;
        this.windowName = windowName;
    }


    @Override
    public void run() {
        int length = types.length;
        int i = 0;
        while (i < length) {
            CustomerType type = types[i];
            BlockingQueue<Integer> q = BlankConstants.getQueueMap().get(type);
            try {
                //获取顾客类型对应的号
                Integer nm = q.poll(100, TimeUnit.MILLISECONDS);
                if (nm != null) {
                    sem.acquire();
                    System.out.println(System.currentTimeMillis() + this.windowName + "正在呼叫" + nm + " ~号" + type);
                    BlankConstants.getCrq().put(sem);
                    System.out.println("BlankConstants.getCrq()==" + BlankConstants.getCrq().size());
                    // 等待服务wait   由于响应完以后阻塞队列会会以响应的方式消耗 信号量所以 需要重新获取信号量
                    sem.acquire();
                    // 成功继续
                    System.out.println(this.windowName + "开始为~" + nm + "~号" + type.toString() + "服务！");
                    // 服务中 --多步操作完全可以用线程模拟
                    Thread.sleep(5000);

                    // 服务完毕
                    System.out.println(this.windowName + "为~" + nm + "~号" + type.toString() + "服务完毕！");

                    sem.release();
                    //数组次序确定优先级
                    i = 0;
                } else {
                    i++;
                }

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }

    }

}


