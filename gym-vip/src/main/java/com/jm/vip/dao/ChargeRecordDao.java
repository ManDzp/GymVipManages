package com.jm.vip.dao;

import java.util.List;

import com.jm.vip.entity.ChargeRecord;

/**
 * 充值记录DAO
 */
public interface ChargeRecordDao extends BaseDao<ChargeRecord>
{
	/**
	 * 获取前几项充值记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<ChargeRecord> getInfoList(Integer topNum, String memberGuid);
}