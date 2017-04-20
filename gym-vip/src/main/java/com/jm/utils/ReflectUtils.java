package com.jm.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 反射相关工具类
 */
public class ReflectUtils
{
	/**
	 * 调用set方法
	 * @param obj ：要操作的对象
	 * @param att ：要操作的属性
	 * @param value ：要设置的属性内容
	 * @param type：要设置的属性类型
	 */
	public static void setter(Object obj, String att, Object value,
			Class<?> type)
	{
		try
		{
			// 得到setter方法
			Method m = obj.getClass().getMethod("set" + initStr(att), type);
			m.invoke(obj, value);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 调用get方法
	 * @param obj ：要操作的对象
	 * @param att ：要操作的属性
	 */
	public static Object getter(Object obj, String att)
	{
		try
		{
			// 得到getter方法
			Method m = obj.getClass().getMethod("get" + initStr(att));
			Object result = m.invoke(obj);
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static String initStr(String oldStr)
	{
		String newStr = oldStr.substring(0, 1).toUpperCase()
				+ oldStr.substring(1).toLowerCase();
		return newStr;
	}

	/**
	 * 获取对象对应类下的字段列表
	 * @param obj 对象
	 * @return 字段列表
	 */
	public static List<String> getFieldList(Object obj)
	{
		Class<?> classType = obj.getClass();

		return getFieldList(classType);
	}

	/**
	 * 获取类下的字段列表
	 * @param classType 类名
	 * @return 字段列表
	 */
	public static List<String> getFieldList(Class<?> classType)
	{
		List<String> fieldList = Lists.newArrayList();

		Field[] fields = classType.getDeclaredFields();

		for (Field field : fields)
		{
			fieldList.add(field.getName().toLowerCase());
		}

		return fieldList;
	}
}
