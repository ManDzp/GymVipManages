package com.jm.vip.dao;

import java.util.List;

import com.jm.commons.page.PageSearch;
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
	 * 查找会员卡号信息
	 * @param cardNumber 会员卡号
	 * @return
	 */
	public MemberHistoryInfo getMemberHistoryByCardNumber(String cardNumber);

	/**
	 * 备份数据
	 * @param memberInfo
	 * @return
	 */
	public int backupData(MemberInfo memberInfo);

}