<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>${jsp_title}</title>
<%@ include file="/WEB-INF/support/common.jsp"%>
</head>

<body class="easyui-layout">
	<!-- 北 -->
	<div data-options="region:'north',border:false" style="height: 78px; overflow: hidden;">
		<c:import url="admin_top.jsp"></c:import>
	</div>
	<!-- 西 -->
	<div data-options="region:'west',split:true"
		style="width: 240px; height: 100%; overflow-y: hidden;">
		<c:import url="admin_left.jsp"></c:import>
	</div>
	<!-- 中 -->
	<div data-options="region:'center'" style="overflow: hidden">
		<ywtag:center/>
	</div>
</body>
</html>