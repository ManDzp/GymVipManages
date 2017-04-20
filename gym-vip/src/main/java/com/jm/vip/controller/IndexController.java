package com.jm.vip.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.jm.base.controller.BaseController;

/**
 * 主页请求控制器
 */
@Controller
public class IndexController extends BaseController
{
	private static final String JSP_PATH = "admin";

	/**
	 * 加载主页
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView loadIndex()
	{
		Map<String, Object> model = Maps.newHashMap();

		// 中间位置，默认显示的页面
		model.put("centerIFrame", "welcome");

		// 跳转到系统管理界面首页
		return new ModelAndView(JSP_PATH + "/index", model);
	}

	/**
	 * 主页跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "index/", method = RequestMethod.GET)
	public String loadIndexRedirect(Model model)
	{
		return "redirect:/index";
	}

	/**
	 * welcome页跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String loadIndexWelcome(Model model)
	{
		return JSP_PATH + "/welcome";
	}

}
