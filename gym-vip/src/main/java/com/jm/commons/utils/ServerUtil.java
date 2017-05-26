package com.jm.commons.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import com.alibaba.fastjson.JSONObject;
import com.jm.commons.log.LogProxy;

/**
 * control用到的帮助类
 */
public class ServerUtil
{
	private static Logger log = Logger.getLogger(ServerUtil.class);

	/**
	 * 将json格式字符串输出
	 * @param response 响应
	 * @param jsonStr 输出的json字符串
	 */
	public static void ResponseOutJson(HttpServletResponse response,
			String jsonStr)
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try
		{
			out = response.getWriter();
			out.append(jsonStr);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (out != null)
			{
				out.close();
			}
		}
	}

	/**
	 * 下载Excel
	 * @param request
	 * @param response
	 * @param sheetName 下载文件名称
	 * @param docType 模块号
	 * @param workbook 工作簿数据
	 * @throws IOException
	 */
	public static void saveToExcel(HttpServletRequest request,
			HttpServletResponse response, String sheetName, String docType,
			Workbook workbook) throws IOException
	{
		String siteUrl = request.getContextPath();
		String sitePath = request.getSession().getServletContext()
				.getRealPath("/");

		// 附件唯一标识
		String blobGuid = BaseUtils.getNewGuid();
		// 打印文档名称
		String fileName = sheetName;
		// 打印文档类型
		String fileExt = ".xls";

		String dirPath = PathUtils.getPhysicsPath(sitePath, "ftemp", "excel");
		String filePath = PathUtils.getPhysicsPath(dirPath, blobGuid + fileExt);
		String fileUrl = siteUrl + "/download/excel?blobguid=" + blobGuid
				+ "&filename=" + fileName + "&fileext=" + fileExt;

		File dirFile = new File(dirPath);
		File file = new File(filePath);

		// 如果文件夹不存在，则添加文件夹
		if (!dirFile.exists())
		{
			dirFile.mkdirs();
		}

		// 如果原来存在重名文件，则删除该文件
		if (file.exists())
		{
			file.delete();
		}

		FileOutputStream fos = null;
		try
		{
			// 创建输出文件
			fos = new FileOutputStream(file);
			workbook.write(fos);
			// 关闭输出文件
			fos.close();
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "落地附件失败", e.toString(), filePath);
		}
		finally
		{
			if (fos != null)
			{
				fos.close();
			}
		}

		ResponseOutJson(response, JSONObject.toJSONString(fileUrl));
	}

}
