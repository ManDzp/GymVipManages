<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>商品信息管理</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/datagrid/grid.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/business/goods/list.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/business/goods/goods.js${res_v}"></script>
</head>

<body>
	<!-- 列表页菜单栏 -->
	<ywbar:listBar/>

    <!-- 商品信息列表页 -->
	<table id="grid" style="width: getWidth(1); height: 100%" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
                <th data-options="field:'goodsCode',align:'left',formatter:getViewUrl,sortable:true,order:'desc'"
                    width="20">商品编码</th>
				<th data-options="field:'goodsName',align:'left',formatter:getViewUrl,sortable:true,order:'desc'"
					width="20">商品名</th>
				<th data-options="field:'goodsUnit',align:'left',formatter:getViewUrl,sortable:true,order:'desc'"
					width="20">单位</th>
				<th data-options="field:'goodsPrice',align:'left',formatter:getViewUrl,sortable:true,order:'desc'"
					width="20">单价</th>
				<th data-options="field:'goodsStatus',align:'left',formatter:getStatusUrl,sortable:true,order:'desc'"
					width="20">状态</th>
			</tr>
		</thead>
	</table>

	<ywtag:dialog/>

	<script type="text/javascript">
		/* 初始化列表基本属性 */
		(function(win){
			var gridData = {};

			// 默认的条件
			gridData.defaultParam = {};
			// 默认的查询参数
			gridData.defaultQueryParams = {
				"params" : JSON.stringify(gridData.defaultParam),
				'mapperid' : '${mapperid}'
			}

			win.gridData = gridData;
		})(window);

		// 初始化列表基本属性
		$(function() {
			// 加载数据
			ywGrid.loadGrid({
				columnSize : 6
			});
		});
	</script>
</body>
</html>