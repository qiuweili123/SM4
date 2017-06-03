/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: AsyncService.java
 * @Package com.sh.taskExecutor
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2015年12月16日 下午2:49:44
 * @version
 */
package com.sh.taskExecutor;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author liqiuwei
 * @create time:2015年12月16日下午2:49:44
 * @Description:TODO(这里用一句话描述这个类的作用)
 */

/**
 * @author liqiuwei
 * @create time:2015年12月16日下午2:49:54
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
@Service
public class AsyncService {

    public void update() throws InterruptedException {
        System.out.println("#########" + Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("开始执行啊");

    }

}
