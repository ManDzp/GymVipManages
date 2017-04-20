package com.jm.vip.entity;

import java.util.Date;

public class AdminInfo
{
	/**
	 * 人员唯一标识
	 */
	private String userguid;
	/**
	 * 登录名
	 */
	private String username;
	/**
	 * 登陆密码
	 */
	private String userpass;
	/**
	 * 显示名
	 */
	private String displayname;
	/**
	 * 创建时间
	 */
	private Date createtime;

	public String getUserguid()
	{
		return userguid;
	}

	public void setUserguid(String userguid)
	{
		this.userguid = userguid == null ? null : userguid.trim();
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username == null ? null : username.trim();
	}

	public String getUserpass()
	{
		return userpass;
	}

	public void setUserpass(String userpass)
	{
		this.userpass = userpass == null ? null : userpass.trim();
	}

	public String getDisplayname()
	{
		return displayname;
	}

	public void setDisplayname(String displayname)
	{
		this.displayname = displayname == null ? null : displayname.trim();
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

}