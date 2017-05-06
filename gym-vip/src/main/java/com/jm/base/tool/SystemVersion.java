package com.jm.base.tool;

import org.springframework.stereotype.Component;

@Component
public class SystemVersion
{
	/**
	 * 静态资源版本号，每更新一次，都要加1
	 */
	private static final String RESOURCES_VERSION = "5";

	@Override
	public String toString()
	{
		return "?v=" + RESOURCES_VERSION;
	}

}
