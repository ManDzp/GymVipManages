package com.jm.vip.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jm.commons.page.PageSearch;
import com.jm.vip.dao.SignRecordDao;
import com.jm.vip.entity.SignRecord;

/**
 * 签到记录DAO实现
 */
@Repository("signRecordDao")
public class SignRecordDaoImpl extends BaseDaoImpl<SignRecord>
		implements SignRecordDao
{

	@Override
	public String getMapperId()
	{
		return "SignRecordMapper";
	}

	@Override
	public List<SignRecord> getInfoList(Integer topNum, String memberGuid)
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
