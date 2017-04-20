package com.jm.vip.dao;

import com.jm.vip.entity.AdminInfo;

public interface AdminInfoDao extends BaseDao<AdminInfo>
{

	/**
	 * 获取用户信息
	 * @param account 登录名
	 * @param password 密码
	 * @return
	 */
	public AdminInfo getUserInfoByAccount(String account, String password);

	/**
	 * 根据登陆账号判断注册账号是否存在
	 * @param userName 登陆账号
	 * @param userGuid 用户唯一标识
	 * @return
	 */
	public boolean isSameName(String userName, String userGuid);

}