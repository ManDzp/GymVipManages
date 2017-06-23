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
import com.jm.vip.dao.PrizeDao;
import com.jm.vip.entity.Prize;

/**
 * 奖品信息业务逻辑层
 */
@Service
public class PrizeService {

    /**
     * 声明log4j
     */
    private Logger log = Logger.getLogger(this.getClass());

    @Resource
    private PrizeDao prizeDao;

    /**
     * 获取奖品信息
     * 
     * @param guid 奖品唯一标识
     * @return
     */
    public Prize getPrizeInfoByGuid(String guid) {
        try {
            return this.prizeDao.getByGuid(guid);
        } catch (Exception e) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "获取奖品信息异常", e.toString(), guid);
            return null;
        }
    }

    /**
     * 添加奖品信息
     * 
     * @param prize 奖品信息
     * @param currUser 当前用户
     * @return
     */
    @Transactional
    public String insertPrizeInfo(Prize prize, CurrentUser currentUser) {
        try {
            // 唯一标识
            String guid = BaseUtils.getPrimaryKey();

            prize.setPrizeId(guid);
            prize.setPrizeStatus("0");
            prize.setCreator(currentUser.getUserName());
            prize.setCreationId(currentUser.getUserGuid());
            prize.setCreationTime(DateHelper.getCurrentStrTime());

            this.prizeDao.insert(prize);

            // 记录操作日志
            LogProxy.WriteLogOperate(log, "添加奖品信息", prize.getPrizeId(),
                    prize.getPrizeName());
            return guid;
        } catch (Exception ex) {
            // 回滚
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            // 记录错误日志
            LogProxy.WriteLogError(log, "添加奖品信息异常", ex.toString(), prize);
            return "";
        }
    }

    /**
     * 修改奖品信息
     * 
     * @param prize 奖品信息
     * @param currUser 当前用户
     * @return
     */
    @Transactional
    public boolean updatePrizeInfo(Prize prize, CurrentUser currentUser) {
        try {
            prize.setModifier(currentUser.getUserName());
            prize.setModificationId(currentUser.getUserGuid());
            prize.setModificationTime(DateHelper.getCurrentStrTime());

            this.prizeDao.updateByGuid(prize);

            // 记录操作日志
            LogProxy.WriteLogOperate(log, "修改奖品信息", prize.getPrizeId(),
                    prize.getPrizeName());
            return true;
        } catch (Exception ex) {
            // 回滚
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            // 记录错误日志
            LogProxy.WriteLogError(log, "修改奖品信息异常", ex.toString(), prize);
            return false;
        }
    }

    /**
     * 逻辑删除奖品信息
     * 
     * @param guidList
     * @param currentUser
     * @return
     */
    @Transactional
    public boolean deletePrizeInfoList(List<String> guidList,
            CurrentUser currentUser) {
        try {
            for (String guid : guidList) {

                Prize prize = new Prize();
                prize.setPrizeId(guid);
                prize.setModifier(currentUser.getUserName());
                prize.setModificationId(currentUser.getUserGuid());
                prize.setModificationTime(DateHelper.getCurrentStrTime());

                this.prizeDao.logicDeleteByGuid(prize);
            }

            // 记录操作日志
            LogProxy.WriteLogOperate(log, "逻辑删除奖品信息", guidList, "");

            return true;
        } catch (Exception ex) {
            // 回滚
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            // 记错误日志
            LogProxy.WriteLogError(log, "逻辑删除奖品信息异常", ex.toString(), guidList);
            return false;
        }
    }

}
