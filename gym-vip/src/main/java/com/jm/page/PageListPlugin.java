package com.jm.page;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;

/**
 * 页面列表插件
 */
@Component
public class PageListPlugin
{
	@Resource
	private SqlSession readSession;

	/**
	 * 获取分页数据
	 * @param whereMap
	 * @param orderby
	 * @param fields
	 * @param mapperid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public DataGridSource getListByPage(Map<String, Object> whereMap,
			String orderby, String mapperid, Integer pageNum, Integer pageSize)
					throws Exception
	{
		PageSearch pageSearch = new PageSearch();
		pageSearch.setWheresql(whereMap);// where条件
		pageSearch.setOrdersql(orderby);// 排序方式
		pageSearch.setMapperid(mapperid);// mapper的id

		List<Object> list = null;

		// 查询
		if (pageNum != null && pageSize != null)
		{
			// 分页查询
			RowBounds rowBounds = new RowBounds(pageNum, pageSize);
			list = this.readSession.selectList(mapperid, pageSearch, rowBounds);
		}
		else
		{
			list = this.readSession.selectList(mapperid, pageSearch);
		}

		PageInfo<Object> pageinfo = new PageInfo<Object>(list);

		// 返回数据和总数
		DataGridSource gridSource = new DataGridSource();
		gridSource.setRows(list);
		gridSource.setTotal(pageinfo.getTotal());
		return gridSource;
	}

	/**
	 * 获取列表打印数据
	 * @param whereMap
	 * @param orderby
	 * @param mapperid
	 * @return
	 * @throws Exception
	 */
	public List<Object> getPrintList(Map<String, Object> whereMap,
			String orderby, String mapperid) throws Exception
	{
		PageSearch pageSearch = new PageSearch();
		pageSearch.setWheresql(whereMap);// where条件
		pageSearch.setOrdersql(orderby);// 排序方式
		pageSearch.setMapperid(mapperid);// mapper的id

		// 查询
		return this.readSession.selectList(mapperid, pageSearch);
	}
}
