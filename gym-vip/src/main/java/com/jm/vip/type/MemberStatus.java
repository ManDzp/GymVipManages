package com.jm.vip.type;

public enum MemberStatus
{
	DEFAULT((short) 0), WAITOPEN((short) 1), NORMAL((short) 2), LEAVE(
			(short) 3), EXPIRE((short) 4);

	private short value;

	private MemberStatus(short value)
	{
		this.value = value;
	}

	public short getValue()
	{
		return value;
	}

	/**
	 * 手写的从int到enum的转换函数
	 * @param value
	 * @return
	 */
	public static MemberStatus valueOf(short value)
	{
		switch (value)
		{
		case 0:
			return DEFAULT;
		case 1:
			return WAITOPEN;
		case 2:
			return NORMAL;
		case 3:
			return LEAVE;
		case 4:
			return EXPIRE;
		default:
			return null;
		}
	}

}
