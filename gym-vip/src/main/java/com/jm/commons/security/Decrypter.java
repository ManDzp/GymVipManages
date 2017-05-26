package com.jm.commons.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class Decrypter
{
	private Cipher cipher;
	/**
	 * 解密构造函数
	 * @param strKey 对称加密key值
	 * @param strIV 堆成加密iv值
	 */
	public Decrypter(String strKey,String strIV)
	{
		//因ecb模式不需要iv值，实际iv值不需要
		try
		{
			DESedeKeySpec spec = new DESedeKeySpec(Base64Utils.str2Byte(strKey));
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			Key deskey = keyfactory.generateSecret(spec);

			cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");

			cipher.init(Cipher.DECRYPT_MODE, deskey);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	/**
	 * 对特定加密的文件进行解密
	 * @param strEnc 加密文件
	 * @return 返回解密串
	 * @throws Exception
	 */
	public String DecryptString(String strEnc)
    {
		try
		{
			byte[] pasByte = cipher.doFinal(Base64Utils.str2Byte(strEnc));
			
			return new String(pasByte,"gb2312");
			
			//return new String(pasByte, "UTF-8");
		}
		catch(Exception ex)
    	{
    		return null;
    	}
    }
}
