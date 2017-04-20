package com.jm.vip.entity;

import java.util.Date;

/**
 * 论坛浏览量
 */
public class VisitInfo
{
	// 唯一标识
	private String guid;
	// 目标唯一标识
	private String objectguid;
	// 目标类型 0：首页，1：板块，2：帖子
	private Integer objecttype;
	// IP地址
	private String ip;
	// 浏览者
	private String creator;
	// 浏览者唯一标识
	private String creatorid;
	// 浏览时间
	private Date browsetime;

	public String getGuid()
	{
		return guid;
	}

	public void setGuid(String guid)
	{
		this.guid = guid == null ? null : guid.trim();
	}

	public String getObjectguid()
	{
		return objectguid;
	}

	public void setObjectguid(String objectguid)
	{
		this.objectguid = objectguid == null ? null : objectguid.trim();
	}

	public Integer getObjecttype()
	{
		return objecttype;
	}

	public void setObjecttype(Integer objecttype)
	{
		this.objecttype = objecttype;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip == null ? null : ip.trim();
	}

	public String getCreator()
	{
		return creator;
	}

	public void setCreator(String creator)
	{
		this.creator = creator == null ? null : creator.trim();
	}

	public String getCreatorid()
	{
		return creatorid;
	}

	public void setCreatorid(String creatorid)
	{
		this.creatorid = creatorid == null ? null : creatorid.trim();
	}

	public Date getBrowsetime()
	{
		return browsetime;
	}

	public void setBrowsetime(Date browsetime)
	{
		this.browsetime = browsetime;
	}
}