<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查看会员资料</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/business/member/member.js${res_v}"></script>
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
						<td class="tdbgcolor" colspan="4">会员卡资料</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">会员卡号：</td>
						<td class="tdbgcolor35"><label>${memberInfo.cardnumber}</label></td>
						<td class="lefttdbgcolor">会员类型：</td>
						<td class="tdbgcolor35"><label>
							<c:if test="${memberInfo.cardtype==0}">时间卡</c:if>
							<c:if test="${memberInfo.cardtype==1}">次卡</c:if>
						</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">开卡日期：</td>
						<td class="tdbgcolor35"><label><fmt:formatDate pattern="yyyy-MM-dd" value="${memberInfo.activetime}" /></label></td>
						<td class="lefttdbgcolor">到期日期：</td>
						<td class="tdbgcolor35"><label><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${memberInfo.expiretime}" /></label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">卡内余额：</td>
						<td class="tdbgcolor35"><label>${memberInfo.balance}</label></td>
						<td class="lefttdbgcolor">累计消费：</td>
						<td class="tdbgcolor35"><label>${memberInfo.consumption}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">会员积分：</td>
						<td class="tdbgcolor" colspan="3"><label>${memberInfo.points}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">可用次数：</td>
						<td class="tdbgcolor35"><label>${memberInfo.times}</label></td>
						<td class="lefttdbgcolor">已用次数：</td>
						<td class="tdbgcolor35"><label>${memberInfo.usedtimes}</label></td>
					</tr>
					<tr>
						<td class="tdbgcolor" colspan="4">会员资料</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">会员名称：</td>
						<td class="tdbgcolor35"><label>${memberInfo.fullname}</label></td>
						<td class="lefttdbgcolor">会员性别：</td>
						<td class="tdbgcolor35"><label>
							<c:if test="${memberInfo.sex==0}">男</c:if>
							<c:if test="${memberInfo.sex==1}">女</c:if>
						</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">国　　籍：</td>
						<td class="tdbgcolor35"><label>${memberInfo.nationality}</label></td>
						<td class="lefttdbgcolor">证件号码：</td>
						<td class="tdbgcolor35"><label>${memberInfo.identitycard}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">生　　日：</td>
						<td class="tdbgcolor35"><label><fmt:formatDate pattern="yyyy-MM-dd" value="${memberInfo.birthday}" /></label></td>
						<td class="lefttdbgcolor">手 机 号：</td>
						<td class="tdbgcolor35"><label>${memberInfo.mobile}</label></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>