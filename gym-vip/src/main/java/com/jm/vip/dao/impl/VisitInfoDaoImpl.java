package com.jm.vip.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jm.vip.dao.VisitInfoDao;
import com.jm.vip.entity.VisitInfo;

/**
 * 论坛浏览量DAO
 */
@Repository("visitInfoDao")
public class VisitInfoDaoImpl extends BaseDaoImpl<VisitInfo>
		implements VisitInfoDao
{

	@Override
	public String getMapperId()
	{
		return "VisitInfoMapper";
	}

	/**
	 * 获取某天论坛的浏览量
	 * @param browsetime 取浏览时间
	 * @return
	 */
	@Override
	public int getVisitNum(String browsetime)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("browsetime", browsetime);

		return this.session.selectOne(getMapperId() + ".selectNumBybrowsetime",
				param);
	}
	/**
	 * 获取某天论坛的发布帖子数
	 * @param createtime 取浏览时间
	 * @return
	 */
	@Override
	public int getsomedaytongjinumNum(String createtime)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("createtime", createtime);

		return this.session.selectOne(getMapperId() + ".selectNumBycreatetime",
				param);
	}

	/**
	 * 获取某天论坛的回复帖子数
	 * @param createtime 取回复时间
	 * @return
	 */
	@Override
	public int getReplyNum(String createtime)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("createtime", createtime);

		return this.session.selectOne(getMapperId() + ".selectNumBydocguid",
				param);
	}
	/**
	 * 获取某天论坛的删除帖子数
	 * @param createtime 取删除时间
	 * @return
	 */
	@Override
	public int getdeletetongjiNum(String createtime)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("createtime", createtime);

		return this.session.selectOne(getMapperId() + ".selectDeleteNumBycreatetime",
				param);
	}

}
