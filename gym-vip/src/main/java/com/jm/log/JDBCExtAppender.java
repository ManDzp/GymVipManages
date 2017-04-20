package com.jm.log;

import org.apache.log4j.Priority;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 使用纯JDBC连接将日志记录到数据库中
 */
public class JDBCExtAppender extends JDBCAppender
{
	public JDBCExtAppender()
	{
		super();
	}

	@Override
	public boolean isAsSevereAsThreshold(Priority priority)
	{
		// 只判断是否相等，而不判断优先级
		return this.getThreshold().equals(priority);
	}

	/**
	 * 重载append()方法， 只将用户指定类型的log4j信息入库
	 */
	@Override
	public void append(LoggingEvent event)
	{
		if (event.getMessage() instanceof ParameterizedMessage)
		{
			super.append(event);
		}
	}

	/**
	 * 重载getLogStatement()方法， 在SQL字符串最后添加用户ID等信息
	 */
	@Override
	protected String getLogStatement(LoggingEvent event)
	{
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(layout.format(event));

		if (event.getMessage() instanceof ParameterizedMessage)
		{

			Object[] params = ((ParameterizedMessage) event.getMessage())
					.getParameters();
			for (int i = 0; i < params.length; i++)
			{
				String str = "{$" + i + "$}";
				int start = stringBuffer.indexOf(str);
				if (start == -1)
				{
					continue;
				}
				int end = start + str.length();
				stringBuffer.replace(start, end, params[i].toString().replace('\'', '"'));
			}
		}
		return stringBuffer.toString();
	}

}
