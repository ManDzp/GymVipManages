<%@ tag pageEncoding="UTF-8" description="adv" body-content="empty"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link type="text/css" rel="stylesheet" href="${ctx}/deco/highquery/style.css" />

<!-- 高级查询 -->
<div style="display: none;">
	<div id="dlgAdvQuery" style="overflow-y: scroll; *padding-right: 17px;" >
		<div id='highQueryPanel'></div>
	</div>
</div>

<script type="text/javascript" src="${ctx}/deco/highquery/highquery.js"></script>
<script type="text/javascript" src="${ctx}/deco/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/business/advquery.js"></script>

<script type="text/javascript">
	$(function() {
		$("#dlgAdvQuery").dialog({
			title : '高级查询',
			width : 620,
			height : 250,
			closed : true,
			buttons : [ {
				text : '执行查询',
				iconCls : 'icon-ok',
				handler : doAdvQuery
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#dlgAdvQuery').dialog('close');
				}
			} ]
		});
	});
</script>
