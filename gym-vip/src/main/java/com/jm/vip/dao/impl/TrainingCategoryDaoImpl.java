package com.jm.vip.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.jm.commons.page.PageSearch;
import com.jm.vip.dao.TrainingCategoryDao;
import com.jm.vip.entity.TrainingCategory;

/**
 * 训练分类DAO实现
 */
@Repository("trainingCategoryDao")
public class TrainingCategoryDaoImpl extends BaseDaoImpl<TrainingCategory>
        implements TrainingCategoryDao {

    @Override
    public String getMapperId() {
        return "TrainingCategoryMapper";
    }

    @Override
    public List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
            Integer pageSize) {
        RowBounds rowBounds = new RowBounds(pageNum, pageSize);
        return this.readSession.selectList(getMapperId() + ".selectListByPage",
                pageSearch, rowBounds);
    }

    @Override
    public int logicDeleteByGuid(TrainingCategory personalTraining) {
        return this.session.update(getMapperId() + ".logicDeleteByGuid",
                personalTraining);
    }

}
