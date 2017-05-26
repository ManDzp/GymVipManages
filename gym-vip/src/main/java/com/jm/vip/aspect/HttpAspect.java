package com.jm.vip.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Http请求处理
 */
@Aspect
@Component
public class HttpAspect
{

	private Logger logger = Logger.getLogger(this.getClass());

	@Pointcut("execution(public * com.jm.vip.controller.*.*(..))")
	public void log()
	{
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint)
	{
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// url
		logger.info("url=" + request.getRequestURL());

		// method
		logger.info("method=" + request.getMethod());

		// ip
		logger.info("ip=" + request.getRemoteAddr());

		// 类方法
		logger.info("class_method="
				+ joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());

		// 参数
		logger.info("args=" + joinPoint.getArgs());
	}

	@After("log()")
	public void doAfter()
	{
		logger.info("222222222222");
	}

	@AfterReturning(returning = "object", pointcut = "log()")
	public void doAfterReturning(Object object)
	{
		// logger.info("response=" + object.toString());
	}

}
