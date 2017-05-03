package com.jm.vip.dao;

import com.jm.vip.entity.BlobInfo;

public interface BlobInfoDao extends BaseDao<BlobInfo>
{
	/**
	 * @Description: 删除附件信息
	 * @param blobGuid 附件唯一标识
	 * @return 返回删除影响行数
	 */
	public int deleteByBlobGuid(String blobGuid);
}
