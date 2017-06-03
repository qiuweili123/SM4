package com.blank;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟生成客户
 *
 * @author inth-liqiuwei
 * @version V1.0
 * @ClassName: GenerateCustomer
 * @Description: TODO
 * @date 2015年7月19日 下午2:59:22
 */
public class GenerateCustomer implements Runnable {

    private CustomerType type;

    public GenerateCustomer(CustomerType type) {

        this.type = type;
    }

    @Override
    public void run() {
        int n = 0;
        while (true) {

            AtomicInteger num = BlankConstants.getNmMap().get(type);
            int i = num.getAndIncrement();
            //System.out.println(type+"i=="+i);
            BlockingQueue<Integer> queue = BlankConstants.getQueueMap().get(type);//得到对应客户类型的队列
            try {
                queue.put(i);
                // 每隔3s生成一个客户
                Thread.sleep(3000);
                n++;
                if (type == CustomerType.VIP && n > 2) {
                    System.out.println("###################");
                    break;

                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }

    }

}
