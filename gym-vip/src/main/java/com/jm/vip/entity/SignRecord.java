package com.jm.vip.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 签到记录
 */
public class SignRecord
{
	/**
	 * 签到唯一标识
	 */
	private String guid;
	/**
	 * 会员唯一标识
	 */
	private String memberguid;
	/**
	 * 会员类型，0：时间卡，1：次卡
	 */
	private String cardtype;
	/**
	 * 原始可用次数
	 */
	private Integer oldtimes;
	/**
	 * 现在可用次数
	 */
	private Integer newtimes;
	/**
	 * 原始已用次数
	 */
	private Integer oldusedtimes;
	/**
	 * 现在已用次数
	 */
	private Integer newusedtimes;
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

	public String getCardtype()
	{
		return cardtype;
	}

	public void setCardtype(String cardtype)
	{
		this.cardtype = cardtype == null ? null : cardtype.trim();
	}

	public Integer getOldtimes()
	{
		return oldtimes;
	}

	public void setOldtimes(Integer oldtimes)
	{
		this.oldtimes = oldtimes;
	}

	public Integer getNewtimes()
	{
		return newtimes;
	}

	public void setNewtimes(Integer newtimes)
	{
		this.newtimes = newtimes;
	}

	public Integer getOldusedtimes()
	{
		return oldusedtimes;
	}

	public void setOldusedtimes(Integer oldusedtimes)
	{
		this.oldusedtimes = oldusedtimes;
	}

	public Integer getNewusedtimes()
	{
		return newusedtimes;
	}

	public void setNewusedtimes(Integer newusedtimes)
	{
		this.newusedtimes = newusedtimes;
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