/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: MultiMapTest.java
 * @Package com.guava.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: ard-liqiuwei
 * @date: 2017年2月22日 下午8:58:55
 * @version
 */
package com.guava.test;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;

/**
 * @author ard-liqiuwei
 * @create time:2017年2月22日下午8:58:55
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class MultiMapTest {
    @Test
    public void teststuScoreMultimap() {
        Multimap<String, StudentScore> scoreMultimap = ArrayListMultimap.create();
        for (int i = 10; i < 20; i++) {
            StudentScore studentScore = new StudentScore();
            studentScore.CourseId = 1001 + i;
            studentScore.score = 100 - i;
            scoreMultimap.put("peida", studentScore);
        }
        System.out.println("scoreMultimap:" + scoreMultimap.size());
        System.out.println("scoreMultimap:" + scoreMultimap.values());
        Collection<StudentScore> studentScore = scoreMultimap.get("peida");
        studentScore.clear();
        StudentScore studentScoreNew = new StudentScore();
        studentScoreNew.CourseId = 1034;
        studentScoreNew.score = 67;
        studentScore.add(studentScoreNew);

        System.out.println("scoreMultimap:" + scoreMultimap.size());
        System.out.println("scoreMultimap:" + scoreMultimap.keys());

        ImmutableMap<String,Integer> oneMap=ImmutableMap.of("key1",1,"key2",2);
        ImmutableMap<String,Integer> twoMap=ImmutableMap.of("key11",11,"key2",2);
        MapDifference<String,Integer> diffHadle=Maps.difference(oneMap,twoMap);
        Map<String,Integer> commonMap=diffHadle.entriesInCommon();//{"key2",2},若无共同Entry，则返回长度为0的Map

        System.out.println(commonMap);
        Map<String,Integer> diffOnLeft=diffHadle.entriesOnlyOnLeft();//返回左边的Map中不同或者特有的元素
        Map<String,Integer> diffOnRight=diffHadle.entriesOnlyOnRight();//返回右边的Map中不同或者特有的元素
        for(Map.Entry<String, Integer> entry:diffOnRight.entrySet()){
            System.out.println("共同Map中key:"+entry.getKey()+"  value:"+entry.getValue());
        }

        Multimap<String,String> map2= ArrayListMultimap.create();
        map2.put("testKey","hello");
        map2.put("testKey","hello");
        System.out.println("##map=="+map2.get("testKey").size());



    }
}
