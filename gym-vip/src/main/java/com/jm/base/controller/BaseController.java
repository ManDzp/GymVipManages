package com.jm.base.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jm.base.tool.CurrentUser;
import com.jm.interceptor.IdentityInterceptorDB;
import com.jm.utils.BaseUtils;

/**
 * 基础控制器
 */
public class BaseController
{
	/**
	 * 请求的信息
	 */
	@Autowired
	protected HttpServletRequest request;

	/**
	 * 静态资源版本号，每更新一次，都要加1
	 */
	private static final String RESOURCES_VERSION = "5";

	/** 
	 * 记录日志
	 */
	protected Logger log = Logger.getLogger(this.getClass());

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception
	{
	}

	/**
	 * 获取当前登录用户信息
	 * @return
	 */
	@ModelAttribute("contextUser")
	public CurrentUser getContextUser()
	{
		Object currentObject = request.getSession()
				.getAttribute(IdentityInterceptorDB.SEESION_USER);
		if (currentObject == null)
		{
			return null;
		}

		return (CurrentUser) currentObject;
	}

	/**
	 * 系统名称，title显示名
	 * 被@ModelAttribute注释的方法会在此controller每个方法执行前被执行
	 * @param model
	 */
	@ModelAttribute("jsp_title")
	public String getSystemTitle()
	{
		return BaseUtils.getConfigValue("systemtitle");
	}

	/**
	 * 静态资源请求版本
	 */
	@ModelAttribute("res_v")
	public String getVersion(Model model)
	{
		return "?v=" + RESOURCES_VERSION;
	}

	/**
	 * 注销页
	 * @return
	 */
	@ModelAttribute("logoutUrl")
	public String getLogoutUrl()
	{
		return BaseUtils.getConfigValue("logoutUrl");
	}

}
