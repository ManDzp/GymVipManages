<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>修改会员资料</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/deco/uploadify/uploadify.css" />

<script type="text/javascript" src="${ctx}/deco/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${ctx}/deco/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/fileupload.js"></script>
<script type="text/javascript" src="${ctx}/business/member/member.js${res_v}"></script>

<script type="text/javascript">
	//上传附件
	$(function() {
		var swfUrl = "${ctx}/deco/uploadify/uploadify.swf";
		var uploaderUrl = '${ctx}/upload/uploadfile;jsessionid=${pageContext.session.id}';

		setUpload(swfUrl, uploaderUrl, 'custom_file_upload', 'custom-queue',
				'会员照片', 'hdFiles', 'hdFilesSize', '999', '999');
	});
</script>
<script type="text/javascript">
	//初始化
	$(function() {
		$('#cardtype').combobox('setValue', '${memberInfo.cardtype}');
		$('#sex').combobox('setValue', '${memberInfo.sex}');
	});
</script>
</head>

<body>
	<div class="page-content">
		<!-- 创建菜单栏 -->
		<ywbar:viewMenu/>

		<!--创建表单信息  -->
		<form id="fm" method="post" enctype="application/x-www-form-urlencoded">
			<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
				<tbody>
					<tr>
						<td class="tdbgcolor" colspan="4">会员卡资料</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">会员卡号：</td>
						<td class="tdbgcolor35"><label>${memberInfo.cardnumber}</label></td>
						<td class="lefttdbgcolor">会员类型：</td>
						<td class="tdbgcolor35"><label>
							<c:if test="${memberInfo.cardtype==0}">时间卡</c:if>
							<c:if test="${memberInfo.cardtype==1}">次卡</c:if>
						</label></td>
					</tr>
					<tr>
						<td class="tdbgcolor" colspan="4">会员资料</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>会员名称：</td>
						<td class="tdbgcolor35"><input id="fullname" name="fullname"
							class="str" v_must="1" v_name="会员名称" v_type="string"
							value="${memberInfo.fullname}" /></td>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>会员性别：</td>
						<td class="tdbgcolor35">
							<select id="sex" name="sex" class="easyui-combobox"
								editable="false" panelHeight="auto" style="width: 45px;">
								<option value="0">男</option>
								<option value="1">女</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>国　　籍：</td>
						<td class="tdbgcolor35"><input id="nationality" name="nationality"
							class="str" v_must="1" v_name="国籍" v_type="string"
							value="${memberInfo.nationality}" /></td>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>证件号码：</td>
						<td class="tdbgcolor35"><input id="identitycard" name="identitycard"
							class="str" v_must="1" v_name="登陆密码" v_type="string"
							value="${memberInfo.identitycard}" /></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>生　　日：</td>
						<td class="tdbgcolor35"><input id="birthday" name="birthday"
							class="Wdate" v_must="1" v_name="生日" v_type="date"
							onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd' })"
								value="<fmt:formatDate pattern="yyyy-MM-dd" value="${memberInfo.birthday}" />" /></td>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>手 机 号：</td>
						<td class="tdbgcolor35"><input id="mobile" name="mobile"
							class="str" v_must="1" v_name="手机号" v_type="string"
							value="${memberInfo.mobile}" /></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor"><input id="custom_file_upload" name="Filedata" type="file" /></td>
						<td class="tdbgcolor" colspan="3">
							<div class="demo">
								<div class="demo-box">
									<div id="custom-queue"></div>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<div style="display: none;">
				<input type="hidden" id="guid" name="guid" value="${memberInfo.guid}" />
				<input type="hidden" id="hdFiles" name="hdFiles" />
				<input type="hidden" id="hdFilesSize" name="hdFilesSize" value="0" />
			</div>
		</form>
	</div>
</body>
</html>