/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: NiftyServer.java
 * @Package com.nifty.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年7月1日 下午4:03:53
 * @version
 */
package com.nifty.test;

import com.facebook.nifty.core.NettyServerTransport;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.nifty.core.ThriftServerDefBuilder;
import org.apache.thrift.TProcessor;

/**
 * @author lenovo
 * @create time:2016年7月1日下午4:03:53
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class NiftyServer {
    public static void main(String[] args) {
        // Create the handler
        HelloWorldService.Iface serviceInterface = new HelloWorldServiceImpl();

        // Create the processor  
        TProcessor processor = new HelloWorldService.Processor<HelloWorldService.Iface>(serviceInterface);

        // Build the server definition  
        ThriftServerDef serverDef = new ThriftServerDefBuilder().listen(7790).withProcessor(processor).build();

        // Create the server transport  
        final NettyServerTransport server = new NettyServerTransport(serverDef);

        // Start the server  
        server.start();

        // Arrange to stop the server at shutdown  
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    server.stop();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        System.out.println("服务器启动成功...");
    }

}
