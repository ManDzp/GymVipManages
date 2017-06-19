package com.jm.vip.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.jm.vip.entity.Prize;
import com.jm.vip.menu.PrizeHelper;
import com.jm.vip.service.PrizeService;

/**
 * 奖品信息控制器
 */
@Controller
@RequestMapping("/prize")
public class PrizeController extends BaseController {

    @Autowired
    private PrizeService prizeService;

    private static final String JSP_PATH = "prize";

    /**
     * 加载奖品信息的列表页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String loadList(Model model) {
        // 加载列表菜单
        PrizeHelper helper = new PrizeHelper();
        model.addAttribute("menulist", helper.getListMenu());

        model.addAttribute("listTitle", "奖品信息");
        model.addAttribute("mapperid", "PrizeMapper.selectListByPage");

        return JSP_PATH + "/list";
    }

    /**
     * 打开奖品信息的添加页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String loadAdd(Model model) {
        // 将菜单集合传给前台
        PrizeHelper helper = new PrizeHelper();
        model.addAttribute("menulist", helper.getAddMenu());

        return JSP_PATH + "/add";
    }

    /**
     * 打开奖品信息的查看页
     * 
     * @param model
     * @param guid 唯一标示
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String loadView(Model model,
            @RequestParam(required = false) String guid) {
        // 入参校验
        if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid)) {
            model.addAttribute("errormessage", "参数校验不正确！");
            return "error/error";
        }

        Prize prize = this.prizeService.getPrizeInfoByGuid(guid);
        if (prize == null) {
            model.addAttribute("errormessage", "奖品信息不存在！");
            return "error/error";
        }

        // 奖品信息
        model.addAttribute("prize", prize);

        // 将菜单集合传给前台
        PrizeHelper helper = new PrizeHelper();
        model.addAttribute("menulist", helper.getViewMenu(prize));

        return JSP_PATH + "/view";
    }

    /**
     * 打开奖品信息的修改页
     * 
     * @param model
     * @param guid 唯一标示
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String loadEdit(Model model,
            @RequestParam(required = false) String guid) {
        // 入参校验
        if (StringUtils.isEmpty(guid) || !RegexHelper.isPrimaryKey(guid)) {
            model.addAttribute("errormessage", "参数校验不正确！");
            return "error/error";
        }

        Prize prize = this.prizeService.getPrizeInfoByGuid(guid);
        if (prize == null) {
            model.addAttribute("errormessage", "奖品信息不存在！");
            return "error/error";
        }

        // 奖品信息
        model.addAttribute("prize", prize);

        // 将菜单集合传给前台
        PrizeHelper helper = new PrizeHelper();
        model.addAttribute("menulist", helper.getEditMenu(guid));

        return JSP_PATH + "/edit";
    }

    /**
     * 添加奖品信息
     * 
     * @param prize 奖品信息
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO insert(Prize prize) {
        ResultDTO result = new ResultDTO();

        try {
            String guid = this.prizeService.insertPrizeInfo(prize,
                    super.getContextUser());

            result.setSuccess(StringUtils.isNotEmpty(guid));
            result.setMessage(guid);
        } catch (Exception ex) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "添加奖品信息失败", ex.toString(), prize);
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 修改奖品信息
     * 
     * @param prize 奖品信息
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO update(Prize prize) {
        ResultDTO result = new ResultDTO();

        try {
            boolean isUpdate = this.prizeService.updatePrizeInfo(prize,
                    super.getContextUser());

            result.setSuccess(isUpdate);
        } catch (Exception ex) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "修改奖品信息失败", ex.toString(), prize);
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 删除奖品信息
     * 
     * @param list JSON列表
     * @return
     */
    @RequestMapping(value = "/deletelist", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO deleteList(@RequestBody List<JSONObject> list) {
        List<String> guidList = JSONUtils.getListByField(list, "prizeId");
        ResultDTO result = new ResultDTO();

        try {
            if (guidList.size() > 0) {
                boolean isDelete = this.prizeService.deletePrizeInfoList(
                        guidList, getContextUser());
                result.setSuccess(isDelete);
            }
        } catch (Exception ex) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "删除奖品信息失败", ex.toString(), list);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 删除奖品信息
     * 
     * @param guid 奖品信息唯一标识
     * @return
     */
    @RequestMapping(value = "/deleteOne", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO deleteOne(@RequestParam String guid) {
        ResultDTO result = new ResultDTO();

        try {
            boolean isDelete = this.prizeService.deletePrizeInfoList(
                    Arrays.asList(guid), getContextUser());
            result.setSuccess(isDelete);
        } catch (Exception ex) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "删除奖品信息失败", ex.toString(), guid);
            result.setSuccess(false);
        }

        return result;
    }

}
