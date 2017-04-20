<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!-- 公共UI样式 -->
<link rel="stylesheet" type="text/css" href="<c:url value='/deco/jquery-easyui/themes/icon.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/deco/jquery-easyui/themes/bootstrap/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/deco/formStyle.css${res_v}'/>">

<!-- 公用JS -->
<script type="text/javascript" src="<c:url value='/deco/jquery/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/deco/jquery/jquery.serialize-object.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/deco/jquery-easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/deco/jquery-easyui/locale/easyui-lang-zh_CN.js'/>"></script>

<!--[if lte IE 7]>
    <script src="<c:url value='/deco/json2.js'/>"></script>
<![endif]-->

<script type="text/javascript" src="<c:url value='/deco/system.js${res_v}'/>"></script>

<script type="text/javascript">
	// 公用全局变量
	window.glogbalData = {
		contextPath : '${ctx}'
	};
</script>
