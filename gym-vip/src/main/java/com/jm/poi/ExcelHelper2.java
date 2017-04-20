package com.jm.poi;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 打印Excel帮助类
 */
public class ExcelHelper2
{
	/**
	 * 创建表
	 * @param excelConfig Excel配置信息
	 * @param list 打印数据源
	 * @return
	 */
	public static HSSFWorkbook createWorkbook(PrintExcelConfig excelConfig,
			List<List<Object>> list)
	{
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = workbook.createSheet(excelConfig.getSheetName());

		// 第三步，创建标题行
		createTitleRow(excelConfig, workbook, sheet);

		// 第四步，创建数据行
		createDataRow(excelConfig, workbook, sheet, list);

		return workbook;
	}

	/**
	 * 创建表
	 * @param excelConfig Excel配置信息
	 * @param array 待打印数组
	 * @return
	 */
	public static HSSFWorkbook createWorkbook(PrintExcelConfig excelConfig,
			String[][] array)
	{
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = workbook.createSheet(excelConfig.getSheetName());

		// 第三步，创建标题行
		createTitleRow(excelConfig, workbook, sheet);

		// 第四步，创建数据行
		createDataRow(excelConfig, workbook, sheet, array);

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
			sheet.setColumnWidth(index, 15 * 256);
		}

		// 合并第一行中的所有列
		CellRangeAddress region = new CellRangeAddress(0, 0, 0,
				excelConfig.getColNameArr().length - 1); // 参数都是从0开始
		sheet.addMergedRegion(region);

		// 设置标题及样式
		HSSFCell firstCell = firstRow.createCell(0);
		firstCell.setCellValue(excelConfig.getTitleName());
		firstCell.setCellStyle(titleStyle);

		// 设置行高
		firstRow.setHeight((short) (30 * 20));
	}

	/**
	 * 创建数据行
	 * @param excelConfig
	 * @param workbook
	 * @param sheet
	 * @param list
	 */
	private static void createDataRow(PrintExcelConfig excelConfig,
			HSSFWorkbook workbook, HSSFSheet sheet, List<List<Object>> list)
	{
		// 数据行样式
		ExcelCellStyle exs = new ExcelCellStyle(workbook);
		HSSFCellStyle dataStyle = exs.createDataCellStyle();

		// 第五步，设置数据
		// 数据行，从第3行开始
		for (int i = 0; i < list.size(); i++)
		{
			HSSFRow dataRow = sheet.createRow(i + 1);

			List<Object> rowList = list.get(i);

			// 在row行上创建一个方格
			for (int j = 0; j < rowList.size(); j++)
			{
				HSSFCell dataCell = dataRow.createCell(j);
				dataCell.setCellStyle(dataStyle);

				Object value = rowList.get(j);

				if (value == null)
					continue;

				dataCell.setCellValue(value.toString());
			}
		}
	}

	/**
	 * 创建数据行
	 * @param excelConfig
	 * @param workbook
	 * @param sheet
	 * @param array
	 */
	private static void createDataRow(PrintExcelConfig excelConfig,
			HSSFWorkbook workbook, HSSFSheet sheet, String[][] array)
	{
		// 数据行样式
		ExcelCellStyle exs = new ExcelCellStyle(workbook);
		HSSFCellStyle dataStyle = exs.createDataCellStyle();

		// 第五步，设置数据
		// 数据行，从第3行开始
		for (int i = 0; i < array.length; i++)
		{
			HSSFRow dataRow = sheet.createRow(i + 1);

			String[] rowArray = array[i];

			// 在row行上创建一个方格
			for (int j = 0; j < rowArray.length; j++)
			{
				HSSFCell dataCell = dataRow.createCell(j);
				dataCell.setCellStyle(dataStyle);

				dataCell.setCellValue(rowArray[j]);
			}
		}
	}

}
