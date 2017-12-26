/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: PropertiesUtils.java
 * @Package com.java.properties
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年8月8日 上午10:37:45
 * @version
 */
package com.properties.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtils {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static Logger log = LoggerFactory.getLogger(PropertiesUtils.class);

    private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
    private static Properties properties;
    private static Properties currentProperties;

    public static Properties loadProperties(String[] resourcesPaths) throws IOException {
        Properties props = new Properties();


        for (String location : resourcesPaths) {
            log.debug("Loading properties file from:" + location);

            InputStream is = null;
            try {
                Resource resource = resourceLoader.getResource(location);
                is = resource.getInputStream();
                System.out.println("load ---");
                propertiesPersister.load(props, new InputStreamReader(is, "UTF-8"));
            } catch (IOException ex) {
                log.info("Could not load properties from classpath:" + location + ": " + ex.getMessage());
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }
        return props;
    }

    public static Properties load(String path) {
        Properties p = new Properties();
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream(path);
            p.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

    public static String getServer(String key) {
        try {
            if (properties == null) {
                properties = loadProperties(new String[]{"/conf/server.properties"});
            }
            return new String(properties.getProperty(key).getBytes("ISO8859_1"), "utf-8").trim();
        } catch (Exception localException) {
        }
        return "";
    }

    public static String getCurrentServer(String key) {
        try {
            if (currentProperties == null) {
                currentProperties = loadProperties(new String[]{"/conf/server.properties"});
            }
            String value = new String(currentProperties.getProperty(key).getBytes("ISO8859_1"), "utf-8").trim();
            log.debug(key + ":" + value);
            return value;
        } catch (Exception e) {
            log.error("读取 static.properties 配置文件失败.", e);
        }
        return "";
    }

    public static Integer getInt(String key, Integer defaultVal) {
        try {
            String valStr = getServer(key);
            return Integer.valueOf(Integer.parseInt(valStr.trim()));
        } catch (Exception localException) {
        }
        return defaultVal;
    }

    public static boolean getBoolean(String key, boolean defaultVal) {
        try {
            String valStr = getServer(key);
            return Boolean.parseBoolean(valStr.trim());
        } catch (Exception localException) {
        }
        return defaultVal;
    }

    public static String getInt(String key, String defaultVal) {
        try {
            return getServer(key).trim();
        } catch (Exception localException) {
        }
        return defaultVal;
    }
}