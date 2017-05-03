package com.jm.vip.dao;

import com.jm.vip.entity.AttachmentInfo;

public interface AttachmentInfoDao extends BaseDao<AttachmentInfo>
{
	/**
	 * @Description: 删除附件信息
	 * @param blobGuid 附件唯一标识
	 * @return 返回删除影响行数
	 */
	public int deleteByBlobGuid(String blobGuid);
}
