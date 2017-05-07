package com.jm.vip.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 积分记录
 */
public class PointsRecord
{
	/**
	 * 积分唯一标识
	 */
	private String guid;
	/**
	 * 会员唯一标识
	 */
	private String memberguid;
	/**
	 * 积分类型 0：获取积分，1：兑换积分
	 */
	private String pointtype;
	/**
	 * 变更积分
	 */
	private Integer points;
	/**
	 * 原始会员积分
	 */
	private Integer oldpoints;
	/**
	 * 现在会员积分
	 */
	private Integer newpoints;
	/**
	 * 原始到期日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date oldexpiretime;
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

	public String getPointtype()
	{
		return pointtype;
	}

	public void setPointtype(String pointtype)
	{
		this.pointtype = pointtype == null ? null : pointtype.trim();
	}

	public Integer getPoints()
	{
		return points;
	}

	public void setPoints(Integer points)
	{
		this.points = points;
	}

	public Integer getOldpoints()
	{
		return oldpoints;
	}

	public void setOldpoints(Integer oldpoints)
	{
		this.oldpoints = oldpoints;
	}

	public Integer getNewpoints()
	{
		return newpoints;
	}

	public void setNewpoints(Integer newpoints)
	{
		this.newpoints = newpoints;
	}

	public Date getOldexpiretime()
	{
		return oldexpiretime;
	}

	public void setOldexpiretime(Date oldexpiretime)
	{
		this.oldexpiretime = oldexpiretime;
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