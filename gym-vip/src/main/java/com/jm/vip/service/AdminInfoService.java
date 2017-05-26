package com.jm.vip.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.jm.commons.base.tool.CurrentUser;
import com.jm.commons.log.LogProxy;
import com.jm.commons.utils.BaseUtils;
import com.jm.vip.dao.AdminInfoDao;
import com.jm.vip.entity.AdminInfo;

@Service
public class AdminInfoService
{
	/**
	 *  声明log4j
	 */
	private Logger log = Logger.getLogger(AdminInfoService.class);

	@Resource(name = "adminInfoDao")
	private AdminInfoDao adminInfoDao;

	/**
	 * 获取用户信息
	 * @param account 登录名
	 * @param password 密码
	 * @return
	 */
	public AdminInfo getUserInfoByAccount(String account, String password)
	{
		try
		{
			return this.adminInfoDao.getUserInfoByAccount(account, password);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取用户信息异常", e.toString(), account,
					password);
			return null;
		}
	}

	/**
	 * 获取用户信息
	 * @param userguid 用户唯一标识
	 * @return
	 */
	public AdminInfo getUserInfoViewByGuid(String userguid)
	{
		try
		{
			return this.adminInfoDao.getByGuid(userguid);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取注册账号信息异常", e.toString(), userguid);
			return null;
		}
	}

	/**
	 * 根据登陆账号判断注册账号是否存在
	 * @param userName 登陆账号
	 * @return
	 */
	public boolean isSameName(String userName)
	{
		return isSameName(userName, null);
	}

	/**
	 * 根据登陆账号判断注册账号是否存在
	 * @param userName 登陆账号
	 * @param userGuid 用户唯一标识
	 * @return
	 */
	public boolean isSameName(String userName, String userGuid)
	{
		try
		{
			return this.adminInfoDao.isSameName(userName, userGuid);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "根据登陆账号判断注册账号是否存在异常", e.toString(),
					userName);
			return false;
		}
	}

	/**
	 * 添加注册用户信息
	 * @param userInfo 注册用户信息
	 * @return
	 */
	@Transactional
	public String insertUserInfo(AdminInfo userInfo)
	{
		String userName = userInfo.getUsername();

		boolean isExist = isSameName(userName);
		if (isExist)
			return "";

		// 唯一标识
		String userGuid = BaseUtils.getUserGuid();
		userInfo.setUserguid(userGuid);

		try
		{
			this.adminInfoDao.insert(userInfo);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "注册新用户", userGuid, userName);
			return userGuid;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "注册新用户", ex.toString(), userInfo);
			return "";
		}
	}

	/**
	 * 修改注册账户信息
	 * @param userInfo 注册用户信息
	 * @return
	 */
	@Transactional
	public boolean updateUserInfo(AdminInfo userInfo)
	{
		try
		{
			this.adminInfoDao.updateByGuid(userInfo);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "修改注册账户信息", userInfo.getUserguid(),
					userInfo.getUsername());
			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "修改注册账户信息异常", ex.toString(), userInfo);
			return false;
		}
	}

	/**
	 * 列表删除
	 * @param guidList
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public boolean deleteUserInfoList(List<String> userguidList,
			CurrentUser currentUser)
	{
		try
		{
			for (String userguid : userguidList)
			{
				this.adminInfoDao.deleteByGuid(userguid);
			}

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "列表删除注册账号", userguidList, "");

			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "列表删除注册账号", ex.toString(),
					userguidList);
			return false;
		}
	}

}
