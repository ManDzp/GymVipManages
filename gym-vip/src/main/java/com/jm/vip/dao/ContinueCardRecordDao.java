package com.jm.vip.dao;

import java.util.List;

import com.jm.vip.entity.ContinueCardRecord;

/**
 * 续卡记录DAO
 */
public interface ContinueCardRecordDao extends BaseDao<ContinueCardRecord>
{
	/**
	 * 获取前几项续卡记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<ContinueCardRecord> getInfoList(Integer topNum,
			String memberGuid);
}