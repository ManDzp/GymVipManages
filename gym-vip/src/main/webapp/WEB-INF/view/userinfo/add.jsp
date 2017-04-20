<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>添加会员账号申请</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/userinfo/userinfo.js"></script>
</head>

<body style="overflow-x: hidden; overflow-y: auto; _overflow-y: inherit;">
	<div id="contenFram">
		<!-- 创建菜单栏 -->
		<ywbar:viewMenu/>

		<!--创建表单信息  -->
		<form id="fm" method="post" novalidate enctype="application/x-www-form-urlencoded">
			<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
				<tbody>
					<tr>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>登陆账号：</td>
						<td class="tdbgcolor35"><input id="username" name="username"
							class="str" v_must="1" v_name="登陆账号" v_type="string" /></td>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>登陆密码：</td>
						<td class="tdbgcolor35"><input id="userpass" name="userpass"
							class="str" v_must="1" v_name="登陆密码" v_type="string" /></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>昵       称：</td>
						<td class="tdbgcolor35"><input id="displayname" name="displayname"
							class="str" v_must="1" v_name="昵称" v_type="string" /></td>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>确认密码：</td>
						<td class="tdbgcolor35"><input id="confirmpass" name="confirmpass"
							class="str" v_must="1" v_name="确认密码" v_type="string" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>