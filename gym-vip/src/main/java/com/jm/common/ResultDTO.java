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

	public ResultDTO()
	{
		super();
	}

	public ResultDTO(boolean success)
	{
		super();
		this.success = success;
	}

	public ResultDTO(boolean success, String message)
	{
		super();
		this.success = success;
		this.message = message;
	}

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
