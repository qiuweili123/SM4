package com.es;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * java操作查询api
 *
 * @author 231
 */
public class JavaESQuery {

    private TransportClient client;

    @Before
    public void testBefore() {
        Settings settings = Settings.settingsBuilder().put("cluster.name", "wenbronk_escluster").build();
        client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
        System.out.println("success to connect escluster");
    }

    /**
     * 使用get查询
     */
    @Test
    public void testGet() {
        GetRequestBuilder requestBuilder = client.prepareGet("twitter", "tweet", "1");
        GetResponse response = requestBuilder.execute().actionGet();
        GetResponse getResponse = requestBuilder.get();
        ListenableActionFuture<GetResponse> execute = requestBuilder.execute();
        System.out.println(response.getSourceAsString());
    }

    /**
     * 使用QueryBuilder
     * termQuery("key", obj) 完全匹配
     * termsQuery("key", obj1, obj2..)   一次匹配多个值
     * matchQuery("key", Obj) 单个匹配, field不支持通配符, 前缀具高级特性
     * multiMatchQuery("text", "field1", "field2"..);  匹配多个字段, field有通配符忒行
     * matchAllQuery();         匹配所有文件
     */
    @Test
    public void testQueryBuilder() {

        QueryBuilder queryBuilder = QueryBuilders.termQuery("user", "kimchy");


        //  QueryBuilder queryBuilder = QueryBuilders.termQuery("user", "kimchy", "wenbronk", "vini");
        QueryBuilders.termsQuery("user", new ArrayList<String>().add("kimchy"));
//        QueryBuilder queryBuilder = QueryBuilders.matchQuery("user", "kimchy");
//        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("kimchy", "user", "message", "gender");
        //  QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();

        System.out.println("##" + queryBuilder);
        //searchFunction(queryBuilder);

    }

    /**
     * 组合查询
     * must(QueryBuilders) :   AND
     * mustNot(QueryBuilders): NOT
     * should:                  : OR
     */
    @Test
    public void testQueryBuilder2() {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("user", "kimchy"))
                .mustNot(QueryBuilders.termQuery("message", "nihao"))
                .should(QueryBuilders.termQuery("gender", "male"));
        searchFunction(queryBuilder);
    }

    /**
     * 只查询一个id的
     * QueryBuilders.idsQuery(String...type).ids(Collection<String> ids)
     */
    @Test
    public void testIdsQuery() {
        QueryBuilder queryBuilder = QueryBuilders.idsQuery().ids("1");
        searchFunction(queryBuilder);
    }

    /**
     * 包裹查询, 高于设定分数, 不计算相关性
     */
    @Test
    public void testConstantScoreQuery() {
        QueryBuilder queryBuilder = QueryBuilders.constantScoreQuery(QueryBuilders.termQuery("name", "kimchy")).boost(2.0f);
        searchFunction(queryBuilder);
        // 过滤查询
//        QueryBuilders.constantScoreQuery(FilterBuilders.termQuery("name", "kimchy")).boost(2.0f);

    }

    /**
     * disMax查询
     * 对子查询的结果做union, score沿用子查询score的最大值,
     * 广泛用于muti-field查询
     */
    @Test
    public void testDisMaxQuery() {
        QueryBuilder queryBuilder = QueryBuilders.disMaxQuery()
                .add(QueryBuilders.termQuery("user", "kimch"))  // 查询条件
                .add(QueryBuilders.termQuery("message", "hello"))
                .boost(1.3f)
                .tieBreaker(0.7f);
        searchFunction(queryBuilder);
    }

    /**
     * 模糊查询
     * 不能用通配符, 不知道干啥用
     */
    @Test
    public void testFuzzyQuery() {
        QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("user", "kimch");
        searchFunction(queryBuilder);
    }

    /**
     * 父或子的文档查询
     */
    @Test
    public void testChildQuery() {
        QueryBuilder queryBuilder = QueryBuilders.hasChildQuery("sonDoc", QueryBuilders.termQuery("name", "vini"));
        searchFunction(queryBuilder);
    }

    /**
     * moreLikeThisQuery: 实现基于内容推荐, 支持实现一句话相似文章查询
     * {
     * "more_like_this" : {
     * "fields" : ["title", "content"],   // 要匹配的字段, 不填默认_all
     * "like_text" : "text like this one",   // 匹配的文本
     * }
     * }
     * <p>
     * percent_terms_to_match：匹配项（term）的百分比，默认是0.3
     * <p>
     * min_term_freq：一篇文档中一个词语至少出现次数，小于这个值的词将被忽略，默认是2
     * <p>
     * max_query_terms：一条查询语句中允许最多查询词语的个数，默认是25
     * <p>
     * stop_words：设置停止词，匹配时会忽略停止词
     * <p>
     * min_doc_freq：一个词语最少在多少篇文档中出现，小于这个值的词会将被忽略，默认是无限制
     * <p>
     * max_doc_freq：一个词语最多在多少篇文档中出现，大于这个值的词会将被忽略，默认是无限制
     * <p>
     * min_word_len：最小的词语长度，默认是0
     * <p>
     * max_word_len：最多的词语长度，默认无限制
     * <p>
     * boost_terms：设置词语权重，默认是1
     * <p>
     * boost：设置查询权重，默认是1
     * <p>
     * analyzer：设置使用的分词器，默认是使用该字段指定的分词器
     */
    @Test
    public void testMoreLikeThisQuery() {
        QueryBuilder queryBuilder = QueryBuilders.moreLikeThisQuery("user")
                .like("kimchy");
//                            .minTermFreq(1)         //最少出现的次数
//                            .maxQueryTerms(12);        // 最多允许查询的词语
        searchFunction(queryBuilder);
    }

    /**
     * 前缀查询
     */
    @Test
    public void testPrefixQuery() {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("user", "kimchy");
        searchFunction(queryBuilder);
    }

    /**
     * 查询解析查询字符串
     */
    @Test
    public void testQueryString() {
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("+kimchy");
        searchFunction(queryBuilder);
    }

    /**
     * 范围内查询
     */
    public void testRangeQuery() {
        QueryBuilder queryBuilder = QueryBuilders.rangeQuery("user")
                .from("kimchy")
                .to("wenbronk")
                .includeLower(true)     // 包含上界
                .includeUpper(true);      // 包含下届
        searchFunction(queryBuilder);
    }

    /**
     * 跨度查询
     */
    @Test
    public void testSpanQueries() {
        QueryBuilder queryBuilder1 = QueryBuilders.spanFirstQuery(QueryBuilders.spanTermQuery("name", "葫芦580娃"), 30000);     // Max查询范围的结束位置

        QueryBuilder queryBuilder2 = QueryBuilders.spanNearQuery()
                .clause(QueryBuilders.spanTermQuery("name", "葫芦580娃")) // Span Term Queries  
                .clause(QueryBuilders.spanTermQuery("name", "葫芦3812娃"))
                .clause(QueryBuilders.spanTermQuery("name", "葫芦7139娃"))
                .slop(30000)                                               // Slop factor  
                .inOrder(false)
                .collectPayloads(false);

        // Span Not
        QueryBuilder queryBuilder3 = QueryBuilders.spanNotQuery()
                .include(QueryBuilders.spanTermQuery("name", "葫芦580娃"))
                .exclude(QueryBuilders.spanTermQuery("home", "山西省太原市2552街道"));

        // Span Or   
        QueryBuilder queryBuilder4 = QueryBuilders.spanOrQuery()
                .clause(QueryBuilders.spanTermQuery("name", "葫芦580娃"))
                .clause(QueryBuilders.spanTermQuery("name", "葫芦3812娃"))
                .clause(QueryBuilders.spanTermQuery("name", "葫芦7139娃"));

        // Span Term  
        QueryBuilder queryBuilder5 = QueryBuilders.spanTermQuery("name", "葫芦580娃");
    }

    /**
     * 测试子查询
     */
    @Test
    public void testTopChildrenQuery() {
        QueryBuilders.hasChildQuery("tweet",
                QueryBuilders.termQuery("user", "kimchy"))
                .scoreMode("max");
    }

    /**
     * 通配符查询, 支持 *
     * 匹配任何字符序列, 包括空
     * 避免* 开始, 会检索大量内容造成效率缓慢
     */
    @Test
    public void testWildCardQuery() {
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("user", "ki*hy");
        searchFunction(queryBuilder);
    }

    /**
     * 嵌套查询, 内嵌文档查询
     */
    @Test
    public void testNestedQuery() {
        QueryBuilder queryBuilder = QueryBuilders.nestedQuery("location",
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("location.lat", 0.962590433140581))
                        .must(QueryBuilders.rangeQuery("location.lon").lt(36.0000).gt(0.000)))
                .scoreMode("total");

    }

    /**
     * 测试索引查询
     */
    @Test
    public void testIndicesQueryBuilder() {
        QueryBuilder queryBuilder = QueryBuilders.indicesQuery(
                QueryBuilders.termQuery("user", "kimchy"), "index1", "index2")
                .noMatchQuery(QueryBuilders.termQuery("user", "kimchy"));

    }


    /**
     * 查询遍历抽取
     *
     * @param queryBuilder
     */
    private void searchFunction(QueryBuilder queryBuilder) {
        SearchResponse response = client.prepareSearch("twitter")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setScroll(new TimeValue(60000))
                .setQuery(queryBuilder)
                .setSize(100).execute().actionGet();

        while (true) {
            response = client.prepareSearchScroll(response.getScrollId())
                    .setScroll(new TimeValue(60000)).execute().actionGet();
            for (SearchHit hit : response.getHits()) {
                Iterator<Entry<String, Object>> iterator = hit.getSource().entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> next = iterator.next();
                    System.out.println(next.getKey() + ": " + next.getValue());
                    if (response.getHits().hits().length == 0) {
                        break;
                    }
                }
            }
            break;
        }
//        testResponse(response);
    }

    /**
     * 对response结果的分析
     *
     * @param response
     */
    public void testResponse(SearchResponse response) {
        // 命中的记录数
        long totalHits = response.getHits().totalHits();

        for (SearchHit searchHit : response.getHits()) {
            // 打分
            float score = searchHit.getScore();
            // 文章id
            int id = Integer.parseInt(searchHit.getSource().get("id").toString());
            // title
            String title = searchHit.getSource().get("title").toString();
            // 内容
            String content = searchHit.getSource().get("content").toString();
            // 文章更新时间
            long updatetime = Long.parseLong(searchHit.getSource().get("updatetime").toString());
        }
    }

    /**
     * 对结果设置高亮显示
     */
    public void testHighLighted() {
        /*  5.0 版本后的高亮设置
         * client.#().#().highlighter(hBuilder).execute().actionGet();
        HighlightBuilder hBuilder = new HighlightBuilder();
        hBuilder.preTags("<h2>");
        hBuilder.postTags("</h2>");
        hBuilder.field("user");        // 设置高亮显示的字段
        */
        // 加入查询中
        SearchResponse response = client.prepareSearch("blog")
                .setQuery(QueryBuilders.matchAllQuery())
                .addHighlightedField("user")        // 添加高亮的字段
                .setHighlighterPreTags("<h1>")
                .setHighlighterPostTags("</h1>")
                .execute().actionGet();

        // 遍历结果, 获取高亮片段
        SearchHits searchHits = response.getHits();
        for (SearchHit hit : searchHits) {
            System.out.println("String方式打印文档搜索内容:");
            System.out.println(hit.getSourceAsString());
            System.out.println("Map方式打印高亮内容");
            System.out.println(hit.getHighlightFields());

            System.out.println("遍历高亮集合，打印高亮片段:");
            Text[] text = hit.getHighlightFields().get("title").getFragments();
            for (Text str : text) {
                System.out.println(str.string());
            }
        }
    }
}