package com.jm.vip.enums;

/**
 * 返回值枚举
 */
public enum ResultEnum
{
	UNKNOWN_ERROR(-1, "未知错误"), SUCCESS(0, "成功");

	private Integer code;

	private String msg;

	ResultEnum(Integer code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode()
	{
		return code;
	}

	public String getMsg()
	{
		return msg;
	}
}