<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>修改注册账户信息</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/userinfo/userinfo.js${res_v}"></script>
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
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>登陆账号：</td>
						<td class="tdbgcolor35"><input id="username" name="username"
							class="str" v_must="1" v_name="登陆账号" v_type="string" value="${userinfo.username}" /></td>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>修改密码：</td>
						<td class="tdbgcolor35"><input id="userpass" name="userpass"
							class="str" v_must="1" v_name="登陆密码" v_type="string" value="${userinfo.userpass}" /></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>昵      称：</td>
						<td class="tdbgcolor35"><input id="displayname" name="displayname"
							class="str" v_must="1" v_name="昵称" v_type="string" value="${userinfo.displayname}" /></td>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>确认密码：</td>
						<td class="tdbgcolor35"><input id="confirmpass" name="confirmpass"
							class="str" v_must="1" v_name="确认密码" v_type="string" /></td>
					</tr>
				</tbody>
			</table>
			<div style="display: none;">
				<input type="hidden" id="userguid" name="userguid" value="${userinfo.userguid}" />
			</div>
		</form>
	</div>
</body>
</html>