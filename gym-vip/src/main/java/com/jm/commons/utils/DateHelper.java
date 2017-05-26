package com.jm.commons.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.jm.commons.security.RegexHelper;

/**
 * 日期操作的基本工具类
 */
public class DateHelper
{
	/**
	 * 获取当前日期时间字符串类型
	 * 时间格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentStrTime()
	{
		return formatNow("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取当前日期字符串类型
	 * 时间格式：yyyy-MM-dd
	 */
	public static String getCurrentStrDate()
	{
		return formatNow("yyyy-MM-dd");
	}

	/**
	 * 获取当前时间:yyyy-MM-dd
	 */
	public static Date getCurrentDateShort()
	{
		String strDateShort = getCurrentStrDate();
		return stringToDate(strDateShort);
	}

	/**
	 * 获取当前日期 yyyy-MM-dd HH:mm:ss
	 * @return 返回日期型
	 */
	public static Date getCurrentDate()
	{
		return new Date();
	}

	/**
	 * 获取毫秒的时间戳 1436513976595  共13位（近期）
	 * 为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数
	 * 
	 * @return
	 */
	public static Long getCurrentTimeMillis()
	{
		return System.currentTimeMillis();
	}

	/**
	 * 得到Unix时间戳，1436513976 共10位（近期）
	 * 是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数，不考虑闰秒
	 * @return
	 */
	public static Long getCurrentTimeSeconds()
	{
		return System.currentTimeMillis() / 1000L;
	}

	/**
	 * 获取当前日期转换为数字型：yyyyMMddHHmmss(年月日时分秒)
	 * @return long类型的时间串
	 */
	public static Long getCurrentLongTime()
	{
		String dateString = formatNow("yyyyMMddHHmmss");
		return parseLong(dateString);
	}

	/**
	 * 返回年份
	 */
	public static String getCurrentYear()
	{
		return formatNow("yyyy");
	}

	/**
	 * 返回月份
	 */
	public static String getCurrentMonth()
	{
		return formatNow("MM");
	}

	/**
	 * 返回当前日
	 */
	public static String getCurrentDay()
	{
		return formatNow("dd");
	}

	/**
	 * long型时间戳转为时间字符串yyyy-MM-dd HH:mm:ss
	 * @param mill 时间戳
	 * @return
	 */
	public static String getLongMillisToString(Long mill)
	{
		return getFormatDate(mill, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * long型时间戳转为时间字符串yyyy-MM-dd
	 * @param mill 时间戳
	 * @return
	 */
	public static String getLongMillisToShort(Long mill)
	{
		return getFormatDate(mill, "yyyy-MM-dd");
	}

	/**
	 * 将"yyyy-MM-dd"或"yyyy-MM-dd HH:mm:ss"格式的字符串时间转成总毫秒数Long
	 * @param dateStr 字符串类型的时间
	 * @return
	 */
	public static Long getStringToLongMillis(String dateStr)
	{
		try
		{
			// 参数校验
			Assert.hasLength(dateStr);

			// 转换成Date类型
			Date date = stringToDate(dateStr);

			// 返回Long类型
			return getDateToLongMillis(date);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 日期转long型
	 * @param date
	 * @return
	 */
	protected static Long getDateToLongMillis(Date date)
	{
		try
		{
			// 参数校验
			Assert.notNull(date);

			return date.getTime();
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * long型时间戳转为时间字符串yyyy-MM-dd HH:mm:ss
	 * @param mill 时间戳
	 * @return
	 */
	public static String getDateToString(Date date)
	{
		return getFormatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 时间戳转为时间字符串yyyy-MM-dd
	 * @param date 时间
	 * @return
	 */
	public static String getDateToShort(Date date)
	{
		return getFormatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 时间戳转为时间字符串MM-dd
	 * @param mill 时间
	 * @return
	 */
	public static String getDateToShortSimple(Date date)
	{
		return getFormatDate(date, "MM-dd");
	}

	/**
	 * 时间戳转为时间字符串yyyy年MM月dd日
	 * @param date 时间
	 * @return
	 */
	public static String getDateToChineseShort(Date date)
	{
		return getFormatDate(date, "yyyy年MM月dd日");
	}

	/**
	 * 时间戳转为时间字符串
	 * @param date 时间戳
	 * @param format 自定义格式
	 * @return
	 */
	public static String getDateFormat(Date date, String format)
	{
		return getFormatDate(date, format);
	}

	/**
	 * 将字符串类型的日期转为java.util.date
	 * @param strDate
	 * @return
	 */
	public static Date stringToDate(String strDate)
	{
		if (StringUtils.isEmpty(strDate))
		{
			return null;
		}

		if (RegexHelper.isLongDate(strDate))
		{
			return getFormatDate(strDate, "yyyy-MM-dd HH:mm:ss");
		}
		else if (RegexHelper.isLongDateShort(strDate))
		{
			return getFormatDate(strDate, "yyyy-MM-dd HH:mm");
		}
		else if (RegexHelper.isShortDate(strDate))
		{
			return getFormatDate(strDate, "yyyy-MM-dd");
		}
		else
		{
			return null;
		}
	}

	/**
	 * 移动当前时间
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date add(int field, int amount)
	{
		Calendar cal = Calendar.getInstance();// 取当前日期。
		cal.add(field, amount);
		return cal.getTime();
	}

	/**
	 * 移动指定时间
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date add(Date date, int field, int amount)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}

	/**
	 * 返回当前日期在特殊格式下的日期字符串
	 * @param pattern 日期格式
	 * @return
	 */
	public static String formatNow(String pattern)
	{
		return getFormatDate(getCurrentDate(), pattern);
	}

	/**
	 * 返回指定日期在特殊格式下的日期字符串
	 * @param date 日期对象
	 * @param pattern 日期格式
	 * @return
	 */
	public static String getFormatDate(Date date, String pattern)
	{
		try
		{
			// 参数校验
			Assert.notNull(date);

			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			return formatter.format(date);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 返回特定格式的日期
	 * @param mill 时间戳
	 * @param pattern 日期格式
	 * @return
	 */
	protected static String getFormatDate(Long mill, String pattern)
	{
		try
		{
			// 参数校验
			Assert.isTrue(mill != null && mill > 0);

			Date date = new Date(mill);

			return getFormatDate(date, pattern);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 返回日期字符串在特殊格式下对应的日期
	 * @param strDate 字符串类型的日期
	 * @param pattern 日期格式
	 * @return
	 */
	public static Date getFormatDate(String strDate, String pattern)
	{
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			ParsePosition pos = new ParsePosition(0);
			return formatter.parse(strDate, pos);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 解析成Long型
	 * @param dateString
	 * @return
	 */
	protected static Long parseLong(String dateString)
	{
		try
		{
			// 参数校验
			Assert.hasLength(dateString);

			return Long.parseLong(dateString);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 获取明天的日期date：2016-04-09 格式
	 * @return
	 */
	public static Date getTomorrowDate()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentDate());
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}

	/**
	 * 获取未来的日期
	 * date：2016-04-09 格式
	 * @param nextDay 未来几天
	 * @return
	 */
	public static Date getNextDate(int nextDay)
	{
		return getNextDate(getCurrentDate(), nextDay);
	}

	/**
	 * 获取未来的日期
	 * date：2016-04-09 格式
	 * @param startDate 起始日期
	 * @param nextDay 未来几天
	 * @return
	 */
	public static Date getNextDate(Date startDate, int nextDay)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.DATE, nextDay);
		return cal.getTime();
	}

	/**
	 * 获取几年后的时间
	 * @param d
	 * @param year
	 * @return
	 */
	public static Date getDateAfterYear(Date d, int year)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.YEAR, now.get(Calendar.YEAR) + year);
		return now.getTime();
	}

	/**
	 * 获取几天后的时间
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfterDay(Date d, int day)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 返回几个小时后的时间
	 * @param d
	 * @param hour
	 * @return
	 */
	public static Date getDateAfterHour(Date d, int hour)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.HOUR, now.get(Calendar.HOUR) + hour);
		return now.getTime();
	}

	/**
	 * 返回几分钟后的某个时间
	 * @param d
	 * @param minutes
	 * @return
	 */
	public static Date getDateAfterMinute(Date d, int minutes)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + minutes);
		return now.getTime();
	}

	/**
	 * 获取两个日期间隔天数befDate-aftDate
	 * @param befDate
	 * @param aftDate
	 * @return
	 */
	public static long getBetweenDay(Date befDate, Date aftDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(befDate);
		long timeN = cal.getTimeInMillis();
		cal.setTime(aftDate);
		long timeT = cal.getTimeInMillis();
		long between_days = (timeN - timeT) / (1000 * 3600 * 24);
		return between_days;
	}
}
