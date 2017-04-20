package com.jm.vip.dao.impl;

import org.springframework.stereotype.Repository;

import com.jm.vip.dao.BlobInfoDao;
import com.jm.vip.entity.BlobInfo;

@Repository("blobinfoDao")
public class BlobInfoDaoImpl extends BaseDaoImpl<BlobInfo>
implements BlobInfoDao
{
	@Override
	public String getMapperId()
	{
		return "BlobInfoMapper";
	}

}
