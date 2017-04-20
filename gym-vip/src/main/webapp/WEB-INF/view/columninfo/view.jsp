<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查看板块信息</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/business/columninfo/columninfo.js${res_v}"></script>
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
						<td class="lefttdbgcolor">板块名称：</td>
						<td class="tdbgcolor35" ><label>${columninfo.title}</label></td>
						<td class="lefttdbgcolor">排序号：</td>
						<td class="tdbgcolor35"><label>${columninfo.ordernum}</label></td>
					</tr>
                </tbody>
             </table>
		</form>
	</div>
</body>
</html>