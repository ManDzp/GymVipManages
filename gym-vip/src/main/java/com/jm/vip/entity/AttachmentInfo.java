package com.jm.vip.entity;

import java.util.Date;

public class AttachmentInfo
{

	private String blobguid;// 文件唯一标识

	private String guid;// 附件唯一标识

	private String attachmenttype;// 附件类型

	private Short doctype;// 应用代码

	private String title;// 附件名称

	private Long filelength;// 附件大小

	private String fileext;// 附件后缀

	private Date createtime;// 创建时间

	// 补充属性-附件文件夹路径
	private String dirpath;
	// 补充属性-附件访问全路径
	private String fileurl;
	// 补充属性-附件物理路径
	private String filepath;
	// 补充属性-blob的内容信息
	private byte[] blobcontent;

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

	public String getAttachmenttype()
	{
		return attachmenttype;
	}

	public void setAttachmenttype(String attachmenttype)
	{
		this.attachmenttype = attachmenttype == null ? null : attachmenttype
				.trim();
	}

	public Short getDoctype()
	{
		return doctype;
	}

	public void setDoctype(Short doctype)
	{
		this.doctype = doctype;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title == null ? null : title.trim();
	}

	public Long getFilelength()
	{
		return filelength;
	}

	public void setFilelength(Long filelength)
	{
		this.filelength = filelength;
	}

	public String getFileext()
	{
		return fileext;
	}

	public void setFileext(String fileext)
	{
		this.fileext = fileext == null ? null : fileext.trim();
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	/**
	 * 补充属性-获取附件存放文件夹路径
	 * @return
	 */
	public String getDirpath()
	{
		return dirpath;
	}

	/**
	 * 补充属性-设置附件存放文件夹路径
	 * @param dirpath
	 */
	public void setDirpath(String dirpath)
	{
		this.dirpath = dirpath;
	}

	/**
	 * 获取附件内容
	 * @return
	 */
	public byte[] getBlobcontent()
	{
		return blobcontent;
	}

	/**
	 * 设置附件内容
	 * @param blobcontent
	 */
	public void setBlobcontent(byte[] blobcontent)
	{
		this.blobcontent = blobcontent;
	}

	/**
	 * 获取附件所在的物理文件路径\\ftemp\\
	 * @return
	 */
	public String getFilepath()
	{
		return filepath;
	}

	/**
	 * 设置附件所在的物理文件路径
	 * @param filepath
	 */
	public void setFilepath(String filepath)
	{
		this.filepath = filepath;
	}

	/**
	 * 获取附件访问路径
	 * @return
	 */
	public String getFileurl()
	{
		return fileurl;
	}

	/**
	 * 设置附件访问路径
	 * @param fileurl
	 */
	public void setFileurl(String fileurl)
	{
		this.fileurl = fileurl;
	}

}