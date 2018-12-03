package com.encrypt.test.aes256;

/**
 * 在线助手|在线工具|在线生成|在线制作
 * http://www.it399.com/
 * 在线助手博客
 * http://www.it399.com/blog/index
 */
public class EncodeType {
//    算法/模式/填充                 16字节加密后数据长度       不满16字节加密后长度
//    AES/CBC/NoPadding                   16                          不支持
//    AES/CBC/PKCS5Padding                32                          16
//    AES/CBC/ISO10126Padding             32                          16
//    AES/CFB/NoPadding                   16                          原始数据长度
//    AES/CFB/PKCS5Padding                32                          16
//    AES/CFB/ISO10126Padding             32                          16
//    AES/ECB/NoPadding                   16                          不支持
//    AES/ECB/PKCS5Padding                32                          16
//    AES/ECB/ISO10126Padding             32                          16
//    AES/OFB/NoPadding                   16                          原始数据长度
//    AES/OFB/PKCS5Padding                32                          16
//    AES/OFB/ISO10126Padding             32                          16
//    AES/PCBC/NoPadding                  16                          不支持
//    AES/PCBC/PKCS5Padding               32                          16
//    AES/PCBC/ISO10126Padding            32                          16
    //默认为 ECB/PKCS5Padding
    public final static String AES_DEFAULT = "AES";
    public final static String AES_CBC_NoPadding = "AES/CBC/NoPadding";
    public final static String AES_CBC_PKCS5Padding = "AES/CBC/PKCS5Padding";
    public final static String AES_CBC_ISO10126Padding = "AES/CBC/ISO10126Padding";

    public final static String AES_CFB_NoPadding = "AES/CFB/NoPadding";
    public final static String AES_CFB_PKCS5Padding = "AES/CFB/PKCS5Padding";
    public final static String AES_CFB_ISO10126Padding = "AES/CFB/ISO10126Padding";

    public final static String AES_ECB_NoPadding = "AES/ECB/NoPadding";
    public final static String AES_ECB_PKCS5Padding = "AES/ECB/PKCS5Padding";
    public final static String AES_ECB_ISO10126Padding = "AES/ECB/ISO10126Padding";
    public final static String AES_OFB_NoPadding = "AES/OFB/NoPadding";
    public final static String AES_OFB_PKCS5Padding = "AES/OFB/PKCS5Padding";
    public final static String AES_OFB_ISO10126Padding = "AES/OFB/ISO10126Padding";
    public final static String AES_PCBC_NoPadding = "AES/PCBC/NoPadding";
    public final static String AES_PCBC_PKCS5Padding = "AES/PCBC/PKCS5Padding";
    public final static String AES_PCBC_ISO10126Padding = "AES/PCBC/ISO10126Padding";
}