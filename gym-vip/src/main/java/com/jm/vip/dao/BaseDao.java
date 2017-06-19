package com.jm.vip.dao;

/**
 * 泛型接口
 */
public interface BaseDao<T> {

    /**
     * 插入一个实体
     * 
     * @param entity 实体对象
     * @return
     */
    int insert(T entity);

    /**
     * 根据guid删除信息
     * 
     * @param guid 信息唯一标识
     * @return 影响的条目数
     */
    int deleteByGuid(String guid);

    /**
     * 修改一个实体对象（UPDATE一条记录）
     * 
     * @param entity 实体对象
     * @return 修改的对象个数，正常情况=1
     */
    int updateByGuid(T entity);

    /**
     * 根据guid获取一个实体对象
     * 
     * @param guid 信息唯一标识
     * @return
     */
    T getByGuid(String guid);

}
