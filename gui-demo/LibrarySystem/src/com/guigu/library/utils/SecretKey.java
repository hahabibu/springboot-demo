package com.guigu.library.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SecretKey {
	// �˴�ʹ����Կ20000���д������ַ�����ÿһ���ַ��������õ���Ӧ���ܺ���ַ���
	// ��������м��ܴ���
	public static String encrypt(String password) {
		char[] array = password.toCharArray();
		for (int i = 0; i < array.length; i++) {
			array[i] = (char) (array[i] ^ 20000);
		}
		String key = new String(array);
		return key;
	}

	// ��������н��ܴ���
	public static String decrypt(String password) {
		char[] array = password.toCharArray();
		for (int i = 0; i < array.length; i++) {
			array[i] = (char) (array[i] ^ 20000);
		}
		String key = new String(array);
		return key;
	}
}
