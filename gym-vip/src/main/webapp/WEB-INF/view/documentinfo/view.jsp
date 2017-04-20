<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查看帖子信息</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/business/documentinfo/documentinfo.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/business/documentinfo/shift_document.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/business/deletereason/deletereason.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/deco/layer/layer.js"></script>
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
						<td class="lefttdbgcolor">标题：</td>
						<td class="tdbgcolor" colspan="3"><label>${documentinfo.title}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">帖子类型：</td>
						<td class="tdbgcolor35"><label>${documentinfo.type}</label></td>
						<td class="lefttdbgcolor">发帖时间：</td>
						<td class="tdbgcolor35">
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm:dd" value="${documentinfo.createtime}" />
						</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">是否置顶：</td>
						<td class="tdbgcolor35"><label>
						<c:if test="${documentinfo.istop==0}">否</c:if>
						<c:if test="${documentinfo.istop==1}">是</c:if>
						</label></td>	
						<td class="lefttdbgcolor">是否精华：</td>
						<td class="tdbgcolor35"><label>
						<c:if test="${documentinfo.iscream==0}">否</c:if>
						<c:if test="${documentinfo.iscream==1}">是</c:if>
						</label>
						</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">发帖人：</td>
						<td class="tdbgcolor" colspan="3"><label>${documentinfo.creator}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">内容：</td>
						<td class="tdbgcolor" colspan="3"><label>${documentinfo.areacontent}</label></td>
					</tr>
                </tbody>
             </table>
		</form>
	</div>
</body>
</html>