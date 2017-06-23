package com.jm.vip.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.jm.commons.page.PageSearch;
import com.jm.vip.dao.PersonalTrainingDao;
import com.jm.vip.entity.PersonalTraining;

/**
 * 私教信息DAO实现
 */
@Repository("personalTrainingDao")
public class PersonalTrainingDaoImpl extends BaseDaoImpl<PersonalTraining>
        implements PersonalTrainingDao {

    @Override
    public String getMapperId() {
        return "PersonalTrainingMapper";
    }

    @Override
    public List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
            Integer pageSize) {
        RowBounds rowBounds = new RowBounds(pageNum, pageSize);
        return this.readSession.selectList(getMapperId() + ".selectListByPage",
                pageSearch, rowBounds);
    }

    @Override
    public int logicDeleteByGuid(PersonalTraining personalTraining) {
        return this.session.update(getMapperId() + ".logicDeleteByGuid",
                personalTraining);
    }

}
