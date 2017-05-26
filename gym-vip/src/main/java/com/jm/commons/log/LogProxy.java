package com.jm.commons.log;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * log4j写日志到数据库代理类
 */
public class LogProxy
{
	/**
	 * 写操作日志
	 * @param log log4j类句柄
	 * @param operate 操作信息
	 * @param guidList 唯一标识
	 * @param titleList 名称
	 */
	public static void WriteLogOperate(final Logger log, final String operate,
			final List<String> guidList, final List<String> titleList)
	{
		final String className = Thread.currentThread().getStackTrace()[2]
				.getClassName();
		final String methodName = Thread.currentThread().getStackTrace()[2]
				.getMethodName();

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				if (CollectionUtils.isEmpty(guidList)
						|| CollectionUtils.isEmpty(titleList)
						|| guidList.size() != titleList.size())
					return;

				for (int i = 0; i < guidList.size(); i++)
				{
					try
					{
						String guid = guidList.get(i);
						String title = titleList.get(i);

						log.info(new JDBCLogMessage(className, methodName,
								operate, guid, title));
					}
					catch (Exception e)
					{
						continue;
					}
				}
			}
		}).start();
	}

	/**
	 * 写操作日志
	 * @param log log4j类句柄
	 * @param operate 操作信息
	 * @param guidList 唯一标识
	 * @param title 名称
	 */
	public static void WriteLogOperate(final Logger log, final String operate,
			final List<String> guidList, final String title)
	{
		final String className = Thread.currentThread().getStackTrace()[2]
				.getClassName();
		final String methodName = Thread.currentThread().getStackTrace()[2]
				.getMethodName();

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				if (CollectionUtils.isEmpty(guidList))
					return;

				for (int i = 0; i < guidList.size(); i++)
				{
					try
					{
						String guid = guidList.get(i);

						log.info(new JDBCLogMessage(className, methodName,
								operate, guid, title));
					}
					catch (Exception e)
					{
						continue;
					}
				}
			}
		}).start();
	}

	/**
	 * 写操作日志
	 * @param log log4j类句柄
	 * @param operate 操作信息
	 * @param params
	 */
	public static void WriteLogOperate(final Logger log, final String operate,
			final Object... params)
	{
		final String className = Thread.currentThread().getStackTrace()[2]
				.getClassName();
		final String methodName = Thread.currentThread().getStackTrace()[2]
				.getMethodName();

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				log.info(new JDBCLogMessage(className, methodName, operate,
						JSONObject.toJSONString(params)));
			}
		}).start();
	}

	/**
	 * 写错误日志
	 * @param log log4j类句柄
	 * @param operate 操作信息
	 * @param ex 错误信息String类型
	 * @param params 执行参数，可传多个支持各种类型
	 */
	public static void WriteLogError(final Logger log, final String operate,
			final String ex, final Object... params)
	{
		final String className = Thread.currentThread().getStackTrace()[2]
				.getClassName();
		final String methodName = Thread.currentThread().getStackTrace()[2]
				.getMethodName();

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				log.error(new JDBCLogMessage(className, methodName, operate,
						JSONObject.toJSONString(params), ex));
			}
		}).start();
	}
}
