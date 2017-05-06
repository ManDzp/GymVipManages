package com.jm.vip.dao;

import java.util.List;

import com.jm.vip.entity.PointsRecord;

/**
 * 积分记录DAO
 */
public interface PointsRecordDao extends BaseDao<PointsRecord>
{
	/**
	 * 获取前几项积分记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<PointsRecord> getInfoList(Integer topNum, String memberGuid);
}