package com.jm.vip.dao;

import java.util.List;

import com.jm.page.PageSearch;
import com.jm.vip.entity.MemberInfo;

public interface MemberInfoDao extends BaseDao<MemberInfo>
{
	/**
	 * 获取分页数据
	 * @param pageSearch
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
			Integer pageSize);
}