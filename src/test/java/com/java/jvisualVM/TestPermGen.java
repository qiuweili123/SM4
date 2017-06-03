package com.java.jvisualVM;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 类型装载之后会创建一个对应的java.lang.Class实例，这个实例本身和普通对象实例一样存储于堆中，我觉得之所以说是这是一种特殊的实例，某种程度上是因为其充当了访问PermGen区域中类型信息的代理者。
 * <p>
 * 运行一段时间后抛OutOfMemoryError了
 *
 * @author liqiuwei
 * @create time:2015年11月11日下午1:38:54
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class TestPermGen {

    private static List<Object> insList = new ArrayList<Object>();

    public static void main(String[] args) throws Exception {
        System.out.println("首先休眠10s");
        TimeUnit.SECONDS.sleep(10);
        permLeak();
        System.out.println("运行完成10s");
        TimeUnit.SECONDS.sleep(10);

    }

    private static void permLeak() throws Exception {
        for (int i = 0; i < 1000; i++) {
            URL[] urls = getURLS();
            URLClassLoader urlClassloader = new URLClassLoader(urls, null);
            Class<?> logfClass = Class.forName("org.apache.commons.logging.LogFactory", true, urlClassloader);
            Method getLog = logfClass.getMethod("getLog", String.class);
            Object result = getLog.invoke(logfClass, "TestPermGen");
            insList.add(result);
            System.out.println(i + ": " + result);
        }
    }

    private static URL[] getURLS() throws MalformedURLException {
        File libDir = new File("D:/.m2/repository/commons-logging/commons-logging/1.2");
        File[] subFiles = libDir.listFiles();
        int count = subFiles.length;
        URL[] urls = new URL[count];
        for (int i = 0; i < count; i++) {
            urls[i] = subFiles[i].toURI().toURL();
            System.out.println("url==" + urls[i]);
        }
        return urls;
    }


}