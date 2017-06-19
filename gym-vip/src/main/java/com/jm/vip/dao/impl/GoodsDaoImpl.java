package com.jm.vip.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.jm.commons.page.PageSearch;
import com.jm.vip.dao.GoodsDao;
import com.jm.vip.entity.Goods;

/**
 * 商品信息DAO实现
 */
@Repository("goodsDao")
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements GoodsDao {

    @Override
    public String getMapperId() {
        return "GoodsMapper";
    }

    @Override
    public List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
            Integer pageSize) {
        RowBounds rowBounds = new RowBounds(pageNum, pageSize);
        return this.readSession.selectList(getMapperId() + ".selectListByPage",
                pageSearch, rowBounds);
    }

    @Override
    public int logicDeleteByGuid(Goods goods) {
        return this.session.update(getMapperId() + ".logicDeleteByGuid", goods);
    }

}
