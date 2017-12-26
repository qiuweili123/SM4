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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.Collection;

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
    }
}
