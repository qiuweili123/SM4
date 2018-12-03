package com.encrypt.test.aes256.bouncycastle;

import com.encrypt.test.aes256.TypeConvert;
import org.apache.commons.codec.digest.DigestUtils;

public class BouncycastleTest {

    public static void main(String[] args) {
        String plainText = "待加密的明文";
        byte[] key = DigestUtils.sha256("abcdabcdabcdabcd".getBytes());
        System.out.println(key.length);
        byte[] INITIAL_VECTOR = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
        };

        //when
        byte[] encrypt = RijndaelEncrypt.encrypt(plainText.getBytes(), key, INITIAL_VECTOR);

        System.out.println(TypeConvert.bytesToHexString(encrypt));
    }
}
