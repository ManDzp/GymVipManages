package com.jm.vip.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.jm.page.PageSearch;
import com.jm.vip.dao.DocumentInfoDao;
import com.jm.vip.entity.DocumentInfo;

/**
 * 帖子管理Dao层实现
 */
@Repository("documentinfoDao")
public class DocumentInfoDaoImpl extends BaseDaoImpl<DocumentInfo>
		implements DocumentInfoDao
{
	@Override
	public String getMapperId()
	{
		return "DocumentInfoMapper";
	}

	/**
	 * 分页数据查询
	 */
	@Override
	public List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
			Integer pageSize)
	{
		String mapperid = getMapperId() + ".selectListByPage";

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

	/**
	 * 获取帖子列表
	 */
	@Override
	public List<DocumentInfo> getDocumentList(int number)
	{
		RowBounds rowBounds = new RowBounds(1, number);
		return this.session.selectList(getMapperId() + ".selectDocument", null,
				rowBounds);
	}

	/**
	 * 获取板块下帖子数
	 */
	@Override
	public int getDocumentNum(String columnguid)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("columnguid", columnguid);

		return this.session.selectOne(getMapperId() + ".selectNumByColumn",
				param);
	}

	/**
	 * 获取今日帖子
	 */
	@Override
	public String getTodayNum(String time)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("time", time);

		return this.session.selectOne(getMapperId() + ".selectNumByCreatetime",
				param);
	}

	/**
	 * 获取帖子总数目
	 */
	@Override
	public String getAllNum()
	{
		return this.session.selectOne(getMapperId() + ".selectAllNum");
	}

	/**
	 * 修改置顶状态
	 */
	@Override
	public int updateIsTopByGuid(String guid, Short istop)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("guid", guid);
		param.put("istop", istop);

		return this.session.update(getMapperId() + ".updateIsTopByGuid", param);
	}

	/**
	 * 修改精华状态
	 */
	@Override
	public int updateIsCreamByGuid(String guid, Short iscream)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("guid", guid);
		param.put("iscream", iscream);

		return this.session.update(getMapperId() + ".updateIsCreamByGuid",
				param);
	}

	/**
	 * 修改状态
	 */
	@Override
	public int updateStatusByGuid(String guid, Short status)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("guid", guid);
		param.put("status", status);

		return this.session.update(getMapperId() + ".updateStatusByGuid",
				param);
	}

	/**
	 * 转移帖子
	 */
	@Override
	public int shiftDocumentByGuid(String guid, String columnguid,
			String columnname)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("guid", guid);
		param.put("columnguid", columnguid);
		param.put("columnname", columnname);

		return this.session.update(getMapperId() + ".shiftDocumentByGuid",
				param);
	}
}
