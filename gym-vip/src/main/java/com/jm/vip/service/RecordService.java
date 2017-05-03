package com.jm.vip.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jm.log.LogProxy;
import com.jm.vip.dao.ActiveCardRecordDao;
import com.jm.vip.dao.BuyCardRecordDao;
import com.jm.vip.dao.ChargeRecordDao;
import com.jm.vip.dao.SignRecordDao;
import com.jm.vip.entity.ActiveCardRecord;
import com.jm.vip.entity.BuyCardRecord;
import com.jm.vip.entity.ChargeRecord;
import com.jm.vip.entity.SignRecord;

/**
 * 查看记录业务逻辑层
 */
@Service
public class RecordService
{
	/**
	 *  声明log4j
	 */
	private Logger log = Logger.getLogger(RecordService.class);

	@Resource(name = "signRecordDao")
	private SignRecordDao signRecordDao;

	@Resource(name = "chargeRecordDao")
	private ChargeRecordDao chargeRecordDao;

	@Resource(name = "buyCardRecordDao")
	private BuyCardRecordDao buyCardRecordDao;

	@Resource(name = "activeCardRecordDao")
	private ActiveCardRecordDao activeCardRecordDao;

	/**
	 * 获取前几项签到记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<SignRecord> getSignRecordList(Integer topNum, String memberGuid)
	{
		try
		{
			return this.signRecordDao.getInfoList(topNum, memberGuid);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取前几项签到记录异常", e.toString(), topNum,
					memberGuid);
			return null;
		}
	}

	/**
	 * 获取前几项充值记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<ChargeRecord> getChargeRecordList(Integer topNum,
			String memberGuid)
	{
		try
		{
			return this.chargeRecordDao.getInfoList(topNum, memberGuid);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取前几项充值记录异常", e.toString(), topNum,
					memberGuid);
			return null;
		}
	}

	/**
	 * 获取前几项买卡记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<BuyCardRecord> getBuyCardRecordList(Integer topNum,
			String memberGuid)
	{
		try
		{
			return this.buyCardRecordDao.getInfoList(topNum, memberGuid);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取前几项买卡记录异常", e.toString(), topNum,
					memberGuid);
			return null;
		}
	}

	/**
	 * 获取前几项开卡记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<ActiveCardRecord> getActiveCardRecordList(Integer topNum,
			String memberGuid)
	{
		try
		{
			return this.activeCardRecordDao.getInfoList(topNum, memberGuid);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取前几项开卡记录异常", e.toString(), topNum,
					memberGuid);
			return null;
		}
	}
}
