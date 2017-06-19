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
import com.jm.vip.dao.GoodsDao;
import com.jm.vip.entity.Goods;

/**
 * 商品信息业务逻辑层
 */
@Service
public class GoodsService {

    /**
     * 声明log4j
     */
    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name = "goodsDao")
    private GoodsDao goodsDao;

    /**
     * 获取商品信息
     * 
     * @param guid 商品唯一标识
     * @return
     */
    public Goods getGoodsInfoByGuid(String guid) {
        try {
            return this.goodsDao.getByGuid(guid);
        } catch (Exception e) {
            // 记录错误日志
            LogProxy.WriteLogError(log, "获取商品信息异常", e.toString(), guid);
            return null;
        }
    }

    /**
     * 添加商品信息
     * 
     * @param goods 商品信息
     * @param currUser 当前用户
     * @return
     */
    @Transactional
    public String insertGoodsInfo(Goods goods, CurrentUser currentUser) {
        try {
            // 唯一标识
            String guid = BaseUtils.getPrimaryKey();

            goods.setGoodsId(guid);
            goods.setGoodsStatus("0");
            goods.setCreator(currentUser.getUserName());
            goods.setCreationId(currentUser.getUserGuid());
            goods.setCreationTime(DateHelper.getCurrentStrTime());

            this.goodsDao.insert(goods);

            // 记录操作日志
            LogProxy.WriteLogOperate(log, "添加商品信息", goods.getGoodsId(),
                    goods.getGoodsName());
            return guid;
        } catch (Exception ex) {
            // 回滚
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            // 记录错误日志
            LogProxy.WriteLogError(log, "添加商品信息异常", ex.toString(), goods);
            return "";
        }
    }

    /**
     * 修改商品信息
     * 
     * @param goods 商品信息
     * @param currUser 当前用户
     * @return
     */
    @Transactional
    public boolean updateGoodsInfo(Goods goods, CurrentUser currentUser) {
        try {
            goods.setModifier(currentUser.getUserName());
            goods.setModificationId(currentUser.getUserGuid());
            goods.setModificationTime(DateHelper.getCurrentStrTime());

            this.goodsDao.updateByGuid(goods);

            // 记录操作日志
            LogProxy.WriteLogOperate(log, "修改商品信息", goods.getGoodsId(),
                    goods.getGoodsName());
            return true;
        } catch (Exception ex) {
            // 回滚
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            // 记录错误日志
            LogProxy.WriteLogError(log, "修改商品信息异常", ex.toString(), goods);
            return false;
        }
    }

    /**
     * 逻辑删除商品信息
     * 
     * @param guidList
     * @param currentUser
     * @return
     */
    @Transactional
    public boolean deleteGoodsInfoList(List<String> guidList,
            CurrentUser currentUser) {
        try {
            for (String guid : guidList) {

                Goods goods = new Goods();
                goods.setGoodsId(guid);
                goods.setModifier(currentUser.getUserName());
                goods.setModificationId(currentUser.getUserGuid());
                goods.setModificationTime(DateHelper.getCurrentStrTime());

                this.goodsDao.logicDeleteByGuid(goods);
            }

            // 记录操作日志
            LogProxy.WriteLogOperate(log, "逻辑删除商品信息", guidList, "");

            return true;
        } catch (Exception ex) {
            // 回滚
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            // 记错误日志
            LogProxy.WriteLogError(log, "逻辑删除商品信息异常", ex.toString(), guidList);
            return false;
        }
    }

}
