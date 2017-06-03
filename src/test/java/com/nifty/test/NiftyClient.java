/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: NiftyClient.java
 * @Package com.nifty.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年7月1日 下午4:21:12
 * @version
 */
package com.nifty.test;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author lenovo
 * @create time:2016年7月1日下午4:21:12
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class NiftyClient {
    public static void main(String[] args) throws Exception {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 7790));
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        HelloWorldService.Client client = new HelloWorldService.Client(protocol);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            client.sayHello("aa");
        }
        System.out.println("###" + (System.currentTimeMillis() - startTime));
        transport.close();
    }

}
