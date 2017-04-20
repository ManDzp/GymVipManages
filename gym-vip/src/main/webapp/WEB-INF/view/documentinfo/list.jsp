<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>帖子列表</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/datagrid/grid.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/business/documentinfo/documentinfo.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/business/documentinfo/list.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/deco/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/deco/dateformat.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/business/deletereason/deletereason.js${res_v}"></script>
</head>

<body>
	<!-- 创建菜单栏 -->
	<ywbar:listBar/>

	<!-- 列表页 -->
	<table id="grid" style="width: getWidth(1); height: 100%;">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'title',align:'left',formatter:getTitleUrl,sortable:true,order:'desc'" width="40">标题</th>
				<th data-options="field:'type',align:'left',formatter:getViewUrl,sortable:true,order:'desc'" width="15">帖子类型</th>	
				<th data-options="field:'createtime',align:'left',formatter:getDateUrl,sortable:true,order:'desc'" width="15">发帖时间</th>	
				<th data-options="field:'creator',align:'left',formatter:getViewUrl,sortable:true,order:'desc'" width="15">发帖人</th>		
			</tr>
		</thead>
	</table>
<ywtag:dialog/>

	<script type="text/javascript">

		/* 初始化列表基本属性 */
		(function(win){
			var gridData = {};

			// 默认的条件
			gridData.defaultParam = {
					'columnguid' : '${columnGuid}',
					'status':'${status}',
					'guid':'${guid}'
			};
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
				columnSize : 5
			});
		});
		
		
		// 显示--精华 --置顶 图片
		function getTitleUrl(val, rec) {
			var html = "";
			if (rec.istop == "1") {
				html += "<img src='${ctx}/deco/img/zhiding.png' border='0' style='cursor:pointer; vercial-align: middle;' title='置顶'/>&nbsp;";
			}
			if (rec.iscream == "1") {
				html += "<img src='${ctx}/deco/img/jinghua.png' border='0' style='cursor:pointer; vercial-align: middle;' title='精华'/>&nbsp;";
			}

			var value = html + "<span style='vercial-align: middle;'>" + (val || "") + "</span>";
			var clickFun = "openView('" + rec.guid + "');return false;";
			return "<a href='javascript:void(0);' onclick=\""
					+ clickFun + "\">" + value + "</a>";
		}
	</script>
</body>
</html>