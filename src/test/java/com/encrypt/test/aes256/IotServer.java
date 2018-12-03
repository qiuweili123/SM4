package com.encrypt.test.aes256;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Arrays;

public class IotServer {

    /*  public static final byte[] DEFAULT_KEY = { (byte) 0xF3, (byte) 0x62,
              (byte) 0x12, (byte) 0x05, (byte) 0x13, (byte) 0xE3, (byte) 0x89,
              (byte) 0xFF, (byte) 0x23, (byte) 0x11, (byte) 0xD7, (byte) 0x36,
              (byte) 0x01, (byte) 0x23, (byte) 0x10, (byte) 0x07, (byte) 0x05,
              (byte) 0xA2, (byte) 0x10, (byte) 0x00, (byte) 0x7A, (byte) 0xCC,
              (byte) 0x02, (byte) 0x3C, (byte) 0x39, (byte) 0x01, (byte) 0xDA,
              (byte) 0x2E, (byte) 0xCB, (byte) 0x12, (byte) 0x44, (byte) 0x8B };
      */
    public static byte[] DEFAULT_KEY;

    static {
        try {
            DEFAULT_KEY =     DigestUtils.sha512("abcdabcdabcdabcd".getBytes("UTF-8")); //"abcdabcdabcdabcd".getBytes("UTF-8");

            System.out.println(DEFAULT_KEY.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static final byte[] AES_IV = null;/*{ 0x15, (byte) 0xFF, 0x01, 0x00, 0x34,
            (byte) 0xAB, 0x4C, (byte) 0xD3, 0x55, (byte) 0xFE, (byte) 0xA1,
            0x22, 0x08, 0x4F, 0x13, 0x07 };*/
    private static final String transform = "AES/ECB/PKCS5Padding";
    private static final String algorithm = "AES";
    private static final SecretKeySpec keySpec = new SecretKeySpec(DEFAULT_KEY, algorithm);

    public static void main(String[] args) throws Exception {

/*

         KeyGenerator kgen = KeyGenerator.getInstance("AES");
        //此处解决mac，linux报错
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(DEFAULT_KEY);
        kgen.init(256, random);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance(transform);// 创建密码器
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
    byte[] result = cipher.doFinal("待加密的明文".getBytes("UTF-8"));
        System.out.println(TypeConvert.bytesToHexString(result));
*/

        Cipher cipher = Cipher.getInstance(transform);
        System.out.println("key::" + new String(DEFAULT_KEY)+"##size=="+cipher.getBlockSize());
        // cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(AES_IV));
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] cipherData = cipher.doFinal("待加密的明文".getBytes("UTF-8"));
        System.out.println(Arrays.toString(cipherData));
        System.out.println(TypeConvert.bytesToHexString(cipherData));
    }

}