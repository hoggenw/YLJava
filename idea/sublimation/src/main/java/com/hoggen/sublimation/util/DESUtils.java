package com.hoggen.sublimation.util;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class DESUtils {
	private static Key key;
	private static String KEY_STR = "myKey";
	private static String CHARSETNAME = "UTF-8";
	private static String ALGORITHM = "DES";

	static {
		try {
			// 生成des算法对象
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
			// 运用SHA安全策略
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			// 设置上秘钥种子
			secureRandom.setSeed(KEY_STR.getBytes());
			// 初始化基于SHA1的算法对象
			generator.init(secureRandom);
			// 生成秘钥对象
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getEncryptString(String str) {
		// 基于base64编码，接收bytes[]并转换成String
		Base64.Encoder base64encoder = Base64.getEncoder();
		try {
			// 按UTF8编码

			byte[] bytes = str.getBytes(CHARSETNAME);
			// 获取加密对象
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			// 初始化密码信息
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 加密
			byte[] doFinal = cipher.doFinal(bytes);
			// byte[] to encode 好的string并返回
			return base64encoder.encodeToString(doFinal);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public static String getDecryptString(String str) {
		Base64.Decoder base64decoder = Base64.getDecoder();
		try {
			// 按UTF8编码
			byte[] bytes = base64decoder.decode(str);
			// 获取解密对象
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(bytes);
			return new String(doFinal, CHARSETNAME);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		System.out.println("账号：  " + getEncryptString("root"));
		System.out.println("密码：  " + getEncryptString("12345678"));
		// System.out.println(getEncryptString("wxd7f6c5b8899fba83"));
		// System.out.println(getEncryptString("665ae80dba31fc91ab6191e7da4d676d"));cmpjUf4dIIIfJAfVsP+M2w==
		System.out.println("解码账号：  " + getDecryptString("WnplV/ietfQ="));
		System.out.println("解码密码：  " + getDecryptString("d0iM1qq5PF4fJAfVsP+M2w=="));

	}
}
