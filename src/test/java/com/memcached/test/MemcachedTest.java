/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: MemcachedTest.java
 * @Package com.memcached.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年4月26日 上午11:48:29
 * @version
 */
package com.memcached.test;


import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.MemcachedClientCallable;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MemcachedTest {
    static MemcachedClient memcachedClient;

    @Before
    public void init() {
        final MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddressMap("10.150.170.103:11211,10.154.80.189:11211 10.150.170.103:11212"));
        builder.setFailureMode(true);//
        builder.setCommandFactory(new BinaryCommandFactory());
        try {
            memcachedClient = builder.build();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @After
    public void finash() throws Exception {
        memcachedClient.shutdown();
    }

    @Test
    public void testInsert() throws Exception {
        for (int i = 0; i < 10; i++) {
            memcachedClient.set("hello" + i, 0, "hello" + i);
        }
    }

    /**
     * @throws Exception
     * @Description:以下方法测试的是永久数据强制剔除的问题
     * @return: void
     * @author:liqiuwei 2016年5月8日上午9:16:28
     * @update1:updater:liqiuwei updatetime:2016年5月8日上午9:16:28 TODO(描述修改内容)
     */
    @Test
    public void testInsertSlab2() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8000; i++) {
            sb.append("a");
        }
        memcachedClient.set("hello0", 0, sb.toString());
/*		memcachedClient.get("hello0");
        for (int i = 1; i < 118; i++) {
			memcachedClient.set("hello"+i, 60,  sb.toString());
		} */
    }

    @Test
    public void testGetActivs() throws Exception {
        for (int n = 0; n < 10; n++) {
            for (int i = 1; i < 118; i++) {
                memcachedClient.get("hello" + i);
            }
        }

    }

    @Test
    public void testGet() throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("gxxwy");
//		for(int i=0;i<8000;i++){
//			sb.append("a");
//		}
        System.out.println("A6CD8D8DEDB929F89EE6B0FBE8708967".length());
        memcachedClient.set("A6CD8D8DEDB929F89EE6B0FBE8708967", 0, sb.toString());
        //Assert.assertEquals(sb.toString(), memcachedClient.get("hello0"));
    }

    /**
     * @throws Exception
     * @Description:测试Xmemocached是否具有hash tag的功能。同时测试standby模式
     * @return: void
     * @author:liqiuwei 2016年5月8日下午3:21:26
     * @update1:updater:liqiuwei updatetime:2016年5月8日下午3:21:26 TODO(描述修改内容)
     */
    @Test
    public void testNamespace() throws Exception {
        for (int i = 1; i < 10; i++) {
            final int n = i;
            String ns = "hello_id_" + n;
            memcachedClient.withNamespace(ns, new MemcachedClientCallable<Void>() {
                public Void call(MemcachedClient client) throws TimeoutException, InterruptedException, MemcachedException {
                    //a,b,c都在namespace下
                    client.set("username", 0, "username_" + n);
                    client.set("email", 0, "email_" + n);
                    return null;
                }
            });
            String ns2 = "hello_id_" + 2 * n;
            memcachedClient.withNamespace(ns2, new MemcachedClientCallable<Void>() {
                public Void call(MemcachedClient client) throws TimeoutException, InterruptedException, MemcachedException {
                    //a,b,c都在namespace下
                    client.set("username", 0, "username_" + 2 * n);
                    client.set("email", 0, "email_" + 2 * n);
                    return null;
                }
            });
            Thread.sleep(3000);
            System.out.println("休眠1s----");
        }

    }


}