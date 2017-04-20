<!-- 便笺列表导航菜单栏 -->
<%@ tag pageEncoding="UTF-8" description="bar" body-content="empty"%>
<%@ taglib tagdir="/WEB-INF/tags/menubar" prefix="ywbar"%>
<div id="list_toolbar">
	<div class="searchbar-wrapper">
		<div class="searchbar-info">
			<span class="searchbar-icon">&nbsp;</span>
			<span class="searchbar-title">${listTitle}</span>
		</div>

		<!-- 选择框 -->
		<div class="searchbar-info">
			<span class="searchbar-sw">分类：</span>
			<select id="filesort" name="filesort" class="easyui-combobox"
				data-options="onSelect: searchBySort" editable="false"
				panelHeight="auto" style="width: 60px;">
				<option value="">全部</option>
				<option value="信函">信函</option>
				<option value="公文">公文</option>
				<option value="信息">信息</option>
				<option value="其他">其他</option>
			</select>
		</div>

		<!-- 检索框 -->
		<div class="div-searchbox">
			<ywbar:queryBtn/>
			<ywbar:advQueryBtn/>
		</div>
	</div>

	<div class="toolbar-wrapper">
		<!-- 功能按钮 -->
		<ywbar:menu />
	</div>
</div>