<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>${systemConfig.systemTitle}</title>

	<link rel="stylesheet" type="text/css" href="${ctx}/business/web/index.css${res_v}" />
	<link rel="stylesheet" type="text/css" href="${ctx}/business/web/index2.css${res_v}" />

	<link rel="stylesheet" type="text/css" href="<c:url value='/deco/jquery-easyui/themes/icon.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/deco/jquery-easyui/themes/bootstrap/easyui.css'/>">

	<script type="text/javascript" src="<c:url value='/deco/jquery/jquery.min.js'/>"></script>

	<script type="text/javascript" src="<c:url value='/deco/jquery-easyui/jquery.easyui.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/deco/jquery-easyui/locale/easyui-lang-zh_CN.js'/>"></script>

	<script type="text/javascript" src="${ctx}/business/web/register.js"></script>

<style type="text/css">
.login {
	width: 500px;
	height: 150px;
	border: 1px #fff solid;
	margin: 0 auto;
}

.l-text {
	font-size: 14px;
	font-weight: bold;
	color: #363636;
	text-align: right;
	vertical-align: middle;
	width: 160px;
	height: 40px;
}

.textbox .textbox-text {
	font-size: 14px;
}
</style>

<script type="text/javascript">
	// 公用全局变量
	window.glogbalData = {
		contextPath : '${ctx}'
	};
</script>

<script type="text/javascript">
	// 对所有输入框，校验回车操作
	$(function() {
		$('input').keydown(function(e) {
			var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
			if (key == 13) {
				e.preventDefault();
				saveAddUser();
			}
		});
	});
</script>

<body>
	<c:import url="web_top.jsp"></c:import>

	<div id="nav">
	    <div id="row1"><a href="${ctx}/webindex/web_index" style="color:#FFFFFF;">首页</a></div>
	</div>

	<div class="clear"></div>
	<!--头-->


	<form id="fm" method="post" novalidate enctype="application/x-www-form-urlencoded">
		<table class="login" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="l-text">昵　　称：</td>
				<td align="left" valign="middle">
					<input class="easyui-textbox" id="displayname" name="displayname"
						v_must="1" v_name="昵称" v_type="string"
						style="width: 200px; height: 30px; padding: 6px;">
				</td>
			</tr>
			<tr>
				<td class="l-text">登陆账号：</td>
				<td align="left" valign="middle">
					<input class="easyui-textbox" id="username" name="username"
						v_must="1" v_name="登陆账号" v_type="string"
						style="width: 200px; height: 30px; padding: 6px;">
				</td>
			</tr>
			<tr>
				<td class="l-text">登陆密码：</td>
				<td align="left" valign="middle">
					<input class="easyui-textbox" id="userpass" name="userpass"
						v_must="1" v_name="登陆密码" v_type="string"
						style="width: 200px; height: 30px; padding: 6px;">
				</td>
			</tr>
			<tr>
				<td class="l-text">确认密码：</td>
				<td align="left" valign="middle">
					<input class="easyui-textbox" id="confirmpass" name="confirmpass"
						v_must="1" v_name="确认密码" v_type="string"
						style="width: 200px; height: 30px; padding: 6px;">
				</td>
			</tr>
			<tr>
				<td height="10" colspan="2" align="center"></td>
			</tr>
			<tr>
				<td height="50" colspan="2" align="center">
					<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"
						onclick="saveAddUser()" style="width: 90px;">注册</a>
				</td>
			</tr>
		</table>
	</form>

	<c:import url="web_footer.jsp"></c:import>

</body>
</html>
