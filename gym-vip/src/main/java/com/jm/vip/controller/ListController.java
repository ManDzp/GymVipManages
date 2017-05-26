package com.jm.vip.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.jm.commons.base.controller.BaseController;
import com.jm.commons.base.tool.CurrentUser;
import com.jm.commons.log.LogProxy;
import com.jm.commons.page.DataGridSource;
import com.jm.commons.page.PageListPlugin;
import com.jm.commons.poi.ExcelHelper;
import com.jm.commons.poi.PrintExcelConfig;
import com.jm.commons.poi.PrintExcelConfigFactory;
import com.jm.commons.utils.ServerUtil;

/**
 * 列表数据请求
 */
@Controller
@RequestMapping("/list")
public class ListController extends BaseController
{
	@Resource(name = "pageListPlugin")
	private PageListPlugin pagePlugin;

	/**
	 * 请求获取列表数据,默认补充userguid参数
	 * @param request
	 * @param response
	 * @param params 数据的where条件
	 * @param orderby
	 * @param mapperid
	 * @param page 当前页码
	 * @param rows 页面容量
	 * @param sort 字段排序
	 * @param order 排序方式
	 */
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public void getDataByPage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false, defaultValue = "") String params,
			@RequestParam(required = false, defaultValue = "") String orderby,
			@RequestParam String mapperid,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer rows,
			@RequestParam(required = false, defaultValue = "") String sort,
			@RequestParam(required = false, defaultValue = "desc") String order)
	{
		try
		{
			// 查询条件
			Map<String, Object> wheremap = JSONObject.parseObject(params);
			CurrentUser currentUser = super.getContextUser();
			if (currentUser != null)
			{
				wheremap.put("userguid", super.getContextUser().getUserGuid());
			}

			// 排序字段
			String strOrderby = orderby;
			if (StringUtils.isNotEmpty(sort))
			{
				strOrderby = sort + " " + order;
			}

			// 使用分页列表插件查询
			DataGridSource gridSource = pagePlugin.getListByPage(wheremap,
					strOrderby, mapperid, page, rows);

			// 将列表数据返回给datagrid
			ServerUtil.ResponseOutJson(response,
					JSONObject.toJSONString(gridSource));
		}
		catch (Exception ex)
		{
			System.out.println(ex.toString());
		}
	}

	/**
	 * 列表打印
	 * @param request
	 * @param response
	 * @param jsonObject
	 */
	@RequestMapping(value = "/printlist", method = RequestMethod.POST)
	public void printList(HttpServletRequest request,
			HttpServletResponse response, @RequestBody JSONObject jsonObject)
	{
		try
		{
			String docType = jsonObject.getString("docType");
			String printId = jsonObject.getString("printId");
			String params = jsonObject.getString("params");
			String orderby = jsonObject.getString("orderby");
			String mapperid = jsonObject.getString("mapperid");
			String sort = jsonObject.getString("sort");
			String order = jsonObject.getString("order");

			// 默认排序方式为倒序
			if (StringUtils.isEmpty(order))
			{
				order = "desc";
			}

			// 1.读取配置文件要打印的信息
			PrintExcelConfig excelConfig = PrintExcelConfigFactory
					.initPrintExcelConfig(printId);

			// 未找到配置信息或者配置信息错误
			if (excelConfig == null)
			{
				ServerUtil.ResponseOutJson(response, "0");
			}

			// 查询条件
			Map<String, Object> wheremap = JSONObject.parseObject(params);
			wheremap.put("userguid", super.getContextUser().getUserGuid());

			// 排序字段
			String strOrderby = orderby;
			if (StringUtils.isNotEmpty(sort))
			{
				strOrderby = sort + " " + order;
			}

			// 2.获取要打印的数据
			List<Object> list = pagePlugin.getPrintList(wheremap, strOrderby,
					mapperid);

			// 根据条件查询结果为空
			if (CollectionUtils.isEmpty(list))
			{
				ServerUtil.ResponseOutJson(response, "1");
			}

			// 3.生成Excel表格
			HSSFWorkbook workbook = ExcelHelper.createWorkbook(excelConfig,
					list);

			// 4.下载Excel
			String fileName = excelConfig.getSheetName();

			// 5.关闭对象
			if (workbook != null)
			{
				workbook.close();
			}

			ServerUtil.saveToExcel(request, response, fileName, docType,
					workbook);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "列表打印失败", ex.toString(), jsonObject);
		}
	}
}
