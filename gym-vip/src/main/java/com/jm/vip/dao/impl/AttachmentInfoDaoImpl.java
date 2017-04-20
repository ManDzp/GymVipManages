package com.jm.vip.dao.impl;

import org.springframework.stereotype.Repository;

import com.jm.vip.dao.AttachmentInfoDao;
import com.jm.vip.entity.AttachmentInfo;

@Repository("attachmentinfoDao")
public class AttachmentInfoDaoImpl extends BaseDaoImpl<AttachmentInfo>
implements AttachmentInfoDao
{
	@Override
	public String getMapperId()
	{
		return "AttachmentInfoMapper";
	}

}
