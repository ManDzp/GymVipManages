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
import com.jm.vip.entity.PersonalTraining;
import com.jm.vip.menu.PersonalTrainingHelper;
import com.jm.vip.service.PersonalTrainingService;

/**
 * 私教信息控制器
 */
@Controller
@RequestMapping("/personaltraining")
public class PersonalTrainingController extends BaseController {

    @Autowired
    private PersonalTrainingService personalTrainingService;

    private static final String JSP_PATH = "personaltraining";

    /**
     * 加载私教信息的列表页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String loadList(Model model) {
        // 加载列表菜单
        PersonalTrainingHelper helper = new PersonalTrainingHelper();
        model.addAttribute("menulist", helper.getListMenu());

        model.addAttribute("listTitle", "私教信息");
        model.addAttribute("mapperid",
                "PersonalTrainingMapper.selectListByPage");

        return JSP_PATH + "/list";
    }

    /**
     * 打开私教信息的添加页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String loadAdd(Model model) {
        // 将菜单集合传给前台
        PersonalTrainingHelper helper = new PersonalTrainingHelper();
        model.addAttribute("menulist", helper.getAddMenu());

        return JSP_PATH + "/add";
    }

    /**
     * 打开私教信息的查看页
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

        PersonalTraining personalTraining = this.personalTrainingService
                .getPersonalTrainingInfoByGuid(guid);
        if (personalTraining == null) {
            model.addAttribute("errormessage", "私教信息不存在！");
            return "error/error";
        }

        // 私教信息
        model.addAttribute("personalTraining", personalTraining);

        // 将菜单集合传给前台
        PersonalTrainingHelper helper = new PersonalTrainingHelper();
        model.addAttribute("menulist", helper.getViewMenu(personalTraining));

        return JSP_PATH + "/view";
    }

    /**
     * 打开私教信息的修改页
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

        PersonalTraining personalTraining = this.personalTrainingService
                .getPersonalTrainingInfoByGuid(guid);
        if (personalTraining == null) {
            model.addAttribute("errormessage", "私教信息不存在！");
            return "error/error";
        }

        // 私教信息
        model.addAttribute("personalTraining", personalTraining);

        // 将菜单集合传给前台
        PersonalTrainingHelper helper = new PersonalTrainingHelper();
        model.addAttribute("menulist", helper.getEditMenu(guid));

        return JSP_PATH + "/edit";
    }

    /**
     * 添加私教信息
     * 
     * @param personalTraining 私教信息
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO insert(PersonalTraining personalTraining) {
        ResultDTO result = new ResultDTO();

        try {
            String guid = this.personalTrainingService
                    .insertPersonalTrainingInfo(personalTraining,
                            super.getContextUser());

            result.setSuccess(StringUtils.isNotEmpty(guid));
            result.setMessage(guid);
        } catch (Exception ex) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "添加私教信息失败", ex.toString(),
                    personalTraining);
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 修改私教信息
     * 
     * @param personalTraining 私教信息
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO update(PersonalTraining personalTraining) {
        ResultDTO result = new ResultDTO();

        try {
            boolean isUpdate = this.personalTrainingService
                    .updatePersonalTrainingInfo(personalTraining,
                            super.getContextUser());

            result.setSuccess(isUpdate);
        } catch (Exception ex) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "修改私教信息失败", ex.toString(),
                    personalTraining);
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 删除私教信息
     * 
     * @param list JSON列表
     * @return
     */
    @RequestMapping(value = "/deletelist", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO deleteList(@RequestBody List<JSONObject> list) {
        List<String> guidList = JSONUtils.getListByField(list,
                "personalTrainingId");
        ResultDTO result = new ResultDTO();

        try {
            if (guidList.size() > 0) {
                boolean isDelete = this.personalTrainingService
                        .deletePersonalTrainingInfoList(guidList,
                                getContextUser());
                result.setSuccess(isDelete);
            }
        } catch (Exception ex) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "删除私教信息失败", ex.toString(), list);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 删除私教信息
     * 
     * @param guid 私教信息唯一标识
     * @return
     */
    @RequestMapping(value = "/deleteOne", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO deleteOne(@RequestParam String guid) {
        ResultDTO result = new ResultDTO();

        try {
            boolean isDelete = this.personalTrainingService
                    .deletePersonalTrainingInfoList(Arrays.asList(guid),
                            getContextUser());
            result.setSuccess(isDelete);
        } catch (Exception ex) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "删除私教信息失败", ex.toString(), guid);
            result.setSuccess(false);
        }

        return result;
    }

}
