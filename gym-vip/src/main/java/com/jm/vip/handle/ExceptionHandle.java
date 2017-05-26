package com.jm.vip.handle;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jm.common.Result;
import com.jm.utils.ResultUtil;
import com.jm.vip.exception.VipException;

/**
 * 控制器的全局配置
 */
@ControllerAdvice
public class ExceptionHandle
{

	protected Logger log = Logger.getLogger(this.getClass());

	/**
	 * 定义全局异常处理，value属性可以过滤拦截条件，此处拦截所有的Exception
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result handle(Exception e)
	{
		if (e instanceof VipException)
		{
			VipException vipException = (VipException) e;
			return ResultUtil.error(vipException.getCode(),
					vipException.getMessage());
		}
		else
		{
			log.error("【系统异常】{}", e);
			return ResultUtil.error(-1, "未知错误");
		}
	}
}
