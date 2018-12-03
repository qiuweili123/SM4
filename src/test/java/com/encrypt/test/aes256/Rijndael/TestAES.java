package com.encrypt.test.aes256.Rijndael;

import junit.framework.TestCase;

public class TestAES extends TestCase {
	//

	public void test_0() throws Exception {
		String strKey = "abcdabcdabcdabcd";
		String clearText = "待加密的明文";

		final int BLOCK_SIZE = 32;
		{

			String cipherText = Rijndael_Util.encode(strKey, clearText,
					BLOCK_SIZE);
			System.out.println(cipherText);
			System.out.println(Rijndael_Util.decode(strKey, cipherText,
					BLOCK_SIZE));
		}

		// {
		// byte[] pt = clear.getBytes();
		// Object key = Rijndael_Algorithm.makeKey(strKey, BLOCK_SIZE);
		// byte[] ct = Rijndael_Algorithm.blockEncrypt(clear.getBytes(), 0,
		// key);
		// byte[] cpt = Rijndael_Algorithm.blockDecrypt(ct, 0, key);
		// System.out.println(byte2String(pt));
		// System.out.println(byte2String(ct));
		// System.out.println(byte2String(cpt));
		// }

		// {
		// long start = System.currentTimeMillis();
		// for (int i = 0; i < 1000; ++i) {
		// Object key = Rijndael_Algorithm.makeKey(strKey, BLOCK_SIZE);
		// }
		// System.out.println(System.currentTimeMillis() - start);
		// }
		//
		// {
		// long start = System.currentTimeMillis();
		// for (int i = 0; i < 1000; ++i) {
		// byte[] pt = clear.getBytes();
		//
		// String cipherText = encode(kb, clear);
		// // System.out.println(decode(kb, cipherText));
		// }
		// System.out.println(System.currentTimeMillis() - start);
		// }
	}

	private int unbox(byte[] bytes) {
		return (int) ((((bytes[3] & 0xff) << 24) | ((bytes[2]) << 16)
				| ((bytes[1] & 0xff) << 8) | ((bytes[0] & 0xff) << 0)));

	}

	public void test_1() throws Exception {
		String strKey = "�����񹦻ӵ��ӹ�";

		// final int BLOCK_SIZE = 32;
		StringBuffer buff = new StringBuffer();
		byte[] kb = Rijndael_Util.make_kb_32(strKey);

		for (int i = 0; i < 1000; ++i) {
			System.out.println("--------------- " + i + "-----------");
			for (int BLOCK_SIZE = 16; BLOCK_SIZE <= 32; BLOCK_SIZE += 8) {
				buff.append(i);
				String clear = buff.toString();
				byte[] pt = clear.getBytes();
				String cipherText = Rijndael_Util.encode(strKey, clear,
						BLOCK_SIZE);
				String cpt = Rijndael_Util.decode(strKey, cipherText,
						BLOCK_SIZE);
				if (!clear.equals(cpt)) {

					System.out.println(clear);
					System.out.println(cipherText);
					System.out.println(cpt);
					break;
				}
			}
		}
	}

	public void test_2() throws Exception {
		String strKey = "�����񹦻ӵ��ӹ�";
		final int BLOCK_SIZE = 32;

		String clear = "01234567891011121314151617181920212223242526272829";
		byte[] pt = clear.getBytes();

		System.out.println(clear);
		String cipherText = Rijndael_Util.encode(strKey, clear, BLOCK_SIZE);
		System.out.println(cipherText);
		System.out
				.println(Rijndael_Util.decode(strKey, cipherText, BLOCK_SIZE));
	}

	public void test_3() throws Exception {
		for (int i = 0; i < 2000; ++i) {
			byte[] bytes = new byte[4];
			bytes[0] = (byte) (i >> 24);
			bytes[1] = (byte) (i >> 16);
			bytes[2] = (byte) (i >> 8);
			bytes[3] = (byte) i;
//			int j = (bytes[3] >= 0 ? bytes[3] : bytes[3])
//					+ (bytes[2] >= 0 ? bytes[2] << 8 : 256 + bytes[2] << 8)
//					+ (bytes[1] >= 0 ? bytes[1] << 16 : 256 + bytes[1] << 16)
//					+ (bytes[0] >= 0 ? bytes[0] << 24 : 256 + bytes[0] << 24);
			
			int j = ((((bytes[0] & 0xff) << 24) | ((bytes[1]) << 16)
					| ((bytes[2] & 0xff) << 8) | ((bytes[3] & 0xff) << 0)));
			if (i != j) {
				System.out.println(i + ", " + j + ", "
						+ Rijndael_Util.byte2String(bytes));
			}
		}
	}
	
	public void test_4() throws Exception {
		for (int i = 0; i < 20000; ++i) {
			byte[] bytes = new byte[4];
			Rijndael_Util.putInt(i, bytes, 0);
			int j = Rijndael_Util.getInt(bytes, 0);
			if (i != j) {
				System.out.println(i + ", " + j + ", "
						+ Rijndael_Util.byte2String(bytes));
			}
		}
	}

}
