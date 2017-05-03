package com.jm.vip.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.common.collect.Lists;
import com.jm.log.LogProxy;
import com.jm.utils.DateHelper;
import com.jm.vip.dao.AttachmentInfoDao;
import com.jm.vip.dao.BlobInfoDao;
import com.jm.vip.entity.AttachmentInfo;
import com.jm.vip.entity.BlobInfo;

@Service
public class AttachmentService
{
	/**
	 * 声明log4j变量
	 */
	private Logger log = Logger.getLogger(AttachmentService.class);

	@Resource(name = "attachmentInfoDao")
	private AttachmentInfoDao attachmentInfoDao;

	@Resource(name = "blobInfoDao")
	private BlobInfoDao blobInfoDao;

	/**
	 * 根据附件唯一标识删除附件
	 * @param blobGuid 附件唯一标识
	 * @return 是否删除成功
	 */
	public boolean deleteByBlobGuid(String blobGuid)
	{
		try
		{
			this.attachmentInfoDao.deleteByGuid(blobGuid);
			this.blobInfoDao.deleteByGuid(blobGuid);
			return true;
		}
		catch (Exception e)
		{
			// 回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			// 记录错误日志
			LogProxy.WriteLogError(log, "删除附件", e.toString(), blobGuid);
			return false;
		}
	}

	/**
	 * @Description: 添加附件
	 * @param guid 文件唯一标识
	 * @param realPath 物理路径
	 * @param allFiles 附件名称
	 * @return
	 */
	@Transactional
	public boolean insertAttachment(String guid, String realPath,
			String allFiles)
	{
		if (StringUtils.isEmpty(allFiles))
		{
			return false;
		}

		// 附件数据
		List<AttachmentInfo> attachments = Lists.newArrayList();
		List<BlobInfo> blobs = Lists.newArrayList();

		// 附件处理
		String[] allFileArr = allFiles.split("[*]");
		for (String allFile : allFileArr)
		{
			if (StringUtils.isEmpty(allFile))
			{
				continue;
			}

			String[] fileDetail = allFile.split("[|]");// 文件信息

			if (fileDetail == null || fileDetail.length < 2)
			{
				continue;
			}

			String blobGuid = fileDetail[1];// 附件唯一标识
			String fileName = fileDetail[2];// 附件名称

			String filePath = StringUtils.join(realPath, "\\ftemp\\", blobGuid,
					"\\", fileName);

			AttachmentInfo attachmentFile = getAttachmentFile(filePath);
			if (attachmentFile == null)
			{
				continue;
			}

			// 附件主表
			AttachmentInfo mdlAttachment = new AttachmentInfo();
			mdlAttachment.setGuid(guid);
			mdlAttachment.setBlobguid(blobGuid);
			mdlAttachment.setTitle(attachmentFile.getTitle());
			mdlAttachment.setFilelength(attachmentFile.getFilelength());
			mdlAttachment.setFileext(attachmentFile.getFileext());
			mdlAttachment.setCreatetime(DateHelper.getCurrentDate());
			attachments.add(mdlAttachment);

			// 附件内容表
			BlobInfo mdlBlob = new BlobInfo();
			mdlBlob.setGuid(guid);
			mdlBlob.setBlobguid(blobGuid);
			mdlBlob.setContent(attachmentFile.getBlobcontent());
			blobs.add(mdlBlob);
		}

		// 添加附件
		for (AttachmentInfo attachment : attachments)
		{
			this.attachmentInfoDao.insert(attachment);
		}
		for (BlobInfo blob : blobs)
		{
			this.blobInfoDao.insert(blob);
		}

		return true;
	}

	/**
	 * @Description: 通过物理路径，获取附件信息
	 * @param filePath 附件物理路径
	 * @return
	 */
	private AttachmentInfo getAttachmentFile(String filePath)
	{
		if (StringUtils.isEmpty(filePath))
		{
			return null;
		}

		File file = new File(filePath);
		if (!file.exists())
		{
			return null;
		}

		String fileName = file.getName();
		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);// 最后文件名称
		fileName = changeMidleTrm(fileName);// 去掉文件标题的空格
		long fileLength = file.length();// 输入流长度
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);// 文件的扩展名

		byte[] blobData = new byte[(int) fileLength];
		try
		{
			FileInputStream fis = new FileInputStream(file);
			fis.read(blobData);
			fis.close();
		}
		catch (IOException e)
		{
			return null;
		}

		AttachmentInfo mdlFile = new AttachmentInfo();
		mdlFile.setTitle(fileName);
		mdlFile.setFilelength(fileLength);
		mdlFile.setFileext(fileType);
		mdlFile.setBlobcontent(blobData);

		return mdlFile;
	}

	/**
	 * 去掉文件中的空格
	 * @param sourceStr 字符串
	 * @return 去除空格后的字符串
	 */
	private String changeMidleTrm(String sourceStr)
	{
		String endStr = "";
		String[] sourceArr = sourceStr.split(" ");
		for (int i = 0; i < sourceArr.length; i++)
			endStr += sourceArr[i].trim();
		return endStr;
	}

}
