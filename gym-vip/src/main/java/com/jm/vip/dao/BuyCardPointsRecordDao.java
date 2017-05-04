package com.jm.vip.dao;

import java.util.List;

import com.jm.vip.entity.BuyCardPointsRecord;

/**
 * 购买次数记录DAO
 */
public interface BuyCardPointsRecordDao extends BaseDao<BuyCardPointsRecord>
{
	/**
	 * 获取前几项购买次数记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<BuyCardPointsRecord> getInfoList(Integer topNum,
			String memberGuid);
}