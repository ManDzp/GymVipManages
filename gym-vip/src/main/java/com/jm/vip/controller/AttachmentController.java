package com.jm.vip.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.jm.commons.domain.ResultDTO;
import com.jm.commons.log.LogProxy;
import com.jm.commons.utils.BaseUtils;
import com.jm.vip.service.AttachmentService;

/**
 * 附件控制器
 */
@Controller
@RequestMapping("/upload")
public class AttachmentController
{
	// 声明log4j
	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "attachmentService")
	private AttachmentService attachmentService;

	/**
	 * 上传附件
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO uploadFile(HttpServletResponse response,
			HttpServletRequest request) throws Exception
	{
		ResultDTO result = new ResultDTO();
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 设置编码
		commonsMultipartResolver.setDefaultEncoding("utf-8");
		// 判断 request 是否有文件上传,即多部分请求...
		if (!commonsMultipartResolver.isMultipart(request))
		{
			result.setSuccess(false);
			result.setMessage("");
			return result;
		}
		// MultipartHttpServletRequest multipartRequest =
		// (MultipartHttpServletRequest) request;
		MultipartHttpServletRequest multipartRequest = null;

		try
		{
			multipartRequest = commonsMultipartResolver
					.resolveMultipart(request);
		}
		catch (Exception e)
		{
			result.setSuccess(false);
			result.setMessage("");
			return result;
		}

		MultipartFile multipartFile = multipartRequest.getFile("Filedata");

		/** 拼成完整的文件保存路径加文件 **/
		String originalFilename = multipartFile.getOriginalFilename(); // 文件全名
		String realPath = request.getSession().getServletContext()
				.getRealPath("/");

		String blobGuid = BaseUtils.getNewGuid();

		String dirPath = StringUtils.join(realPath, "\\ftemp\\", blobGuid,
				"\\");

		initDirctoryExist(dirPath);

		// 处理附件名称
		String fileName = trimFileName(originalFilename);
		// 拼接文件路径
		String filePath = StringUtils.join(dirPath, fileName);

		// 保存附件
		File file = new File(filePath);
		multipartFile.transferTo(file);

		result.setSuccess(true);
		result.setMessage(blobGuid);
		return result;
	}

	/**
	 * 处理文件名称
	 * @param fileName 文件名称
	 * @return
	 */
	private String trimFileName(String fileName)
	{
		int splitIndex = fileName.lastIndexOf(".");

		String fileTitle = fileName.substring(0, splitIndex);
		String fileType = fileName.substring(splitIndex);// 文件的扩展名

		return StringUtils.substring(fileTitle, 0, 100) + fileType;
	}

	/**
	 * 初始化文件夹，并保证文件夹存在
	 */
	private File initDirctoryExist(String dirPath)
	{
		File serverFile = new File(dirPath);
		if (!serverFile.exists())
		{
			serverFile.mkdirs();
		}
		return serverFile;
	}

	/**
	 * 删除单个附件
	 * @param blobGuid
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO deleteFile(@RequestParam String blobGuid)
	{
		ResultDTO result = new ResultDTO();

		try
		{
			boolean ret = attachmentService.deleteByBlobGuid(blobGuid);
			result.setSuccess(ret);
		}
		catch (Exception ex)
		{
			// 记录错误日志
			LogProxy.WriteLogError(log, "删除附件", ex.toString(), blobGuid);
			result.setSuccess(false);
		}

		return result;
	}

}