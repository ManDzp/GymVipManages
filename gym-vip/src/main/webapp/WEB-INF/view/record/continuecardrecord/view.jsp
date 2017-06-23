<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查看续卡记录</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/business/record/continuecardrecord/continuecardrecord.js${res_v}"></script>
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
						<td class="lefttdbgcolor">消费金额：</td>
						<td class="tdbgcolor" colspan="3"><label>${continueCardRecord.money}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">卡内余额变更：</td>
						<td class="tdbgcolor35"><label>${continueCardRecord.oldbalance} => ${continueCardRecord.newbalance}</label></td>
						<td class="lefttdbgcolor">累计消费变更：</td>
						<td class="tdbgcolor35"><label>${continueCardRecord.oldconsumption} => ${continueCardRecord.newconsumption}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">到期日期：</td>
						<td class="tdbgcolor35"><fmt:formatDate pattern="yyyy-MM-dd" value="${continueCardRecord.expiretime}" /></td>
						<td class="lefttdbgcolor">到期日期变更：</td>
						<td class="tdbgcolor35">
							<fmt:formatDate pattern="yyyy-MM-dd" value="${continueCardRecord.oldexpiretime}" />
							=>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${continueCardRecord.expiretime}" />
						</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">续卡时间：</td>
						<td class="tdbgcolor" colspan="3"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${continueCardRecord.createtime}" /></label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">备注说明：</td>
						<td class="tdbgcolor" colspan="3">
							<textarea style="width: 100%;" rows="4" cols="10" readonly="readonly">${continueCardRecord.remark}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>