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
import com.jm.vip.entity.TrainingCategory;
import com.jm.vip.menu.TrainingCategoryHelper;
import com.jm.vip.service.TrainingCategoryService;

/**
 * 训练分类控制器
 */
@Controller
@RequestMapping("/trainingcategory")
public class TrainingCategoryController extends BaseController {

    @Autowired
    private TrainingCategoryService trainingCategoryService;

    private static final String JSP_PATH = "training/trainingcategory";

    /**
     * 加载训练分类的列表页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String loadList(Model model) {
        // 加载列表菜单
        TrainingCategoryHelper helper = new TrainingCategoryHelper();
        model.addAttribute("menulist", helper.getListMenu());

        model.addAttribute("listTitle", "训练分类");
        model.addAttribute("mapperid",
                "TrainingCategoryMapper.selectListByPage");

        return JSP_PATH + "/list";
    }

    /**
     * 打开训练分类的添加页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String loadAdd(Model model) {
        // 将菜单集合传给前台
        TrainingCategoryHelper helper = new TrainingCategoryHelper();
        model.addAttribute("menulist", helper.getAddMenu());

        return JSP_PATH + "/add";
    }

    /**
     * 打开训练分类的查看页
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

        TrainingCategory trainingCategory = this.trainingCategoryService
                .getTrainingCategoryInfoByGuid(guid);
        if (trainingCategory == null) {
            model.addAttribute("errormessage", "训练分类不存在！");
            return "error/error";
        }

        // 训练分类
        model.addAttribute("trainingCategory", trainingCategory);

        // 将菜单集合传给前台
        TrainingCategoryHelper helper = new TrainingCategoryHelper();
        model.addAttribute("menulist", helper.getViewMenu(trainingCategory));

        return JSP_PATH + "/view";
    }

    /**
     * 打开训练分类的修改页
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

        TrainingCategory trainingCategory = this.trainingCategoryService
                .getTrainingCategoryInfoByGuid(guid);
        if (trainingCategory == null) {
            model.addAttribute("errormessage", "训练分类不存在！");
            return "error/error";
        }

        // 训练分类
        model.addAttribute("trainingCategory", trainingCategory);

        // 将菜单集合传给前台
        TrainingCategoryHelper helper = new TrainingCategoryHelper();
        model.addAttribute("menulist", helper.getEditMenu(guid));

        return JSP_PATH + "/edit";
    }

    /**
     * 添加训练分类
     * 
     * @param trainingCategory 训练分类
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO insert(TrainingCategory trainingCategory) {
        ResultDTO result = new ResultDTO();

        try {
            String guid = this.trainingCategoryService
                    .insertTrainingCategoryInfo(trainingCategory,
                            super.getContextUser());

            result.setSuccess(StringUtils.isNotEmpty(guid));
            result.setMessage(guid);
        } catch (Exception ex) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "添加训练分类失败", ex.toString(),
                    trainingCategory);
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 修改训练分类
     * 
     * @param trainingCategory 训练分类
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO update(TrainingCategory trainingCategory) {
        ResultDTO result = new ResultDTO();

        try {
            boolean isUpdate = this.trainingCategoryService
                    .updateTrainingCategoryInfo(trainingCategory,
                            super.getContextUser());

            result.setSuccess(isUpdate);
        } catch (Exception ex) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "修改训练分类失败", ex.toString(),
                    trainingCategory);
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 删除训练分类
     * 
     * @param list JSON列表
     * @return
     */
    @RequestMapping(value = "/deletelist", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO deleteList(@RequestBody List<JSONObject> list) {
        List<String> guidList = JSONUtils.getListByField(list,
                "trainingCategoryId");
        ResultDTO result = new ResultDTO();

        try {
            if (guidList.size() > 0) {
                boolean isDelete = this.trainingCategoryService
                        .deleteTrainingCategoryInfoList(guidList,
                                getContextUser());
                result.setSuccess(isDelete);
            }
        } catch (Exception ex) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "删除训练分类失败", ex.toString(), list);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 删除训练分类
     * 
     * @param guid 训练分类唯一标识
     * @return
     */
    @RequestMapping(value = "/deleteOne", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO deleteOne(@RequestParam String guid) {
        ResultDTO result = new ResultDTO();

        try {
            boolean isDelete = this.trainingCategoryService
                    .deleteTrainingCategoryInfoList(Arrays.asList(guid),
                            getContextUser());
            result.setSuccess(isDelete);
        } catch (Exception ex) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "删除训练分类失败", ex.toString(), guid);
            result.setSuccess(false);
        }

        return result;
    }

}
