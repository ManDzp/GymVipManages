package com.jm.vip.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jm.vip.dao.AttachmentInfoDao;
import com.jm.vip.entity.AttachmentInfo;

@Repository("attachmentInfoDao")
public class AttachmentInfoDaoImpl extends BaseDaoImpl<AttachmentInfo>
		implements AttachmentInfoDao
{
	@Override
	public String getMapperId()
	{
		return "AttachmentInfoMapper";
	}

	@Override
	public int deleteByBlobGuid(String blobGuid)
	{
		Map<String, Object> param = Maps.newHashMap();
		param.put("blobguid", blobGuid);

		return this.session.delete(getMapperId() + ".deleteByMap", param);
	}

	@Override
	public List<AttachmentInfo> listByGuid(String guid)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("guid", guid);

		return this.session.selectList(getMapperId() + ".listByGuid", param);
	}

}
