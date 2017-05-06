<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ include file="/WEB-INF/support/include.inc.jsp"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="headtop">
	<div style="float: left; width: 20px; height: 25px;">
		<img src="${ctx}/deco/img/web/topheadico.jpg" style="vertical-align: middle;" />
	</div>
	<div style="float: left; height: 25px;">
		<span style="vertical-align: middle;">欢迎来到${systemConfig.systemTitle}！</span>
	</div>
	<div style="float: right; height: 25px;">
		<span style="vertical-align: middle;">
			<c:choose>
			<c:when test="${empty contextUser}">
				【访客】您好！<a href="${ctx}/login">【请登录】</a>
			</c:when>
			<c:otherwise>
				【${contextUser.userName}】您好！<a href="${ctx}/logout">【注销】</a>
				<c:if test="${contextUser.userType == 9}"><a href="${ctx}/admin/index" target="_blank">【管理后台】</a></c:if>
			</c:otherwise>
			</c:choose>
		</span>
	</div>
</div>

<div class="web-top-header">
	<div class="web-top-title">${systemConfig.systemTitle}</div>
</div>
