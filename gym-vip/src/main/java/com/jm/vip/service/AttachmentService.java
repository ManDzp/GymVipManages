package com.jm.vip.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
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
	 * 获取第一个图片地址
	 * @param request 页面请求
	 * @param guid 发文唯一标识
	 * @return
	 */
	public String getFirstImageUrl(HttpServletRequest request, String guid)
	{
		String siteUrl = request.getContextPath();
		String sitePath = request.getSession().getServletContext()
				.getRealPath("/");

		// 附件列表
		List<AttachmentInfo> attachments = getAttachmentsInfoByGuid(guid,
				siteUrl, sitePath);

		if (CollectionUtils.isEmpty(attachments))
		{
			return "";
		}

		for (AttachmentInfo attachment : attachments)
		{
			if (Arrays.asList("gif", "jpg")
					.contains(attachment.getFileext().toLowerCase()))
			{
				return attachment.getFileurl();
			}
		}

		return "";
	}

	/**
	 * 根据文件标识获取文件多个附件，包含附件地址信息
	 * @param guid 文件标识
	 * @param siteUrl 网络地址
	 * @param sitePath 物理地址
	 * @return 附件列表
	 */
	public List<AttachmentInfo> getAttachmentsInfoByGuid(String guid,
			String siteUrl, String sitePath)
	{
		try
		{
			List<AttachmentInfo> attachments = this.attachmentInfoDao
					.listByGuid(guid);

			setAttachmentUrl(attachments, siteUrl, sitePath);

			return attachments;
		}
		catch (Exception e)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "根据文件标识获取附件", e.toString(), guid);
			return null;
		}
	}

	/**
	 * @Description: 设置附件信息
	 * @param attachments 附件列表
	 * @param siteUrl 网络地址
	 * @param sitePath 物理地址
	 */
	private void setAttachmentUrl(List<AttachmentInfo> attachments,
			String siteUrl, String sitePath)
	{
		for (AttachmentInfo attachment : attachments)
		{
			String blobGuid = attachment.getBlobguid();
			BlobInfo blob = this.blobInfoDao.getByGuid(blobGuid);
			if (blob != null)
			{
				String[] urlArr = getAttachmentUrl(attachment.getTitle(),
						blobGuid, siteUrl, sitePath);
				attachment.setDirpath(urlArr[0]);
				attachment.setFilepath(urlArr[1]);
				attachment.setFileurl(urlArr[2]);
				attachment.setBlobcontent(blob.getContent());
			}
		}
	}

	/**
	 * 根据传递过来的附件唯一标识、文件名、附件存放路径生成文件
	 * @param fileName 附件中文名
	 * @param blobGuid 附件唯一标识
	 * @param siteUrl
	 * @param sitePath
	 * @return [0]附件所在的文件夹；[1]附件存放物理路径\\ftemp\\；[2]附件访问路径 /ftemp/
	 */
	public String[] getAttachmentUrl(String fileName, String blobGuid,
			String siteUrl, String sitePath)
	{
		String[] file = new String[3];

		// 附件文件夹路径 dirpath
		file[0] = sitePath + "\\ftemp\\" + blobGuid;
		// 附件物理路径 filepath
		file[1] = sitePath + "\\ftemp\\" + blobGuid + "\\" + fileName;
		// 附件访问全路径 fileurl
		file[2] = siteUrl + "/ftemp/" + blobGuid + "/" + fileName;

		return file;
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
