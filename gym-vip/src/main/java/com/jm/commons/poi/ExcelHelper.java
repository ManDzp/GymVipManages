package com.jm.commons.poi;

import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.alibaba.fastjson.JSONObject;
import com.jm.commons.utils.DateHelper;

/**
 * 打印Excel帮助类
 */
public class ExcelHelper
{
	/**
	 * 创建表
	 * @param excelConfig Excel配置信息
	 * @param list 打印数据源
	 * @return
	 */
	public static HSSFWorkbook createWorkbook(PrintExcelConfig excelConfig,
			List<Object> list)
	{
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = workbook.createSheet(excelConfig.getSheetName());

		// 第三步，创建标题行
		createTitleRow(excelConfig, workbook, sheet);

		// 第四步，创建列名行
		createColumnRow(excelConfig, workbook, sheet);

		// 第四步，创建数据行
		createDataRow(excelConfig, workbook, sheet, list);

		return workbook;
	}

	/**
	 * 创建标题行
	 * @param excelConfig
	 * @param workbook
	 * @param sheet
	 */
	private static void createTitleRow(PrintExcelConfig excelConfig,
			HSSFWorkbook workbook, HSSFSheet sheet)
	{
		// 标题行样式
		ExcelCellStyle exs = new ExcelCellStyle(workbook);
		HSSFCellStyle titleStyle = exs.createTitleCellStyle();

		// 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

		// 标题行
		HSSFRow firstRow = sheet.createRow(0);

		// 设置每列的宽度
		for (int index = 0; index < excelConfig.getColNameArr().length; index++)
		{
			sheet.setColumnWidth(index + 1, 15 * 256);
		}

		// 合并第一行中的所有列
		CellRangeAddress region = new CellRangeAddress(0, 0, 0,
				excelConfig.getColNameArr().length); // 参数都是从0开始
		sheet.addMergedRegion(region);

		// 设置标题及样式
		HSSFCell firstCell = firstRow.createCell(0);
		firstCell.setCellValue(excelConfig.getTitleName());
		firstCell.setCellStyle(titleStyle);

		// 设置行高
		firstRow.setHeight((short) (30 * 20));
	}

	/**
	 * 创建列名行
	 * @param excelConfig
	 * @param workbook
	 * @param sheet
	 */
	private static void createColumnRow(PrintExcelConfig excelConfig,
			HSSFWorkbook workbook, HSSFSheet sheet)
	{
		// 列名行样式
		ExcelCellStyle exs = new ExcelCellStyle(workbook);
		HSSFCellStyle columnstyle = exs.createColumnCellStyle();

		// 列名行
		HSSFRow columnRow = sheet.createRow(1);

		// 第一列，序号列
		HSSFCell columnFirstCell = columnRow.createCell(0);
		columnFirstCell.setCellValue("序号");
		columnFirstCell.setCellStyle(columnstyle);

		// 其他列，字段列
		for (int index = 0; index < excelConfig.getColNameArr().length; index++)
		{
			HSSFCell cell = columnRow.createCell(index + 1);
			cell.setCellValue(excelConfig.getColNameArr()[index]);
			cell.setCellStyle(columnstyle);
		}

		// 设置行高
		columnRow.setHeight((short) (25 * 20));
	}

	/**
	 * 创建数据行
	 * @param excelConfig
	 * @param workbook
	 * @param sheet
	 * @param list
	 */
	private static void createDataRow(PrintExcelConfig excelConfig,
			HSSFWorkbook workbook, HSSFSheet sheet, List<Object> list)
	{
		// 显示字段
		String[] filedArr = excelConfig.getFieldArr();

		// 数据行样式
		ExcelCellStyle exs = new ExcelCellStyle(workbook);
		HSSFCellStyle dataStyle = exs.createDataCellStyle();

		// 第五步，设置数据
		// 数据行，从第3行开始
		for (int i = 0; i < list.size(); i++)
		{
			HSSFRow dataRow = sheet.createRow(i + 2);

			// 第一列，序号列
			HSSFCell dataFirstCell = dataRow.createCell(0);
			dataFirstCell.setCellValue(i + 1);
			dataFirstCell.setCellStyle(dataStyle);

			Object object = list.get(i);
			JSONObject jsonObject = (JSONObject) JSONObject.toJSON(object);

			// 遍历列
			for (int j = 0; j < filedArr.length; j++)
			{
				HSSFCell dataCell = dataRow.createCell(j + 1);
				dataCell.setCellStyle(dataStyle);

				Object value = jsonObject.get(filedArr[j]);

				if (value == null)
					continue;

				// 对日期，特殊处理
				if (value instanceof Date)
				{
					value = DateHelper.getDateToString((Date) value);
				}

				// 其它格式，特殊处理

				dataCell.setCellValue(value.toString());
			}
		}
	}

}
