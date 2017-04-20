package com.jm.vip.entity;

import java.util.Date;

/**
 * 帖子管理
 */
public class DocumentInfo
{
	private String guid;// 唯一标识

	private String columnguid;// 栏目唯一标识

	private String columnname;// 栏目标题

	private String title;// 标题

	private String type;// 帖子类型 ‘ 咨询、建议、提示、文件’

	private Short istop;// 是否置顶 0：否，1：是

	private Short iscream;// 是否精华 0：否，1：是

	private Integer ordernum;// 排序号

	private Short status;// 状态 0：正常，1：删除

	private String creator;// 创建者

	private String creatorid;// 创建者唯一标识

	private Date createtime;// 创建时间

	private String areacontent;// 内容

	public String getGuid()
	{
		return guid;
	}

	public void setGuid(String guid)
	{
		this.guid = guid == null ? null : guid.trim();
	}

	public String getColumnguid()
	{
		return columnguid;
	}

	public void setColumnguid(String columnguid)
	{
		this.columnguid = columnguid == null ? null : columnguid.trim();
	}

	public String getColumnname()
	{
		return columnname;
	}

	public void setColumnname(String columnname)
	{
		this.columnname = columnname == null ? null : columnname.trim();
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title == null ? null : title.trim();
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type == null ? null : type.trim();
	}

	public Short getIstop()
	{
		return istop;
	}

	public void setIstop(Short istop)
	{
		this.istop = istop;
	}

	public Short getIscream()
	{
		return iscream;
	}

	public void setIscream(Short iscream)
	{
		this.iscream = iscream;
	}

	public Integer getOrdernum()
	{
		return ordernum;
	}

	public void setOrdernum(Integer ordernum)
	{
		this.ordernum = ordernum;
	}

	public Short getStatus()
	{
		return status;
	}

	public void setStatus(Short status)
	{
		this.status = status;
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

	public String getAreacontent()
	{
		return areacontent;
	}

	public void setAreacontent(String areacontent)
	{
		this.areacontent = areacontent == null ? null : areacontent.trim();
	}
}
