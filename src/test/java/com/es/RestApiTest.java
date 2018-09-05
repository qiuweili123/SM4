package com.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lenovo on 2018/2/6.
 */
public class RestApiTest {
    static RestClient restClient = RestClient.builder(
            new HttpHost("192.168.19.128", 9200, "http")).build();

    public static void main(String[] args) throws IOException {
        System.out.println("##" + restClient);

        testPost();
    }


    public static void testPost() throws IOException {
        HttpEntity entity = new StringEntity(createJson1(), ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest("post", "/phr/hospital", Collections.singletonMap("pretty", "true"), entity);
        System.out.println(EntityUtils.toString(response.getEntity()));

    }

    public static void testQuery() {
        List<String> tables = Lists.newArrayList();
        Map<String, Object> requestData = Maps.newHashMap();
        requestData.put("from", 1);
        requestData.put("size", 100);
        HttpEntity entity = new NStringEntity(JSON.toJSONString(requestData), ContentType.APPLICATION_JSON);
        Response response;
        JSONObject jsonObj = null;
        long rsCount = 0l;
        try {
            response = restClient.performRequest("GET", "/phr/hospital", Collections.singletonMap("pretty", "true"), entity);
            jsonObj = JSON.parseObject(EntityUtils.toString(response.getEntity()));
            JSONObject hits = jsonObj.getJSONObject("hits");
            JSONArray arr = hits.getJSONArray("hits");
            if (Objects.nonNull(arr)) {
                rsCount = hits.getLong("total");
                if (rsCount > 0) {
                    for (int i = 0; i < arr.size(); i++) {
                        String _id = arr.getJSONObject(i).getString("_id");
                        JSONObject obj = arr.getJSONObject(i).getJSONObject("_source");
                        obj.put("_id", _id);
                        Map<String, Object> map = (Map<String, Object>) (obj);
                        //    rsList.add(map);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

   /*   Map<String,Object> mappings = (Map<String,Object>)(jsonObj.getJSONObject(database).get("mappings"));
      for (Map.Entry<String,Object> m : mappings.entrySet()) {
        //  tables.add(m.getKey());
      }*/
    }

    public static String createJson1() {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"orgid\":1," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        return json;
    }


}
