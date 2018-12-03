package com.encrypt.test.aes256;

/**
     * 在线助手|在线工具|在线生成|在线制作
     * http://www.it399.com/
     * 在线助手博客
     * http://www.it399.com/blog/index
     */
    enum AESType {
        AES_128(128), AES_192(192), AES_256(256);
        public int value;
        private AESType(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }
    }