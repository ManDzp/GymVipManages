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
import com.jm.vip.dao.PersonalTrainingDao;
import com.jm.vip.entity.PersonalTraining;

/**
 * 私教信息业务逻辑层
 */
@Service
public class PersonalTrainingService {

    /**
     * 声明log4j
     */
    private Logger log = Logger.getLogger(this.getClass());

    @Resource
    private PersonalTrainingDao personalTrainingDao;

    /**
     * 获取私教信息
     * 
     * @param guid 私教唯一标识
     * @return
     */
    public PersonalTraining getPersonalTrainingInfoByGuid(String guid) {
        try {
            return this.personalTrainingDao.getByGuid(guid);
        } catch (Exception e) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "获取私教信息异常", e.toString(), guid);
            return null;
        }
    }

    /**
     * 添加私教信息
     * 
     * @param personalTraining 私教信息
     * @param currUser 当前用户
     * @return
     */
    @Transactional
    public String insertPersonalTrainingInfo(PersonalTraining personalTraining,
            CurrentUser currentUser) {
        try {
            // 唯一标识
            String guid = BaseUtils.getPrimaryKey();

            personalTraining.setPersonalTrainingId(guid);
            personalTraining.setPersonalTrainingStatus("0");
            personalTraining.setCreator(currentUser.getUserName());
            personalTraining.setCreationId(currentUser.getUserGuid());
            personalTraining.setCreationTime(DateHelper.getCurrentStrTime());

            this.personalTrainingDao.insert(personalTraining);

            // 记录操作日志
            LogProxy.WriteLogOperate(log, "添加私教信息",
                    personalTraining.getPersonalTrainingId(),
                    personalTraining.getPersonalTrainingName());
            return guid;
        } catch (Exception ex) {
            // 回滚
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            // 记录错误日志
            LogProxy.WriteLogError(log, "添加私教信息异常", ex.toString(),
                    personalTraining);
            return "";
        }
    }

    /**
     * 修改私教信息
     * 
     * @param personalTraining 私教信息
     * @param currUser 当前用户
     * @return
     */
    @Transactional
    public boolean updatePersonalTrainingInfo(
            PersonalTraining personalTraining, CurrentUser currentUser) {
        try {
            personalTraining.setModifier(currentUser.getUserName());
            personalTraining.setModificationId(currentUser.getUserGuid());
            personalTraining
                    .setModificationTime(DateHelper.getCurrentStrTime());

            this.personalTrainingDao.updateByGuid(personalTraining);

            // 记录操作日志
            LogProxy.WriteLogOperate(log, "修改私教信息",
                    personalTraining.getPersonalTrainingId(),
                    personalTraining.getPersonalTrainingName());
            return true;
        } catch (Exception ex) {
            // 回滚
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            // 记录错误日志
            LogProxy.WriteLogError(log, "修改私教信息异常", ex.toString(),
                    personalTraining);
            return false;
        }
    }

    /**
     * 逻辑删除私教信息
     * 
     * @param guidList
     * @param currentUser
     * @return
     */
    @Transactional
    public boolean deletePersonalTrainingInfoList(List<String> guidList,
            CurrentUser currentUser) {
        try {
            for (String guid : guidList) {

                PersonalTraining personalTraining = new PersonalTraining();
                personalTraining.setPersonalTrainingId(guid);
                personalTraining.setModifier(currentUser.getUserName());
                personalTraining.setModificationId(currentUser.getUserGuid());
                personalTraining.setModificationTime(DateHelper
                        .getCurrentStrTime());

                this.personalTrainingDao.logicDeleteByGuid(personalTraining);
            }

            // 记录操作日志
            LogProxy.WriteLogOperate(log, "逻辑删除私教信息", guidList, "");

            return true;
        } catch (Exception ex) {
            // 回滚
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            // 记错误日志
            LogProxy.WriteLogError(log, "逻辑删除私教信息异常", ex.toString(), guidList);
            return false;
        }
    }

}
