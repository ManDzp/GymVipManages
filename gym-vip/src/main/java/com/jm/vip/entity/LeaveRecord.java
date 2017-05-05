package com.jm.vip.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 请销假记录
 */
public class LeaveRecord
{
	/**
	 * 请销假唯一标识
	 */
	private String guid;
	/**
	 * 会员唯一标识
	 */
	private String memberguid;
	/**
	 * 请销假类型 0：请假，1：销假
	 */
	private String leavetype;
	/**
	 * 到期日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expiretime;
	/**
	 * 备注说明
	 */
	private String remark;
	/**
	 * 记录时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;
	/**
	 * 操作人
	 */
	private String creator;
	/**
	 * 操作人唯一标识
	 */
	private String creatorid;

	public String getGuid()
	{
		return guid;
	}

	public void setGuid(String guid)
	{
		this.guid = guid;
	}

	public String getMemberguid()
	{
		return memberguid;
	}

	public void setMemberguid(String memberguid)
	{
		this.memberguid = memberguid;
	}

	public String getLeavetype()
	{
		return leavetype;
	}

	public void setLeavetype(String leavetype)
	{
		this.leavetype = leavetype == null ? null : leavetype.trim();
	}

	public Date getExpiretime()
	{
		return expiretime;
	}

	public void setExpiretime(Date expiretime)
	{
		this.expiretime = expiretime;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
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
}