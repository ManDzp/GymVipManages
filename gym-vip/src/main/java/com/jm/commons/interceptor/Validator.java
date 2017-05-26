package com.jm.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.util.Assert;

import com.jm.commons.base.tool.CurrentUser;
import com.jm.commons.log.LogProxy;

/**
 * 验证器
 */
public class Validator
{
	/**
	 *  log4j日志
	 */
	private static final Logger log = Logger.getLogger(Validator.class);

	private static ThreadLocal<Validator> validatorHolder = new ThreadLocal<Validator>()
	{

		protected Validator initialValue()
		{
			return new Validator();
		}

	};

	// 当前请求的session
	private HttpSession session = null;

	// 当前的请求
	private HttpServletRequest request = null;

	// 当前登录系统的用户信息
	private CurrentUser user = null;

	private Validator()
	{
	}

	public static Validator getInstance()
	{
		return validatorHolder.get();
	}

	/**
	 * 执行初始化
	 * @param httpRequest
	 */
	public void init(HttpServletRequest httpRequest)
	{
		this.request = httpRequest;
		this.session = request.getSession();
		this.user = null;
	}

	/**
	 * 将用户填充到session
	 */
	public void confirm()
	{
		Assert.notNull(this.user, "authentication is null.");

		this.session.setAttribute(IdentityInterceptorDB.SEESION_USER,
				this.user);

		// 将用户信息记录到log4j中MDC中
		MDC.put("uguid", this.user.getUserGuid());
		MDC.put("uname", this.user.getUserName());
		MDC.put("uip", this.user.getClientIp());
	}

	/**
	 * 验证当前是否存在用户信息
	 */
	public boolean validate()
	{
		// session不存在，返回失败
		if (this.session == null)
			return false;

		try
		{
			// 如果session中不存在信息时
			if (session
					.getAttribute(IdentityInterceptorDB.SEESION_USER) != null)
			{
				// 从session中，将用户信息读取出来
				this.user = (CurrentUser) session
						.getAttribute(IdentityInterceptorDB.SEESION_USER);
			}
		}
		catch (Exception ex)
		{
			LogProxy.WriteLogError(log, "验证用户身份", ex.toString(),
					IdentityInterceptorDB.SEESION_USER);
		}

		if (this.user == null)
			return false;

		return true;
	}

	/**
	 * 清除session
	 */
	public void cancel()
	{
		this.session = null;
		this.user = null;
	}

}
