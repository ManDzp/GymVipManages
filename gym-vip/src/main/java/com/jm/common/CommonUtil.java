package com.jm.common;

public class CommonUtil
{

	/**
	 * @return
	 */
	public static ResultDTO newSuccessedDTO()
	{
		return new ResultDTO(true);
	}

	/**
	 * @param message
	 * @return
	 */
	public static ResultDTO newFailedDTO(String message)
	{
		return new ResultDTO(false, message);
	}

}
