package com.jm.base.tool;

import org.springframework.stereotype.Component;

import com.jm.utils.BaseUtils;

@Component
public class SystemConfig
{
	private String systemTitle;

	public SystemConfig()
	{
		super();
		systemTitle = BaseUtils.getConfigValue("systemtitle");
	}

	public String getSystemTitle()
	{
		return systemTitle;
	}

	public void setSystemTitle(String systemTitle)
	{
		this.systemTitle = systemTitle;
	}

}
