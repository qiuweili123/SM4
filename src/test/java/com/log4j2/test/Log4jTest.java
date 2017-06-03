/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: BaseTest.java
 * @Package com.log4j2.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年5月11日 下午6:15:37
 * @version
 */
package com.log4j2.test;

import org.junit.Test;

/**
 * @author liqiuwei
 * @create time:2016年5月11日下午6:15:37 测试log4j2的日志输出格式。通过设置加载指定位置的配置文件
 * -Dlog4j.configurationFile=path/ to /log4j2.xml <br>
 * log4j使用规范
 */
public class Log4jTest extends BaseLog {

    // private static final Logger log =
    // LoggerFactory.getLogger(Log4jTest.class);
    // private static final Log log = Log.getLogger(Log4jTest.class);

    @Test
    public void testSomeMethod() {
        // System.setProperty("log4j.configurationFile", "conf/log4j2.xml");
    /*
     * System.out.println("sdd"); getLogger().info("yyyddd试试");
	 * getLogger().error("error");
	 */
        // 文本信息输出
        getLogger().error("error1:{},error2:{}.", "111", "2222");
        // MDC.put("clientNumber", "456");
        getLogger().info("new log");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sddddddd");
        try {
            throw new RuntimeException("msg,clientNumber=123");
        } catch (Exception e) {
            getLogger().error("messtext：{} ", "错误异常", e);

        } finally {
            // MDC.clear();
        }

        getLogger().trace("This will not show because the default logging level is ERROR	and the resources/log4j2_conf.xml is not loaded");

    }

}
