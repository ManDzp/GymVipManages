package com.jm.vip.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jm.vip.dao.ColumnInfoDao;
import com.jm.vip.entity.ColumnInfo;

/**
 * 板块管理Dao层实现
 */
@Repository("columninfoDao")
public class ColumnInfoDaoImpl extends BaseDaoImpl<ColumnInfo>
		implements ColumnInfoDao
{
	@Override
	public String getMapperId()
	{
		return "ColumnInfoMapper";
	}

	/**
	 * 修改板块状态
	 */
	@Override
	public int updateStatusByGuid(String guid, String status)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("guid", guid);
		param.put("status", status);

		return this.session.update(getMapperId() + ".updateStatusByGuid",
				param);
	}

	/**
	 * 获取板块名称列表
	 */
	@Override
	public List<ColumnInfo> getColumnList()
	{
		return this.session.selectList(getMapperId() + ".selectColumnList");
	}

	/**
	 * 获取已发布板块列表
	 * @return
	 */
	@Override
	public List<ColumnInfo> getPublishColumnList()
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", (short) 1);

		return this.session.selectList(getMapperId() + ".selectColumnList",
				param);
	}

}
