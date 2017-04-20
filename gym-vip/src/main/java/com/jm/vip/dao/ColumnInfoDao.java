package com.jm.vip.dao;

import java.util.List;

import com.jm.vip.entity.ColumnInfo;

/**
 * 板块管理Dao层
 */
public interface ColumnInfoDao extends BaseDao<ColumnInfo>
{
	/**
	 * 修改板块状态
	 * @param guid 板块唯一标识
	 * @param status 板块状态
	 * @return
	 */
	public int updateStatusByGuid(String guid, String status);

	/**
	 * 获取板块名称列表
	 * @return
	 */
	public List<ColumnInfo> getColumnList();

	/**
	 * 获取已发布板块列表
	 * @return
	 */
	public List<ColumnInfo> getPublishColumnList();
}
