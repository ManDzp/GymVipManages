package com.jm.vip.dao;

import java.util.List;

import com.jm.vip.entity.ActiveCardRecord;

/**
 * 开卡记录DAO
 */
public interface ActiveCardRecordDao extends BaseDao<ActiveCardRecord>
{
	/**
	 * 获取前几项开卡记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<ActiveCardRecord> getInfoList(Integer topNum, String memberGuid);
}