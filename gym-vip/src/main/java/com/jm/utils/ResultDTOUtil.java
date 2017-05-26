package com.jm.utils;

import com.jm.common.ResultDTO;

/**
 * 返回值工作类
 */
public class ResultDTOUtil
{

	/**
	 * @return
	 */
	public static ResultDTO success()
	{
		return new ResultDTO(true);
	}

	/**
	 * @param message
	 * @return
	 */
	public static ResultDTO error(String message)
	{
		return new ResultDTO(false, message);
	}

}
