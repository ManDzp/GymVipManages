<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查询页面</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/ueditor2/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/deco/ueditor2/ueditor.all.js"></script>
<script type="text/javascript" src="${ctx}/deco/ueditor2/lang/zh-cn/zh-cn.js" charset="utf-8"></script>

<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/replyinfo/replyinfo.js${res_v}"></script>
</head>

<body>
	<div class="page-content">
		<!-- 创建菜单栏 -->
		<ywbar:viewMenu/>

		<!--创建表单信息  -->
		<form id="fm" method="post" enctype="application/x-www-form-urlencoded">
			<table class="tablebgcolor" width="100%" cellpadding="1" cellspacing="1" border="0" style="margin: 2px;">
				<tbody>
					<tr>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>卡　　号：</td>
						<td class="tdbgcolor"><input id="title" name="title"
							class="str" v_must="1" v_name="卡号" v_type="string" " /></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>会员姓名：</td>
						<td class="tdbgcolor">
						<input id="name" name="title"
							class="str" v_must="1" v_name="卡号" v_type="string" " />
						</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>会员手机号：</td>
						<td class="tdbgcolor">
						<textarea style=height:100px id="replycontent" name="replycontent" rows="5" cols="100">${replyInfo.replycontent}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<div style="display: none;">
				<input type="hidden" id="guid" name="guid" value="${replyInfo.guid}" />
			</div>
		</form>
	</div>

<script type='text/javascript'>
	var editor;
	$(document).ready(function () {
		editor = UE.getEditor('replycontent', {
			initialFrameHeight: 200, initialFrameWidth: '100%',
			elementPathEnabled: false
		});
	});
</script>
</body>
</html>