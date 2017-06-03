/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TdhsTest.java
 * @Package com.mysql.com
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年7月20日 下午1:33:38
 * @version
 */
package com.mysql.com;

import com.taobao.tdhs.client.TDHSClient;
import com.taobao.tdhs.client.TDHSClientImpl;
import com.taobao.tdhs.client.response.TDHSResponse;

import java.net.InetSocketAddress;

/**
 * @author lenovo
 * @create time:2016年7月20日下午1:33:38
 * @Description:TDHSClient 测试
 */
public class TdhsTest {
    public static void main(String[] args) throws Exception {
        TDHSClient client = new TDHSClientImpl(new InetSocketAddress("10.150.170.104", 9998), 2);
        System.out.println("##client " + client.getCharsetName());
        TDHSResponse response = client.query().use("mydatabase").from("mytable").select("name").where().equal("1").and().field("name").equal("name12055616").get();
        System.out.println("##client==" + response.getResultSet());
    }
}
