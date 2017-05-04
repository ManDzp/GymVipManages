package com.jm.vip.dao;

import java.util.List;

import com.jm.vip.entity.AttachmentInfo;

public interface AttachmentInfoDao extends BaseDao<AttachmentInfo>
{
	/**
	 * @Description: 获取附件信息列表
	 * @param guid 文章唯一标识
	 * @return 返回附件信息列表
	 */
	public List<AttachmentInfo> listByGuid(String guid);

	/**
	 * @Description: 删除附件信息
	 * @param blobGuid 附件唯一标识
	 * @return 返回删除影响行数
	 */
	public int deleteByBlobGuid(String blobGuid);
}
