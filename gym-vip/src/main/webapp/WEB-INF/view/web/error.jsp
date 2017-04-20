<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>错误页面</title>

<link rel="stylesheet" type="text/css" href="${ctx}/business/web/index.css${res_v}" />
<link rel="stylesheet" type="text/css" href="${ctx}/business/web/index2.css${res_v}" />

</head>

<body>
	<c:import url="web_top.jsp"></c:import>

	<div class="clear"></div>

	<div style="font-size: 20px; font-weight: bold;"><font>${errormessage}</font></div>

	<c:import url="web_footer.jsp"></c:import>

</body>
</html>