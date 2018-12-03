package com.encrypt.test.aes256.bouncycastle;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.RijndaelEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class RijndaelEncrypt {

    private static final int INPUT_DATA_START_OFFSET = 0;
    private static final int OUTPUT_DATA_COPY_START_OFFSET = 0;
    private static final int KEY_SIZE = 256;

    public static byte[] encrypt(byte[] plainData, byte[] key, byte[] initialVector) {
        try {
            BlockCipher blockCipher = new CBCBlockCipher(new RijndaelEngine(KEY_SIZE));
            BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(blockCipher, new ZeroBytePadding());

            CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), initialVector);
            cipher.init(true, ivAndKey);

            byte[] cipherText = new byte[cipher.getOutputSize(plainData.length)];
            int cipherTextSize = cipher.processBytes(plainData, INPUT_DATA_START_OFFSET, plainData.length, cipherText, OUTPUT_DATA_COPY_START_OFFSET);
            cipher.doFinal(cipherText, cipherTextSize);

            return cipherText;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred when encrypting given data. Base64EncodedData : " + Base64.encodeBase64String(plainData));
        }
    }
}