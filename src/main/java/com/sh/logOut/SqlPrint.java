package com.sh.logOut;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.StdoutLogger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlPrint extends StdoutLogger {
    @Override
    public void logSQL(int connectionId, String now, long elapsed, Category category, String prepared, String sql) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            if (!"".equals(sql)) {
                System.out.println(dateFormat.format(new Date()) + "|" + sql);
            } else {
                System.out.println("--------------------------------------------------");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
