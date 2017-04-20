package com.jm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 数据库用户
 * 自定义拦截器，拦截符合条件url的请求 身份验证拦截
 * (是否有登录用户信息)
 */
public class IdentityInterceptorDB extends HandlerInterceptorAdapter
{
	// 加载日志
	static Logger log = Logger.getLogger(IdentityInterceptorDB.class);

	public final static String SEESION_USER = "seesion_user";

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception
	{
		log.debug("==============执行顺序: 1、preHandle================");
		// 获取当前请求的url
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		// String queryString = request.getQueryString();

		String url = requestUri.substring(contextPath.length());
		log.debug("requestUri:" + requestUri);
		log.debug("contextPath:" + contextPath);
		log.debug("url:" + url);

		Validator validator = Validator.getInstance();

		// 注入当前session
		validator.init(request);

		// 当前是否存在用户信息
		if (!validator.validate())
		{
			// 没有查询到用户信息时
			// 清空当前存储的用户信息
			validator.cancel();
			// 跳转至登录页面
			response.sendRedirect(contextPath + "/login");
			return false;
		}
		else
		{
			// 验证通过，将用户信息填到session中
			validator.confirm();
			return true;
		}
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		log.debug("==============执行顺序: 2、postHandle================");
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
					throws Exception
	{
		log.debug("==============执行顺序: 3、afterCompletion================");
	}
}
