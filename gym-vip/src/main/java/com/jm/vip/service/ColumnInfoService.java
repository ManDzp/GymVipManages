package com.jm.vip.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jm.base.tool.CurrentUser;
import com.jm.log.LogProxy;
import com.jm.utils.BaseUtils;
import com.jm.utils.DateHelper;
import com.jm.utils.SecurityUtil;
import com.jm.vip.dao.ColumnInfoDao;
import com.jm.vip.entity.ColumnInfo;

@Service
public class ColumnInfoService
{
	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "columninfoDao")
	private ColumnInfoDao columnInfoDao;

	@Resource(name = "documentInfoService")
	private DocumentInfoService documentInfoService;

	/**
	 * 获取板块信息
	 */
	public ColumnInfo queryColumnInfoByGuid(String guid)
	{
		try
		{
			return this.columnInfoDao.getByGuid(guid);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取板块信息异常", e.toString(), guid);
			return null;
		}
	}

	/**
	 * 获取板块名称列表
	 * @return
	 */
	public JSONArray getPublishColumnList()
	{
		JSONArray array = new JSONArray();

		List<ColumnInfo> list = this.columnInfoDao.getPublishColumnList();
		if (!CollectionUtils.isEmpty(list))
		{
			// 遍历列表
			for (ColumnInfo columnInfo : list)
			{
				JSONObject json = new JSONObject();

				String title = columnInfo.getTitle();
				json.put("title", title);

				String columnguid = columnInfo.getGuid();
				json.put("columnguid", columnguid);

				int documentNum = this.documentInfoService
						.getDocumentNum(columnguid);
				json.put("documentNum", documentNum);

				array.add(json);
			}
		}

		return array;
	}

	/**
	 * 获取板块列表
	 * @return
	 */
	public List<ColumnInfo> getColumnList()
	{
		try
		{
			return this.columnInfoDao.getColumnList();
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取板块列表异常", e.toString());
			return null;
		}
	}

	/**
	 * 添加板块
	 * @param columnInfo 板块信息
	 * @param currentUser 当前用户信息
	 * @return
	 */
	@Transactional
	public String addColumnInfo(ColumnInfo columnInfo, CurrentUser currentUser)
	{
		try
		{
			// 唯一标识
			String guid = BaseUtils.getNewGuid();

			initAddColumnInfo(guid, columnInfo, currentUser);

			this.columnInfoDao.insert(columnInfo);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "添加板块信息", guid,
					columnInfo.getTitle());
			return guid;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "添加板块信息异常", ex.toString(), columnInfo);
			return "";
		}
	}

	/**
	 * 修改板块
	 * @param columnInfo 板块信息
	 * @param currentUser 当前用户信息
	 * @return
	 */
	@Transactional
	public boolean updateColumn(ColumnInfo columnInfo, CurrentUser currentUser)
	{
		try
		{
			initEditColumnInfo(columnInfo);

			this.columnInfoDao.updateByGuid(columnInfo);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "修改板块信息", columnInfo.getGuid(),
					columnInfo.getTitle());
			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "修改板块信息异常", ex.toString(), columnInfo);
			return false;
		}
	}

	/**
	 * 修改板块状态
	 * @param guid 板块唯一标识
	 * @param status 板块状态
	 * @param currentUser 当前用户
	 * @return
	 */
	public boolean updateStatus(String guid, String status,
			CurrentUser currentUser)
	{
		try
		{
			// 更新状态
			this.columnInfoDao.updateStatusByGuid(guid, status);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "修改版块信息状态", guid, "");

			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "修改版块信息状态异常", ex.toString(), guid,
					status, currentUser);
			return false;
		}
	}

	/**
	 * 删除板块列表
	 * @param guidList 板块唯一标识列表
	 * @param titleList 板块名称列表
	 * @param currentUser 当前用户
	 * @return
	 */
	@Transactional
	public boolean deleteColumnList(List<String> guidList,
			List<String> titleList, CurrentUser currentUser)
	{
		try
		{
			for (String guid : guidList)
			{
				// 判断当前板块的状态，只有在编状态的板块才可以删除
				ColumnInfo columnInfo = queryColumnInfoByGuid(guid);
				if (columnInfo.getStatus() == 0)
				{
					this.columnInfoDao.deleteByGuid(guid);
				}
				// TODO 判断是否有文章，有文章则不能删除
			}

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "删除板块列表", guidList, titleList);

			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "删除板块列表", ex.toString(), guidList,
					titleList);
			return false;
		}
	}

	/**
	 * 添加板块初始化
	 * @param guid 唯一标识
	 * @param mdlColumn 板块信息
	 * @param currentUser 当前操作人
	 */
	private void initAddColumnInfo(String guid, ColumnInfo mdlColumn,
			CurrentUser currentUser)
	{
		mdlColumn.setGuid(guid);// 唯一标识

		// 标题SQL防注入
		String title = SecurityUtil.inPutAllFilter(mdlColumn.getTitle());
		mdlColumn.setTitle(title);

		mdlColumn.setCreator(currentUser.getUserName());
		mdlColumn.setCreatorid(currentUser.getUserGuid());
		mdlColumn.setCreatetime(DateHelper.getCurrentDate());
		mdlColumn.setStatus((short) 0);
	}

	/**
	 * 修改板块信息初始化
	 * @param mdlColumn 板块信息
	 */
	private void initEditColumnInfo(ColumnInfo mdlColumn)
	{
		// 标题SQL防注入
		String title = SecurityUtil.inPutAllFilter(mdlColumn.getTitle());
		mdlColumn.setTitle(title);
	}

}
