<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>新建板块</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/columninfo/columninfo.js${res_v}"></script>
</head>

<body style="overflow-x: hidden; overflow-y: auto; _overflow-y: inherit;">
	<div id="contenFram">
		<!-- 创建菜单栏 -->
		<ywbar:viewMenu/>

		<!--创建表单信息  -->
		<form id="fm" method="post" novalidate enctype="application/x-www-form-urlencoded">
			<table class="tablebgcolor" width="100%" cellpadding="1" cellspacing="1" border="0" style="margin: 2px;">
				<tbody>
					<tr>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>板块名称：</td>
						<td class="tdbgcolor35"><input id="title" name="title" class="str" v_must="1" v_type="string" v_name="板块名称"/></td>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>排序号：</td>
						<td class="tdbgcolor35"><input id="ordernum" name="ordernum" class="str"  v_must="1" v_type="int" v_name="排序号"/></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>