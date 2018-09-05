package com.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * Created by lenovo on 2018/2/6.
 */
public class RestHighApiTest {
    static RestHighLevelClient restHighLevelClient;

    public static void main(String[] args) throws IOException {
        //init();
        ESClientFactory factory = new ESClientFactory();
        IndexRequest indexRequest = new IndexRequest("phr", "hospital");
        //indexRequest.contentType(XContentType.JSON);
        indexRequest.id("1000000");
        indexRequest.source(createJson1());

        IndexResponse response = ESClientFactory.getHighLevelClient().index(indexRequest);
        System.out.println("response::" + response);
    }

    public void init() {
        restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")
                ).build());
    }

    public static String createJson1() {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        return json;
    }

}
