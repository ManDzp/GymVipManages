package com.jm.vip.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import com.jm.vip.dao.BaseDao;

/**
 * 基础dao层
 * @param <T>
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T>
{
	@Resource
	protected SqlSession session;

	@Resource
	protected SqlSession readSession;

	/**
	 * 获取mapper文件标识
	 * @return
	 */
	public abstract String getMapperId();

	/**
	 * 插入一个实体
	 * @param entity 实体对象
	 * @return
	 */
	@Override
	public int insert(T entity)
	{
		return this.session.insert(getMapperId() + ".insert", entity);
	}

	/**
	 * 根据guid删除信息
	 * @param guid 信息唯一标识
	 * @return 影响的条目数
	 */
	@Override
	public int deleteByGuid(String guid)
	{
		return this.session.delete(getMapperId() + ".deleteByGuid", guid);
	}

	/** 
	 * 修改一个实体对象（UPDATE一条记录） 
	 * @param entity 实体对象 
	 * @return 修改的对象个数，正常情况=1 
	 */
	@Override
	public int updateByGuid(T entity)
	{
		return this.session.update(getMapperId() + ".updateByGuid", entity);
	}

	/**
	 * 根据guid获取一个实体对象
	 * @param guid 信息唯一标识
	 * @return
	 */
	@Override
	public T getByGuid(String guid)
	{
		return this.readSession.selectOne(getMapperId() + ".selectByGuid",
				guid);
	}

}
