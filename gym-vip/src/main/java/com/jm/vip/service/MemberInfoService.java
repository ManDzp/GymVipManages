package com.jm.vip.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.jm.base.tool.CurrentUser;
import com.jm.common.CommonUtil;
import com.jm.common.ResultDTO;
import com.jm.log.LogProxy;
import com.jm.security.RegexHelper;
import com.jm.utils.BaseUtils;
import com.jm.utils.DateHelper;
import com.jm.vip.dao.ActiveCardRecordDao;
import com.jm.vip.dao.BuyCardNumberRecordDao;
import com.jm.vip.dao.BuyCardRecordDao;
import com.jm.vip.dao.ChargeRecordDao;
import com.jm.vip.dao.ContinueCardRecordDao;
import com.jm.vip.dao.LeaveRecordDao;
import com.jm.vip.dao.MemberHistoryInfoDao;
import com.jm.vip.dao.MemberInfoDao;
import com.jm.vip.dao.PointsRecordDao;
import com.jm.vip.dao.SignRecordDao;
import com.jm.vip.entity.ActiveCardRecord;
import com.jm.vip.entity.BuyCardNumberRecord;
import com.jm.vip.entity.BuyCardRecord;
import com.jm.vip.entity.ChargeRecord;
import com.jm.vip.entity.ContinueCardRecord;
import com.jm.vip.entity.LeaveRecord;
import com.jm.vip.entity.MemberHistoryInfo;
import com.jm.vip.entity.MemberInfo;
import com.jm.vip.entity.PointsRecord;
import com.jm.vip.entity.SignRecord;

@Service
public class MemberInfoService
{
	/**
	 *  声明log4j
	 */
	private Logger log = Logger.getLogger(MemberInfoService.class);

	@Resource(name = "attachmentService")
	private AttachmentService attachmentService;

	@Resource(name = "memberInfoDao")
	private MemberInfoDao memberInfoDao;

	@Resource(name = "memberHistoryInfoDao")
	private MemberHistoryInfoDao memberHistoryInfoDao;

	@Resource(name = "signRecordDao")
	private SignRecordDao signRecordDao;

	@Resource(name = "chargeRecordDao")
	private ChargeRecordDao chargeRecordDao;

	@Resource(name = "buyCardRecordDao")
	private BuyCardRecordDao buyCardRecordDao;

	@Resource(name = "activeCardRecordDao")
	private ActiveCardRecordDao activeCardRecordDao;

	@Resource(name = "leaveRecordDao")
	private LeaveRecordDao leaveRecordDao;

	@Resource(name = "continueCardRecordDao")
	private ContinueCardRecordDao continueCardRecordDao;

	@Resource(name = "buyCardNumberRecordDao")
	private BuyCardNumberRecordDao buyCardNumberRecordDao;

	@Resource(name = "pointsRecordDao")
	private PointsRecordDao pointsRecordDao;

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
	 * 获取会员封存资料信息
	 * @param guid 会员唯一标识
	 * @return
	 */
	public MemberHistoryInfo getMemberHistoryInfoByGuid(String guid)
	{
		try
		{
			return this.memberHistoryInfoDao.getByGuid(guid);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "获取会员封存资料信息异常", e.toString(), guid);
			return null;
		}
	}

	/**
	 * 查找会员卡号信息
	 * @param cardNumber 会员卡号
	 * @return
	 */
	public MemberInfo getMemberByCardNumber(String cardNumber)
	{
		try
		{
			return this.memberInfoDao.getMemberByCardNumber(cardNumber);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "查找会员卡号信息异常", e.toString(), cardNumber);
			return null;
		}
	}

	/**
	 * 查找会员卡号信息
	 * @param cardNumber 会员卡号
	 * @param withoutGuid 排除的唯一标识
	 * @return
	 */
	public MemberInfo getMemberByCardNumber(String cardNumber,
			String withoutGuid)
	{
		try
		{
			return this.memberInfoDao.getMemberByCardNumber(cardNumber,
					withoutGuid);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "查找会员卡号信息异常", e.toString(), cardNumber,
					withoutGuid);
			return null;
		}
	}

	/**
	 * 查找会员卡号信息
	 * @param cardNumber 会员卡号
	 * @return
	 */
	public MemberHistoryInfo getMemberHistoryByCardNumber(String cardNumber)
	{
		try
		{
			return this.memberHistoryInfoDao
					.getMemberHistoryByCardNumber(cardNumber);
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "查找会员卡号信息异常", e.toString(), cardNumber);
			return null;
		}
	}

	/**
	 * 校验会员卡号是否存在
	 * @param cardNumber 会员卡号
	 * @return
	 */
	public boolean isExistCardNumber(String cardNumber)
	{
		try
		{
			MemberInfo memberInfo = this.getMemberByCardNumber(cardNumber);
			MemberHistoryInfo memberHistoryInfo = this
					.getMemberHistoryByCardNumber(cardNumber);
			return memberInfo != null || memberHistoryInfo != null;
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "校验会员卡号是否存在异常", e.toString(),
					cardNumber);
			return false;
		}
	}

	/**
	 * 发放新会员卡
	 * @param memberInfo 会员资料
	 * @param realPath 地址
	 * @param allFiles 附件
	 * @param currUser 当前用户
	 * @return
	 */
	@Transactional
	public String insertMemberInfo(MemberInfo memberInfo, String realPath,
			String allFiles, CurrentUser currentUser)
	{
		// 唯一标识
		String guid = BaseUtils.getPrimaryKey();

		try
		{
			memberInfo.setGuid(guid);
			// memberInfo.setStatus(MemberStatus.DEFAULT.getValue());
			memberInfo.setStatus(0);// 状态，0：初始状态
			memberInfo.setBalance(0d);
			memberInfo.setConsumption(0d);
			memberInfo.setPoints(0);
			memberInfo.setTimes(0);
			memberInfo.setUsedtimes(0);
			memberInfo.setCreator(currentUser.getUserName());
			memberInfo.setCreatorid(currentUser.getUserGuid());
			memberInfo.setCreatetime(DateHelper.getCurrentDate());
			this.memberInfoDao.insert(memberInfo);

			// 添加附件
			this.attachmentService.insertAttachment(guid, realPath, allFiles);

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
	 * @param realPath 地址
	 * @param allFiles 附件
	 * @return
	 */
	@Transactional
	public boolean updateMemberInfo(MemberInfo memberInfo, String realPath,
			String allFiles)
	{
		try
		{
			String guid = memberInfo.getGuid();

			this.memberInfoDao.updateByGuid(memberInfo);

			// 添加附件
			this.attachmentService.insertAttachment(guid, realPath, allFiles);

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
	 * @param content 备注说明
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public ResultDTO charge(String guid, Double money, String content,
			CurrentUser currentUser)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return CommonUtil.newFailedDTO("会员信息不正确！");
		if (money == null || money <= 0)
			return CommonUtil.newFailedDTO("充值金额需要为正数！");

		try
		{
			MemberInfo memberInfo = getMemberInfoByGuid(guid);
			if (memberInfo == null)
				return CommonUtil.newFailedDTO("会员信息不存在！");

			Double balance = memberInfo.getBalance();
			if (balance == null)
				balance = 0d;

			// 更新会员资料
			MemberInfo updateMemberInfo = new MemberInfo();
			updateMemberInfo.setGuid(guid);
			updateMemberInfo.setBalance(balance + money);
			this.memberInfoDao.charge(updateMemberInfo);

			// 保存充值记录
			ChargeRecord chargeRecord = new ChargeRecord();
			chargeRecord.setGuid(BaseUtils.getPrimaryKey());
			chargeRecord.setMemberguid(guid);
			chargeRecord.setMoney(money);
			chargeRecord.setRemark(content);
			chargeRecord.setCreator(currentUser.getUserName());
			chargeRecord.setCreatorid(currentUser.getUserGuid());
			chargeRecord.setCreatetime(DateHelper.getCurrentDate());
			this.chargeRecordDao.insert(chargeRecord);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "充值成功", guid, money, content);
			return CommonUtil.newSuccessedDTO();
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "充值异常", ex.toString(), guid, money,
					content);
			return CommonUtil.newFailedDTO("充值异常！");
		}
	}

	/**
	 * 买卡
	 * @param guid 会员资料唯一标识
	 * @param money 消费金额
	 * @param content 备注说明
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public ResultDTO buyCard(String guid, Double money, String content,
			CurrentUser currentUser)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return CommonUtil.newFailedDTO("会员信息不正确！");
		if (money == null || money <= 0)
			return CommonUtil.newFailedDTO("消费金额需要为正数！");

		try
		{
			MemberInfo memberInfo = getMemberInfoByGuid(guid);
			if (memberInfo == null)
				return CommonUtil.newFailedDTO("会员信息不存在！");

			Integer status = memberInfo.getStatus();
			if (status != 0 && status != 1)
				return CommonUtil.newFailedDTO("会员信息状态不正确！");

			Double balance = memberInfo.getBalance();
			if (balance == null || balance < money)
				return CommonUtil.newFailedDTO("卡内余额不足！");

			// 更新会员资料状态为待开卡
			MemberInfo updateMemberInfo = new MemberInfo();
			updateMemberInfo.setGuid(guid);
			updateMemberInfo.setStatus(1);// 状态，1：待开卡
			updateMemberInfo.setBalance(balance - money);
			this.memberInfoDao.buyCard(updateMemberInfo);

			// 保存买卡记录
			BuyCardRecord buyCardRecord = new BuyCardRecord();
			buyCardRecord.setGuid(BaseUtils.getPrimaryKey());
			buyCardRecord.setMemberguid(guid);
			buyCardRecord.setMoney(money);
			buyCardRecord.setRemark(content);
			buyCardRecord.setCreator(currentUser.getUserName());
			buyCardRecord.setCreatorid(currentUser.getUserGuid());
			buyCardRecord.setCreatetime(DateHelper.getCurrentDate());
			this.buyCardRecordDao.insert(buyCardRecord);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "买卡成功", guid, money, content);
			return CommonUtil.newSuccessedDTO();
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "买卡异常", ex.toString(), guid, money,
					content);
			return CommonUtil.newFailedDTO("买卡异常！");
		}
	}

	/**
	 * 开卡
	 * @param guid 会员资料唯一标识
	 * @param activetime 开卡时间
	 * @param expiretime 到期日期
	 * @param content 备注说明
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public ResultDTO activeCard(String guid, Date activetime, Date expiretime,
			String content, CurrentUser currentUser)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return CommonUtil.newFailedDTO("会员信息不正确！");

		try
		{
			MemberInfo memberInfo = getMemberInfoByGuid(guid);
			if (memberInfo == null)
				return CommonUtil.newFailedDTO("会员信息不存在！");

			Integer status = memberInfo.getStatus();
			if (status != 1)
				return CommonUtil.newFailedDTO("会员信息状态不正确！");

			// 更新会员资料
			MemberInfo updateMemberInfo = new MemberInfo();
			updateMemberInfo.setGuid(guid);
			updateMemberInfo.setStatus(2);// 状态，2：正常
			updateMemberInfo.setActivetime(activetime);
			updateMemberInfo.setExpiretime(expiretime);
			this.memberInfoDao.activeCard(updateMemberInfo);

			// 保存开卡记录
			ActiveCardRecord activeCardRecord = new ActiveCardRecord();
			activeCardRecord.setGuid(BaseUtils.getPrimaryKey());
			activeCardRecord.setMemberguid(guid);
			activeCardRecord.setActivetime(activetime);
			activeCardRecord.setExpiretime(expiretime);
			activeCardRecord.setRemark(content);
			activeCardRecord.setCreator(currentUser.getUserName());
			activeCardRecord.setCreatorid(currentUser.getUserGuid());
			activeCardRecord.setCreatetime(DateHelper.getCurrentDate());
			this.activeCardRecordDao.insert(activeCardRecord);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "开卡成功", guid, activetime, expiretime,
					content);
			return CommonUtil.newSuccessedDTO();
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "开卡异常", ex.toString(), guid, activetime,
					expiretime, content);
			return CommonUtil.newFailedDTO("开卡异常！");
		}
	}

	/**
	 * 续卡
	 * @param guid 会员资料唯一标识
	 * @param money 消费金额
	 * @param expiretime 到期日期
	 * @param content 备注说明
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public ResultDTO continueCard(String guid, Double money, Date expiretime,
			String content, CurrentUser currentUser)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return CommonUtil.newFailedDTO("会员信息不正确！");
		if (money == null || money <= 0)
			return CommonUtil.newFailedDTO("消费金额需要为正数！");
		if (expiretime == null)
			return CommonUtil.newFailedDTO("到期日期格式不正确！");

		try
		{
			MemberInfo memberInfo = getMemberInfoByGuid(guid);
			if (memberInfo == null)
				return CommonUtil.newFailedDTO("会员信息不存在！");

			Double balance = memberInfo.getBalance();
			if (balance == null || balance < money)
				return CommonUtil.newFailedDTO("卡内余额不足！");

			// 更新会员资料
			MemberInfo updateMemberInfo = new MemberInfo();
			updateMemberInfo.setGuid(guid);
			updateMemberInfo.setBalance(balance - money);
			updateMemberInfo.setExpiretime(expiretime);
			this.memberInfoDao.continueCard(updateMemberInfo);

			// 保存续卡记录
			ContinueCardRecord continueCardRecord = new ContinueCardRecord();
			continueCardRecord.setGuid(BaseUtils.getPrimaryKey());
			continueCardRecord.setMemberguid(guid);
			continueCardRecord.setMoney(money);
			continueCardRecord.setExpiretime(expiretime);
			continueCardRecord.setRemark(content);
			continueCardRecord.setCreator(currentUser.getUserName());
			continueCardRecord.setCreatorid(currentUser.getUserGuid());
			continueCardRecord.setCreatetime(DateHelper.getCurrentDate());
			this.continueCardRecordDao.insert(continueCardRecord);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "续卡成功", guid, money, expiretime,
					content);
			return CommonUtil.newSuccessedDTO();
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "续卡异常", ex.toString(), guid, money,
					expiretime, content);
			return CommonUtil.newFailedDTO("续卡异常！");
		}
	}

	/**
	 * 保存签到记录
	 * @param guid 会员资料唯一标识
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public ResultDTO saveSignRecord(String guid, CurrentUser currentUser)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return CommonUtil.newFailedDTO("会员信息不正确！");

		try
		{
			MemberInfo memberInfo = getMemberInfoByGuid(guid);
			if (memberInfo == null)
				return CommonUtil.newFailedDTO("会员信息不存在！");

			Integer status = memberInfo.getStatus();
			if (status != 2)
				return CommonUtil.newFailedDTO("会员信息状态不正确！");

			// 保存签到记录
			SignRecord signRecord = new SignRecord();
			signRecord.setGuid(BaseUtils.getPrimaryKey());
			signRecord.setMemberguid(guid);
			signRecord.setCardtype(memberInfo.getCardtype());
			signRecord.setCreator(currentUser.getUserName());
			signRecord.setCreatorid(currentUser.getUserGuid());
			signRecord.setCreatetime(DateHelper.getCurrentDate());
			this.signRecordDao.insert(signRecord);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "保存签到记录成功", guid);
			return CommonUtil.newSuccessedDTO();
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "保存签到记录异常", ex.toString(), guid);
			return CommonUtil.newFailedDTO("保存签到记录异常！");
		}
	}

	/**
	 * 请假
	 * @param guid 会员资料唯一标识
	 * @param content 备注说明
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public ResultDTO leaveApply(String guid, String content,
			CurrentUser currentUser)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return CommonUtil.newFailedDTO("会员信息不正确！");

		try
		{
			MemberInfo memberInfo = getMemberInfoByGuid(guid);
			if (memberInfo == null)
				return CommonUtil.newFailedDTO("会员信息不存在！");

			Integer status = memberInfo.getStatus();
			if (status != 2)
				return CommonUtil.newFailedDTO("会员信息状态不正确！");

			// 更新会员资料
			MemberInfo updateMemberInfo = new MemberInfo();
			updateMemberInfo.setGuid(guid);
			updateMemberInfo.setStatus(3);// 状态，3：请假
			this.memberInfoDao.leaveApply(updateMemberInfo);

			// 保存请假记录
			LeaveRecord leaveRecord = new LeaveRecord();
			leaveRecord.setGuid(BaseUtils.getPrimaryKey());
			leaveRecord.setMemberguid(guid);
			leaveRecord.setLeavetype("0");
			leaveRecord.setRemark(content);
			leaveRecord.setCreator(currentUser.getUserName());
			leaveRecord.setCreatorid(currentUser.getUserGuid());
			leaveRecord.setCreatetime(DateHelper.getCurrentDate());
			this.leaveRecordDao.insert(leaveRecord);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "请假成功", guid, content);
			return CommonUtil.newSuccessedDTO();
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "请假异常", ex.toString(), guid, content);
			return CommonUtil.newFailedDTO("请假异常！");
		}
	}

	/**
	 * 销假
	 * @param guid 会员资料唯一标识
	 * @param expiretime 到期日期
	 * @param content 备注说明
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public ResultDTO leaveBack(String guid, Date expiretime, String content,
			CurrentUser currentUser)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return CommonUtil.newFailedDTO("会员信息不正确！");

		try
		{
			MemberInfo memberInfo = getMemberInfoByGuid(guid);
			if (memberInfo == null)
				return CommonUtil.newFailedDTO("会员信息不存在！");

			Integer status = memberInfo.getStatus();
			if (status != 3)
				return CommonUtil.newFailedDTO("会员信息状态不正确！");

			// 更新会员资料
			MemberInfo updateMemberInfo = new MemberInfo();
			updateMemberInfo.setGuid(guid);
			updateMemberInfo.setStatus(2);// 状态，2：正常
			updateMemberInfo.setExpiretime(expiretime);
			this.memberInfoDao.leaveBack(updateMemberInfo);

			// 保存销假记录
			LeaveRecord leaveRecord = new LeaveRecord();
			leaveRecord.setGuid(BaseUtils.getPrimaryKey());
			leaveRecord.setMemberguid(guid);
			leaveRecord.setLeavetype("1");
			leaveRecord.setExpiretime(expiretime);
			leaveRecord.setRemark(content);
			leaveRecord.setCreator(currentUser.getUserName());
			leaveRecord.setCreatorid(currentUser.getUserGuid());
			leaveRecord.setCreatetime(DateHelper.getCurrentDate());
			this.leaveRecordDao.insert(leaveRecord);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "销假成功", guid, expiretime, content);
			return CommonUtil.newSuccessedDTO();
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "销假异常", ex.toString(), guid, expiretime,
					content);
			return CommonUtil.newFailedDTO("销假异常！");
		}
	}

	/**
	 * 购买次数
	 * @param guid 会员资料唯一标识
	 * @param money 消费金额
	 * @param times 购买次数
	 * @param expiretime
	 * @param content 备注说明
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public ResultDTO buyCardNumber(String guid, Double money, Integer times,
			Date expiretime, String content, CurrentUser currentUser)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return CommonUtil.newFailedDTO("会员信息不正确！");
		if (money == null || money <= 0)
			return CommonUtil.newFailedDTO("消费金额需要为正数！");
		if (times == null || times <= 0)
			return CommonUtil.newFailedDTO("购买次数需要为正数！");
		if (expiretime == null)
			return CommonUtil.newFailedDTO("到期日期格式不正确！");

		try
		{
			MemberInfo memberInfo = getMemberInfoByGuid(guid);
			if (memberInfo == null)
				return CommonUtil.newFailedDTO("会员信息不存在！");

			Double balance = memberInfo.getBalance();
			if (balance == null || balance < money)
				return CommonUtil.newFailedDTO("卡内余额不足！");

			// 如果没有可用次数
			Integer oldTimes = memberInfo.getTimes();
			if (oldTimes == null)
				oldTimes = 0;

			// 更新会员资料状态为正常
			MemberInfo updateMemberInfo = new MemberInfo();
			updateMemberInfo.setGuid(guid);
			updateMemberInfo.setStatus(2);// 状态，2：正常
			updateMemberInfo.setBalance(balance - money);
			updateMemberInfo.setTimes(oldTimes + times);
			updateMemberInfo.setExpiretime(expiretime);
			this.memberInfoDao.buyCardNumber(updateMemberInfo);

			// 保存购买次数记录
			BuyCardNumberRecord buyCardNumberRecord = new BuyCardNumberRecord();
			buyCardNumberRecord.setGuid(BaseUtils.getPrimaryKey());
			buyCardNumberRecord.setMemberguid(guid);
			buyCardNumberRecord.setMoney(money);
			buyCardNumberRecord.setNumber(times);
			buyCardNumberRecord.setExpiretime(expiretime);
			buyCardNumberRecord.setRemark(content);
			buyCardNumberRecord.setCreator(currentUser.getUserName());
			buyCardNumberRecord.setCreatorid(currentUser.getUserGuid());
			buyCardNumberRecord.setCreatetime(DateHelper.getCurrentDate());
			this.buyCardNumberRecordDao.insert(buyCardNumberRecord);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "购买次数成功", guid, money, times,
					expiretime, content);
			return CommonUtil.newSuccessedDTO();
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "购买次数异常", ex.toString(), guid, money,
					times, expiretime, content);
			return CommonUtil.newFailedDTO("购买次数异常！");
		}
	}

	/**
	 * 次卡保存签到记录
	 * @param guid 会员资料唯一标识
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public ResultDTO numberSignRecord(String guid, CurrentUser currentUser)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return CommonUtil.newFailedDTO("会员信息不正确！");

		try
		{
			MemberInfo memberInfo = getMemberInfoByGuid(guid);
			if (memberInfo == null)
				return CommonUtil.newFailedDTO("会员信息不存在！");

			// 如果不是次卡，则返回失败
			String cardtype = memberInfo.getCardtype();
			if (!"1".equals(cardtype))
				return CommonUtil.newFailedDTO("会员卡不是次卡！");

			// 如果没有可用次数
			Integer oldTimes = memberInfo.getTimes();
			if (oldTimes == null || oldTimes <= 0)
				return CommonUtil.newFailedDTO("会员卡没有可用次数！");

			Integer oldUsedTimes = memberInfo.getUsedtimes();
			if (oldUsedTimes == null)
				oldUsedTimes = 0;

			// 更新会员使用次数
			MemberInfo updateMemberInfo = new MemberInfo();
			updateMemberInfo.setGuid(guid);
			updateMemberInfo.setTimes(oldTimes - 1);
			updateMemberInfo.setUsedtimes(oldUsedTimes + 1);
			this.memberInfoDao.numberSignRecord(updateMemberInfo);

			// 保存签到记录
			SignRecord signRecord = new SignRecord();
			signRecord.setGuid(BaseUtils.getPrimaryKey());
			signRecord.setMemberguid(guid);
			signRecord.setCardtype(cardtype);
			signRecord.setCreator(currentUser.getUserName());
			signRecord.setCreatorid(currentUser.getUserGuid());
			signRecord.setCreatetime(DateHelper.getCurrentDate());
			this.signRecordDao.insert(signRecord);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "次卡保存签到记录成功", guid);
			return CommonUtil.newSuccessedDTO();
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "次卡保存签到记录异常", ex.toString(), guid);
			return CommonUtil.newFailedDTO("次卡保存签到记录异常！");
		}
	}

	/**
	 * 保存积分
	 * @param guid 会员资料唯一标识
	 * @param points 积分
	 * @param content 备注说明
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public ResultDTO saveCardPoints(String guid, Integer points, String content,
			CurrentUser currentUser)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return CommonUtil.newFailedDTO("会员信息不正确！");
		if (points == null || points <= 0)
			return CommonUtil.newFailedDTO("积分需要为正数！");

		try
		{
			MemberInfo memberInfo = getMemberInfoByGuid(guid);
			if (memberInfo == null)
				return CommonUtil.newFailedDTO("会员信息不存在！");

			Integer oldPoints = memberInfo.getPoints();
			if (oldPoints == null)
				oldPoints = 0;

			// 保存积分
			MemberInfo updateMemberInfo = new MemberInfo();
			updateMemberInfo.setGuid(guid);
			updateMemberInfo.setPoints(oldPoints + points);
			this.memberInfoDao.saveCardPoints(updateMemberInfo);

			// 保存积分记录
			PointsRecord pointsRecord = new PointsRecord();
			pointsRecord.setGuid(BaseUtils.getPrimaryKey());
			pointsRecord.setMemberguid(guid);
			pointsRecord.setPointtype("0");
			pointsRecord.setPoints(points);
			pointsRecord.setRemark(content);
			pointsRecord.setCreator(currentUser.getUserName());
			pointsRecord.setCreatorid(currentUser.getUserGuid());
			pointsRecord.setCreatetime(DateHelper.getCurrentDate());
			this.pointsRecordDao.insert(pointsRecord);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "保存积分成功", guid, points, content);
			return CommonUtil.newSuccessedDTO();
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "保存积分异常", ex.toString(), guid, points,
					content);
			return CommonUtil.newFailedDTO("保存积分异常！");
		}
	}

	/**
	 * 积分兑换时间
	 * @param guid 会员资料唯一标识
	 * @param points 兑换积分
	 * @param expiretime 到期日期
	 * @param content 备注说明
	 * @param currentUser
	 * @return
	 */
	@Transactional
	public ResultDTO pointsExchangeTime(String guid, Integer points,
			Date expiretime, String content, CurrentUser currentUser)
	{
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
			return CommonUtil.newFailedDTO("会员信息不正确！");
		if (points == null || points <= 0)
			return CommonUtil.newFailedDTO("兑换积分需要为正数！");
		if (expiretime == null)
			return CommonUtil.newFailedDTO("到期日期格式不正确！");

		try
		{
			MemberInfo memberInfo = getMemberInfoByGuid(guid);
			if (memberInfo == null)
				return CommonUtil.newFailedDTO("会员信息不存在！");

			// 如果不是时间卡，则返回失败
			String cardtype = memberInfo.getCardtype();
			if (!"0".equals(cardtype))
				return CommonUtil.newFailedDTO("会员卡不是时间卡！");

			Integer oldPoints = memberInfo.getPoints();
			if (oldPoints == null || oldPoints < points)
				return CommonUtil.newFailedDTO("会员积分不足！");

			Date oldExpireTime = memberInfo.getExpiretime();

			// 保存积分
			MemberInfo updateMemberInfo = new MemberInfo();
			updateMemberInfo.setGuid(guid);
			updateMemberInfo.setPoints(oldPoints - points);
			updateMemberInfo.setExpiretime(expiretime);
			this.memberInfoDao.pointsExchangeTime(updateMemberInfo);

			// 保存积分记录
			PointsRecord pointsRecord = new PointsRecord();
			pointsRecord.setGuid(BaseUtils.getPrimaryKey());
			pointsRecord.setMemberguid(guid);
			pointsRecord.setPointtype("1");
			pointsRecord.setPoints(points);
			pointsRecord.setOldexpiretime(oldExpireTime);
			pointsRecord.setExpiretime(expiretime);
			pointsRecord.setRemark(content);
			pointsRecord.setCreator(currentUser.getUserName());
			pointsRecord.setCreatorid(currentUser.getUserGuid());
			pointsRecord.setCreatetime(DateHelper.getCurrentDate());
			this.pointsRecordDao.insert(pointsRecord);

			// 记录操作日志
			LogProxy.WriteLogOperate(log, "积分兑换时间成功", guid, points, content);
			return CommonUtil.newSuccessedDTO();
		}
		catch (Exception ex)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记错误日志
			LogProxy.WriteLogError(log, "积分兑换时间异常", ex.toString(), guid, points,
					content);
			return CommonUtil.newFailedDTO("积分兑换时间异常！");
		}
	}

}
