/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: HashCodeTest.java
 * @Package com.java.strToNum
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年5月9日 上午9:38:44
 * @version
 */
package com.java.strToNum;

/**
 * @author liqiuwei
 * @create time:2016年5月9日上午9:38:44
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class HashCodeTest {

    public static void main(String[] args) {
        String str = "hello0";
        long hashCode = str.hashCode();
        System.out.println("##" + hashCode + "##" + (hashCode & 0xffffffffL));/* Convert to unsigned 32-bits */
    }
}
