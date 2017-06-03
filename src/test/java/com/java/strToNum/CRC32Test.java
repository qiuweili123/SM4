/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: CRC32Test.java
 * @Package com.java.strToNum
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年5月7日 下午2:30:49
 * @version
 */
package com.java.strToNum;

import java.util.zip.CRC32;

/**
 * @author liqiuwei
 * @create time:2016年5月7日下午2:30:49
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class CRC32Test {
    public static void main(String[] args) {
        CRC32 c = new CRC32();
        String str = "北京";
        c.update(str.getBytes());
        System.out.println(c.getValue());
    }

}
