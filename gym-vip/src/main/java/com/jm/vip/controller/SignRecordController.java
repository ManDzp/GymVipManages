package com.jm.vip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jm.base.controller.BaseController;
import com.jm.vip.menu.SignRecordHelper;

/**
 * 签到记录控制器
 */
@Controller
@RequestMapping("/signrecord")
public class SignRecordController extends BaseController
{
	private static final String JSP_PATH = "signrecord";

	/**
	 * 加载签到记录的列表页
	 * @param model
	 * @param memberguid 会员资料唯一标示
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String loadList(Model model,
			@RequestParam(required = false) String memberguid)
	{
		model.addAttribute("memberguid", memberguid);
		
		// 加载列表菜单
		SignRecordHelper helper = new SignRecordHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "签到记录");
		model.addAttribute("mapperid", "SignRecordMapper.selectListByPage");

		return JSP_PATH + "/list";
	}

}
