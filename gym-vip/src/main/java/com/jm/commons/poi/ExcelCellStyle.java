package com.jm.commons.poi;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Excel单元格样式
 */
public class ExcelCellStyle
{
	private HSSFWorkbook workbook;

	public ExcelCellStyle(HSSFWorkbook workbook)
	{
		this.workbook = workbook;
	}

	/**
	 * 标题行样式
	 * @return
	 */
	public HSSFCellStyle createTitleCellStyle()
	{
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 16);
		font.setFontName("宋体");
		font.setBoldweight((short) 700);// 粗体

		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		// 上下左右，都是用细边
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);

		// 上下使用黑色线
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBottomBorderColor(HSSFColor.BLACK.index);

		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillBackgroundColor(HSSFColor.BLUE.index);

		// 居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 垂直对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		return style;
	}

	/**
	 * 列名行样式
	 * @return
	 */
	public HSSFCellStyle createColumnCellStyle()
	{
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("宋体");
		font.setBoldweight((short) 600);// 粗体

		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		// 上下左右，都是用细边
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);

		// 上下使用黑色线
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBottomBorderColor(HSSFColor.BLACK.index);

		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillBackgroundColor(HSSFColor.BLUE.index);

		// 居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 垂直对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		return style;
	}

	/**
	 * 数据单元格样式
	 * @return
	 */
	public HSSFCellStyle createDataCellStyle()
	{
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("宋体");

		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		// 上下左右，都是用细边
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);

		// 上下使用黑色线
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBottomBorderColor(HSSFColor.BLACK.index);

		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillBackgroundColor(HSSFColor.BLUE.index);

		style.setWrapText(true);

		// 居中
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		// 垂直对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		return style;
	}

}
