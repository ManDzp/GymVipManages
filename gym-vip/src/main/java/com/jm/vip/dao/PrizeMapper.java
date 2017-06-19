package com.jm.vip.dao;

import com.jm.vip.entity.Prize;

public interface PrizeMapper {
    int deleteByPrimaryKey(Long prizeId);

    int insert(Prize record);

    int insertSelective(Prize record);

    Prize selectByPrimaryKey(Long prizeId);

    int updateByPrimaryKeySelective(Prize record);

    int updateByPrimaryKey(Prize record);
}