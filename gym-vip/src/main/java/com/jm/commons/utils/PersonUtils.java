package com.jm.commons.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 人员相关工具类
 */
public class PersonUtils
{
	/**
	 * 通过人员全路径，获取人员所在部门名称
	 * <pre class="code">
	 * String userPath = "CN=山东海事局文书,OU=办公室,OU=山东海事局,DC=sdmsa,DC=gov,DC=cn";
	 * String departName = PersonUtils.getDepartNameFromPath(userPath);
	 * departName = "办公室";
	 * </pre>
	 * @param userPath 人员OU路径
	 * @return
	 */
	public static String getDepartNameFromPath(String userPath)
	{
		if (StringUtils.isBlank(userPath))
		{
			return "";
		}

		try
		{
			String[] deptArray = userPath.replace("CN=", "").replace("OU=", "")
					.replace("DC=", "").split(",");

			if (deptArray != null && deptArray.length > 3)
			{
				return deptArray[1];
			}
		}
		catch (Exception e)
		{
		}

		return "";
	}

	/**
	 * 通过人员全路径，获取人员所在部门OU
	 * <pre class="code">
	 * String userPath = "CN=山东海事局文书,OU=办公室,OU=山东海事局,DC=sdmsa,DC=gov,DC=cn";
	 * String departName = PersonUtils.getOUFromName(userPath);
	 * departName = "OU=办公室,OU=山东海事局,DC=sdmsa,DC=gov,DC=cn";
	 * </pre>
	 * @param userPath 人员OU路径
	 * @return
	 */
	public static String getOUFromName(String userPath)
	{
		if (StringUtils.isBlank(userPath))
		{
			return "";
		}

		int startIndex = userPath.indexOf(",");
		return userPath.substring(startIndex + 1);
	}

	/**
	 * 获取使用单位
	 * <pre class="code">
	 * String userPath = "CN=山东海事局文书,OU=办公室,OU=山东海事局,DC=sdmsa,DC=gov,DC=cn";
	 * String dutyOrg = PersonUtils.getDutyOrg(userPath);
	 * dutyOrg = "办公室.山东海事局";
	 * </pre>
	 * @param userPath
	 * @return
	 */
	public static String getDutyOrg(String userPath)
	{
		if (StringUtils.isEmpty(userPath))
		{
			return "";
		}

		String[] ouArray = userPath.split(",DC=")[0].split(",OU=");

		List<String> list = Arrays.asList(ouArray);
		ArrayList<String> arrayList = new ArrayList<String>(list);
		arrayList.remove(0);

		return StringUtils.join(arrayList, ".");
	}

	/**
	 * 把CN=曹峰,OU=市委办公厅,DC=qd,DC=gov,DC=cn改为“曹峰.市委办公厅”
	 * <pre class="code">
	 * String userPath = "CN=山东海事局文书,OU=办公室,OU=山东海事局,DC=sdmsa,DC=gov,DC=cn";
	 * String fullName = PersonUtils.processFullName(userPath);
	 * fullName = "山东海事局文书.办公室.山东海事局";
	 * </pre>
	 * @param namePath 全名
	 * @return
	 */
	public static String processFullName(String namePath)
	{
		if (StringUtils.isEmpty(namePath))
		{
			return "";
		}

		String fullName = namePath.toUpperCase();

		if (fullName.startsWith("CN="))
		{
			int lastIndex = fullName.indexOf(",DC=");
			fullName = fullName.substring(0, lastIndex);

			fullName = fullName.replace("CN=", "").replace("OU=", "");

			return fullName.replace(",", ".");
		}
		else
		{
			return namePath;
		}
	}

	/**
	 * 得到该人员所在部门OU
	 * <pre class="code">
	 * String ouPath = "OU=办公室,OU=山东海事局,DC=sdmsa,DC=gov,DC=cn";
	 * String simpleOU = PersonUtils.getSimpleOU(ouPath);
	 * simpleOU = "办公室,山东海事局,sdmsa,gov,cn";
	 * </pre>
	 * @param userPath 人员OU路径
	 * @return
	 */
	public static String getSimpleOU(String ouPath)
	{
		if (StringUtils.isBlank(ouPath))
		{
			return "";
		}

		return ouPath.replace("CN=", "").replace("OU=", "").replace("DC=", "");
	}

	/**
	 * 得到该人员所在部门数组
	 * <pre class="code">
	 * String ouPath = "OU=办公室,OU=山东海事局,DC=sdmsa,DC=gov,DC=cn";
	 * String[] simpleOUArray = PersonUtils.getSimpleOUArray(ouPath);
	 * simpleOUArray = [办公室, 山东海事局, sdmsa, gov, cn];
	 * </pre>
	 * @param userPath 人员OU路径
	 * @return
	 */
	public static String[] getSimpleOUArray(String ouPath)
	{
		String simpleOuPath = getSimpleOU(ouPath);

		if (StringUtils.isEmpty(simpleOuPath))
		{
			return null;
		}

		return simpleOuPath.split(",");
	}
	
	/**
	 * 判断两个人员是否拥有相同的部门
	 * @param userPath1 人员1路径
	 * @param userPath2 人员2路径
	 * @return
	 */
	public static boolean isSameOU(String userPath1, String userPath2)
	{
		if (StringUtils.isEmpty(userPath1) || StringUtils.isEmpty(userPath2))
		{
			return false;
		}

		String ouPath1 = getOUFromName(userPath1);
		String simpleOUPath1 = getSimpleOU(ouPath1);

		String ouPath2 = getOUFromName(userPath2);
		String simpleOUPath2 = getSimpleOU(ouPath2);

		boolean isSameOU = simpleOUPath1.endsWith(simpleOUPath2);

		return isSameOU;
	}
}
