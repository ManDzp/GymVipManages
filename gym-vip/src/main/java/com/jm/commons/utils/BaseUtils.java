package com.jm.commons.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

/**
 * 基础工具类
 */
public class BaseUtils
{
	/**
	 * 获得18位长度的随机数（13位从1970到现在的毫秒数+5位随机数）
	 * @return 得到18位随机数
	 */
	public static String getPrimaryKey()
	{
		StringBuilder sbKeyNumber = new StringBuilder();
		sbKeyNumber.append(System.currentTimeMillis());
		sbKeyNumber.append(getRandomNum(5));
		return sbKeyNumber.toString();
	}

	/**
	 * 取得指定位数的（0-9）随机数
	 * @param 获取随机数的位数
	 * @return 生成指定长度的随机数
	 */
	public static String getRandomNum(int num)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++)
		{
			sb.append(new Random(UUID.randomUUID().hashCode()).nextInt(9));
		}
		return sb.toString();
	}

	/**
	 * 取得GUID（38位）。
	 * 去除了以前版本直接从GUID得到的模式，而改为：
	 * 年（四位）月（两位）日（两位）时（两位）分（两位）秒（两位）
	 * 毫秒（三位）+五位随机数+10位UUID除去-号的前10位）
	 * @return 返回自己组成的GUID {20160224-1754-1920-688378-d592716bfb}
	 * y20160224w1754s1920o688378fd592716bfbt
	 */
	public static String getNewGuid()
	{
		String strKeyNumber = getRandomNum(5);
		String uu = UUID.randomUUID().toString();// 1704d09b-a4ad-4959-a693-2873a19e29d9

		String strGuid = "{" + DateHelper.formatNow("yyyyMMdd") + "-"
				+ DateHelper.formatNow("HHmm") + "-"
				+ DateHelper.formatNow("ss")
				+ DateHelper.formatNow("SSS").substring(0, 2) + "-"
				+ DateHelper.formatNow("SSS").substring(2, 3)
				+ strKeyNumber.substring(0, 3) + "-"
				+ strKeyNumber.substring(3, 5) + uu.substring(0, 8)
				+ uu.substring(9, 11) + "}";

		return strGuid;
	}


	/**
	 * 取得UserGuid（36位）。
	 * 去除了以前版本直接从GUID得到的模式，而改为：
	 * 年（四位）月（两位）日（两位）时（两位）分（两位）秒（两位）
	 * 毫秒（三位）+五位随机数+10位UUID除去-号的前10位）
	 * @return 返回自己组成的GUID 20160224-1754-1920-688378-d592716bfb
	 */
	public static String getUserGuid()
	{
		String strKeyNumber = getRandomNum(5);
		String uu = UUID.randomUUID().toString();// 1704d09b-a4ad-4959-a693-2873a19e29d9

		String strGuid = DateHelper.formatNow("yyyyMMdd") + "-"
				+ DateHelper.formatNow("HHmm") + "-"
				+ DateHelper.formatNow("ss")
				+ DateHelper.formatNow("SSS").substring(0, 2) + "-"
				+ DateHelper.formatNow("SSS").substring(2, 3)
				+ strKeyNumber.substring(0, 3) + "-"
				+ strKeyNumber.substring(3, 5) + uu.substring(0, 8)
				+ uu.substring(9, 11);

		return strGuid;
	}
	
	
	/**
	 * 获取配置文件中的配置信息
	 * @param key 配置文件key值
	 * @return
	 */
	public static String getConfigValue(String key)
	{
		try
		{
			Properties props = new Properties();
			InputStream in = BaseUtils.class.getClassLoader()
					.getResourceAsStream("config.properties");
			BufferedReader bf = new BufferedReader(
					new InputStreamReader(in, "UTF-8"));
			props.load(bf);
			return props.getProperty(key);
		}
		catch (Exception ex)
		{
			return "";
		}
	}

	/**
	 * 计算文件大小
	 * 
	 * @param fileLength
	 * @return
	 */
	public static String getFileSize(long fileLength)
	{
		String fileSize = "";
		DecimalFormat decimalFormat = new DecimalFormat(".00");

		double fileEndLength = 0;
		if (fileLength > 1048576)
		{
			fileEndLength = fileLength / 1024f / 1024f;
			fileSize = decimalFormat.format(fileEndLength) + "M";// 转换为M,只保留两位小数
		}
		else if (fileLength > 1024f)
		{
			fileEndLength = fileLength / 1024f;
			fileSize = decimalFormat.format(fileEndLength) + "K";// 转换为M,只保留两位小数
		}
		else
		{
			fileSize = fileLength + "字节";
		}

		return fileSize;
	}

	/**
	 * 获取全角空格
	 * @param num 空格个数
	 * @return
	 */
	public static String getBlankSpace(int num)
	{
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < num; i++)
		{
			sb.append("　");
		}

		return sb.toString();
	}

}
