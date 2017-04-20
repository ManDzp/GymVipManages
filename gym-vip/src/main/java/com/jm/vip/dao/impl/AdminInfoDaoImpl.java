package com.jm.vip.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jm.vip.dao.AdminInfoDao;
import com.jm.vip.entity.AdminInfo;

/**
 * 人员注册Dao层
 */
@Repository("adminInfoDao")
public class AdminInfoDaoImpl extends BaseDaoImpl<AdminInfo>
		implements AdminInfoDao
{

	@Override
	public String getMapperId()
	{
		return "AdminInfoMapper";
	}

	/**
	 * 人员注册Dao层
	 * @param username 注册用户名
	 * @param password 密码
	 */
	@Override
	public AdminInfo getUserInfoByAccount(String account, String password)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", account);
		param.put("password", password);

		return this.session.selectOne(getMapperId() + ".selectByUser", param);
	}

	/*
	 * 判断注册会员是用户名在库中是否已经存在
	 * 
	 * @param username 注册用户名
	 * 
	 * @param password 密码
	 */
	@Override
	public boolean isSameName(String userName, String userGuid)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", userName);
		param.put("userguid", userGuid);

		List<AdminInfo> list = this.session
				.selectList(getMapperId() + ".isSameName", param);
		return list.size() > 0;
	}

}
