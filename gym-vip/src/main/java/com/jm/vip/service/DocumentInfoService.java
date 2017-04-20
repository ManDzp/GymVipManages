package com.jm.vip.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jm.base.tool.CurrentUser;
import com.jm.log.LogProxy;
import com.jm.page.DataGridSource;
import com.jm.page.PageSearch;
import com.jm.utils.BaseUtils;
import com.jm.utils.DateHelper;
import com.jm.utils.SecurityUtil;
import com.jm.vip.dao.DeleteReasonDao;
import com.jm.vip.dao.DocumentInfoDao;
import com.jm.vip.entity.DeleteReason;
import com.jm.vip.entity.DocumentInfo;

/**
 * 帖子管理
 */
@Service
public class DocumentInfoService
{
	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "documentinfoDao")
	private DocumentInfoDao documentInfoDao;

	@Resource(name = "deletereasonDao")
	private DeleteReasonDao deleteReasonDao;

	/**
	 * 获取帖子信息
	 * @param guid 帖子唯一标识
	 * @return
	 */
	public DocumentInfo queryDocumentInfoByGuid(String guid)
	{
		try
		{
			return this.documentInfoDao.getByGuid(guid);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取帖子信息异常", e.toString(), guid);
			return null;
		}
	}

	/**
	 * 获取帖子列表
	 * @param number
	 * @return
	 */
	public JSONArray getDocumentList(int number)
	{
		JSONArray array = new JSONArray();

		List<DocumentInfo> list = this.documentInfoDao.getDocumentList(number);
		if (!CollectionUtils.isEmpty(list))
		{
			// 遍历列表
			for (DocumentInfo documentInfo : list)
			{
				JSONObject json = new JSONObject();

				String guid = documentInfo.getGuid();
				String title = documentInfo.getTitle();
				String creator = documentInfo.getCreator();
				String createtime = DateHelper.getFormatDate(
						documentInfo.getCreatetime(), "yyyy/MM/dd HH:mm:dd");

				json.put("guid", guid);
				json.put("title", title);
				json.put("creator", creator);
				json.put("createtime", createtime);

				array.add(json);
			}
		}

		return array;
	}

	/**
	 * 获取板块下帖子数
	 * @param columnguid 帖子板块标识
	 * @return
	 */
	public int getDocumentNum(String columnguid)
	{
		try
		{
			return this.documentInfoDao.getDocumentNum(columnguid);
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	/**
	 * 获取今日昨日总共帖子数
	 * @param type 0今天，1昨天，2全部
	 * @return
	 */
	public String getDayNum(String type)
	{
		String tongji = "暂无数据";
		try
		{
			if (type == "0")// 0今天，1昨天，2全部
			{
				String time = DateHelper.getCurrentStrDate();
				tongji = this.documentInfoDao.getTodayNum(time);
			}
			else if (type == "1")
			{
				String time = DateHelper
						.getDateToShort(DateHelper.getNextDate(-1));
				tongji = this.documentInfoDao.getTodayNum(time);
			}
			else if (type == "2")
			{
				tongji = this.documentInfoDao.getAllNum();
			}

			return tongji;
		}
		catch (Exception e)
		{
			return "";
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

		List<Object> list = this.documentInfoDao.getListByPage(pageSearch,
				pageNum, pageSize);

		PageInfo<Object> pageinfo = new PageInfo<Object>(list);

		// 返回数据和总数
		DataGridSource gridSource = new DataGridSource();
		gridSource.setRows(list);
		gridSource.setTotal(pageinfo.getTotal());
		return gridSource;
	}

	/**
	 * 添加帖子
	 * @param mdlDocument
	 * @param currUser 当前用户
	 * @return
	 */
	public String addDocumentInfo(DocumentInfo mdlDocument,
			CurrentUser currUser)
	{
		try
		{
			// 唯一标识
			String guid = BaseUtils.getNewGuid();

			initAddColumnInfo(guid, mdlDocument, currUser);

			this.documentInfoDao.insert(mdlDocument);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "添加帖子信息", guid,
					mdlDocument.getTitle());
			return guid;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "添加帖子信息异常", ex.toString(), mdlDocument);
			return "";
		}
	}

	/**
	 * 修改帖子
	 * @param mdlDocument
	 * @param currUser 当前用户
	 * @return
	 */
	public boolean updateDocumentInfo(DocumentInfo mdlDocument,
			CurrentUser currUser)
	{
		try
		{
			String guid = mdlDocument.getGuid();// 唯一标识
			String title = mdlDocument.getTitle();

			initEditDocumentInfo(mdlDocument);

			this.documentInfoDao.updateByGuid(mdlDocument);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "修改板块信息", guid, title);
			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "修改帖子信息异常", ex.toString(), mdlDocument);
			return false;
		}
	}

	/**
	 * 删除帖子列表并添加删除理由
	 * @param guidList 唯一标识列表
	 * @param content 删除理由
	 * @param currentUser 当前用户
	 * @return
	 */
	@Transactional
	public boolean deleteListWithReason(List<String> guidList, String content,
			CurrentUser currentUser)
	{
		try
		{
			for (String guid : guidList)
			{
				this.documentInfoDao.updateStatusByGuid(guid, (short) 1);

				DeleteReason deleteReason = new DeleteReason();

				deleteReason.setGuid(BaseUtils.getNewGuid());// 唯一标识
				deleteReason.setObjectguid(guid);
				deleteReason.setObjecttype(0);
				deleteReason.setContent(content);
				deleteReason.setCreator(currentUser.getUserName());
				deleteReason.setCreatorid(currentUser.getUserGuid());
				deleteReason.setCreatetime(DateHelper.getCurrentDate());

				this.deleteReasonDao.insert(deleteReason);
			}

			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "删除帖子列表并添加删除理由异常", ex.toString(),
					guidList, content);
			return false;
		}
	}

	/**
	 * 添加帖子初始化
	 * @param guid 唯一标识
	 * @param mdlColumn 板块信息
	 * @param currentUser 当前操作人
	 */
	private void initAddColumnInfo(String guid, DocumentInfo mdlColumn,
			CurrentUser currentUser)
	{
		// 标题SQL防注入
		String title = SecurityUtil.inPutAllFilter(mdlColumn.getTitle());
		mdlColumn.setTitle(title);
		mdlColumn.setCreator(currentUser.getUserName());
		mdlColumn.setCreatorid(currentUser.getUserGuid());
		mdlColumn.setGuid(guid);// 唯一标识
		mdlColumn.setStatus((short) 0);
		mdlColumn.setIstop((short) 0);
		mdlColumn.setIscream((short) 0);

		mdlColumn.setCreatetime(DateHelper.getCurrentDate());
	}

	/**
	 * 修改帖子初始化
	 * @param mdlColumn 板块信息
	 */
	private void initEditDocumentInfo(DocumentInfo mdlDocument)
	{
		// 标题SQL防注入
		String title = SecurityUtil.inPutAllFilter(mdlDocument.getTitle());
		mdlDocument.setTitle(title);
	}

	/**
	 * 修改帖子置顶状态
	 * @param guid 帖子唯一标识
	 * @param status 帖子状态
	 * @param currentUser 当前用户
	 * @return
	 */
	public boolean updateIsTop(String guid, Short istop,
			CurrentUser currentUser)
	{
		try
		{
			// 修改帖子置顶状态
			this.documentInfoDao.updateIsTopByGuid(guid, istop);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "修改帖子置顶状态", guid, "", istop);

			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "修改帖子置顶状态异常", ex.toString(), guid,
					istop, currentUser);
			return false;
		}
	}

	/**
	 * 修改帖子精华状态
	 * @param guid 帖子唯一标识
	 * @param status 帖子状态
	 * @param currentUser 当前用户
	 * @return
	 */
	public boolean updateIsCream(String guid, Short iscream,
			CurrentUser currentUser)
	{
		try
		{
			// 修改帖子精华状态
			this.documentInfoDao.updateIsCreamByGuid(guid, iscream);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "修改帖子精华状态", guid, "", iscream);

			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "修改帖子精华状态异常", ex.toString(), guid,
					iscream, currentUser);
			return false;
		}
	}

	/**
	 * 转移帖子
	 * @param guid 帖子唯一标识
	 * @param columnguid 板块唯一标识
	 * @param columnname 板块名称
	 * @return
	 */
	public boolean shiftDocument(String guid, String columnguid,
			String columnname)
	{
		try
		{
			this.documentInfoDao.shiftDocumentByGuid(guid, columnguid,
					columnname);
			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "转移帖子异常", ex.toString(), guid,
					columnguid, columnname);
			return false;
		}
	}

}
