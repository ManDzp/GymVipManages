package com.jm.vip.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jm.commons.page.PageSearch;
import com.jm.vip.dao.LeaveRecordDao;
import com.jm.vip.entity.LeaveRecord;

/**
 * 请销假记录DAO实现
 */
@Repository("leaveRecordDao")
public class LeaveRecordDaoImpl extends BaseDaoImpl<LeaveRecord>
		implements LeaveRecordDao
{

	@Override
	public String getMapperId()
	{
		return "LeaveRecordMapper";
	}

	@Override
	public List<LeaveRecord> getInfoList(Integer topNum, String memberGuid)
	{
		PageSearch pageSearch = new PageSearch();
		Map<String, Object> whereMap = Maps.newHashMap();
		whereMap.put("memberguid", memberGuid);
		pageSearch.setWheresql(whereMap);// where条件

		RowBounds rowBounds = new RowBounds(1, topNum);
		return this.readSession.selectList(getMapperId() + ".selectListByPage",
				pageSearch, rowBounds);
	}

}
