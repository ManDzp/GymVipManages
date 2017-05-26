package com.jm.commons.page;

/**
 * 方言配置
 */
public class DialectConfig
{
	// 方言信息
	private String dialect;
	
	/**
	 * 获取当前方言
	 * @return
	 */
	public String getDialect()
	{
		return dialect;
	}

	/**
	 * 设置方言信息
	 * @param dialect
	 */
	public void setDialect(String dialect)
	{
		this.dialect = dialect;
	}

	/**
	 * 获取特定方言对应的后缀
	 * @return
	 */
	public String getSuffix()
	{
		return "_" + dialect;
	}

}
