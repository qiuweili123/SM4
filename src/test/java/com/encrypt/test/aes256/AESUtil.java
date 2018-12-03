package com.encrypt.test.aes256;

import java.io.UnsupportedEncodingException;
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
    /**
     * 在线助手|在线工具|在线生成|在线制作
     * http://www.it399.com/
     * 在线助手博客
     * http://www.it399.com/blog/index
     */
    public class AESUtil {
        public static final String CHARSET = "UTF-8";
        public static final byte[] DEFAULT_KEY = { (byte) 0xF3, (byte) 0x62,
                (byte) 0x12, (byte) 0x05, (byte) 0x13, (byte) 0xE3, (byte) 0x89,
                (byte) 0xFF, (byte) 0x23, (byte) 0x11, (byte) 0xD7, (byte) 0x36,
                (byte) 0x01, (byte) 0x23, (byte) 0x10, (byte) 0x07, (byte) 0x05,
                (byte) 0xA2, (byte) 0x10, (byte) 0x00, (byte) 0x7A, (byte) 0xCC,
                (byte) 0x02, (byte) 0x3C, (byte) 0x39, (byte) 0x01, (byte) 0xDA,
                (byte) 0x2E, (byte) 0xCB, (byte) 0x12, (byte) 0x44, (byte) 0x8B };
        private static final byte[] AES_IV = { 0x15, (byte) 0xFF, 0x01, 0x00, 0x34,
                (byte) 0xAB, 0x4C, (byte) 0xD3, 0x55, (byte) 0xFE, (byte) 0xA1,
                0x22, 0x08, 0x4F, 0x13, 0x07 };

        private static byte[] encryptOrDecrypt(int mode, byte[] byteContent, String key, byte[] iv, AESType type, String modeAndPadding) throws InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {


            SecretKeySpec secretKeySpec = new SecretKeySpec(DEFAULT_KEY, "AES");
            IvParameterSpec paramSpec = new IvParameterSpec(AES_IV);
            Cipher ecipher;
            ecipher = Cipher.getInstance("AES/CFB/NOPADDING");
            ecipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, paramSpec);
            byte[] result = ecipher.doFinal(byteContent);
            return result;
            /*
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //此处解决mac，linux报错
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
            kgen.init(type.value, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance(modeAndPadding);// 创建密码器
            if (null != iv) {
                //指定一个初始化向量 (Initialization vector，IV)， IV 必须是16位
                cipher.init(mode, keySpec, new IvParameterSpec(iv));
            } else {
                cipher.init(mode, keySpec);
            }
            byte[] result = cipher.doFinal(byteContent);
            return result;*/
        }

        public static void main(String[] args) throws Exception {
            //        System.out.println("【1】AES不指定模式和填充，默认为 ECB/PKCS5Padding，输入可以不是16字节，也不需要填充向量\n");
            //        // 需要加密的内容
            //        String content = "在线助手";
            //        // 生成密钥需要的密码值
            //        String key = "www.it399.com111";
            //        System.out.println("content： " + content + "\nkey： " + key);
            //        byte[] encodeByte;
            //        byte[] decodeByte;
            //        //默认方式  每次加密都不一样，但是秘钥是一样的，所以解密还是一样的
            //        // 内容加密后的值
            //        encodeByte = encrypt(content.getBytes(CHARSET), key, AESType.AES_128, EncodeType.AES_DEFAULT);
            //        String encodeStr = TypeConvert.bytesToHexString(encodeByte);
            //        // 被加密的内容解密后的值
            //        decodeByte = decrypt(encodeByte, key, AESType.AES_128, EncodeType.AES_DEFAULT);
            //        String decodeStr = new String(decodeByte,CHARSET);
            //        System.out.println("encode： " + encodeStr + "\ndecode： " + decodeStr);
            //
            //
            //        System.out.println("【2】AES_CBC_NoPadding模式，输入必须是16*n字节，需要填充向量\n");
            //        // 内容加密后的值
            //        //待加密内容不足16*n位 报错javax.crypto.IllegalBlockSizeException: Input length not multiple of 16 bytes
            //        //需要填充向量，不然报错java.security.InvalidKeyException: Parameters missing
            //        //得到加密后的内容先base64编码再解码再传给解码，不然直接转回乱码
            //        content = "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈";
            //        encodeByte = encrypt(content.getBytes(CHARSET), key, AESType.AES_256, EncodeType.AES_CBC_NoPadding);
            //        encodeStr = TypeConvert.bytesToHexString(encodeByte);
            //        decodeByte = decrypt(TypeConvert.hexStringToBytes(encodeStr), key, AESType.AES_256, EncodeType.AES_CBC_NoPadding);
            //        decodeStr = new String(decodeByte,CHARSET);
            //        System.out.println("encode： " + encodeStr + "\ndecode： " + decodeStr);
            String content = "在线助手";
            // 生成密钥需要的密码值
            String key = "www.it399.com";
            byte[] encrypt;
            /**
             * AES加密方式一:AES不指定模式和填充，默认为 ECB/PKCS5Padding
             */
            //        System.out.println("【0】AES不指定模式和填充，默认为 ECB/PKCS5Padding，输入可以不是16字节，也不需要填充向量\n");
            //        //128
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_128,EncodeType.AES_DEFAULT);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_128,EncodeType.AES_DEFAULT);
            //        //192
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,getIV(),AESType.AES_192,EncodeType.AES_DEFAULT);
            //        encryptOrdecrypt(false,encrypt,key,getIV(),AESType.AES_192,EncodeType.AES_DEFAULT);
            //        //256
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_256,EncodeType.AES_DEFAULT);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_256,EncodeType.AES_DEFAULT);
            //        /**
            //         * 1.1 AES/CBC （需要填充向量16*n）
            //         * AES/CBC/NoPadding
            //         * AES/CBC/PKCS5Padding
            //         * AES/CBC/ISO10126Padding
            //         */
            //        System.out.println("【1.1】AES_CBC_NoPadding模式，需要填充向量,待加密必须是16*n");
            //        content = "在线助手在线助手在线助手在线助手";
            //        key = "www.it399.com";
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,getIV(),AESType.AES_128,EncodeType.AES_CBC_NoPadding);
            //        encryptOrdecrypt(false,encrypt,key,getIV(),AESType.AES_128,EncodeType.AES_CBC_NoPadding);
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,getIV(),AESType.AES_192,EncodeType.AES_CBC_PKCS5Padding);
            //        encryptOrdecrypt(false,encrypt,key,getIV(),AESType.AES_192,EncodeType.AES_CBC_PKCS5Padding);
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,getIV(),AESType.AES_256,EncodeType.AES_CBC_ISO10126Padding);
            //        encryptOrdecrypt(false,encrypt,key,getIV(),AESType.AES_256,EncodeType.AES_CBC_ISO10126Padding);
            //        /**
            //         * 1.2 AES/CFB
            //         * AES/CBC/NoPadding
            //         * AES/CBC/PKCS5Padding
            //         * AES/CBC/ISO10126Padding
            //         */
                    System.out.println("【1.2】AES_CFB_PKCS5Padding\n");
                    content = "在线助手";
                    // 生成密钥需要的密码值
                    key = "www.it399";
             /*       encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,getIV(),AESType.AES_128,EncodeType.AES_CFB_PKCS5Padding);
            System.out.println("128 "+TypeConvert.bytesToHexString(encrypt));
                    encryptOrdecrypt(false,encrypt,key,getIV(),AESType.AES_128,EncodeType.AES_CFB_PKCS5Padding);
                    encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,getIV(),AESType.AES_192,EncodeType.AES_CFB_PKCS5Padding);
                    encryptOrdecrypt(false,encrypt,key,getIV(),AESType.AES_192,EncodeType.AES_CFB_PKCS5Padding);*/
                    encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,getIV(),AESType.AES_256,EncodeType.AES_CFB_PKCS5Padding);
            System.out.println(TypeConvert.byte2Base64StringFun(encrypt));
            encrypt= encryptOrdecrypt(false,encrypt,key,getIV(),AESType.AES_256,EncodeType.AES_CFB_PKCS5Padding);
            System.out.println("##"+new String(encrypt));
            //        /**
            //         * 1.2 AES/ECB
            //         * AES/ECB/NoPadding
            //         * AES/ECB/PKCS5Padding
            //         * AES/ECB/ISO10126Padding
            //         */
            //        System.out.println("【1.3】AES_ECB模式");
            //        content = "在线助手";
            //        // 生成密钥需要的密码值
            //        key = "http://www.it399.com";
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_128,EncodeType.AES_ECB_PKCS5Padding);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_128,EncodeType.AES_ECB_PKCS5Padding);
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_192,EncodeType.AES_ECB_PKCS5Padding);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_192,EncodeType.AES_ECB_PKCS5Padding);
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_256,EncodeType.AES_ECB_PKCS5Padding);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_256,EncodeType.AES_ECB_PKCS5Padding);
            /**
             * 1.4 AES/OFB
             * AES/OFB/NoPadding
             * AES/OFB/PKCS5Padding
             * AES/OFB/ISO10126Padding
             */
//            System.out.println("【1.4】AES_OFB模式");
//            content = "在线助手";
//            // 生成密钥需要的密码值
//            key = "http://www.it399.com";
//            encrypt = encryptOrdecrypt(true, content.getBytes(CHARSET), key, getIV(), AESType.AES_128, EncodeType.AES_OFB_PKCS5Padding);
//            encryptOrdecrypt(false, encrypt, key, getIV(), AESType.AES_128, EncodeType.AES_OFB_PKCS5Padding);
//            encrypt = encryptOrdecrypt(true, content.getBytes(CHARSET), key, getIV(), AESType.AES_192, EncodeType.AES_OFB_PKCS5Padding);
//            encryptOrdecrypt(false, encrypt, key, getIV(), AESType.AES_192, EncodeType.AES_OFB_PKCS5Padding);
//            encrypt = encryptOrdecrypt(true, content.getBytes(CHARSET), key, getIV(), AESType.AES_256, EncodeType.AES_OFB_PKCS5Padding);
//            encryptOrdecrypt(false, encrypt, key, getIV(), AESType.AES_256, EncodeType.AES_OFB_PKCS5Padding);
//            /**
//             * 1.5 AES/PCBC
//             * AES/PCBC/NoPadding
//             * AES/PCBC/PKCS5Padding
//             * AES/PCBC/ISO10126Padding
//             */
//            System.out.println("【1.5】AES_PCBC模式");
//            content = "在线助手";
//            // 生成密钥需要的密码值
//            key = "http://www.it399.com";
//            encrypt = encryptOrdecrypt(true, content.getBytes(CHARSET), key, getIV(), AESType.AES_128, EncodeType.AES_PCBC_PKCS5Padding);
//            encryptOrdecrypt(false, encrypt, key, getIV(), AESType.AES_128, EncodeType.AES_PCBC_PKCS5Padding);
//            encrypt = encryptOrdecrypt(true, content.getBytes(CHARSET), key, getIV(), AESType.AES_192, EncodeType.AES_PCBC_PKCS5Padding);
//            encryptOrdecrypt(false, encrypt, key, getIV(), AESType.AES_192, EncodeType.AES_PCBC_PKCS5Padding);
//            encrypt = encryptOrdecrypt(true, content.getBytes(CHARSET), key, getIV(), AESType.AES_256, EncodeType.AES_PCBC_PKCS5Padding);
//            encryptOrdecrypt(false, encrypt, key, getIV(), AESType.AES_256, EncodeType.AES_PCBC_PKCS5Padding);
//            //
//            //        /**1.3 AES/CBC
//            //         * AES_CBC_NoPadding模式（填充向量可选）
//            //         */
//            System.out.println("【1.3】AES_CBC_NoPadding模式");
//            content = "在线助手在线助手在线助手在线助手";
//            // 生成密钥需要的密码值
//            key = "www.it399.com";
//            encrypt = encryptOrdecrypt(true, content.getBytes(CHARSET), key, getIV(), AESType.AES_128, EncodeType.AES_CBC_NoPadding);
//            encryptOrdecrypt(false, encrypt, key, getIV(), AESType.AES_128, EncodeType.AES_CBC_NoPadding);
//            content = "在线助手";
//            // 生成密钥需要的密码值
//            key = "www.it399.com";
//            encrypt = encryptOrdecrypt(true, content.getBytes(CHARSET), key, getIV(), AESType.AES_192, EncodeType.AES_CBC_PKCS5Padding);
//            encryptOrdecrypt(false, encrypt, key, getIV(), AESType.AES_192, EncodeType.AES_CBC_PKCS5Padding);
//            encrypt = encryptOrdecrypt(true, content.getBytes(CHARSET), key, getIV(), AESType.AES_256, EncodeType.AES_CBC_ISO10126Padding);
//            encryptOrdecrypt(false, encrypt, key, getIV(), AESType.AES_256, EncodeType.AES_CBC_ISO10126Padding);
            //
            //
            //
            //        /**
            //         * 2.1 AES/CFB 128/192/256位加解密
            //         * AES_CFB_NoPadding模式（填充向量可选）
            //         */
            //        System.out.println("【2.1】AES_CFB_NoPadding模式，需要填充向量\n");
            //        content = "在线助手";
            //        // 生成密钥需要的密码值
            //        key = "www.it399.com";
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_128,EncodeType.AES_CFB_NoPadding);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_128,EncodeType.AES_CFB_NoPadding);
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_192,EncodeType.AES_CFB_NoPadding);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_192,EncodeType.AES_CFB_NoPadding);
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_256,EncodeType.AES_CFB_NoPadding);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_256,EncodeType.AES_CFB_NoPadding);
            //
            //        /**
            //         * 2.2 AES/CFB
            //         * AES_CFB_NoPadding模式（填充向量可选）
            //         */
            //        System.out.println("【2.2】AES_CFB_NoPadding模式\n");
            //        content = "在线助手";
            //        // 生成密钥需要的密码值
            //        key = "www.it399.com";
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_128,EncodeType.AES_CFB_PKCS5Padding);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_128,EncodeType.AES_CFB_PKCS5Padding);
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_192,EncodeType.AES_CFB_PKCS5Padding);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_192,EncodeType.AES_CFB_PKCS5Padding);
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_256,EncodeType.AES_CFB_PKCS5Padding);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_256,EncodeType.AES_CFB_PKCS5Padding);
            //
            //        /**2.3 AES/CFB
            //         * AES_CFB_NoPadding模式（填充向量可选）
            //         */
            //        System.out.println("【2.3】AES_CFB_NoPadding模式\n");
            //        content = "在线助手";
            //        // 生成密钥需要的密码值
            //        key = "www.it399.com";
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_128,EncodeType.AES_CFB_ISO10126Padding);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_128,EncodeType.AES_CFB_ISO10126Padding);
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_192,EncodeType.AES_CFB_ISO10126Padding);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_192,EncodeType.AES_CFB_ISO10126Padding);
            //        encrypt = encryptOrdecrypt(true,content.getBytes(CHARSET),key,null,AESType.AES_256,EncodeType.AES_CFB_ISO10126Padding);
            //        encryptOrdecrypt(false,encrypt,key,null,AESType.AES_256,EncodeType.AES_CFB_ISO10126Padding);
        }

        /**
         * @param isEncrypt
         * @param source
         * @param key
         * @param type
         * @param encodeType
         */
        public static byte[] encryptOrdecrypt(boolean isEncrypt, byte[] source, String key, byte[] iv, AESType type, String encodeType) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
            if (isEncrypt) {
                byte[] encodeByte = encryptOrDecrypt(Cipher.ENCRYPT_MODE, source, key, iv, type, encodeType);
                String encodeStr = TypeConvert.bytesToHexString(encodeByte);
                return encodeByte;
            } else {
                byte[] decodeByte = encryptOrDecrypt(Cipher.DECRYPT_MODE, source, key, iv, type, encodeType);
                String decodeStr = new String(decodeByte, CHARSET);
                return decodeByte;
            }
        }

        /**
         * 指定一个初始化向量 (Initialization vector，IV)，IV 必须是16位
         */
        public static final byte[] getIV() throws Exception {
            return "1234567812345678".getBytes(CHARSET);
        }
    }