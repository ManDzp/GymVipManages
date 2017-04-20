package com.jm.vip.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.jm.log.LogProxy;
import com.jm.utils.BaseUtils;
import com.jm.utils.DateHelper;
import com.jm.vip.dao.VisitInfoDao;
import com.jm.vip.entity.VisitInfo;

/**
 * 论坛浏览量业务逻辑层
 */
@Service
public class VisitInfoService
{
	/**
	 *  声明log4j
	 */
	private Logger log = Logger.getLogger(AdminInfoService.class);

	@Resource(name = "visitInfoDao")
	private VisitInfoDao visitInfoDao;

	/**
	 * 记录论坛浏览量到visitInfo表中
	 * @param visitInfo
	 * @return
	 */
	public boolean insertVisitInfo(VisitInfo visitInfo)
	{
		// 唯一标识
		String guid = BaseUtils.getNewGuid();
		visitInfo.setGuid(guid);
		visitInfo.setBrowsetime(DateHelper.getCurrentDate());

		try
		{
			this.visitInfoDao.insert(visitInfo);
			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "记录论坛浏览量异常", ex.toString(), visitInfo);
			return false;
		}
	}

	/**
	 * 获取某天论坛的浏览量
	 * @param browsetime
	 * @return
	 */
	public int getVisitNum(String browsetime)
	{
		try
		{
			return this.visitInfoDao.getVisitNum(browsetime);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取某天的浏览量异常", ex.toString(),
					browsetime);
			return 0;
		}
	}

	/**
	 * 获取某天论坛的回复帖子数
	 * @param createtime
	 * @return
	 */
	public int getReplyNum(String createtime)
	{
		try
		{
			return this.visitInfoDao.getReplyNum(createtime);

		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取某天的回复帖子数异常", ex.toString(),
					createtime);
			return 0;
		}
	}
	/**
	 * 获取某天论坛的发布帖子数
	 * @param browsetime
	 * @return
	 */
	public int getsomedaytongjinumNum(String createtime)
	{
		try
		{
			return this.visitInfoDao.getsomedaytongjinumNum(createtime);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取某天的发布帖子数异常", ex.toString(),
					createtime);
			return 0;
		}
	}
	/**
	 * 获取某天论坛的删除的帖子数
	 * @param browsetime
	 * @return
	 */
	public int getdeletetongjiNum(String createtime)
	{
		try
		{
			return this.visitInfoDao.getdeletetongjiNum(createtime);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取某天的删除帖子数异常", ex.toString(),
					createtime);
			return 0;
		}
	}
}
