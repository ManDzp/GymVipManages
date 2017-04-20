package com.jm.vip.entity;

import java.util.Date;

/**
 * 删除理由
 */
public class DeleteReason
{
	// 唯一标识
	private String guid;
	// 目标唯一标识
	private String objectguid;
	// 目标类型 0：帖子
	private Integer objecttype;
	// 创建者
	private String creator;
	// 创建者唯一标识
	private String creatorid;
	// 创建时间
	private Date createtime;
	// 删除理由
	private String content;

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

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content == null ? null : content.trim();
	}
}