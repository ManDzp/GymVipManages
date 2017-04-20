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
			<span class="sw">分类：</span> 
			<select id="ddlStatus" name="ddlStatus" style="width: 70px;">
				<option value="1">显示</option>
				<option value="0">不显示</option>
			</select>
		</div>

		<!-- 检索框 -->
		<div class="div-searchbox">
			<ywbar:queryBtn/>
		</div>
	</div>

	<div class="toolbar-wrapper">
		<ywbar:menu />
	</div>
</div>