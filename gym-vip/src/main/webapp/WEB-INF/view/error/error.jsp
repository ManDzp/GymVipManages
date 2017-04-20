<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>

<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>错误页面</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

</head>
<body>
	<div style="padding-bottom: 10px;">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-back"
			onclick="closeIFrameDialog();">返回</a>
	</div>
	<br/>
	<div style="font-size:14px;"><font>${errormessage}</font></div>
</body>
</html>