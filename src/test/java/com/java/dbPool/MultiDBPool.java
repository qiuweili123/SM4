package com.java.dbPool;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 在BoneCP的基础上再封装了一层
 * 支持多数据库
 * 支持master,slave随机获取
 * 支持动态更新，当配置文件改变后会自动重新加载
 *
 * @author liqiuwei
 * @create time:2015年11月10日下午11:59:37
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class MultiDBPool {

    public static final String DEFAULT_CONFIG_FILE = "dbpool.properties";
    private static final ConcurrentMap<String, PoolGroup> poolGroups = new ConcurrentHashMap<String, PoolGroup>(5);
    private static MultiDBPool pool;
    private String conf = DEFAULT_CONFIG_FILE;
    private File configFile;
    private AtomicLong lastModified = new AtomicLong();
    private AtomicBoolean isLoading = new AtomicBoolean();

    private MultiDBPool() throws Exception {
        loadConfig(conf);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                destory();
            }
        });
    }

    private MultiDBPool(String conf) throws Exception {
        this.conf = conf;
        loadConfig(conf);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                destory();
            }
        });
    }

    private MultiDBPool(Properties properties) {
        intiMultiPool(properties);
    }

    public static synchronized MultiDBPool getPool() throws Exception {
        if (pool == null) {
            pool = new MultiDBPool();
        }
        return pool;
    }

    public static synchronized MultiDBPool getPool(String conf) throws Exception {
        if (pool == null) {
            pool = new MultiDBPool(conf);
        }
        return pool;
    }

    public static void main(String... args) throws Exception {
        MultiDBPool pool = MultiDBPool.getPool();
        for (int i = 0; i < 10000; i++) {
            Connection con = pool.getSlaveConnection("mediaresource");
            try {
                Statement stat = con.createStatement();
                stat.execute("select 1;");
                stat.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void check() throws Exception {
        if (configFile == null || configFile.lastModified() != lastModified.get()) {
            while (isLoading.get()) {
                synchronized (isLoading) {
                    try {
                        isLoading.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }

            }
            synchronized (isLoading) {
                loadConfig(conf);
                isLoading.notifyAll();
            }
        }
    }

    private synchronized void destory() {
        for (PoolGroup pg : poolGroups.values()) {
            pg.close();
        }
        poolGroups.clear();
    }

    private void loadConfig(String conf2) throws Exception {
        if (isLoading.get()) {
            return;
        }
        FileInputStream in = null;
        try {
            isLoading.set(true);
            URL url = MultiDBPool.class.getClassLoader().getResource(conf2);
            if (url == null) {
                throw new Exception(conf2 + " not found");
            }
            destory();
            configFile = new File(url.getFile());
            Properties p = new Properties();

            in = new FileInputStream(configFile);
            p.load(in);
            intiMultiPool(p);
            lastModified.set(configFile.lastModified());
        } catch (IOException e) {
            throw new Exception("IO  exception");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            isLoading.set(false);
        }
    }

    private void intiMultiPool(Properties prop) {
        Set<String> names = new HashSet<String>();
        for (String key : prop.stringPropertyNames()) {
            int p = key.indexOf(".");
            if (p > 0) {
                names.add(key.substring(0, p));
            }
        }
        for (String name : names) {
            Properties p = new Properties();
            for (Entry<Object, Object> e : prop.entrySet()) {
                String key = e.getKey().toString();
                String value = e.getValue().toString();
                if (key.startsWith(name)) {
                    p.setProperty(key.replaceFirst(name + ".", ""), value);
                }
            }
            poolGroups.put(name, new PoolGroup(name, p));
            System.out.print(name);
            p.list(System.out);
        }
    }

    public Connection getConnection(String dbname) throws Exception {
        return getMasterConnection(dbname);
    }

    public Connection getMasterConnection(String dbname) throws Exception {
        check();
        PoolGroup pg = poolGroups.get(dbname);
        if (pg == null) {
            throw new Exception("Database " + dbname + " not config");
        }
        BoneCP bcp = pg.getMaster();
        if (bcp == null) {
            throw new Exception("Database " + dbname + " master not config");
        }

        try {
            return bcp.getConnection();
        } catch (SQLException e) {
            throw new Exception("##  Database ");
        }
    }

    public Connection getSlaveConnection(String dbname) throws Exception {
        check();
        PoolGroup pg = poolGroups.get(dbname);
        if (pg == null) {
            throw new Exception("Database " + dbname + " not config");
        }
        BoneCP bcp = pg.getSlave();
        if (bcp == null) {
            return getMasterConnection(dbname);
        }

        try {
            return bcp.getConnection();
        } catch (SQLException e) {
            throw new Exception("Database ");
        }
    }

    class PoolGroup {
        String name;
        List<BoneCP> masters;
        List<BoneCP> slaves;
        Properties prop;
        AtomicBoolean isLoad = new AtomicBoolean();
        Random r = new Random();

        PoolGroup(String name, Properties p) {
            this.name = name;
            this.prop = p;
        }

        BoneCP getMaster() throws Exception {
            if (!isLoad.get()) {
                initGroup();
            }
            int size = masters.size();
            if (size == 1) {
                return masters.get(0);
            }
            if (size > 1) {
                // System.out.println("Master:"+r.nextInt(size));
                return masters.get(r.nextInt(size));
            }
            return null;
        }

        BoneCP getSlave() throws Exception {
            if (slaves == null) {
                initGroup();
            }
            int size = slaves.size();
            if (size == 1) {
                return slaves.get(0);
            }
            if (size > 1) {
                // System.out.println("Slave:"+r.nextInt(size));
                return slaves.get(r.nextInt(size));
            }
            return getMaster();
        }

        synchronized void initGroup() throws Exception {
            if (isLoad.get()) {
                return;
            }
            masters = new ArrayList<BoneCP>(2);
            slaves = new ArrayList<BoneCP>(10);
            String servers = prop.getProperty("servers");
            if (servers != null && servers.trim().length() > 0) {

                String ss[] = servers.split("[, ]");
                for (String server : ss) {
                    String s[] = server.trim().split("[:]", 3);
                    if (s.length < 2) {
                        return;
                    }
                    String url = prop.getProperty("jdbcUrl").replace("{server}", s[0] + ":" + s[1]);
                    prop.setProperty("jdbcUrl", url);
                    try {
                        BoneCPConfig config = new BoneCPConfig(prop);
                        Class.forName(prop.getProperty("driverClass"));
                        if (s.length > 2) {
                            if ("m".equalsIgnoreCase(s[2])) {
                                masters.add(new BoneCP(config));
                            }
                            if ("s".equalsIgnoreCase(s[2])) {
                                slaves.add(new BoneCP(config));
                            }
                        } else {
                            masters.add(new BoneCP(config));
                        }

                    } catch (Exception e) {
                        throw new Exception("异常信息");
                    }
                }
            }
            isLoad.set(true);
        }

        void close() {
            if (masters != null) {
                for (BoneCP bcp : masters) {
                    bcp.shutdown();
                }
                masters.clear();
            }
            if (slaves != null) {
                for (BoneCP bcp : slaves) {
                    bcp.shutdown();
                }
                slaves.clear();
            }
            if (prop != null) {
                prop.clear();
            }
        }

        @Override
        public String toString() {
            return String.format("PoolGroup [name=%s, prop=%s]", name, prop);
        }

    }
}