<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查询会员资料</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/datagrid/grid.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/business/member/searchlist.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/business/member/member.js${res_v}"></script>
</head>
<body>
	<!-- 菜单栏 -->
	<div id="list_toolbar">
		<div class="searchbar-wrapper">
			<div class="searchbar-info">
				<span class="searchbar-icon">&nbsp;</span>
				<span class="searchbar-title">${listTitle}</span>
			</div>
		</div>
		
		<div id="searcht" style="width: 100%; background-color: #CCDDFF;">
			<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
				<tbody>
					<tr>
						<td class="lefttdbgcolor">会员卡号：</td>
						<td class="tdbgcolor20"><input id="cardnumber" name="cardnumber" class="str" /></td>
						<td class="lefttdbgcolor">会员名称：</td>
						<td class="tdbgcolor20"><input id="fullname" name="fullname" class="str" /></td>
						<td class="lefttdbgcolor">手 机 号：</td>
						<td class="tdbgcolor20"><input id="mobile" name="mobile" class="str" /></td>
					</tr>
	                <tr>
	                    <td class="tdbgcolor" align="center" colspan="6">
	                        <img src="${ctx}/deco/img/search.jpg" alt="点击搜索" style="cursor: pointer;" onclick="toSearch();" />
	                    </td>
	                </tr>
				</tbody>
            </table>
        </div>
	</div>

	<!-- 待办列表页 -->
	<table id="grid" style="width: getWidth(1); height: 100%">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'cardnumber',align:'left',formatter:getViewUrl,sortable:true,order:'desc'"
					width="20">会员卡号</th>
				<th data-options="field:'cardtype',align:'left',formatter:getCardTypeUrl,sortable:true,order:'desc'"
					width="20">会员类型</th>
				<th data-options="field:'fullname',align:'left',formatter:getViewUrl,sortable:true,order:'desc'"
					width="20">会员名称</th>
				<th data-options="field:'mobile',align:'left',formatter:getViewUrl,sortable:true,order:'desc'"
					width="20">手机号</th>
				<th data-options="field:'status',align:'left',formatter:getStatusUrl,sortable:true,order:'desc'"
					width="10">状态</th>
				<th data-options="field:'deletetype',align:'left',formatter:getDeleteTypeUrl,sortable:true,order:'desc'"
					width="10">状态</th>
			</tr>		
		</thead>
	</table>

	<ywtag:dialog/>

	<script type="text/javascript">

		//获取当前列表的状态
		function getListStatus() {
			return '${status}';
		}

		//点击查询
        function toSearch() {
			var cardnumber = $('#cardnumber').val();
			var fullname = $('#fullname').val();
			var mobile = $('#mobile').val();

			ywGrid.reloadGrid({
				"cardnumber" : cardnumber,
				"fullname" : fullname,
				"mobile" : mobile
			});
        }
		//列表
        (function(win){
			var gridData = {};

			// 默认的条件
			gridData.defaultParam = {
				'status' : '1'
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
				columnSize : 7
			});
		});
	</script>

</body>
</html>