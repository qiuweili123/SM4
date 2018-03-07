package com.es;

import io.searchbox.action.AbstractDocumentTargetedAction;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.search.sort.Sort;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/2/7.
 */

public class JestTest {
    static JestClient client;

    public static void main(String[] args) throws IOException {
        init();
        //  testInsertStr();
        //testInsertMap();
       testQuery();
    }

    public static void init() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200")
                .multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                //   .defaultMaxTotalConnectionPerRoute(<YOUR_DESIRED_LEVEL_OF_CONCURRENCY_PER_ROUTE>)
                // and no more 20 connections in total
                //   .maxTotalConnection(<YOUR_DESIRED_LEVEL_OF_CONCURRENCY_TOTAL>)
                .build());
        client = factory.getObject();
    }

    public static void testInsertStr() throws IOException {
        Index index = new Index.Builder(createJson1()).index("customer").type("person").build();
        DocumentResult result = client.execute(index);
        System.out.println("result==" + result);

    }

    public static void testInsertMap() throws IOException {
        Map<String, Object> map = createMap();

        Index index = new Index.Builder(map).index("customer").type("person").id(map.get("id").toString()).refresh(true).build();

        DocumentResult result = client.execute(index);
        System.out.println("result==" + result);

    }

    public static Map<String, Object> createMap() {
        Map<String, Object> map = new HashMap();
        map.put("id",124);
        map.put("user", "limingming5");
        map.put("oid", 124);
        map.put("postDate", new Date());
        return map;
    }


    public static String createJson1() {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"oid\":1223," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        return json;
    }

    public static void testQuery() throws IOException {
        //使用es自身的querybuilder
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("oid", "123");
        System.out.println("##"+queryBuilder);
        String str = "{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"oid\":123\n" +
                "    }\n" +
                "  }\n" +
                "}";
        String str1="{\"query\":{\n" +
                "  \"match\" : {\n" +
                "    \"oid\" : {\n" +
                "      \"query\" : \"123\",\n" +
                "      \"type\" : \"boolean\"\n" +
                "    }\n" +
                "  }\n" +
                "}}";

        System.out.println("##str=="+str+"\nstr1="+str1);

        Search.Builder builder = new Search.Builder(str);

        builder.addIndex("customer")
                .addType("person")
                //  .setHeader(PWDKEY, getSecret())
                .setParameter("from", 0)
                .setParameter("size", 10);
        /*if(esQuery.getSort()!=null){
            builder.addSort(esQuery.getSort());
        }*/
       SortBuilder sortBuilder= SortBuilders.fieldSort("user").sortMode("desc");


        System.out.println("##sortBuilder=="+sortBuilder);
        Sort sort=new Sort("user", Sort.Sorting.DESC);
        builder.addSort(sort);





        JestResult jestResult = client.execute(builder.build());
        List<Map> asObjectList = jestResult.getSourceAsObjectList(Map.class);
        System.out.println("lsit==" + asObjectList);
        for (Map map : asObjectList) {
            System.out.println("##map=="+map+"::vlaue=="+map.get("user"));
        }
    }

     public  static  void testInsertOrUpdate(){

     }

}
