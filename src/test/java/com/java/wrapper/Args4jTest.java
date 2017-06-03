/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: Args4jTest.java
 * @Package com.java.wrapper
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年4月27日 下午1:14:19
 * @version
 */
package com.java.wrapper;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * @author liqiuwei
 * @create time:2016年4月27日下午1:14:19
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class Args4jTest {
    public static final Args myArgs = new Args();

    public static void main(String[] args) {
        CmdLineParser parser = new CmdLineParser(myArgs);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
