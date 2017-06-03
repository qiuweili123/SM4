/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: AboutArgs.java
 * @Package com.java.basic
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年4月9日 下午11:36:12
 * @version
 */
package com.java.basic;

/**
 * @author liqiuwei
 * @create time:2016年4月9日下午11:36:12
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class AboutArgs {
    public static void main(String[] args) {
        for (int i = 0, l = args.length; i < l; i++) {
            String arg = args[i].trim();
            int split = arg.indexOf('=');
            System.out.println(arg.substring(1, split) + "##" + arg.substring(split + 1));
        }
    }

}
