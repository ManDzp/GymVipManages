<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>认证中心</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="<c:url value='/deco/jquery-easyui/themes/bootstrap/easyui.css'/>">

<script type="text/javascript" src="<c:url value='/deco/jquery/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/deco/jquery-easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/deco/jquery-easyui/locale/easyui-lang-zh_CN.js'/>"></script>

<style type="text/css">
.login {
	width: 570px;
	height: 305px;
	border: 1px #fff solid;
	margin: 0 auto;
	margin-top: 160px;
}

.l-text {
	font-size: 14px;
	font-weight: bold;
	color: #363636;
}

body {
	background-color: #187DBD;
	background-image: url(${ctx}/deco/img/login/loginbackground.jpg);
	background-repeat: repeat-x;
}

body, td, th {
	font-size: 12px;
	color: #3E3E3E;
	line-height: 22px;
}

.textbox .textbox-text {
	font-size: 14px;
}
</style>

<script type="text/javascript">
	// 检验数据
	function checkData() {
		$("#btnLogin").click();
	}

	// 对所有输入框，校验回车操作
	$(function() {
		$('input').keydown(function(e) {
			var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
			if (key == 13) {
				e.preventDefault();
				checkData();
			}
		});
	});
</script>

</head>

<body>
	<form method="post" action="${ctx}/check">

		<div>
			<table width="570" border="0" cellpadding="0" cellspacing="0"
				class="login">
				<tr>
					<td><img src="${ctx}/deco/img/login/loginbanner.jpg" width="570" height="91"
						alt="" /></td>
				</tr>
				<tr>
					<td height="225" align="center" valign="middle"
						style="background-image: url(${ctx}/deco/img/login/loginbg.jpg)"><table
							width="530" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="270" height="160" align="center" valign="top">
									<table width="90%" border="0" cellspacing="0" cellpadding="0">
										<c:if test="${!empty message}">
											<td height="32" align="center" valign="middle" class="l-text"
												colspan="2"><span style="color: red">${message}</span></td>
										</c:if>
										<tr>
											<td width="160" height="40" align="right" valign="middle"
												class="l-text">用户名：</td>
											<td align="left" valign="middle">
												<input class="easyui-textbox" name="account"
													style="width: 200px; height: 30px; padding: 6px;">
											</td>
										</tr>
										<tr>
											<td height="40" align="right" valign="middle" class="l-text">密　码：</td>
											<td align="left" valign="middle">
												<input class="easyui-textbox" type="password" name="password"
													style="width: 200px; height: 30px; padding: 6px;">
											</td>
										</tr>
										<tr>
											<td height="50" colspan="2" align="center"><img style="cursor: pointer"
												src="${ctx}/deco/img/login/login-button.jpg" onclick="checkData();"
												alt="" /></td>
										</tr>
									</table></td>
							</tr>
						</table></td>
				</tr>
			</table>
		</div>

		<div style="display: none;">
			<input type="submit" id="btnLogin" />
		</div>
	</form>
</body>
</html>