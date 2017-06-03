/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: HelloWorld.java
 * @Package com.java.wrapper
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年4月14日 下午5:38:35
 * @version
 */
package com.java.wrapper;

import java.util.concurrent.TimeUnit;

/**
 * @author liqiuwei
 * @create time:2016年4月14日下午5:38:35
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class HelloWorld {
    public final static int _1M = 1024 * 1024;

    public static void main(String[] args) {
        String str;
        StringBuffer tempOOM = new StringBuffer();
        for (String s : args) {
            System.out.println("##args=" + s);

        }


        System.out.println("休眠后");
        int i = 0;
        while (i < 300000000) {
            i++;
            try {
                tempOOM.append("a");
            } catch (OutOfMemoryError e) {
                System.out.println("OutOfMemoryError后");
                e.printStackTrace();
                break;
            }
        }
        str = tempOOM.toString();
        System.out.println(i + "分配后");
    /*  Args myArgs = new Args();
        CmdLineParser parser = new CmdLineParser(myArgs);
	    try {
			parser.parseArgument(args);
			 
			parser.printUsage(System.out);
		} catch (CmdLineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
