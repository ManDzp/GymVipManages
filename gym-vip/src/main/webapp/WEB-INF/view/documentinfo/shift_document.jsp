<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>选择板块</title>
<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/business/documentinfo/shift_document.js${res_v}"></script>
<script type="text/javascript">
	function getContextPath() {
		return "${ctx}";
	}
</script>
</head>

<body style="overflow-x: hidden; overflow-y: auto; _overflow-y: inherit;">
	<div id="contenFram">

		<!--创建表单信息  -->
		<form id="fm" method="post" novalidate enctype="application/x-www-form-urlencoded">
			<table class="tablebgcolor" width="100%" cellpadding="1" cellspacing="1" border="0" style="margin: 2px;">
				<tbody>
					<tr>
						<td class="lefttdbgcolor" colspan="2" style="height: 25px; text-indent: 2em; text-align: left; vertical-align: middle;">
							<font face="宋体" color="red"><strong>选择板块：</strong></font>
						</td>
					</tr>
					<!-- select -->
					<tr style="width: 100%">
                         <td class="tdbgcolor35" ><select id="column" name="column"
							style="width: 250px;" >
								<c:forEach items="${columnList}" var="column">
									<option value="${column.guid}">${column.title}</option>
								</c:forEach>
						</select></td>
                    </td>
                </tr>
				</tbody>
			</table>

			<div style="height: 25px; margin: 13px 0 5px; text-align: center;">
				<a href="#" class="easyui-linkbutton" style="width: 80px; height: 24px;"
					onclick="sendDocument();">转移</a>&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" style="width: 80px; height: 24px;"
					onclick="cancelSendFlow();">取消</a>
			</div>

			<div style="display: none;">
				<input type="hidden" id="guid" name="guid" value="${guid}" />
				<input type="hidden" id="columnguid" name="columnguid" value="${documentInfo.columnguid}" />
				<input type="hidden" id="columnname" name="columnname" value="${documentInfo.columnname}" />
			</div>
		</form>
	</div>

	<script type="text/javascript">
	// 
	$(function() {
		$('#column').combobox({
			editable : false,
			panelHeight : '80',
			onSelect: function(rec){
				$("#columnguid").val(rec.value);
				$("#columnname").val(rec.text);
			}
		});
		$('#column').combobox('setValue', '${documentInfo.columnguid}');
	});
	</script>
</body>
</html>