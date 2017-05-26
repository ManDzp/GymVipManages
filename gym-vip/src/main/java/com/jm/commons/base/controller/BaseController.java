package com.jm.commons.base.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jm.commons.base.tool.CurrentUser;
import com.jm.commons.base.tool.SystemConfig;
import com.jm.commons.base.tool.SystemVersion;
import com.jm.commons.interceptor.IdentityInterceptorDB;

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

	@Resource
	protected SystemConfig systemConfig;

	@Resource
	protected SystemVersion systemVersion;

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
	 * 系统配置
	 * 被@ModelAttribute注释的方法会在此controller每个方法执行前被执行
	 * @return
	 */
	@ModelAttribute("systemConfig")
	public SystemConfig getSystemConfig()
	{
		return systemConfig;
	}

	/**
	 * 静态资源请求版本
	 */
	@ModelAttribute("res_v")
	public SystemVersion getSystemVersion()
	{
		return systemVersion;
	}

}
