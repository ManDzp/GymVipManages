<!-- 决策督查列表导航菜单栏 -->
<%@ tag pageEncoding="UTF-8" description="bar" body-content="empty"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/menubar" prefix="ywbar"%>
<div id="list_toolbar">
	<div class="searchbar-wrapper">
		<div class="searchbar-info">
			<span class="searchbar-icon">&nbsp;</span>
			<span class="searchbar-title">${listTitle}</span>
		</div>
		<!-- 选择框 -->
		<div class="searchbar-info">
			
					<span class="sw">分类：</span>
					<select id="status" name="status" class="easyui-combobox"
						data-options="onSelect: searchByStatus" editable="false"
						panelHeight="auto" style="width: 60px;">
						<option value="">全部</option>
						<option value="0">待办</option>
						<option value="1">在办</option>
						<option value="2">已办</option>
						<option value="3">归档</option>
					</select>
				
		</div>
		
	</div>
	<div class="toolbar-wrapper">
		<!-- 功能按钮 -->
		<ywbar:menu />
	</div>
</div>
<script type="text/javascript">	
	//初始化
	$(function() {
		$('#status').combobox('setValue', '${status}');
	});
</script>