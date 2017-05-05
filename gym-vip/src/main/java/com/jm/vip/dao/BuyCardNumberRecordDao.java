package com.jm.vip.dao;

import java.util.List;

import com.jm.vip.entity.BuyCardNumberRecord;

/**
 * 购买次数记录DAO
 */
public interface BuyCardNumberRecordDao extends BaseDao<BuyCardNumberRecord>
{
	/**
	 * 获取前几项购买次数记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<BuyCardNumberRecord> getInfoList(Integer topNum,
			String memberGuid);
}