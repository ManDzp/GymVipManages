package com.jm.vip.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jm.commons.page.PageSearch;
import com.jm.vip.dao.PointsRecordDao;
import com.jm.vip.entity.PointsRecord;

/**
 * 积分记录DAO实现
 */
@Repository("pointsRecordDao")
public class PointsRecordDaoImpl extends BaseDaoImpl<PointsRecord>
		implements PointsRecordDao
{

	@Override
	public String getMapperId()
	{
		return "PointsRecordMapper";
	}

	@Override
	public List<PointsRecord> getInfoList(Integer topNum, String memberGuid)
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
