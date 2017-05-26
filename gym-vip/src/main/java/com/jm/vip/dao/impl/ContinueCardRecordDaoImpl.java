package com.jm.vip.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jm.commons.page.PageSearch;
import com.jm.vip.dao.ContinueCardRecordDao;
import com.jm.vip.entity.ContinueCardRecord;

/**
 * 续卡卡记录DAO实现
 */
@Repository("continueCardRecordDao")
public class ContinueCardRecordDaoImpl extends BaseDaoImpl<ContinueCardRecord>
		implements ContinueCardRecordDao
{

	@Override
	public String getMapperId()
	{
		return "ContinueCardRecordMapper";
	}

	@Override
	public List<ContinueCardRecord> getInfoList(Integer topNum,
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
