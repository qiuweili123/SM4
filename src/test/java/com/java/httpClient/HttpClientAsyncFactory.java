package com.java.httpClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HttpClientAsyncFactory {
    private final static HttpClientAsyncFactory httpClientFactory = new HttpClientAsyncFactory();
    private final static Log log = LogFactory.getLog(HttpClientAsyncFactory.class);
    private CloseableHttpAsyncClient httpClient = null;

    private HttpClientAsyncFactory() {
        initHttpClient();
    }

    public static HttpClientAsyncFactory getHttpClientFactory() {
        return httpClientFactory;
    }

    private void initHttpClient() {
        try {
            ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor();
            PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
            connManager.setMaxTotal(HttpClientConstant.MAX_TOTAL);
            connManager.setDefaultMaxPerRoute(HttpClientConstant.MAX_ROUTE_TOTAL);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(HttpClientConstant.CONNECT_TIMEOUT)//请求超时
                    .build();
            //设置重定向策略
            LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();

            httpClient = HttpAsyncClients.custom().setConnectionManager(connManager).setDefaultRequestConfig(requestConfig).setRedirectStrategy(redirectStrategy).build();
        } catch (IOReactorException e) {
            log.error("initHttpClient err : " + e);
        }
    }

    public void asyncPost(Map<String, List<NameValuePair>> params) {
        try {
            httpClient.start();
            List<String> urls = new ArrayList<String>();
            Iterator iter = params.entrySet().iterator();
            Map.Entry<String, List<NameValuePair>> entry;
            while (iter.hasNext()) {
                entry = (Map.Entry) iter.next();
                urls.add(entry.getKey());
            }
            HttpPost[] postRequests = new HttpPost[urls.size()];
            int i = 0;
            for (String url : urls) {
                postRequests[i] = new HttpPost(url);
                postRequests[i].setEntity(new UrlEncodedFormEntity(params.get(url), HTTP.UTF_8));
                i++;
            }
            final CountDownLatch latch = new CountDownLatch(postRequests.length);
            for (final HttpPost request : postRequests) {
                httpClient.execute(request, new FutureCallback<HttpResponse>() {
                    public void completed(final HttpResponse response) {
                        latch.countDown();
                        System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                    }

                    public void failed(final Exception e) {
                        latch.countDown();
                    }

                    public void cancelled() {
                        latch.countDown();
                    }
                });
            }
//            latch.await();
            latch.await(HttpClientConstant.WAIT_TIMEOUT, TimeUnit.MILLISECONDS);
//            httpClient.close();
        } catch (Exception e) {
            log.error("asyncPost err : " + e);
        }
    }
}