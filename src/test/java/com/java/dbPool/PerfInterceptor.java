package com.java.dbPool;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class PerfInterceptor implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PerfInterceptor.class);

    private static ConcurrentHashMap<String, MethodStats> methodStats = new ConcurrentHashMap<String, MethodStats>();

    public static Enumeration<MethodStats> getMethodStats() {
        return methodStats.elements();
    }

    public static void clearMethodStats() {
        methodStats.clear();
    }

    public static void printMethodStats() {

        Enumeration<MethodStats> e = methodStats.elements();


        while (e.hasMoreElements()) {
            logger.info(e.nextElement().toString());
        }
    }  
      
    /*  
     * performance monitor for service layer 
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation) 
     */

    public Object invoke(MethodInvocation method) throws Throwable {
        long start = System.nanoTime();
        try {
            return method.proceed();
        } finally {
            updateStats(method.getMethod().getName(), (System.nanoTime() - start));
        }
    }

    private void updateStats(String methodName, long elapsedTime) {
        MethodStats stats = methodStats.get(methodName);
        if (stats == null) {
            stats = new MethodStats(methodName);
            methodStats.put(methodName, stats);
        }
        stats.count++;
        stats.totalTime += elapsedTime;
        if (elapsedTime > stats.maxTime) {
            stats.maxTime = elapsedTime;
        }

        if (elapsedTime < stats.minTime || 0 == stats.minTime) {
            stats.minTime = elapsedTime;
        }

    }

    class MethodStats {
        public String methodName;
        public long count;
        public long totalTime;
        public long maxTime;
        public long minTime;

        public MethodStats(String methodName) {
            this.methodName = methodName;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("method: ");
            sb.append(methodName).append("(), cnt = ").append(count).append(", avgTime = ").append(totalTime / count).append(", maxTime = ").append(maxTime).append(", minTime = ").append(minTime);
            return sb.toString();
        }
    }
}  