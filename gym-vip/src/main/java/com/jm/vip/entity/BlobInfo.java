package com.jm.vip.entity;

public class BlobInfo
{
	/**
	 * 文件唯一标识
	 */
	private String blobguid;
	/**
	 * 附件唯一标识
	 */
	private String guid;
	/**
	 * 附件内容
	 */
	private byte[] content;

	public String getBlobguid()
	{
		return blobguid;
	}

	public void setBlobguid(String blobguid)
	{
		this.blobguid = blobguid == null ? null : blobguid.trim();
	}

	public String getGuid()
	{
		return guid;
	}

	public void setGuid(String guid)
	{
		this.guid = guid == null ? null : guid.trim();
	}

	public byte[] getContent()
	{
		return content;
	}

	public void setContent(byte[] content)
	{
		this.content = content;
	}
}