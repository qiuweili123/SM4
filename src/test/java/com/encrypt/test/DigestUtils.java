/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: DigestUtils.java
 * @Package com.encrypt.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年6月16日 下午11:13:24
 * @version
 */
package com.encrypt.test;

import org.apache.commons.codec.digest.HmacUtils;

/**
 * @author liqiuwei
 * @create time:2016年6月16日下午11:13:24
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class DigestUtils {

    /**
     * @param args
     * @Description:TODO(这里用一句话描述这个方法的作用)
     * @return: void
     * @author:liqiuwei 2016年6月16日下午11:13:24
     * @update1:updater:liqiuwei updatetime:2016年6月16日下午11:13:24 TODO(描述修改内容)
     */
    public static void main(String[] args) {
        String cKey = "1234567890123456";
        // 需要加密的字串
        String cSrc = "Email : arix04@xxx.com";

        // TODO Auto-generated method stub
        long lStart = System.currentTimeMillis();
        String str;
        for (int i = 0; i < 100000; i++) {
            str = HmacUtils.hmacMd5Hex(cKey, cSrc);
        }

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
    }

}
