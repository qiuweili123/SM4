package com.java.basic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Created by lenovo on 2017/11/30.
 */
public class AboutUUid {
    public static final int TIME_LENGTH = 9;
    public static final long TIME_MOD = (long) Math.pow(10, TIME_LENGTH);

    public static void main(String[] args) {
        System.out.println(TIME_MOD);
        System.out.println(System.currentTimeMillis());
        System.out.println(String.valueOf((System.currentTimeMillis() / 1000) % TIME_MOD));
        int uniqueId = (int) (System.currentTimeMillis() & 0xfffff);
        System.out.println(uniqueId);

        System.out.println(generateUniqueId());
        System.out.println(g2());

    }

    public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }

    public static String g2() {
        LocalDateTime localDateAndTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");
        String uid = localDateAndTime.format(formatter);
        return uid;
    }
}
