package com.guava.test;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.PrimitiveSink;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class BloomFilterTest {
   static   BloomFilter<String> bloomFilter = BloomFilter.create(new Funnel<String>() {
        @Override
        public void funnel(String s, PrimitiveSink primitiveSink) {
            primitiveSink.putString(s,Charset.forName("UTF-8"));
        }
    }, 1000, 0.000001);

    public static void main(String[] args) {

       bloomFilter.put("hello");
        bloomFilter.put("www");
        bloomFilter.put("nn");
        bloomFilter.put("我是");

        System.out.printf(bloomFilter.apply("我是")+"contains :: "+bloomFilter.mightContain("我是"));


    }
    //模拟防止redis缓存击穿
    public   String get(String key){
        Map<String,String> redis=new HashMap<>();//模拟redisDao

        String  value = redis.get("name");
        if(value==null){
             if (!bloomFilter.mightContain(key)){
                 return null;
             }
             else{
                  //db.get();
                 value="get from db";
                 redis.put(key,"get from db");

             }
        }

         return value;
    }
}