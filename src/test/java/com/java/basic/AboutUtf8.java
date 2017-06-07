package com.java.basic;

import java.io.UnsupportedEncodingException;

/**
 * Created by lenovo on 2017/6/6.
 */
public class AboutUtf8 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String t = "你好";
        String utf8 = new String(t.getBytes("UTF-8"));
        System.out.println(utf8);
        String unicode = new String(utf8.getBytes(), "UTF-8");
        System.out.println(unicode);
        String gbk = new String(unicode.getBytes("UTF-8"), "GBK");
        System.out.println(gbk);
        String utfd = new String(gbk.getBytes("GBK"), "UTF-8");
        System.out.println(utfd);
    }
}
