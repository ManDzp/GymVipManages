package com.jm.vip.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.jm.page.PageSearch;
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
	public List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
			Integer pageSize)
	{
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		return this.readSession.selectList(getMapperId() + ".selectListByPage",
				pageSearch, rowBounds);
	}

}
