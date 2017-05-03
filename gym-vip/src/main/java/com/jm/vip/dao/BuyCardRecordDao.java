package com.jm.vip.dao;

import java.util.List;

import com.jm.vip.entity.BuyCardRecord;

/**
 * 买卡记录DAO
 */
public interface BuyCardRecordDao extends BaseDao<BuyCardRecord>
{
	/**
	 * 获取前几项买卡记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<BuyCardRecord> getInfoList(Integer topNum, String memberGuid);
}