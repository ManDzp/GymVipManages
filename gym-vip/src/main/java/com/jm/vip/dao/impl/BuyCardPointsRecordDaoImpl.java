package com.jm.vip.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jm.page.PageSearch;
import com.jm.vip.dao.BuyCardPointsRecordDao;
import com.jm.vip.entity.BuyCardPointsRecord;

/**
 * 购买次数记录DAO实现
 */
@Repository("buyCardPointsRecordDao")
public class BuyCardPointsRecordDaoImpl extends BaseDaoImpl<BuyCardPointsRecord>
		implements BuyCardPointsRecordDao
{

	@Override
	public String getMapperId()
	{
		return "BuyCardPointsRecordMapper";
	}

	@Override
	public List<BuyCardPointsRecord> getInfoList(Integer topNum,
			String memberGuid)
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
