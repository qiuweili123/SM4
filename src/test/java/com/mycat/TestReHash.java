/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TestReHash.java
 * @Package com.mycat
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年4月10日 上午11:37:25
 * @version
 */
package com.mycat;

/**
 * @author liqiuwei
 * @create time:2016年4月10日上午11:37:25
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class TestReHash {

    public static void main(String[] args) {
        String[] strArry = new String[100];
        int oldCnt = 12;//原分片数量
        String[] oldArray = new String[oldCnt];
        for (int i = 0; i < 100; i++) {
            strArry[i] = String.format("%02d", i);
            oldArray[i % oldCnt] = (oldArray[i % oldCnt] == null ? "" : oldArray[i % oldCnt] + ",") + strArry[i];
        }
        for (int i = 0; i < oldCnt; i++) {
            System.out.println("分片:" + i + "-->" + oldArray[i]);
        }


    }
}
