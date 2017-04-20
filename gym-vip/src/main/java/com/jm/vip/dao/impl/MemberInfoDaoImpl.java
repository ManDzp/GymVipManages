package com.jm.vip.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.jm.page.PageSearch;
import com.jm.vip.dao.MemberInfoDao;
import com.jm.vip.entity.MemberInfo;

/**
 * 
 */
@Repository("memberInfoDao")
public class MemberInfoDaoImpl extends BaseDaoImpl<MemberInfo>
		implements MemberInfoDao
{
	@Override
	public String getMapperId()
	{
		return "MemberInfoMapper";
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
