<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查看充值记录</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/business/record/chargerecord/chargerecord.js${res_v}"></script>
</head>

<body>
	<div class="page-content">
		<!-- 创建菜单栏 -->
		<ywbar:viewMenu/>

		<!--创建表单信息  -->
		<form id="fm" method="post" enctype="application/x-www-form-urlencoded">
			<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
				<tbody>
					<tr>
						<td class="lefttdbgcolor">充值余额：</td>
						<td class="tdbgcolor35"><label>${chargeRecord.money}</label></td>
						<td class="lefttdbgcolor">余额变更：</td>
						<td class="tdbgcolor35"><label>${chargeRecord.oldbalance} => ${chargeRecord.newbalance}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">充值时间：</td>
						<td class="tdbgcolor" colspan="3"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${chargeRecord.createtime}" /></label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">备注说明：</td>
						<td class="tdbgcolor" colspan="3">
							<textarea style="width: 100%;" rows="4" cols="10" readonly="readonly">${chargeRecord.remark}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>