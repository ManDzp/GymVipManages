package com.jm.vip.dao;

import java.util.List;

import com.jm.vip.entity.SignRecord;

/**
 * 签到记录DAO
 */
public interface SignRecordDao extends BaseDao<SignRecord>
{
	/**
	 * 获取前几项签到记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<SignRecord> getInfoList(Integer topNum, String memberGuid);
}