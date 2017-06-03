import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;
import org.perf4j.aop.Profiled;

/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TestPerf4j.java
 * @Package
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2015年11月3日 上午11:34:18
 * @version
 */

/**
 * @author liqiuwei
 * @create time:2015年11月3日上午11:34:18
 * @Description:TODO(这里用一句话描述这个类的作用) 简洁的 stop watch计时机制。
 * 提供命令行工具，从原始的日志文件中生成汇总的统计数据和性能图表。
 * 定制的log4j appender，可以在运行时应用中生成数据和图表，计划在以后的版本中支持java.util.logging和logback。
 * 能够以JMX属性的形式发布性能数据，在数据超过指定阈值时发送通知。
 * 提供@Profiled注解和一套自定义机制，允许在与AOP框架（如AspectJ或者Spring AOP）集成时巧妙的计时。
 * lap在一个方法体中，分段进行测试耗时
 * 具体详见
 * http://www.ibm.com/developerworks/cn/java/j-lo-logforperf/
 */
public class TestPerf4j {

    public static void main(String[] args) {

        testMethod();
    }

    @Profiled
    public static void testMethod() {
        StopWatch stopWatch = new LoggingStopWatch();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        stopWatch.lap("时间段1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        stopWatch.lap("时间段3");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        stopWatch.stop("时间段2");

    }
}
