package com.jm.vip.dao;

import java.util.List;

import com.jm.vip.entity.LeaveRecord;

/**
 * 请销假记录DAO
 */
public interface LeaveRecordDao extends BaseDao<LeaveRecord>
{
	/**
	 * 获取前几项请销假记录
	 * @param topNum
	 * @param memberGuid 会员资料
	 * @return
	 */
	public List<LeaveRecord> getInfoList(Integer topNum, String memberGuid);
}