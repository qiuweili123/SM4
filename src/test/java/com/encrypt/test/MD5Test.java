package com.encrypt.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Test {

    //静态方法，便于作为工具类  
    public static String getMd5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密  
            return buf.toString();
            // 16位的加密  
            //return buf.toString().substring(8, 24);  
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        String cSrc = "Email : arix04@xxx.com";
        long lStart = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            MD5Test.getMd5(cSrc);
        }
        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        //测试      
        System.out.println(MD5Test.getMd5("hello"));
    }
}
  