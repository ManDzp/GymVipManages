package com.jm.vip.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.jm.commons.base.tool.CurrentUser;
import com.jm.commons.log.LogProxy;
import com.jm.commons.utils.BaseUtils;
import com.jm.commons.utils.DateHelper;
import com.jm.vip.dao.TrainingCategoryDao;
import com.jm.vip.entity.TrainingCategory;

/**
 * 训练分类业务逻辑层
 */
@Service
public class TrainingCategoryService {

    /**
     * 声明log4j
     */
    private Logger log = Logger.getLogger(this.getClass());

    @Resource
    private TrainingCategoryDao trainingCategoryDao;

    /**
     * 获取训练分类
     * 
     * @param guid 私教唯一标识
     * @return
     */
    public TrainingCategory getTrainingCategoryInfoByGuid(String guid) {
        try {
            return this.trainingCategoryDao.getByGuid(guid);
        } catch (Exception e) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "获取训练分类异常", e.toString(), guid);
            return null;
        }
    }

    /**
     * 添加训练分类
     * 
     * @param tc 训练分类
     * @param currUser 当前用户
     * @return
     */
    @Transactional
    public String insertTrainingCategoryInfo(TrainingCategory tc,
            CurrentUser currentUser) {
        try {
            // 唯一标识
            String guid = BaseUtils.getPrimaryKey();

            tc.setId(guid);
            tc.setStatus("0");
            tc.setCreator(currentUser.getUserName());
            tc.setCreationId(currentUser.getUserGuid());
            tc.setCreationTime(DateHelper.getCurrentDate());

            this.trainingCategoryDao.insert(tc);

            // 记录操作日志
            LogProxy.WriteLogOperate(log, "添加训练分类", tc.getId(), tc.getName());
            return guid;
        } catch (Exception ex) {
            // 回滚
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            // 记录错误日志
            LogProxy.WriteLogError(log, "添加训练分类异常", ex.toString(), tc);
            return "";
        }
    }

    /**
     * 修改训练分类
     * 
     * @param tc 训练分类
     * @param currUser 当前用户
     * @return
     */
    @Transactional
    public boolean updateTrainingCategoryInfo(TrainingCategory tc,
            CurrentUser currentUser) {
        try {
            tc.setModifier(currentUser.getUserName());
            tc.setModificationId(currentUser.getUserGuid());
            tc.setModificationTime(DateHelper.getCurrentDate());

            this.trainingCategoryDao.updateByGuid(tc);

            // 记录操作日志
            LogProxy.WriteLogOperate(log, "修改训练分类", tc.getId(), tc.getName());
            return true;
        } catch (Exception ex) {
            // 回滚
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            // 记录错误日志
            LogProxy.WriteLogError(log, "修改训练分类异常", ex.toString(), tc);
            return false;
        }
    }

    /**
     * 逻辑删除训练分类
     * 
     * @param guidList
     * @param currentUser
     * @return
     */
    @Transactional
    public boolean deleteTrainingCategoryInfoList(List<String> guidList,
            CurrentUser currentUser) {
        try {
            for (String guid : guidList) {

                TrainingCategory tc = new TrainingCategory();
                tc.setId(guid);
                tc.setModifier(currentUser.getUserName());
                tc.setModificationId(currentUser.getUserGuid());
                tc.setModificationTime(DateHelper.getCurrentDate());

                this.trainingCategoryDao.logicDeleteByGuid(tc);
            }

            // 记录操作日志
            LogProxy.WriteLogOperate(log, "逻辑删除训练分类", guidList, "");

            return true;
        } catch (Exception ex) {
            // 回滚
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            // 记错误日志
            LogProxy.WriteLogError(log, "逻辑删除训练分类异常", ex.toString(), guidList);
            return false;
        }
    }

}
