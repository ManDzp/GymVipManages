package com.jm.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class DaoMapUtils
{
	public static boolean checkBadMap(Map<String, Object> keys,
			Class<?> classType)
	{
		return !checkMap(keys, classType);
	}

	/**
	 * 检验map是否符合
	 * @param keys
	 * @return
	 */
	public static boolean checkMap(Map<String, Object> keys, Class<?> classType)
	{
		if (CollectionUtils.isEmpty(keys))
		{
			return false;
		}

		List<String> fieldList = ReflectUtils.getFieldList(classType);
		// 没有字段列表时，返回失败
		if (CollectionUtils.isEmpty(fieldList))
		{
			return false;
		}

		Iterator<Entry<String, Object>> iterator = keys.entrySet().iterator();
		while (iterator.hasNext())
		{
			Entry<String, Object> entry = iterator.next();

			if (StringUtils.isBlank(entry.getKey())
					|| !fieldList.contains(entry.getKey().toLowerCase())
					|| entry.getValue() == null)
			{
				iterator.remove();
			}
		}

		return keys.size() > 0;
	}

}
