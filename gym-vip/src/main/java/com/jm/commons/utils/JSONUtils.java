package com.jm.commons.utils;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

/**
 * JSON解析工具类
 */
public class JSONUtils
{
	/**
	 * 通过字段获取信息
	 * @param text json串
	 * @param fieldName 字段值
	 * @return
	 */
	public static String getByField(String text, String fieldName)
	{
		JSONObject jsonObject = JSONObject.parseObject(text);

		return getByField(jsonObject, fieldName);
	}

	/**
	 * 通过字段获取信息
	 * @param json json对象
	 * @param fieldName 字段值
	 * @return
	 */
	public static String getByField(JSONObject jsonObject, String fieldName)
	{
		if (jsonObject != null)
		{
			return jsonObject.getString(fieldName);
		}

		return null;
	}

	/**
	 * 通过字段获取信息列表
	 * @param list json对象列表
	 * @param field 字段值
	 * @return
	 */
	public static List<String> getListByField(List<JSONObject> list,
			String field)
	{
		List<String> guidList = Lists.newArrayList();

		for (JSONObject jsonObject : list)
		{
			guidList.add(jsonObject.getString(field));
		}

		return guidList;
	}

	/**
	 * 通过字段获取信息列表
	 * @param jsonArray json对象列表
	 * @param field 字段值
	 * @return
	 */
	public static List<String> getListByField(JSONArray jsonArray, String field)
	{
		List<String> guidList = Lists.newArrayList();

		for (int i = 0; i < jsonArray.size(); i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			guidList.add(jsonObject.getString(field));
		}

		return guidList;
	}

	/**
	 * 通过字段获取信息列表
	 * @param json json串
	 * @param fieldName 字段值
	 * @return
	 */
	public static List<String> getListByField(String json, String fieldName)
	{
		JSONArray jsonArray = JSONArray.parseArray(json);

		return getListByField(jsonArray, fieldName);
	}

}
