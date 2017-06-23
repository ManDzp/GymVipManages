<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查看积分记录</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/business/record/pointsrecord/pointsrecord.js${res_v}"></script>
</head>

<body>
	<div class="page-content">
		<!-- 创建菜单栏 -->
		<ywbar:viewMenu/>

		<!--创建表单信息  -->
		<form id="fm" method="post" enctype="application/x-www-form-urlencoded">
			<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
				<tbody>
				<c:if test="${pointsRecord.pointtype==0}">
				<!-- 获取积分 -->
					<tr>
						<td class="lefttdbgcolor">类　　型：</td>
						<td class="tdbgcolor" colspan="3"><label>获取积分</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">积　　分：</td>
						<td class="tdbgcolor35"><label>${pointsRecord.points}</label></td>
						<td class="lefttdbgcolor">积分变更：</td>
						<td class="tdbgcolor35"><label>${pointsRecord.oldpoints} => ${pointsRecord.newpoints}</label></td>
					</tr>
				</c:if>
				<c:if test="${pointsRecord.pointtype==1}">
				<!-- 兑换积分 -->
					<tr>
						<td class="lefttdbgcolor">类　　型：</td>
						<td class="tdbgcolor" colspan="3"><label>兑换积分</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">积　　分：</td>
						<td class="tdbgcolor35"><label>${pointsRecord.points}</label></td>
						<td class="lefttdbgcolor">积分变更：</td>
						<td class="tdbgcolor35"><label>${pointsRecord.oldpoints} => ${pointsRecord.newpoints}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">到期日期：</td>
						<td class="tdbgcolor35"><label><fmt:formatDate pattern="yyyy-MM-dd" value="${pointsRecord.expiretime}" /></label></td>
						<td class="lefttdbgcolor">到期日期变更：</td>
						<td class="tdbgcolor35">
							<fmt:formatDate pattern="yyyy-MM-dd" value="${pointsRecord.oldexpiretime}" />
							=>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${pointsRecord.expiretime}" />
						</td>
					</tr>
				</c:if>
					<tr>
						<td class="lefttdbgcolor">操作时间：</td>
						<td class="tdbgcolor" colspan="3"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${pointsRecord.createtime}" /></label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">备注说明：</td>
						<td class="tdbgcolor" colspan="3">
							<textarea style="width: 100%;" rows="4" cols="10" readonly="readonly">${pointsRecord.remark}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>