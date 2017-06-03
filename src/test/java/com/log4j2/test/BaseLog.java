/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: BaseLog.java
 * @Package com.log4j2.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: ard-liqiuwei
 * @date: 2017年2月6日 下午2:31:38
 * @version
 */
package com.log4j2.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ard-liqiuwei
 * @create time:2017年2月6日下午2:31:38
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class BaseLog<T> {
    private final Logger logger;
    private Class<T> classz;

    BaseLog() {
        // classz = ((Class) ((ParameterizedType)
        // (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
        logger = LoggerFactory.getLogger(getClass());

    }

    /**
     * @return the logger
     */
    public Logger getLogger() {
        return this.logger;
    }

}
