package com.jm.vip.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jm.page.PageSearch;
import com.jm.vip.dao.BuyCardNumberRecordDao;
import com.jm.vip.entity.BuyCardNumberRecord;

/**
 * 购买次数记录DAO实现
 */
@Repository("buyCardNumberRecordDao")
public class BuyCardNumberRecordDaoImpl extends BaseDaoImpl<BuyCardNumberRecord>
		implements BuyCardNumberRecordDao
{

	@Override
	public String getMapperId()
	{
		return "BuyCardNumberRecordMapper";
	}

	@Override
	public List<BuyCardNumberRecord> getInfoList(Integer topNum,
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
