<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查看论坛统计</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<style type="text/css">
.tdbgcolor {
	background-color: rgb(255, 255, 255);
	width: inherit;
}
</style>

<script type="text/javascript" src="${ctx}/deco/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/business/replyinfo/replyinfo.js${res_v}"></script>
</head>
<body>
	<div class="page-content">
		<table cellpadding="1" cellspacing="0" border="0">
			<tbody>
				<tr>
					<td class="tdbgcolor" style="width: 1%;"><img height="16" src="${ctx}/deco/img/online.gif" width="16"></td>
					<td class="tdbgcolor" style="width: 99%;">论坛统计分析<font color="#ff0000">（提示：该统计数据仅供参考）</font></td>
				</tr>
			</tbody>
		</table>

		<table class="tablebgcolor" cellSpacing="1" cellPadding="2" width="100%" align="center"
			bgColor="#000000" border="0">
			<tr>
				<td class="lefttdbgcolor">选择日期：</td>
				<td class="tdbgcolor20"><input class="Wdate" id="datestr" name="datestr" readonly="readonly"
							onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd' })" value="${nowdate}"/></td>
			</tr>
			<tr>
				<td class="tdbgcolor" style="text-align:right; padding-right:50px;">
					<span>
						<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-search"
							onclick="loadvisit();" style="width: 90px;">浏览量统计</a>
					</span>
				</td>
				<td class="tdbgcolor"  style="text-align:left; padding-left:50px;">
					<span>
						<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-search"
							onclick="loadreplynum();" style="width: 90px;">回复帖统计</a>
					</span>
				</td>
			</tr>
			<tr>
				<td class="tdbgcolor"  style="text-align: right; padding-right:50px;">
					<span>
						<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-search"
							onclick="somedaytongji();" style="width: 90px;">发帖数统计</a>
					</span>
				</td>
				<td class="tdbgcolor"  style="text-align: left; padding-left:50px;">
					<span>
						<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-search"
							onclick="deletetongji();" style="width: 90px;">删除贴统计</a>
					</span>
				</td>
			</tr>
		</table>

		<div id="result_div"></div>

		<div style="display: none;">
			<input type="hidden" id="docguid" name="docguid" value="${replyInfo.docguid}" />
		</div>
	</div>
</body>
</html>