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

	/**
	 * 充值
	 * @param memberInfo
	 * @return
	 */
	public int charge(MemberInfo memberInfo);

	/**
	 * 买卡
	 * @param memberInfo
	 * @return
	 */
	public int buyCard(MemberInfo memberInfo);

	/**
	 * 消费
	 * @param memberInfo
	 * @return
	 */
	public int spend(MemberInfo memberInfo);

	/**
	 * 开卡
	 * @param memberInfo
	 * @return
	 */
	public int activeCard(MemberInfo memberInfo);

	/**
	 * 续卡
	 * @param memberInfo
	 * @return
	 */
	public int continueCard(MemberInfo memberInfo);

}