package com.jm.vip.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageInfo;
import com.jm.base.tool.CurrentUser;
import com.jm.log.LogProxy;
import com.jm.page.DataGridSource;
import com.jm.page.PageSearch;
import com.jm.utils.BaseUtils;
import com.jm.utils.DateHelper;
import com.jm.utils.SecurityUtil;
import com.jm.vip.dao.ReplyInfoDao;
import com.jm.vip.entity.ReplyInfo;

@Service
public class ReplyInfoService
{
	/**
	 *  声明log4j
	 */
	private Logger log = Logger.getLogger(AdminInfoService.class);

	@Resource(name = "replyInfoDao")
	private ReplyInfoDao replyInfoDao;

	/**
	 * 获取查看页信息
	 */
	public ReplyInfo getReplyInfoViewByGuid(String guid)
	{
		try
		{
			return this.replyInfoDao.getByGuid(guid);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取帖子回复信息异常", e.toString(), guid);
			return null;
		}
	}

	/**
	 * 获取最新回复列表
	 * @return
	 */
	public List<ReplyInfo> getReplyList(int number)
	{
		try
		{
			return this.replyInfoDao.getReplyList(number);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 获取分页数据
	 * @param whereMap
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public DataGridSource getListByPage(Map<String, Object> whereMap,
			Integer pageNum, Integer pageSize) throws Exception
	{
		PageSearch pageSearch = new PageSearch();
		pageSearch.setWheresql(whereMap);// where条件

		List<Object> list = this.replyInfoDao.getListByPage(pageSearch, pageNum,
				pageSize);

		PageInfo<Object> pageinfo = new PageInfo<Object>(list);

		// 返回数据和总数
		DataGridSource gridSource = new DataGridSource();
		gridSource.setRows(list);
		gridSource.setTotal(pageinfo.getTotal());
		return gridSource;
	}

	/**
	 * 添加回复内容
	 */
	@Transactional
	public String addReplyInfo(ReplyInfo mdlReply, CurrentUser currUser)
	{
		try
		{
			// 唯一标识
			String guid = BaseUtils.getNewGuid();

			initAddReplyInfo(guid, mdlReply, currUser);

			this.replyInfoDao.insert(mdlReply);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "添加回复信息", guid, mdlReply.getTitle());
			return guid;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "添加回复异常", ex.toString(), mdlReply);
			return "";
		}
	}

	/**
	 * 修改注册账户信息
	 * @param mdlColumn
	 * @param currUser
	 * @return
	 */
	public boolean updateReplyInfo(ReplyInfo mdlReplyInfo, CurrentUser currUser)
	{
		try
		{
			this.replyInfoDao.updateByGuid(mdlReplyInfo);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "修改帖子回复信息", mdlReplyInfo.getGuid(),
					mdlReplyInfo.getTitle());
			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "修改帖子回复信息异常", ex.toString(),
					mdlReplyInfo);
			return false;
		}
	}

	/**
	 * 列表删除
	 * @param guidList
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public boolean deleteReplyInfoList(List<String> guidList,
			CurrentUser currentUser)
	{
		try
		{
			for (String guid : guidList)
			{
				this.replyInfoDao.deleteByGuid(guid);
			}

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "列表删除帖子回复", guidList, "");

			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "列表删除帖子回复", ex.toString(), guidList);
			return false;
		}
	}

	/**
	 * 添加回复内容初始化
	 * @param guid 唯一标识
	 * @param mdlReply 回复信息
	 * @param currentUser 当前操作人
	 */
	private void initAddReplyInfo(String guid, ReplyInfo mdlReply,
			CurrentUser currentUser)
	{
		mdlReply.setGuid(guid);// 唯一标识

		// 标题SQL防注入
		String title = SecurityUtil.inPutAllFilter(mdlReply.getTitle());
		mdlReply.setTitle(title);

		mdlReply.setCreator(currentUser.getUserName());
		mdlReply.setCreatorid(currentUser.getUserGuid());
		mdlReply.setCreatetime(DateHelper.getCurrentDate());
	}
}
