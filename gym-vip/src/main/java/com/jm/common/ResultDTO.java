package com.jm.common;

/**
 * 返回true or false 结果
 */
public class ResultDTO
{
	// 返回true or false
	private boolean success;
	// 返回结果说明
	private String message;
	
	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean b)
	{
		this.success = b;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}



}
