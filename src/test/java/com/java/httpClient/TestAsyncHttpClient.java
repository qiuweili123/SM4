/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TestAsyncHttpClient.java
 * @Package com.java.httpClient
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2015年12月17日 下午3:26:44
 * @version
 */
package com.java.httpClient;


import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig.Builder;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Response;
import com.ning.http.client.providers.netty.NettyAsyncHttpProvider;
import com.ning.http.client.providers.netty.response.NettyResponse;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class TestAsyncHttpClient {

    private final static String url = "http://hao123.com";
    public static int runCount = 100;

    public static void main(String[] args) throws Exception {
        TestAsyncHttpClient testAsyncHttpClient = new TestAsyncHttpClient();
        // --------------AsyncHttpClient-------------------------
        long start1 = System.currentTimeMillis();
        testAsyncHttpClient.AsyncHttpClientTest1();
        long end1 = System.currentTimeMillis();
        System.out.println("使用AsyncHttpClient 共用时间　" + (end1 - start1) + "ms");
        // --------------NettyAsyncHttpProvider-------------------------
        long start2 = System.currentTimeMillis();
        testAsyncHttpClient.AsyncHttpClientTest2();
        long end2 = System.currentTimeMillis();
        System.out.println("使用AsyncHttpClient中的NettyAsyncHttpProvider 共用时间　" + (end2 - start2) + "ms");
        // --------------httpClient-------------------------
        long start11 = System.currentTimeMillis();
        testAsyncHttpClient.HttpCientTest();
        long end11 = System.currentTimeMillis();
        System.out.println("使用HttpClient 共用时间　" + (end11 - start11) + "ms");

        long start12 = System.currentTimeMillis();
        testAsyncHttpClient.okHttpClient();
        long end12 = System.currentTimeMillis();
        System.out.println("使用okHttpClient 共用时间　" + (end12 - start12) + "ms");
    }


    @Test
    public void AsyncHttpClientTest1() {
        try {
            Builder builder = new Builder();
            builder.setConnectTimeout(100000);
            builder.setMaxConnections(100);

            //builder.setMaximumConnectionsTotal(10000);
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient(builder.build());
            for (int i = 0; i < runCount; i++) {
                ListenableFuture<Response> future = asyncHttpClient.prepareGet(url).execute();

                NettyResponse nettyResponse = (NettyResponse) future.get();
                // System.out.println(nettyResponse.getResponseBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AsyncHttpClientTest2() {
        try {
            Builder config = new Builder();
            config.setConnectTimeout(100000);
            config.setMaxConnections(100);
            NettyAsyncHttpProvider nettyAsyncHttpProvider = new NettyAsyncHttpProvider(config.build());
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient(nettyAsyncHttpProvider);
            for (int i = 0; i < runCount; i++) {
                ListenableFuture<Response> future = asyncHttpClient.prepareGet(url).execute();
                NettyResponse nettyResponse = (NettyResponse) future.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void HttpCientTest() {
        try {
            for (int i = 0; i < runCount; i++) {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity entity = httpResponse.getEntity();
                // System.out.println(entity.getContent());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void okHttpClient() throws Exception {
        ConnectionPool connectionPool = new ConnectionPool(50, 5, TimeUnit.MINUTES);

        OkHttpClient client = new OkHttpClient().newBuilder().connectionPool(connectionPool).build();


        Request request = new Request.Builder().url(url).build();
        for (int i = 0; i < runCount; i++) {
            okhttp3.Response response = client.newCall(request).execute();
        }

    }
}
