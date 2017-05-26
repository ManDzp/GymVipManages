package com.jm.commons.poi;

import java.net.URLDecoder;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.util.CollectionUtils;

import com.jm.commons.utils.ClassLoaderUtil;
import com.jm.commons.utils.XmlParser;

/**
 * 列表打印配置信息工厂
 */
public class PrintExcelConfigFactory
{
	/**
	 * 根据打印标识生成列表打印配置信息
	 * @param printId 打印标识
	 * @return
	 */
	public static PrintExcelConfig initPrintExcelConfig(String printId)
	{
		String docPath = getPrintConfigPath();

		if (docPath == null)
			return null;

		PrintExcelConfig excelConfig = null;

		Document doc = XmlParser.getDocument(docPath);
		Element root = XmlParser.getRootNode(doc);
		List<Element> list = XmlParser.getChildList(root);

		if (CollectionUtils.isEmpty(list))
			return null;

		for (Element element : list)
		{
			String id = XmlParser.attrValue(element, "ID");
			if (id.equals(printId))
			{
				PrintExcelConfigConverter converter = new PrintExcelConfigConverter();
				excelConfig = converter.convert(element);

				break;
			}
		}

		return excelConfig;
	}

	/**
	 * 获取打印配置文件路径
	 * @return
	 */
	private static String getPrintConfigPath()
	{
		try
		{
			String docPath = ClassLoaderUtil
					.getExtendResource("PrintTemplate/PrintConfig.xml")
					.getPath();
			docPath = URLDecoder.decode(docPath, "UTF-8");

			return docPath;
		}
		catch (Exception e)
		{
			return null;
		}
	}

}
