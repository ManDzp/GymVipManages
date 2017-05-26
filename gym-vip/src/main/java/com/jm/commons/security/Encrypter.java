package com.jm.commons.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class Encrypter
{
	private Cipher cipher;
	
	public Encrypter(String strKey,String strIV)
	{
		//因ECB模式不需要iv值，实际iv值不需要
        try
        {
        	DESedeKeySpec spec = new DESedeKeySpec(Base64Utils.str2Byte(strKey));
        	SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        	Key deskey = keyfactory.generateSecret(spec);
        	cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        	cipher.init(Cipher.ENCRYPT_MODE, deskey);
        }
        catch(Exception ex)
        {}
	}
	/**
     * ECB加密,不要IV
     * @param 要加密的原始明文
     * @return Base64编码的密文
     * @throws Exception
     */
    public String EncryptString(String strSource)
    {
    	try
    	{
    		byte[] bOut = cipher.doFinal(strSource.getBytes("gb2312"));
    		return  Base64Utils.byte2Str(bOut);
    	}
    	catch(Exception ex)
    	{
    		return null;
    	}
    }

}
