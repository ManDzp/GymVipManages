package com.jm.commons.poi;

/**
 * 列表打印配置信息类
 */
public class PrintExcelConfig
{
	// 工作簿名称
	// 文件名称
	String sheetName;
	// 当前表格的标题
	String titleName;
	// 取值数目
	String topnum;
	// 列名称
	String colName;
	// 排序方式
	String order;
	// 列名称数组
	String[] colNameArr;
	// 字段信息
	String filedValue;
	// 字段信息数组
	String[] fieldArr;
	// 字段显示配置数组
	String[] showArr;
	//
	boolean isHasValue;

	public String getSheetName()
	{
		return sheetName;
	}

	public void setSheetName(String sheetName)
	{
		this.sheetName = sheetName;
	}

	public String getTitleName()
	{
		return titleName;
	}

	public void setTitleName(String titleName)
	{
		this.titleName = titleName;
	}

	public String getTopnum()
	{
		return topnum;
	}

	public void setTopnum(String topnum)
	{
		this.topnum = topnum;
	}

	public String getColName()
	{
		return colName;
	}

	public void setColName(String colName)
	{
		this.colName = colName;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = order;
	}

	public String[] getColNameArr()
	{
		return colNameArr;
	}

	public void setColNameArr(String[] colNameArr)
	{
		this.colNameArr = colNameArr;
	}

	public String getFiledValue()
	{
		return filedValue;
	}

	public void setFiledValue(String filedValue)
	{
		this.filedValue = filedValue;
	}

	public String[] getFieldArr()
	{
		return fieldArr;
	}

	public void setFieldArr(String[] fieldArr)
	{
		this.fieldArr = fieldArr;
	}

	public String[] getShowArr()
	{
		return showArr;
	}

	public void setShowArr(String[] showArr)
	{
		this.showArr = showArr;
	}

	public boolean isHasValue()
	{
		return isHasValue;
	}

	public void setHasValue(boolean isHasValue)
	{
		this.isHasValue = isHasValue;
	}

}
