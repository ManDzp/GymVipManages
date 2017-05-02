package com.jm.vip.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jm.page.PageSearch;
import com.jm.vip.dao.ChargeRecordDao;
import com.jm.vip.entity.ChargeRecord;

/**
 * 充值记录DAO实现
 */
@Repository("chargeRecordDao")
public class ChargeRecordDaoImpl extends BaseDaoImpl<ChargeRecord>
		implements ChargeRecordDao
{

	@Override
	public String getMapperId()
	{
		return "ChargeRecordMapper";
	}

	@Override
	public List<ChargeRecord> getInfoList(Integer topNum, String memberGuid)
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
