package com.encrypt.test.aes256;

import java.security.Key;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;

import javax.crypto.KeyGenerator;

import javax.crypto.SecretKey;

import javax.crypto.spec.SecretKeySpec;

 

public class AESCoder {

       

   

    private static final String KEY_ALGORITHM = "AES";

       

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

       

   

    public static byte[] initSecretKey() {

        //返回生成指定算法的秘密密钥的 KeyGenerator 对象

        KeyGenerator kg = null;

        try {

            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

            return new byte[0];

        }

        //初始化此密钥生成器，使其具有确定的密钥大小

        //AES 要求密钥长度为 128

        kg.init(256);

        //生成一个密钥

        SecretKey  secretKey = kg.generateKey();

        return secretKey.getEncoded();

    }

       

   

    private static Key toKey(byte[] key){

        //生成密钥

        return new SecretKeySpec(key, KEY_ALGORITHM);

    }

       

   

    public static byte[] encrypt(byte[] data,Key key) throws Exception{

        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);

    }

       

   

    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{

        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);

    }

       

       

   

    public static byte[] encrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{

        //还原密钥

        Key k = toKey(key);

        return encrypt(data, k, cipherAlgorithm);

    }

       

   

    public static byte[] encrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{

        //实例化

        Cipher cipher = Cipher.getInstance(cipherAlgorithm);

        //使用密钥初始化，设置为加密模式

        cipher.init(Cipher.ENCRYPT_MODE, key);

        //执行操作

        return cipher.doFinal(data);

    }

       

       

       

   

    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{

        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);

    }

       

   

    public static byte[] decrypt(byte[] data,Key key) throws Exception{

        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);

    }

       

   

    public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{

        //还原密钥

        Key k = toKey(key);

        return decrypt(data, k, cipherAlgorithm);

    }

   

    public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{

        //实例化

        Cipher cipher = Cipher.getInstance(cipherAlgorithm);

        //使用密钥初始化，设置为解密模式

        cipher.init(Cipher.DECRYPT_MODE, key);

        //执行操作

        return cipher.doFinal(data);

    }

       

    private static String  showByteArray(byte[] data){

        if(null == data){

            return null;

        }

        StringBuilder sb = new StringBuilder("{");

        for(byte b:data){

            sb.append(b).append(",");

        }

        sb.deleteCharAt(sb.length()-1);

        sb.append("}");

        return sb.toString();

    }

       

    public static void main(String[] args) throws Exception {

      //  byte[] key = initSecretKey();
        byte[] key = "abcdabcdabcdabcd".getBytes();//initSecretKey();
        System.out.println("key："+TypeConvert.bytesToHexString(key));

           

        Key k = toKey(key);

           

        String data ="待加密的明文";

        System.out.println("加密前数据: string:"+data);

        System.out.println("加密前数据: byte[]:"+showByteArray(data.getBytes()));

        System.out.println();

        byte[] encryptData = encrypt(data.getBytes(), k);

        System.out.println("加密后数据: byte[]:"+TypeConvert.bytesToHexString(encryptData));

//        System.out.println("加密后数据: hexStr:"+Hex.encodeHexStr(encryptData));

        System.out.println();

        byte[] decryptData = decrypt(encryptData, k);

        System.out.println("解密后数据: byte[]:"+showByteArray(decryptData));

        System.out.println("解密后数据: string:"+new String(decryptData));

           

    }

 

}