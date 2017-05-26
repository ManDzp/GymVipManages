package com.jm.vip.exception;

import com.jm.vip.enums.ResultEnum;

/**
 * 自定义异常
 */
public class VipException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8662575474547077280L;

	private Integer code;

	public VipException(ResultEnum resultEnum)
	{
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}

	public Integer getCode()
	{
		return code;
	}

	public void setCode(Integer code)
	{
		this.code = code;
	}
}