/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: AbstractDataSourceTest.java
 * @Package com.test.datasouce
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2015年11月24日 下午3:03:40
 * @version
 */
package com.test.datasouce;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Timer;

/**
 * @author liqiuwei
 * @create time:2015年11月24日下午3:03:40
 * @Description:TODO各类数据源连接新能测试
 */
public abstract class AbstractDataSourceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDataSourceTest.class);

    private static final int MAX_ITERATIONS = 1000;

    private Slf4jReporter logReporter;

    private Timer timer;

    protected abstract DataSource getDataSource();

    @Before
    public void init() {
        MetricRegistry metricRegistry = new MetricRegistry();
        this.logReporter = Slf4jReporter.forRegistry(metricRegistry).outputTo(LOGGER).build();
        timer = metricRegistry.timer("connection");
    }

    @Test
    public void testOpenCloseConnections() throws SQLException {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            Timer.Context context = timer.time();
            getDataSource().getConnection().close();
            context.stop();
        }
        logReporter.report();
    }
}
