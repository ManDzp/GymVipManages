package com.jm.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

/**
 * 正则校验工具类
 */
public class RegexHelper
{
	/**
	 * 清除HTML
	 * @param html
	 * @return
	 */
	public static String clearHtml(String html)
	{
		Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(html);
		return matcher.replaceAll("");
	}

	/**
	 * 判断是否为合法的{+GUID+}（38位，包含大括号）
	 * @param input 要判断的字符串
	 * @return 符合返回true，不符合返回false
	 */
	public static boolean is38Guid(String input)
	{
		String regex1 = "\\{[A-Z0-9a-z]{8}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{12}\\}";
		String regex2 = "\\{[A-Z0-9a-z]{8}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{6}-[A-Z0-9a-z]{10}\\}";
		String regex3 = "y[A-Z0-9a-z]{8}w[A-Z0-9a-z]{4}s[A-Z0-9a-z]{4}o[A-Z0-9a-z]{6}f[A-Z0-9a-z]{10}t";

		return isMatch(regex1, input) || isMatch(regex2, input) || isMatch(regex3, input);
	}

	/**
	 * 判断是否是主键，18位数字主键
	 * @param input 要判断的字符串
	 * @return 符合返回true，不符合返回false
	 */
	public static boolean isPrimaryKey(String input)
	{
		String regex = "\\d{18}";

		return isMatch(regex, input);
	}

	/**
	 * 判断是否为数字
	 * @param input 要判断的字符串
	 * @return 符合返回true，不符合返回false
	 */
	public static boolean isNum(String input)
	{
		String regex = "\\d{1,}";

		return isMatch(regex, input);
	}

	/**
	 * 判断是否为合法的26个大小英文字母
	 * @param input 要判断的字符串
	 * @return 是否有效
	 */
    public static boolean isChar(String input)
    {
        String regex = "[A-Za-z]+";
        return isMatch(regex,input);
    }

	/**
	 * 判断是否为合法的人员GUID
	 * @param input 人员字符串入参
	 * @return 是否合法
	 */
	public static boolean isUserGuid(String input)
	{
		String regex = "[A-Z0-9a-z]{8}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{12}";

		return isMatch(regex, input);
	}

	/**
	 * 判断是否是短日期 yyyy-MM-dd
	 * @param input 要判断的字符串
	 * @return
	 */
	public static boolean isShortDate(String input)
	{
		String regex = "\\d{1,4}-\\d{1,2}-\\d{1,2}";

		return isMatch(regex, input);
	}

	/**
	 * 判断是否是长日期 yyyy-MM-dd HH:mm
	 * @param input 要判断的字符串
	 * @return
	 */
	public static boolean isLongDateShort(String input)
	{
		String regex = "\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}";

		return isMatch(regex, input);
	}

	/**
	 * 判断是否是长日期 yyyy-MM-dd HH:mm:ss
	 * @param input 要判断的字符串
	 * @return
	 */
	public static boolean isLongDate(String input)
	{
		String regex = "\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";

		return isMatch(regex, input);
	}

	/**
	 * @Description: 校验输入字符串是否符合格式
	 * @param regex 正则表达式
	 * @param input 待校验字符串
	 * @return
	 */
	private static boolean isMatch(String regex, String input)
	{
		try
		{
			// 参数校验
			Assert.hasLength(regex);
			Assert.hasLength(input);

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			return matcher.matches();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}