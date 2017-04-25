<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ include file="/WEB-INF/support/include.inc.jsp"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/deco/north.css">

<style type="text/css">
.admin-top-title {
	font-size: 35px;
	font-weight: bold;
	padding-left: 30px;
	height: 78px;
	line-height: 78px;
}
</style>

<table width="100%" border="0" cellspacing="0" cellpadding="0"
	style="background-repeat: repeat-x; background-image: url('${ctx}/deco/img/header9.jpg');">
	<tr>
		<td rowspan="2" width="70%">
			<div class="admin-top-title">${jsp_title}</div>
		</td>
		<td width="30%" height="45"></td>
	</tr>
	<tr width="100%">
		<td align="right">
			<font color="#000000" size="2">
				<span style="white-space: nowrap; padding-right: 20px;">【${contextUser.userName}】您好！<a href="${ctx}/logout">【注销】</a></span>
			</font>
		</td>
	</tr>
</table>
