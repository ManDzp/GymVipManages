package com.jm.poi;

import org.dom4j.Element;

import com.jm.utils.XmlParser;

/**
 * 列表打印配置转换器
 */
public class PrintExcelConfigConverter
{
	/**
	 * 将xml信息转换成PrintExcelConfig
	 * @param element xml信息
	 * @return
	 */
	public PrintExcelConfig convert(Element element)
	{
		PrintExcelConfig excelConfig = new PrintExcelConfig();

		String topnum = XmlParser.attrValue(element, "topnum");
		String filedValue = XmlParser.attrValue(element, "FiledValue");
		String colName = XmlParser.attrValue(element, "ColName");
		String showType = XmlParser.attrValue(element, "showType");
		String remark = XmlParser.attrValue(element, "Remark");
		String order = XmlParser.attrValue(element, "order");

		excelConfig.setTopnum(topnum);
		excelConfig.setColName(colName);
		excelConfig.setColNameArr(colName.split(","));
		excelConfig.setFiledValue(filedValue);
		excelConfig.setFieldArr(filedValue.split(","));
		excelConfig.setShowArr(showType.split(","));
		excelConfig.setTitleName(remark);
		excelConfig.setSheetName(remark);
		excelConfig.setOrder(order);
		excelConfig.setHasValue(true);

		return excelConfig;
	}

}
