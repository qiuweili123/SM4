package com.java.httpClient;


import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

/**
 * 由于此类已经由commons，转移至http，成为一个独立的项目，已经作废，需要重写一个Util类
 *
 * @author liqiuwei
 * @create time:2015年11月3日下午3:09:43
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
@Deprecated
public class HttpClientUtil {

    private static final int TIMEOUT = 5 * 1000;
    private static final int MAX_HTTP_CONNECTION = 50;
    private static final int MAX_CONNECTION_PER_HOST = 10;
    private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
    private static HttpClientUtil instance = null;

    static {
        //HttpClient 连接池属性设置，HOST并发数默认为50, 客户端总并发数为200, TimeOut时间为5s.
        HttpConnectionManagerParams httpConnectionManagerParams = new HttpConnectionManagerParams();
        httpConnectionManagerParams.setMaxTotalConnections(MAX_HTTP_CONNECTION);
        httpConnectionManagerParams.setDefaultMaxConnectionsPerHost(MAX_CONNECTION_PER_HOST);
        httpConnectionManagerParams.setSoTimeout(TIMEOUT);
        httpConnectionManagerParams.setConnectionTimeout(TIMEOUT);
        connectionManager.setParams(httpConnectionManagerParams);
    }

    public HttpClientUtil() {

    }

    public static HttpClientUtil getInstance() {

        if (null == instance) {
            synchronized (HttpClientUtil.class) {
                if (instance == null) {
                    instance = new HttpClientUtil();
                }
            }
        }
        return instance;
    }

    public HttpClient createHttpClient() {

        HttpClient httpClient = new HttpClient(connectionManager);

        return httpClient;
    }

    /**
     * 进行 http请求返回resultVO结果对象
     *
     * @param url
     * @return
     */
    public ResultVO getHttpClientJson(String url) {

        HttpClient httpClient = createHttpClient();
        GetMethod getMethod = new GetMethod(url);

        try {
            httpClient.executeMethod(getMethod);

            String jsonString = getMethod.getResponseBodyAsString();

            return JSON.parseObject(jsonString, ResultVO.class);

        } catch (Exception e) {
            e.printStackTrace();
            ResultVO resultVO = new ResultVO();
            resultVO.setRet(1);
            resultVO.setMsg("调用失败");
            return resultVO;
        } finally {
            getMethod.releaseConnection();
        }
    }


    /**
     * 进行 http post请求返回resultVO结果对象
     *
     * @param url
     * @return
     */
    public ResultVO postHttpClientJson(String url) {

        HttpClient httpClient = createHttpClient();
        PostMethod postMethod = new PostMethod(url);

        try {
            httpClient.executeMethod(postMethod);

            String jsonString = postMethod.getResponseBodyAsString();

            return JSON.parseObject(jsonString, ResultVO.class);

        } catch (Exception e) {
            e.printStackTrace();
            ResultVO resultVO = new ResultVO();
            resultVO.setRet(1);
            resultVO.setMsg("调用失败");
            return resultVO;
        } finally {
            postMethod.releaseConnection();
        }
    }
}