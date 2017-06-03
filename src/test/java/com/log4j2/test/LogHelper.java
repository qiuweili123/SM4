package com.log4j2.test;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LogHelper {
    // 此处使用的是log4j的1.0的版本
    static {
        DOMConfigurator.configure("./config/log4j.xml");
    }

    public static void info(Object msg) {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();

        Logger logger = Logger.getLogger(stack[1].getClassName());
        logger.log(LogHelper.class.getName(), Level.INFO, msg, null);
    }

    public static void error(Object msg) {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();

        Logger logger = Logger.getLogger(stack[1].getClassName());
        logger.log(LogHelper.class.getName(), Level.ERROR, msg, null);
    }

    public static void debug(Object msg) {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();

        Logger logger = Logger.getLogger(stack[1].getClassName());
        logger.log(LogHelper.class.getName(), Level.DEBUG, msg, null);
    }

    public static void warn(Object msg) {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();

        Logger logger = Logger.getLogger(stack[1].getClassName());
        logger.log(LogHelper.class.getName(), Level.WARN, msg, null);
    }

    public static void exception(Exception e) {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();

        Logger logger = Logger.getLogger(stack[1].getClassName());
        logger.log(LogHelper.class.getName(), Level.ERROR, ExceptionUtil.toString(e), null);
    }
}
