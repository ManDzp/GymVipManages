package com.jm.vip.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jm.commons.base.controller.BaseController;
import com.jm.commons.domain.ResultDTO;
import com.jm.commons.log.LogProxy;
import com.jm.commons.utils.ResultDTOUtil;
import com.jm.vip.service.MemberInfoService;

@Controller
@RequestMapping("/member")
public class MemberOperateController extends BaseController
{
	@Resource(name = "memberInfoService")
	private MemberInfoService memberInfoService;

	/**
	 * 充值
	 * @param guid 会员资料唯一标识
	 * @param money 充值金额
	 * @param content 备注说明
	 * @return
	 */
	@RequestMapping(value = "/charge", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO charge(@RequestParam String guid,
			@RequestParam Double money, @RequestParam String content)
	{
		try
		{
			ResultDTO result = this.memberInfoService.charge(guid, money,
					content, getContextUser());
			return result;
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "充值失败", ex.toString(), guid, money,
					content);
			return ResultDTOUtil.error("充值失败！");
		}
	}

	/**
	 * 买卡
	 * @param guid 会员资料唯一标识
	 * @param money 消费金额
	 * @param content 备注说明
	 * @return
	 */
	@RequestMapping(value = "/buyCard", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO buyCard(@RequestParam String guid,
			@RequestParam Double money, @RequestParam String content)
	{
		try
		{
			ResultDTO result = this.memberInfoService.buyCard(guid, money,
					content, getContextUser());
			return result;
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "买卡失败", ex.toString(), guid, money,
					content);
			return ResultDTOUtil.error("买卡失败！");
		}
	}

	/**
	 * 开卡
	 * @param guid 会员资料唯一标识
	 * @param activetime 开卡时间
	 * @param expiretime 到期日期
	 * @param content 备注说明
	 * @return
	 */
	@RequestMapping(value = "/activeCard", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO activeCard(@RequestParam String guid,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date activetime,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date expiretime,
			@RequestParam String content)
	{
		try
		{
			ResultDTO result = this.memberInfoService.activeCard(guid,
					activetime, expiretime, content, getContextUser());
			return result;
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "开卡失败", ex.toString(), guid, activetime,
					expiretime, content);
			return ResultDTOUtil.error("开卡失败！");
		}
	}

	/**
	 * 续卡
	 * @param guid 会员资料唯一标识
	 * @param money 消费金额
	 * @param expiretime 到期日期
	 * @param content 备注说明
	 * @return
	 */
	@RequestMapping(value = "/continueCard", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO continueCard(@RequestParam String guid,
			@RequestParam Double money,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date expiretime,
			@RequestParam String content)
	{
		try
		{
			ResultDTO result = this.memberInfoService.continueCard(guid, money,
					expiretime, content, getContextUser());
			return result;
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "续卡失败", ex.toString(), guid, money,
					expiretime, content);
			return ResultDTOUtil.error("续卡失败！");
		}
	}

	/**
	 * 保存签到记录
	 * @param guid 会员资料唯一标识
	 * @return
	 */
	@RequestMapping(value = "/signRecord", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO signRecord(@RequestParam String guid)
	{
		try
		{
			ResultDTO result = this.memberInfoService.saveSignRecord(guid,
					getContextUser());
			return result;
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "保存签到记录失败", ex.toString(), guid);
			return ResultDTOUtil.error("保存签到记录失败！");
		}
	}

	/**
	 * 请假
	 * @param guid 会员资料唯一标识
	 * @param content 备注说明
	 * @return
	 */
	@RequestMapping(value = "/leaveApply", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO leaveApply(@RequestParam String guid,
			@RequestParam String content)
	{
		try
		{
			ResultDTO result = this.memberInfoService.leaveApply(guid, content,
					getContextUser());
			return result;
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "请假失败", ex.toString(), guid, content);
			return ResultDTOUtil.error("请假失败！");
		}
	}

	/**
	 * 销假
	 * @param guid 会员资料唯一标识
	 * @param expiretime 到期日期
	 * @param content 备注说明
	 * @return
	 */
	@RequestMapping(value = "/leaveBack", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO leaveBack(@RequestParam String guid,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date expiretime,
			@RequestParam String content)
	{
		try
		{
			ResultDTO result = this.memberInfoService.leaveBack(guid,
					expiretime, content, getContextUser());
			return result;
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "销假失败", ex.toString(), guid, expiretime,
					content);
			return ResultDTOUtil.error("销假失败！");
		}
	}

	/**
	 * 购买次数
	 * @param guid 会员资料唯一标识
	 * @param money 消费金额
	 * @param times 购买次数
	 * @param expiretime 到期日期
	 * @param content 备注说明
	 * @return
	 */
	@RequestMapping(value = "/buyCardNumber", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO buyCardNumber(@RequestParam String guid,
			@RequestParam Double money, @RequestParam Integer times,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date expiretime,
			@RequestParam String content)
	{
		try
		{
			ResultDTO result = this.memberInfoService.buyCardNumber(guid, money,
					times, expiretime, content, getContextUser());
			return result;
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "购买次数失败", ex.toString(), guid, money,
					times, expiretime, content);
			return ResultDTOUtil.error("购买次数失败！");
		}
	}

	/**
	 * 保存签到记录
	 * @param guid 会员资料唯一标识
	 * @return
	 */
	@RequestMapping(value = "/numberSignRecord", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO numberSignRecord(@RequestParam String guid)
	{
		try
		{
			ResultDTO result = this.memberInfoService.numberSignRecord(guid,
					getContextUser());
			return result;
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "次卡保存签到记录失败", ex.toString(), guid);
			return ResultDTOUtil.error("次卡保存签到记录失败！");
		}
	}

	/**
	 * 保存积分
	 * @param guid 会员资料唯一标识
	 * @param points 积分
	 * @param content 备注说明
	 * @return
	 */
	@RequestMapping(value = "/saveCardPoints", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO saveCardPoints(@RequestParam String guid,
			@RequestParam Integer points, @RequestParam String content)
	{
		try
		{
			ResultDTO result = this.memberInfoService.saveCardPoints(guid,
					points, content, getContextUser());
			return result;
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "保存积分失败", ex.toString(), guid, points,
					content);
			return ResultDTOUtil.error("保存积分失败！");
		}
	}

	/**
	 * 积分兑换时间
	 * @param guid 会员资料唯一标识
	 * @param points 兑换积分
	 * @param expiretime 到期日期
	 * @param content 备注说明
	 * @return
	 */
	@RequestMapping(value = "/pointsExchangeTime", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO pointsExchangeTime(@RequestParam String guid,
			@RequestParam Integer points,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date expiretime,
			@RequestParam String content)
	{
		try
		{
			ResultDTO result = this.memberInfoService.pointsExchangeTime(guid,
					points, expiretime, content, getContextUser());
			return result;
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "积分兑换时间失败", ex.toString(), guid, points,
					expiretime, content);
			return ResultDTOUtil.error("积分兑换时间失败！");
		}
	}

}
