package com.jm.vip.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jm.base.controller.BaseController;
import com.jm.common.ResultDTO;
import com.jm.log.LogProxy;
import com.jm.security.RegexHelper;
import com.jm.utils.JSONUtils;
import com.jm.vip.entity.ChargeRecord;
import com.jm.vip.entity.MemberHistoryInfo;
import com.jm.vip.entity.MemberInfo;
import com.jm.vip.entity.SignRecord;
import com.jm.vip.menu.MemberInfoHelper;
import com.jm.vip.service.MemberInfoService;
import com.jm.vip.service.RecordService;

@Controller
@RequestMapping("/member")
public class MemberController extends BaseController
{
	@Resource(name = "memberInfoService")
	private MemberInfoService memberInfoService;

	@Resource(name = "recordService")
	private RecordService recordService;

	private static final String JSP_PATH = "member";

	/**
	 * 加载会员资料的列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String loadList(Model model)
	{
		// 加载列表菜单
		MemberInfoHelper helper = new MemberInfoHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "会员资料");
		model.addAttribute("mapperid", "MemberInfoMapper.selectListByPage");

		return JSP_PATH + "/list";
	}

	/**
	 * 加载会员封存资料的列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/historylist", method = RequestMethod.GET)
	public String loadHistoryList(Model model)
	{
		model.addAttribute("listTitle", "会员封存资料");
		model.addAttribute("mapperid",
				"MemberHistoryInfoMapper.selectListByPage");

		return JSP_PATH + "/historylist";
	}

	/**
	 * 打开会员资料的添加页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String loadAdd(Model model)
	{
		// 将菜单集合传给前台
		MemberInfoHelper helper = new MemberInfoHelper();
		model.addAttribute("menulist", helper.getAddMenu());

		return JSP_PATH + "/add";
	}

	/**
	 * 打开会员资料的查看页
	 * @param model
	 * @param guid 唯一标示
	 * @return
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String loadView(Model model,
			@RequestParam(required = false) String guid)
	{
		// 入参校验
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
		{
			model.addAttribute("errormessage", "参数校验不正确！");
			return "error/error";
		}

		MemberInfo memberInfo = this.memberInfoService
				.getMemberInfoByGuid(guid);
		if (memberInfo == null)
		{
			model.addAttribute("errormessage", "会员资料不存在！");
			return "error/error";
		}

		// 会员资料
		model.addAttribute("memberInfo", memberInfo);

		List<SignRecord> signRecordList = this.recordService
				.getSignRecordList(5, guid);
		model.addAttribute("signRecordList", signRecordList);

		List<ChargeRecord> chargeRecordList = this.recordService
				.getChargeRecordList(5, guid);
		model.addAttribute("chargeRecordList", chargeRecordList);

		// 将菜单集合传给前台
		MemberInfoHelper helper = new MemberInfoHelper();
		model.addAttribute("menulist", helper.getViewMenu(memberInfo));

		return JSP_PATH + "/view";
	}

	/**
	 * 打开会员封存资料的查看页
	 * @param model
	 * @param guid 唯一标示
	 * @return
	 */
	@RequestMapping(value = "/historyview", method = RequestMethod.GET)
	public String loadHistoryView(Model model,
			@RequestParam(required = false) String guid)
	{
		// 入参校验
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
		{
			model.addAttribute("errormessage", "参数校验不正确！");
			return "error/error";
		}

		MemberHistoryInfo memberInfo = this.memberInfoService
				.getMemberHistoryInfoByGuid(guid);
		if (memberInfo == null)
		{
			model.addAttribute("errormessage", "会员资料不存在！");
			return "error/error";
		}

		// 会员资料
		model.addAttribute("memberInfo", memberInfo);

		// 将菜单集合传给前台
		MemberInfoHelper helper = new MemberInfoHelper();
		model.addAttribute("menulist", helper.getHistoryViewMenu(memberInfo));

		return JSP_PATH + "/view";
	}

	/**
	 * 打开会员资料的修改页
	 * @param model
	 * @param guid 唯一标示
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String loadEdit(Model model,
			@RequestParam(required = false) String guid)
	{
		// 入参校验
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
		{
			model.addAttribute("errormessage", "参数校验不正确！");
			return "error/error";
		}

		MemberInfo memberInfo = this.memberInfoService
				.getMemberInfoByGuid(guid);
		if (memberInfo == null)
		{
			model.addAttribute("errormessage", "会员资料不存在！");
			return "error/error";
		}

		// 会员资料
		model.addAttribute("memberInfo", memberInfo);

		// 将菜单集合传给前台
		MemberInfoHelper helper = new MemberInfoHelper();
		model.addAttribute("menulist", helper.getEditMenu(guid));

		return JSP_PATH + "/edit";
	}

	/**
	 * 校验会员卡号是否存在
	 * @param cardNumber 会员卡号
	 * @return
	 */
	@RequestMapping(value = "/isExistCardNumber", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO isExistCardNumber(@RequestParam String cardNumber)
	{
		ResultDTO result = new ResultDTO();

		try
		{
			boolean isExist = this.memberInfoService
					.isExistCardNumber(cardNumber);
			result.setSuccess(isExist);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "校验会员卡号是否存在失败", ex.toString(),
					cardNumber);
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 添加会员账号
	 * @param memberInfo
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "text/html")
	@ResponseBody
	public ResultDTO insert(MemberInfo memberInfo)
	{
		ResultDTO result = new ResultDTO();

		try
		{
			String guid = this.memberInfoService.insertMemberInfo(memberInfo,
					super.getContextUser());

			result.setSuccess(StringUtils.isNotEmpty(guid));
			result.setMessage(guid);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "添加会员账号失败", ex.toString(), memberInfo);
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 修改会员资料
	 * @param memberInfo
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO update(MemberInfo memberInfo)
	{
		ResultDTO result = new ResultDTO();

		try
		{
			boolean isUpdate = this.memberInfoService
					.updateMemberInfo(memberInfo);

			result.setSuccess(isUpdate);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "修改会员资料失败", ex.toString(), memberInfo);
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 会员资料列表封存
	 * @param list JSON列表
	 * @return
	 */
	@RequestMapping(value = "/deletelist", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO deleteList(@RequestBody List<JSONObject> list)
	{
		List<String> guidList = JSONUtils.getListByField(list, "guid");
		ResultDTO result = new ResultDTO();

		try
		{
			if (guidList.size() > 0)
			{
				boolean isDelete = this.memberInfoService
						.deleteMemberInfoList(guidList, getContextUser());
				result.setSuccess(isDelete);
			}
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "会员资料列表封存失败", ex.toString(), list);
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 查看页封存
	 * @param guid 会员资料唯一标识
	 * @return
	 */
	@RequestMapping(value = "/deleteOne", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO deleteOne(@RequestParam String guid)
	{
		ResultDTO result = new ResultDTO();

		try
		{
			boolean isDelete = this.memberInfoService.deleteMemberInfoList(
					Arrays.asList(guid), getContextUser());
			result.setSuccess(isDelete);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "封存会员资料失败", ex.toString(), guid);
			result.setSuccess(false);
		}

		return result;
	}

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
		ResultDTO result = new ResultDTO();

		try
		{
			boolean success = this.memberInfoService.charge(guid, money,
					content, getContextUser());
			result.setSuccess(success);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "充值失败", ex.toString(), guid, money,
					content);
			result.setSuccess(false);
		}

		return result;
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
		ResultDTO result = new ResultDTO();

		try
		{
			boolean success = this.memberInfoService.buyCard(guid, money,
					content);
			result.setSuccess(success);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "买卡失败", ex.toString(), guid, money,
					content);
			result.setSuccess(false);
		}

		return result;
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
		ResultDTO result = new ResultDTO();

		try
		{
			boolean success = this.memberInfoService.activeCard(guid,
					activetime, expiretime, content);
			result.setSuccess(success);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "开卡失败", ex.toString(), guid, activetime,
					expiretime, content);
			result.setSuccess(false);
		}

		return result;
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
		ResultDTO result = new ResultDTO();

		try
		{
			boolean success = this.memberInfoService.continueCard(guid, money,
					expiretime, content);
			result.setSuccess(success);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "续卡失败", ex.toString(), guid, money,
					expiretime, content);
			result.setSuccess(false);
		}

		return result;
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
		ResultDTO result = new ResultDTO();

		try
		{
			boolean success = this.memberInfoService.saveSignRecord(guid,
					getContextUser());
			result.setSuccess(success);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "保存签到记录失败", ex.toString(), guid);
			result.setSuccess(false);
		}

		return result;
	}

}
