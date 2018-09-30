package com.logBack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackTest {
    static Logger logger = LoggerFactory.getLogger(LogBackTest.class);

    public static void main(String[] args) {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
