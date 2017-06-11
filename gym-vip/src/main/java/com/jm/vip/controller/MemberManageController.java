package com.jm.vip.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jm.commons.base.controller.BaseController;
import com.jm.commons.domain.ResultDTO;
import com.jm.commons.log.LogProxy;
import com.jm.commons.security.RegexHelper;
import com.jm.commons.utils.JSONUtils;
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
import com.jm.vip.menu.MemberInfoHelper;
import com.jm.vip.service.AttachmentService;
import com.jm.vip.service.MemberInfoService;
import com.jm.vip.service.RecordService;

@Controller
@RequestMapping("/member")
public class MemberManageController extends BaseController
{
	@Resource(name = "memberInfoService")
	private MemberInfoService memberInfoService;

	@Resource(name = "recordService")
	private RecordService recordService;

	@Resource(name = "attachmentService")
	protected AttachmentService attachmentService;

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
	 * 加载查询会员资料的列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/searchlist", method = RequestMethod.GET)
	public String loadSearchList(Model model)
	{
		model.addAttribute("listTitle", "查询会员资料");
		model.addAttribute("mapperid",
				"MemberSearchInfoMapper.selectListByPage");

		return JSP_PATH + "/searchlist";
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

		// 检验是否超期
		this.memberInfoService.checkOverTime(guid, getContextUser());

		MemberInfo memberInfo = this.memberInfoService
				.getMemberInfoByGuid(guid);
		if (memberInfo == null)
		{
			model.addAttribute("errormessage", "会员资料不存在！");
			return "error/error";
		}

		// 会员资料
		model.addAttribute("memberInfo", memberInfo);

		// 封存状态，0：正常，1：封存
		model.addAttribute("deletetype", "0");

		// 查看页会员图片
		String imgUrl = attachmentService.getFirstImageUrl(request, guid);
		if (StringUtils.isNotEmpty(imgUrl))
		{
			String imgHtml = "<img class='member-info-img' src='" + imgUrl
					+ "'>";
			model.addAttribute("imgHtml", imgHtml);
		}

		// 充值记录
		List<ChargeRecord> chargeRecordList = this.recordService
				.getChargeRecordList(5, guid);
		model.addAttribute("chargeRecordList", chargeRecordList);

		// 签到记录
		List<SignRecord> signRecordList = this.recordService
				.getSignRecordList(5, guid);
		model.addAttribute("signRecordList", signRecordList);

		String cardtype = memberInfo.getCardtype();// 会员类型，0：时间卡，1：次卡

		if ("0".equals(cardtype))
		{
			// 买卡记录
			List<BuyCardRecord> buyCardRecordList = this.recordService
					.getBuyCardRecordList(5, guid);
			model.addAttribute("buyCardRecordList", buyCardRecordList);

			// 开卡记录
			List<ActiveCardRecord> activeCardRecordList = this.recordService
					.getActiveCardRecordList(5, guid);
			model.addAttribute("activeCardRecordList", activeCardRecordList);

			// 续卡记录
			List<ContinueCardRecord> continueCardRecordList = this.recordService
					.getContinueCardRecordList(5, guid);
			model.addAttribute("continueCardRecordList",
					continueCardRecordList);

			// 请销假记录
			List<LeaveRecord> leaveRecordList = this.recordService
					.getLeaveRecordList(5, guid);
			model.addAttribute("leaveRecordList", leaveRecordList);

			// 积分记录
			List<PointsRecord> pointsRecordList = this.recordService
					.getPointsRecordList(5, guid);
			model.addAttribute("pointsRecordList", pointsRecordList);
		}
		else if ("1".equals(cardtype))
		{
			// 购买次数记录
			List<BuyCardNumberRecord> buyCardNumberRecordList = this.recordService
					.getBuyCardNumberRecordList(5, guid);
			model.addAttribute("buyCardNumberRecordList",
					buyCardNumberRecordList);
		}

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

		// 封存状态，0：正常，1：封存
		model.addAttribute("deletetype", "1");

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

		// 附件信息列表
		model.addAttribute("attachmentStr",
				attachmentService.getAttachmentHtml(request, guid, true));

		// 获取附件大小
		model.addAttribute("totalSize",
				this.attachmentService.getAttachmentTotalSize(guid));

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
	 * @param request
	 * @param memberInfo
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "text/html")
	@ResponseBody
	public ResultDTO insert(HttpServletRequest request, MemberInfo memberInfo)
	{
		ResultDTO result = new ResultDTO();

		try
		{
			// 文件路径
			String realPath = request.getSession().getServletContext()
					.getRealPath("/");
			String allFiles = request.getParameter("hdFiles");

			String guid = this.memberInfoService.insertMemberInfo(memberInfo,
					realPath, allFiles, super.getContextUser());

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
			// 文件路径
			String realPath = request.getSession().getServletContext()
					.getRealPath("/");
			String allFiles = request.getParameter("hdFiles");

			boolean isUpdate = this.memberInfoService
					.updateMemberInfo(memberInfo, realPath, allFiles);

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

}
