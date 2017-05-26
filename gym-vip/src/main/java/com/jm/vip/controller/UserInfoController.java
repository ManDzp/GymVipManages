package com.jm.vip.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

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
import com.jm.vip.entity.AdminInfo;
import com.jm.vip.menu.AdminInfoHelper;
import com.jm.vip.service.AdminInfoService;

/**
 * 简易论坛类别控制器
 */
@Controller
@RequestMapping("/userinfo")
public class UserInfoController extends BaseController
{
	@Resource(name = "adminInfoService")
	private AdminInfoService adminInfoService;

	private static final String JSP_PATH = "userinfo";

	/**
	 * 加载会员账号注册的列表页
	 * 通过list格式访问
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register_list", method = RequestMethod.GET)
	public String loadList(Model model)
	{
		// 加载列表菜单
		AdminInfoHelper helper = new AdminInfoHelper();
		model.addAttribute("menulist", helper.getListMenu());

		model.addAttribute("listTitle", "论坛管理");
		model.addAttribute("mapperid", "UserInfoMapper.selectUserInfoByPage");

		return JSP_PATH + "/list";
	}

	/**
	 * 打开添加页
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String loadAdd(Model model)
	{
		// 将菜单集合传给前台
		AdminInfoHelper helper = new AdminInfoHelper();
		model.addAttribute("menulist", helper.getAddMenu());

		return JSP_PATH + "/add";
	}

	/**
	 * 打开注册账号信息查看页
	 * @param model
	 * @param userguid 唯一标示
	 * @return
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String loadView(Model model,
			@RequestParam(required = false) String userguid)
	{
		// 入参校验
		if (StringUtils.isEmpty(userguid) || !RegexHelper.isUserGuid(userguid))
		{
			model.addAttribute("errormessage", "参数校验不正确！");
			return "error/error";
		}

		AdminInfo userInfo = this.adminInfoService
				.getUserInfoViewByGuid(userguid);

		if (userInfo == null)
		{
			model.addAttribute("errormessage", "该注册会员信息不存在！");
			return "error/error";
		}

		// 注册会员信息
		model.addAttribute("userinfo", userInfo);

		// 将菜单集合传给前台
		AdminInfoHelper helper = new AdminInfoHelper();
		model.addAttribute("menulist", helper.getViewMenu(userguid));

		return JSP_PATH + "/view";
	}

	/**
	 * 根据登陆账号判断注册账号是否存在
	 * @param userName 登陆账号
	 * @param userGuid 用户唯一标识
	 * @return
	 */
	@RequestMapping(value = "/isSameName", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO isSameName(@RequestParam String userName,
			@RequestParam(required = false) String userGuid)
	{
		ResultDTO result = new ResultDTO();

		try
		{
			boolean isSame = this.adminInfoService.isSameName(userName,
					userGuid);
			result.setSuccess(isSame);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "根据登陆账号判断注册账号是否存在失败", ex.toString(),
					userName, userGuid);
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 添加会员账号
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "text/html")
	@ResponseBody
	public ResultDTO insert(AdminInfo userInfo)
	{
		ResultDTO result = new ResultDTO();

		try
		{
			String uuid = this.adminInfoService.insertUserInfo(userInfo);

			result.setSuccess(StringUtils.isNotEmpty(uuid));
			result.setMessage(uuid);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "添加会员账号失败", ex.toString(), userInfo);
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 打开修改页
	 * @param model
	 * @param userguid
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String loadEdit(Model model, @RequestParam String userguid)
	{
		// 入参校验
		if (StringUtils.isEmpty(userguid) || !RegexHelper.isUserGuid(userguid))
		{
			model.addAttribute("errormessage", "参数校验不正确！");
			return "error/error";
		}

		AdminInfo userInfo = this.adminInfoService
				.getUserInfoViewByGuid(userguid);
		if (userInfo == null)
		{
			model.addAttribute("errormessage", "该注册账号信息不存在！");
			return "error/error";
		}

		// 页面板块信息
		model.addAttribute("userinfo", userInfo);

		// 将菜单集合传给前台
		AdminInfoHelper helper = new AdminInfoHelper();
		model.addAttribute("menulist", helper.getEditMenu(userguid));

		return JSP_PATH + "/edit";
	}

	/**
	 * 修改注册用户
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO update(AdminInfo userInfo)
	{
		ResultDTO result = new ResultDTO();

		try
		{
			boolean isUpdate = this.adminInfoService.updateUserInfo(userInfo);

			result.setSuccess(isUpdate);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "修改注册用户信息失败", ex.toString(), userInfo);
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 列表页的删除
	 * @param list JSON列表
	 * @return
	 */
	@RequestMapping(value = "/deletelist", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO deleteList(@RequestBody List<JSONObject> list)
	{
		List<String> userguidList = JSONUtils.getListByField(list, "userguid");
		ResultDTO result = new ResultDTO();

		try
		{
			if (userguidList.size() > 0)
			{
				boolean isDelete = this.adminInfoService
						.deleteUserInfoList(userguidList, getContextUser());
				result.setSuccess(isDelete);
			}
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "删除列表数据失败", ex.toString(), list);
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 查看页删除
	 * @param userguid 唯一标识
	 * @return
	 */
	@RequestMapping(value = "/deleteOne", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO deleteOne(@RequestParam String userguid)
	{
		ResultDTO result = new ResultDTO();

		try
		{
			boolean isDelete = this.adminInfoService.deleteUserInfoList(
					Arrays.asList(userguid), getContextUser());
			result.setSuccess(isDelete);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "删除该账号失败", ex.toString(), userguid);
			result.setSuccess(false);
		}

		return result;
	}

}
