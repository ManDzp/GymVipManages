package com.jm.vip.entity;

import java.util.Date;

/**
 * 板块管理
 */
public class ColumnInfo
{

	private String guid;// 板块唯一标识

	private String title;// 板块名称

	private String logourl;// 板块图片

	private Integer ordernum;// 排序号

	private Short status;// 状态 0：在编 1：发布

	private Date publishdate;// 发布时间

	private String creator;// 创建者

	private String creatorid;// 创建者唯一标识

	private Date createtime;// 创建时间

	public String getGuid()
	{
		return guid;
	}

	public void setGuid(String guid)
	{
		this.guid = guid == null ? null : guid.trim();
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title == null ? null : title.trim();
	}

	public String getLogourl()
	{
		return logourl;
	}

	public void setLogourl(String logourl)
	{
		this.logourl = logourl == null ? null : logourl.trim();
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

	public Date getPublishdate()
	{
		return publishdate;
	}

	public void setPublishdate(Date publishdate)
	{
		this.publishdate = publishdate;
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
}