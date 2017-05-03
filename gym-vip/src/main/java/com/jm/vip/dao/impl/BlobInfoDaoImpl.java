package com.jm.vip.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jm.vip.dao.BlobInfoDao;
import com.jm.vip.entity.BlobInfo;

@Repository("blobInfoDao")
public class BlobInfoDaoImpl extends BaseDaoImpl<BlobInfo>
		implements BlobInfoDao
{
	@Override
	public String getMapperId()
	{
		return "BlobInfoMapper";
	}

	@Override
	public int deleteByBlobGuid(String blobGuid)
	{
		Map<String, Object> param = Maps.newHashMap();
		param.put("blobguid", blobGuid);

		return this.session.delete(getMapperId() + ".deleteByMap", param);
	}

}
