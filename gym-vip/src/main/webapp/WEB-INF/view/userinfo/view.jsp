<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查看注册账号信息</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

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
						<td class="lefttdbgcolor">登陆账号：</td>
						<td class="tdbgcolor20" ><label>${userinfo.username}</label></td>
						<td class="lefttdbgcolor">昵       称：</td>
						<td class="tdbgcolor20" ><label>${userinfo.displayname}</label></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>