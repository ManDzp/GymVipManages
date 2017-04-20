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
			<span class="searchbar-sw">状态：</span>
			<select id="infoFlag" name="infoFlag" class="easyui-combobox"
				data-options="onSelect: selectByInfoFlag"
				panelHeight="auto" editable="false" style="width: 60px;">
				<option value="">全部</option>
				<option value="0">发布</option>
				<option value="1">在编</option>
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