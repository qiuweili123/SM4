package com.encrypt.test.aes256;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Aes2 {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    public static void main(String[] args) throws Exception {


        byte[] key= new byte[32];
        byte[] iv = new byte[16];

        String srcStr = "This is java default pkcs5padding PKCS5 TEST";
        System.out.println(srcStr);

        //设置key 全8，iv，全1，这里测试用
        for (int i = 0; i <32; i++) {
            key[i] = 8;
            if (i < 16) {iv[i] = 1;}
        }


        byte[] encbt = AES_cbc_encrypt(srcStr.getBytes(),key,iv);
        byte[] decbt = AES_cbc_decrypt(encbt,key,iv);
        String decStr = new String(decbt);
        System.out.println(decStr);

        if(srcStr.equals(decStr))
        {
            System.out.println("TEST PASS");
        }else
        {
            System.out.println("TEST NO PASS");
        }

    }
    //加密
    public static byte[] AES_cbc_encrypt(byte[] srcData,byte[] key,byte[] iv) throws  Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] encData = cipher.doFinal(srcData);
        return encData;
    }

    //解密
    public static byte[] AES_cbc_decrypt(byte[] encData,byte[] key,byte[] iv) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] decbbdt = cipher.doFinal(encData);
        return decbbdt;
    }
}
