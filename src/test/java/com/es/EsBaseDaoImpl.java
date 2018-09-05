package com.es;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("esBasedao")
public class EsBaseDaoImpl<T> {


    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;


    /**
     * 插入或更新
     *
     * @param
     * @return
     */
    public boolean insertOrUpdate(String id, String string) {

        try {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(id).withObject(string).build();
            elasticsearchTemplate.index(indexQuery);
            return true;
        } catch (Exception e) {

            return false;
        }
    }


}