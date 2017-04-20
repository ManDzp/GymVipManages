package com.jm.vip.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.jm.page.PageSearch;
import com.jm.vip.dao.ReplyInfoDao;
import com.jm.vip.entity.ReplyInfo;

@Repository("replyInfoDao")
public class ReplyInfoDaoImpl extends BaseDaoImpl<ReplyInfo>
		implements ReplyInfoDao
{

	@Override
	public String getMapperId()
	{
		return "ReplyInfoMapper";
	}

	/**
	 * 获取最新回复列表
	 */
	@Override
	public List<ReplyInfo> getReplyList(int number)
	{
		RowBounds rowBounds = new RowBounds(1, number);
		return this.session.selectList(getMapperId() + ".selectReplyInfo", null,
				rowBounds);
	}

	/**
	 * 获取分页
	 */
	@Override
	public List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
			Integer pageSize)
	{
		String mapperid = getMapperId() + ".selectReplyInfoByPage";

		// 查询
		if (pageNum != null && pageSize != null)
		{
			// 分页查询
			RowBounds rowBounds = new RowBounds(pageNum, pageSize);
			return this.readSession.selectList(mapperid, pageSearch, rowBounds);
		}
		else
		{
			return this.readSession.selectList(mapperid, pageSearch);
		}
	}
}
