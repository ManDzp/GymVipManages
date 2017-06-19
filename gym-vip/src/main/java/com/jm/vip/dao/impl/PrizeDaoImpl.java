package com.jm.vip.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.jm.commons.page.PageSearch;
import com.jm.vip.dao.PrizeDao;
import com.jm.vip.entity.Prize;

/**
 * 奖品信息DAO实现
 */
@Repository("prizeDao")
public class PrizeDaoImpl extends BaseDaoImpl<Prize> implements PrizeDao {

    @Override
    public String getMapperId() {
        return "PrizeMapper";
    }

    @Override
    public List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
            Integer pageSize) {
        RowBounds rowBounds = new RowBounds(pageNum, pageSize);
        return this.readSession.selectList(getMapperId() + ".selectListByPage",
                pageSearch, rowBounds);
    }

    @Override
    public int logicDeleteByGuid(Prize prize) {
        return this.session.update(getMapperId() + ".logicDeleteByGuid", prize);
    }

}
