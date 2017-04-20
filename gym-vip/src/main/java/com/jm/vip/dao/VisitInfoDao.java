package com.jm.vip.dao;

import com.jm.vip.entity.VisitInfo;

/**
 * 论坛浏览量DAO
 */
public interface VisitInfoDao extends BaseDao<VisitInfo>
{
	/**
	 * 获取某天论坛的浏览量
	 * @param browsetime
	 * @return
	 */
	public int getVisitNum(String browsetime);

	/**
	 * 获取某天论坛的回复帖子数
	 * @param createtime
	 * @return
	 */
	public int getReplyNum(String createtime);
	/**
	 * 获取某天论坛的发布帖子数
	 * @param createtime
	 * @return
	 */
	public int getsomedaytongjinumNum(String createtime);
	/**
	 * 获取某天论坛的删除帖子数
	 * @param createtime
	 * @return
	 */
	public int getdeletetongjiNum(String createtime);
}