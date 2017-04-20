package com.jm.page;

import java.util.Map;

/**
 * 列表查询信息
 */
public class PageSearch
{
	// 查询条件
	private Map<String, Object> wheresql;
	// 排序方式
	private String ordersql;
	// SQL语句
	private String mapperid;
	// 方言
	private String dialect;

	/**
	 * 获取where条件参数
	 * @return
	 */
	public Map<String, Object> getWheresql()
	{
		return wheresql;
	}

	/**
	 * 设置where条件参数
	 * @param wheresql
	 */
	public void setWheresql(Map<String, Object> wheresql)
	{
		this.wheresql = wheresql;
	}

	/**
	 * 获取排序方式
	 * @return
	 */
	public String getOrdersql()
	{
		return ordersql;
	}

	/**
	 * 设置排序方式
	 * @param ordersql
	 */
	public void setOrdersql(String ordersql)
	{
		this.ordersql = ordersql;
	}

	/**
	 * 获取SQL语句
	 * @return
	 */
	public String getMapperid()
	{
		return mapperid;
	}

	/**
	 * 设置SQL语句
	 * @param mapperid
	 */
	public void setMapperid(String mapperid)
	{
		this.mapperid = mapperid;
	}

	public String getDialect()
	{
		return dialect;
	}

	public void setDialect(String dialect)
	{
		this.dialect = dialect;
	}

}
