package com.jm.vip.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 买卡记录
 */
public class BuyCardNumberRecord
{
	/**
	 * 买卡唯一标识
	 */
	private String guid;
	/**
	 * 会员唯一标识
	 */
	private String memberguid;
	/**
	 * 消费金额
	 */
	private Double money;
	/**
	 * 使用次数
	 */
	private Integer number;
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
	 * 原始卡内余额
	 */
	private Double oldbalance;
	/**
	 * 现在卡内余额
	 */
	private Double newbalance;
	/**
	 * 原始累计消费
	 */
	private Double oldconsumption;
	/**
	 * 现在累计消费
	 */
	private Double newconsumption;
	/**
	 * 原始可用次数
	 */
	private Integer oldtimes;
	/**
	 * 现在可用次数
	 */
	private Integer newtimes;
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

	public Double getMoney()
	{
		return money;
	}

	public void setMoney(Double money)
	{
		this.money = money;
	}

	public Integer getNumber()
	{
		return number;
	}

	public void setNumber(Integer number)
	{
		this.number = number;
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

	public Double getOldbalance()
	{
		return oldbalance;
	}

	public void setOldbalance(Double oldbalance)
	{
		this.oldbalance = oldbalance;
	}

	public Double getNewbalance()
	{
		return newbalance;
	}

	public void setNewbalance(Double newbalance)
	{
		this.newbalance = newbalance;
	}

	public Double getOldconsumption()
	{
		return oldconsumption;
	}

	public void setOldconsumption(Double oldconsumption)
	{
		this.oldconsumption = oldconsumption;
	}

	public Double getNewconsumption()
	{
		return newconsumption;
	}

	public void setNewconsumption(Double newconsumption)
	{
		this.newconsumption = newconsumption;
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