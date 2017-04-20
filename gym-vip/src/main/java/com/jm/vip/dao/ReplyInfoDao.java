package com.jm.vip.dao;

import java.util.List;

import com.jm.page.PageSearch;
import com.jm.vip.entity.ReplyInfo;

public interface ReplyInfoDao extends BaseDao<ReplyInfo>
{
	/**
	 * 获取最新回复列表
	 * @param number
	 * @return
	 */
	public List<ReplyInfo> getReplyList(int number);

	/**
	 * 获取分页数据列表
	 * @param pageSearch
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
			Integer pageSize);
}