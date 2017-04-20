package com.jm.vip.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jm.base.controller.BaseController;
import com.jm.base.tool.CurrentUser;
import com.jm.interceptor.IdentityInterceptorDB;
import com.jm.interceptor.ValidatorHelper;
import com.jm.vip.entity.AdminInfo;
import com.jm.vip.service.AdminInfoService;

/**
 * 登录请求控制器
 */
@Controller
public class LoginController extends BaseController
{
	@Resource(name = "adminInfoService")
	private AdminInfoService adminInfoService;

	/**
	 * 加载登录界面
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loadLogin()
	{
		// 跳转到login.jsp登录页
		return "web/login";
	}

	/**
	 * 登录校验
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/check")
	public ModelAndView checkLogin(HttpServletRequest request,
			HttpServletResponse response)
	{
		String account = null;
		String password = null;

		try
		{
			account = request.getParameter("account").trim();
			password = request.getParameter("password").trim();

			if (StringUtils.isBlank(account) || StringUtils.isBlank(password))
			{
				return new ModelAndView("login", "message", "用户名或密码不能为空。");
			}
		}
		catch (Exception ex)
		{
			return new ModelAndView("redirect:/login");
		}

		AdminInfo adminInfo = this.adminInfoService.getUserInfoByAccount(account,
				password);

		if (adminInfo == null)
		{
			return new ModelAndView("login", "message", "用户名或密码错误。");
		}

		// 记录登录用户的信息
		CurrentUser currUser = new CurrentUser();
		currUser.setAccount(adminInfo.getUsername());
		currUser.setUserName(adminInfo.getDisplayname());
		currUser.setUserGuid(adminInfo.getUserguid());
		currUser.setClientIp(ValidatorHelper.getIpAddr(request));

		// 登录成功，则将用户信息记录到session
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute(IdentityInterceptorDB.SEESION_USER, currUser);

		// 暂时不记录Cookie

		return new ModelAndView("redirect:/index");
	}

	/**
	 * 注销
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response)
	{
		HttpSession session = request.getSession();

		// 清除session
		if (session.getAttribute(IdentityInterceptorDB.SEESION_USER) != null)
		{
			session.removeAttribute(IdentityInterceptorDB.SEESION_USER);
		}

		// 跳转到网站首页
		return new ModelAndView("redirect:/webindex/web_index");
	}

}
