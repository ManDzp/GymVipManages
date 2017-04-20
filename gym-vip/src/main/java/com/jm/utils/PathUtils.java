package com.jm.utils;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

/**
 * 路径工具类
 */
public class PathUtils
{

	/**
	 * 获取物理路径
	 * @param path 路径数组
	 * @return
	 */
	public static String getPhysicsPath(String... path)
	{
		return StringUtils.join(path, File.separator);
	}

}
