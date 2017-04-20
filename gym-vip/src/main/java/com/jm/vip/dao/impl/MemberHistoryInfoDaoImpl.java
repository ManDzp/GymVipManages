package com.jm.vip.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.jm.page.PageSearch;
import com.jm.vip.dao.MemberHistoryInfoDao;
import com.jm.vip.entity.MemberHistoryInfo;
import com.jm.vip.entity.MemberInfo;

/**
 * 
 */
@Repository("memberHistoryInfoDao")
public class MemberHistoryInfoDaoImpl extends BaseDaoImpl<MemberHistoryInfo>
		implements MemberHistoryInfoDao
{
	@Override
	public String getMapperId()
	{
		return "MemberHistoryInfoMapper";
	}

	@Override
	public List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
			Integer pageSize)
	{
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		return this.readSession.selectList(getMapperId() + ".selectListByPage",
				pageSearch, rowBounds);
	}

	@Override
	public int backupData(MemberInfo memberInfo)
	{
		return this.session.insert(getMapperId() + ".backupData", memberInfo);
	}
}
