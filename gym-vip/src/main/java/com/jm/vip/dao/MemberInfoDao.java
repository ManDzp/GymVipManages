package com.jm.vip.dao;

import java.util.List;

import com.jm.commons.page.PageSearch;
import com.jm.vip.entity.MemberInfo;

public interface MemberInfoDao extends BaseDao<MemberInfo>
{
	/**
	 * 获取分页数据
	 * @param pageSearch
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
			Integer pageSize);

	/**
	 * 查找会员卡号信息
	 * @param cardNumber 会员卡号
	 * @return
	 */
	public MemberInfo getMemberByCardNumber(String cardNumber);

	/**
	 * 查找会员卡号信息
	 * @param cardNumber 会员卡号
	 * @param withoutGuid 排除的唯一标识
	 * @return
	 */
	public MemberInfo getMemberByCardNumber(String cardNumber,
			String withoutGuid);

	/**
	 * 设置超期
	 * @param memberInfo
	 * @return
	 */
	public int setOverTime(MemberInfo memberInfo);

	/**
	 * 充值
	 * @param memberInfo
	 * @return
	 */
	public int charge(MemberInfo memberInfo);

	/**
	 * 买卡
	 * @param memberInfo
	 * @return
	 */
	public int buyCard(MemberInfo memberInfo);

	/**
	 * 消费
	 * @param memberInfo
	 * @return
	 */
	public int spend(MemberInfo memberInfo);

	/**
	 * 开卡
	 * @param memberInfo
	 * @return
	 */
	public int activeCard(MemberInfo memberInfo);

	/**
	 * 续卡
	 * @param memberInfo
	 * @return
	 */
	public int continueCard(MemberInfo memberInfo);

	/**
	 * 请假
	 * @param memberInfo
	 * @return
	 */
	public int leaveApply(MemberInfo memberInfo);

	/**
	 * 销假
	 * @param memberInfo
	 * @return
	 */
	public int leaveBack(MemberInfo memberInfo);

	/**
	 * 购买次数
	 * @param memberInfo
	 * @return
	 */
	public int buyCardNumber(MemberInfo memberInfo);

	/**
	 * 次卡签到
	 * @param memberInfo
	 * @return
	 */
	public int numberSignRecord(MemberInfo memberInfo);

	/**
	 * 获取积分
	 * @param memberInfo
	 * @return
	 */
	public int saveCardPoints(MemberInfo memberInfo);

	/**
	 * 积分兑换时间
	 * @param memberInfo
	 * @return
	 */
	public int pointsExchangeTime(MemberInfo memberInfo);

}