package com.jm.vip.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jm.commons.security.RegexHelper;
import com.jm.vip.entity.ActiveCardRecord;
import com.jm.vip.entity.BuyCardNumberRecord;
import com.jm.vip.entity.BuyCardRecord;
import com.jm.vip.entity.ChargeRecord;
import com.jm.vip.entity.ContinueCardRecord;
import com.jm.vip.entity.LeaveRecord;
import com.jm.vip.entity.PointsRecord;
import com.jm.vip.menu.RecordHelper;
import com.jm.vip.service.RecordService;

/**
 * 查看记录控制器
 */
@Controller
@RequestMapping("/record")
public class RecordController
{
	@Resource(name = "recordService")
	private RecordService recordService;

	private static final String CHARGE_RECORD_JSP_PATH = "record/chargerecord";

	private static final String BUY_CARD_RECORD_JSP_PATH = "record/buycardrecord";

	private static final String ACTIVE_CARD_RECORD_JSP_PATH = "record/activecardrecord";

	private static final String CONTINUE_CARD_RECORD_JSP_PATH = "record/continuecardrecord";

	private static final String LEAVE_RECORD_JSP_PATH = "record/leaverecord";

	private static final String SIGN_RECORD_JSP_PATH = "record/signrecord";

	private static final String BUY_CARD_NUMBER_RECORD_JSP_PATH = "record/buycardnumberrecord";

	private static final String POINTS_RECORD_JSP_PATH = "record/pointsrecord";

	/**
	 * 加载充值记录的列表页
	 * @param model
	 * @param memberguid 会员资料唯一标示
	 * @return
	 */
	@RequestMapping(value = "/chargerecord/list", method = RequestMethod.GET)
	public String loadChargeRecordList(Model model,
			@RequestParam(required = false) String memberguid)
	{
		model.addAttribute("memberguid", memberguid);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "充值记录");
		model.addAttribute("mapperid", "ChargeRecordMapper.selectListByPage");

		return CHARGE_RECORD_JSP_PATH + "/list";
	}

	/**
	 * 获取充值记录信息
	 * @param model
	 * @param guid 记录唯一标示
	 * @return
	 */
	@RequestMapping(value = "/chargerecord/view", method = RequestMethod.GET)
	public String loadChargeRecordView(Model model,
			@RequestParam(required = false) String guid)
	{
		// 入参校验
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
		{
			model.addAttribute("errormessage", "参数校验不正确！");
			return "error/error";
		}

		ChargeRecord chargeRecord = this.recordService.getChargeRecord(guid);
		if (chargeRecord == null)
		{
			model.addAttribute("errormessage", "充值记录不存在！");
			return "error/error";
		}

		// 充值记录
		model.addAttribute("chargeRecord", chargeRecord);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getViewMenu());

		return CHARGE_RECORD_JSP_PATH + "/view";
	}

	/**
	 * 加载买卡记录的列表页
	 * @param model
	 * @param memberguid 会员资料唯一标示
	 * @return
	 */
	@RequestMapping(value = "/buycardrecord/list", method = RequestMethod.GET)
	public String loadBuyCardRecordList(Model model,
			@RequestParam(required = false) String memberguid)
	{
		model.addAttribute("memberguid", memberguid);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "买卡记录");
		model.addAttribute("mapperid", "BuyCardRecordMapper.selectListByPage");

		return BUY_CARD_RECORD_JSP_PATH + "/list";
	}

	/**
	 * 获取买卡记录信息
	 * @param model
	 * @param guid 记录唯一标示
	 * @return
	 */
	@RequestMapping(value = "/buycardrecord/view", method = RequestMethod.GET)
	public String loadBuyCardRecordView(Model model,
			@RequestParam(required = false) String guid)
	{
		// 入参校验
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
		{
			model.addAttribute("errormessage", "参数校验不正确！");
			return "error/error";
		}

		BuyCardRecord buyCardRecord = this.recordService.getBuyCardRecord(guid);
		if (buyCardRecord == null)
		{
			model.addAttribute("errormessage", "买卡记录不存在！");
			return "error/error";
		}

		// 买卡记录
		model.addAttribute("buyCardRecord", buyCardRecord);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getViewMenu());

		return BUY_CARD_RECORD_JSP_PATH + "/view";
	}

	/**
	 * 加载开卡记录的列表页
	 * @param model
	 * @param memberguid 会员资料唯一标示
	 * @return
	 */
	@RequestMapping(value = "/activecardrecord/list", method = RequestMethod.GET)
	public String loadActiveCardRecordList(Model model,
			@RequestParam(required = false) String memberguid)
	{
		model.addAttribute("memberguid", memberguid);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "开卡记录");
		model.addAttribute("mapperid",
				"ActiveCardRecordMapper.selectListByPage");

		return ACTIVE_CARD_RECORD_JSP_PATH + "/list";
	}

	/**
	 * 获取开卡记录信息
	 * @param model
	 * @param guid 记录唯一标示
	 * @return
	 */
	@RequestMapping(value = "/activecardrecord/view", method = RequestMethod.GET)
	public String loadActiveCardRecordView(Model model,
			@RequestParam(required = false) String guid)
	{
		// 入参校验
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
		{
			model.addAttribute("errormessage", "参数校验不正确！");
			return "error/error";
		}

		ActiveCardRecord activeCardRecord = this.recordService
				.getActiveCardRecord(guid);
		if (activeCardRecord == null)
		{
			model.addAttribute("errormessage", "开卡记录不存在！");
			return "error/error";
		}

		// 开卡记录
		model.addAttribute("activeCardRecord", activeCardRecord);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getViewMenu());

		return ACTIVE_CARD_RECORD_JSP_PATH + "/view";
	}

	/**
	 * 加载续卡记录的列表页
	 * @param model
	 * @param memberguid 会员资料唯一标示
	 * @return
	 */
	@RequestMapping(value = "/continuecardrecord/list", method = RequestMethod.GET)
	public String loadContinueCardRecordList(Model model,
			@RequestParam(required = false) String memberguid)
	{
		model.addAttribute("memberguid", memberguid);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "续卡记录");
		model.addAttribute("mapperid",
				"ContinueCardRecordMapper.selectListByPage");

		return CONTINUE_CARD_RECORD_JSP_PATH + "/list";
	}

	/**
	 * 获取续卡记录信息
	 * @param model
	 * @param guid 记录唯一标示
	 * @return
	 */
	@RequestMapping(value = "/continuecardrecord/view", method = RequestMethod.GET)
	public String loadContinueCardRecordView(Model model,
			@RequestParam(required = false) String guid)
	{
		// 入参校验
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
		{
			model.addAttribute("errormessage", "参数校验不正确！");
			return "error/error";
		}

		ContinueCardRecord continueCardRecord = this.recordService
				.getContinueCardRecord(guid);
		if (continueCardRecord == null)
		{
			model.addAttribute("errormessage", "续卡记录不存在！");
			return "error/error";
		}

		// 续卡记录
		model.addAttribute("continueCardRecord", continueCardRecord);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getViewMenu());

		return CONTINUE_CARD_RECORD_JSP_PATH + "/view";
	}

	/**
	 * 加载签到记录的列表页
	 * @param model
	 * @param memberguid 会员资料唯一标示
	 * @return
	 */
	@RequestMapping(value = "/signrecord/list", method = RequestMethod.GET)
	public String loadSignRecordList(Model model,
			@RequestParam(required = false) String memberguid)
	{
		model.addAttribute("memberguid", memberguid);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "签到记录");
		model.addAttribute("mapperid", "SignRecordMapper.selectListByPage");

		return SIGN_RECORD_JSP_PATH + "/list";
	}

	/**
	 * 加载请销假记录的列表页
	 * @param model
	 * @param memberguid 会员资料唯一标示
	 * @return
	 */
	@RequestMapping(value = "/leaverecord/list", method = RequestMethod.GET)
	public String loadLeaveRecordList(Model model,
			@RequestParam(required = false) String memberguid)
	{
		model.addAttribute("memberguid", memberguid);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "请销假记录");
		model.addAttribute("mapperid", "LeaveRecordMapper.selectListByPage");

		return LEAVE_RECORD_JSP_PATH + "/list";
	}

	/**
	 * 获取请销假记录信息
	 * @param model
	 * @param guid 记录唯一标示
	 * @return
	 */
	@RequestMapping(value = "/leaverecord/view", method = RequestMethod.GET)
	public String loadLeaveRecordView(Model model,
			@RequestParam(required = false) String guid)
	{
		// 入参校验
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
		{
			model.addAttribute("errormessage", "参数校验不正确！");
			return "error/error";
		}

		LeaveRecord leaveRecord = this.recordService.getLeaveRecord(guid);
		if (leaveRecord == null)
		{
			model.addAttribute("errormessage", "请销假记录不存在！");
			return "error/error";
		}

		// 请销假记录
		model.addAttribute("leaveRecord", leaveRecord);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getViewMenu());

		return LEAVE_RECORD_JSP_PATH + "/view";
	}

	/**
	 * 加载购买次数记录的列表页
	 * @param model
	 * @param memberguid 会员资料唯一标示
	 * @return
	 */
	@RequestMapping(value = "/buycardnumberrecord/list", method = RequestMethod.GET)
	public String loadBuyCardNumberRecordList(Model model,
			@RequestParam(required = false) String memberguid)
	{
		model.addAttribute("memberguid", memberguid);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "购买次数记录");
		model.addAttribute("mapperid",
				"BuyCardNumberRecordMapper.selectListByPage");

		return BUY_CARD_NUMBER_RECORD_JSP_PATH + "/list";
	}

	/**
	 * 获取购买次数记录信息
	 * @param model
	 * @param guid 记录唯一标示
	 * @return
	 */
	@RequestMapping(value = "/buycardnumberrecord/view", method = RequestMethod.GET)
	public String loadBuyCardNumberRecordView(Model model,
			@RequestParam(required = false) String guid)
	{
		// 入参校验
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
		{
			model.addAttribute("errormessage", "参数校验不正确！");
			return "error/error";
		}

		BuyCardNumberRecord buyCardNumberRecord = this.recordService
				.getBuyCardNumberRecord(guid);
		if (buyCardNumberRecord == null)
		{
			model.addAttribute("errormessage", "购买次数记录不存在！");
			return "error/error";
		}

		// 购买次数记录
		model.addAttribute("buyCardNumberRecord", buyCardNumberRecord);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getViewMenu());

		return BUY_CARD_NUMBER_RECORD_JSP_PATH + "/view";
	}

	/**
	 * 加载积分记录的列表页
	 * @param model
	 * @param memberguid 会员资料唯一标示
	 * @return
	 */
	@RequestMapping(value = "/pointsrecord/list", method = RequestMethod.GET)
	public String loadPointsRecordList(Model model,
			@RequestParam(required = false) String memberguid)
	{
		model.addAttribute("memberguid", memberguid);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "积分记录");
		model.addAttribute("mapperid", "PointsRecordMapper.selectListByPage");

		return POINTS_RECORD_JSP_PATH + "/list";
	}

	/**
	 * 获取积分记录信息
	 * @param model
	 * @param guid 记录唯一标示
	 * @return
	 */
	@RequestMapping(value = "/pointsrecord/view", method = RequestMethod.GET)
	public String loadPointsRecordView(Model model,
			@RequestParam(required = false) String guid)
	{
		// 入参校验
		if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid))
		{
			model.addAttribute("errormessage", "参数校验不正确！");
			return "error/error";
		}

		PointsRecord pointsRecord = this.recordService.getPointsRecord(guid);
		if (pointsRecord == null)
		{
			model.addAttribute("errormessage", "积分记录不存在！");
			return "error/error";
		}

		// 积分记录
		model.addAttribute("pointsRecord", pointsRecord);

		// 加载列表菜单
		RecordHelper helper = new RecordHelper();
		model.addAttribute("menulist", helper.getViewMenu());

		return POINTS_RECORD_JSP_PATH + "/view";
	}

}
