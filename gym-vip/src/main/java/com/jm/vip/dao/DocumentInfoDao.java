package com.jm.vip.dao;

import java.util.List;

import com.jm.page.PageSearch;
import com.jm.vip.entity.DocumentInfo;

/**
 *  帖子管理
 */
public interface DocumentInfoDao extends BaseDao<DocumentInfo>
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
	 * 获取发帖列表
	 * @param number 帖子数目
	 * @return
	 */
	public List<DocumentInfo> getDocumentList(int number);

	/**
	 * 获取板块下帖子数
	 * @param columnguid 板块唯一标识
	 * @return
	 */
	public int getDocumentNum(String columnguid);

	/**
	 * 获取今天帖子数
	 * @param time 时间
	 * @return
	 */
	public String getTodayNum(String time);

	/**
	 * 获取帖子总数
	 * @return
	 */
	public String getAllNum();

	/**
	 * 修改精华状态
	 * @param guid 帖子唯一标识
	 * @param iscream 精华
	 * @return
	 */
	public int updateIsCreamByGuid(String guid, Short iscream);

	/**
	 * 修改置顶状态
	 * @param guid 帖子唯一标识
	 * @param istop 置顶
	 * @return
	 */
	public int updateIsTopByGuid(String guid, Short istop);

	/**
	 * 修改状态
	 * @param guid 帖子唯一标识
	 * @param status 板块状态
	 * @return
	 */
	public int updateStatusByGuid(String guid, Short status);

	/**
	 * 转移帖子
	 * @param guid 帖子唯一标识
	 * @param columnguid 板块唯一标识
	 * @param columnname 板块名称
	 * @return
	 */
	public int shiftDocumentByGuid(String guid, String columnguid,
			String columnname);
}
