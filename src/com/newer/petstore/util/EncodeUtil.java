package com.newer.petstore.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 工具类
 * 
 * @author wtao
 *
 */
public class EncodeUtil {

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String md5(String text) {
		try {
			// 获得摘要信息（数值）
			byte[] data = MessageDigest.getInstance("MD5").digest(text.getBytes());

			// 数值的16进制
			return new BigInteger(1, data).toString(16);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
//	public static void main(String[] args) {
//		System.out.println(md5(""));
//		System.out.println(md5("hello"));
//		System.out.println(md5("hi"));
//		System.out.println(md5("hello"));
//	}

}
