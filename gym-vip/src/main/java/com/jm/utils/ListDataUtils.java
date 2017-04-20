package com.jm.utils;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * 列表数据工具类
 */
public class ListDataUtils
{
	/**
	 * 根据父标识获取子信息列表
	 * @param list 列表
	 * @param parentGuid 父唯一标识
	 * @return
	 */
	public static <T> List<T> getListByParentGuid(List<T> list,
			String parentGuid)
	{
		List<T> childList = Lists.newArrayList();

		if (CollectionUtils.isEmpty(list))
		{
			return childList;
		}

		for (T obj : list)
		{
			if (parentGuid.equals(ReflectUtils.getter(obj, "parentguid")))
			{
				childList.add(obj);
			}
		}

		return childList;
	}
}