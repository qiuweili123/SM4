package com.blank;

import java.util.concurrent.Semaphore;

/**
 * 模拟呼叫
 *
 * @author inth-liqiuwei
 * @version V1.0
 * @ClassName: CallRes
 * @Description: TODO
 * @date 2015年7月19日 下午3:21:02
 */
public class CallRes implements Runnable {

    @Override
    public void run() {
        while (true) {

            Semaphore semaphore;
            try {
                semaphore = BlankConstants.getCrq().take();
                System.out.println("响应");
                // 响应一下，释放许可
                semaphore.release();
                System.out.println("##BlankConstants.getCrq()2==" + BlankConstants.getCrq().size());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }

    }

}
