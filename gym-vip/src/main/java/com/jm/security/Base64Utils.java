package com.jm.security;

import org.apache.commons.codec.binary.Base64;

/**
 * Base64编码方法集合
 * @author yawei
 */
public class Base64Utils
{
	/**
	 * 把base64编码的字符串转换为正常的二进制
	 * @param strBase64 base64编码的字符串
	 * @return 二进制
	 */
	public static byte[] str2Byte(String strBase64)
	{
		return Base64.decodeBase64(strBase64);
		//return Base64.getDecoder().decode(strBase64);
	}

	/**
	 * 将byte数字转换为等效的base64字符串
	 * @param strByt
	 * @return
	 */
	public static String byte2Str(byte[] strByt)
	{
		return Base64.encodeBase64String(strByt);
		//return Base64.getEncoder().encodeToString(strByt);
	}
}
