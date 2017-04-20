package com.jm.vip.entity;

import java.util.Date;

public class ReplyInfo
{
	// 唯一标识
	private String guid;

	// 帖子唯一标识
	private String docguid;

	// 栏目唯一标识
	private String columnguid;

	// 标题
	private String title;

	// 创建者
	private String creator;

	// 创建者唯一标识
	private String creatorid;

	// 创建时间
	private Date createtime;

	// 回复内容
	private String replycontent;

	public String getGuid()
	{
		return guid;
	}

	public void setGuid(String guid)
	{
		this.guid = guid == null ? null : guid.trim();
	}

	public String getDocguid()
	{
		return docguid;
	}

	public void setDocguid(String docguid)
	{
		this.docguid = docguid == null ? null : docguid.trim();
	}

	public String getColumnguid()
	{
		return columnguid;
	}

	public void setColumnguid(String columnguid)
	{
		this.columnguid = columnguid == null ? null : columnguid.trim();
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title == null ? null : title.trim();
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

	public String getReplycontent()
	{
		return replycontent;
	}

	public void setReplycontent(String replycontent)
	{
		this.replycontent = replycontent == null ? null : replycontent.trim();
	}
}