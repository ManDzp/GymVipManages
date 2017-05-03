package com.jm.vip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jm.vip.menu.ChargeRecordHelper;
import com.jm.vip.menu.SignRecordHelper;

/**
 * 查看记录控制器
 */
@Controller
@RequestMapping("/record")
public class RecordController
{
	private static final String CHARGE_RECORD_JSP_PATH = "chargerecord";

	private static final String BUY_CARD_RECORD_JSP_PATH = "buycardrecord";

	private static final String ACTIVE_CARD_RECORD_JSP_PATH = "activecardrecord";

	private static final String CONTINUE_CARD_RECORD_JSP_PATH = "continuecardrecord";

	private static final String SIGN_RECORD_JSP_PATH = "signrecord";

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
		ChargeRecordHelper helper = new ChargeRecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "充值记录");
		model.addAttribute("mapperid", "ChargeRecordMapper.selectListByPage");

		return CHARGE_RECORD_JSP_PATH + "/list";
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
		ChargeRecordHelper helper = new ChargeRecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "买卡记录");
		model.addAttribute("mapperid", "BuyCardRecordMapper.selectListByPage");

		return BUY_CARD_RECORD_JSP_PATH + "/list";
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
		ChargeRecordHelper helper = new ChargeRecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "开卡记录");
		model.addAttribute("mapperid",
				"ActiveCardRecordMapper.selectListByPage");

		return ACTIVE_CARD_RECORD_JSP_PATH + "/list";
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
		ChargeRecordHelper helper = new ChargeRecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "续卡记录");
		model.addAttribute("mapperid",
				"ContinueCardRecordMapper.selectListByPage");

		return CONTINUE_CARD_RECORD_JSP_PATH + "/list";
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
		SignRecordHelper helper = new SignRecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "签到记录");
		model.addAttribute("mapperid", "SignRecordMapper.selectListByPage");

		return SIGN_RECORD_JSP_PATH + "/list";
	}

}
