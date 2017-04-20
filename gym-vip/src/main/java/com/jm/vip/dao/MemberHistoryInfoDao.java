package com.jm.vip.dao;

import java.util.List;

import com.jm.page.PageSearch;
import com.jm.vip.entity.MemberHistoryInfo;
import com.jm.vip.entity.MemberInfo;

public interface MemberHistoryInfoDao extends BaseDao<MemberHistoryInfo>
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

	/**
	 * 备份数据
	 * @param memberInfo
	 * @return
	 */
	public int backupData(MemberInfo memberInfo);

}