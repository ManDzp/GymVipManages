package com.jm.vip.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.jm.base.tool.CurrentUser;
import com.jm.log.LogProxy;
import com.jm.security.RegexHelper;
import com.jm.utils.BaseUtils;
import com.jm.utils.DateHelper;
import com.jm.vip.dao.MemberHistoryInfoDao;
import com.jm.vip.dao.MemberInfoDao;
import com.jm.vip.entity.MemberInfo;
import com.jm.vip.type.MemberStatus;

@Service
public class MemberInfoService
{
	/**
	 *  声明log4j
	 */
	private Logger log = Logger.getLogger(MemberInfoService.class);

	@Resource(name = "memberInfoDao")
	private MemberInfoDao memberInfoDao;

	@Resource(name = "memberHistoryInfoDao")
	private MemberHistoryInfoDao memberHistoryInfoDao;

	/**
	 * 获取会员资料信息
	 * @param guid 会员唯一标识
	 * @return
	 */
	public MemberInfo getMemberInfoByGuid(String guid)
	{
		try
		{
			return this.memberInfoDao.getByGuid(guid);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取会员资料信息异常", e.toString(), guid);
			return null;
		}
	}

	/**
	 * 发放新会员卡
	 * @param memberInfo 会员资料
	 * @param currUser 当前用户
	 * @return
	 */
	public String insertMemberInfo(MemberInfo memberInfo,
			CurrentUser currentUser)
	{
		// 唯一标识
		String guid = BaseUtils.getPrimaryKey();

		try
		{
			memberInfo.setGuid(guid);
			// memberInfo.setStatus(MemberStatus.DEFAULT.getValue());
			memberInfo.setStatus((short) 0);
			memberInfo.setCreator(currentUser.getUserName());
			memberInfo.setCreatorid(currentUser.getUserGuid());
			memberInfo.setCreatetime(DateHelper.getCurrentDate());

			this.memberInfoDao.insert(memberInfo);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "发卡", memberInfo.getCardnumber(),
					memberInfo.getFullname());
			return guid;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "发卡异常", ex.toString(), memberInfo);
			return "";
		}
	}

	/**
	 * 修改会员资料
	 * @param memberInfo 会员资料
	 * @return
	 */
	public boolean updateMemberInfo(MemberInfo memberInfo)
	{
		try
		{
			this.memberInfoDao.updateByGuid(memberInfo);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "修改会员资料", memberInfo.getGuid(),
					memberInfo.getFullname());
			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "修改会员资料异常", ex.toString(), memberInfo);
			return false;
		}
	}

	/**
	 * 会员资料列表封存
	 * @param guidList
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public boolean deleteMemberInfoList(List<String> guidList,
			CurrentUser currentUser)
	{
		try
		{
			for (String guid : guidList)
			{
				MemberInfo memberInfo = getMemberInfoByGuid(guid);
				if (memberInfo != null)
				{
					this.memberHistoryInfoDao.backupData(memberInfo);
				}
				this.memberInfoDao.deleteByGuid(guid);
			}

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "会员资料列表封存", guidList, "");

			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "会员资料列表封存", ex.toString(), guidList);
			return false;
		}
	}

	/**
	 * 充值
	 * @param guid 会员资料唯一标识
	 * @param money 充值金额
	 * @return
	 */
	@Transactional
	public boolean charge(String guid, Integer money)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return false;
		if (money == null || money <= 0)
			return false;

		try
		{
			// 记录操作日志
			LogProxy.WriteLogOperate(log, "充值成功", guid, money);

			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "充值异常", ex.toString(), guid, money);
			return false;
		}
	}

	/**
	 * 买卡
	 * @param guid 会员资料唯一标识
	 * @param money 消费金额
	 * @param content 备注说明
	 * @return
	 */
	@Transactional
	public boolean buyCard(String guid, Integer money, String content)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return false;
		if (money == null || money <= 0)
			return false;

		try
		{
			// 记录操作日志
			LogProxy.WriteLogOperate(log, "买卡成功", guid, money, content);

			return true;
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "买卡异常", ex.toString(), guid, money,
					content);
			return false;
		}
	}

}
