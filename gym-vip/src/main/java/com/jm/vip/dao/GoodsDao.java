package com.jm.vip.dao;

import java.util.List;

import com.jm.commons.page.PageSearch;
import com.jm.vip.entity.Goods;

/**
 * 商品信息DAO接口声明
 */
public interface GoodsDao extends BaseDao<Goods> {

    /**
     * 获取分页数据
     * 
     * @param pageSearch
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
            Integer pageSize);

    /**
     * 逻辑删除信息
     * 
     * @param goods 商品信息
     * @return 影响的条目数
     */
    int logicDeleteByGuid(Goods goods);

}