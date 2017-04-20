<!-- 待办列表导航菜单栏 -->
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
			<span class="searchbar-sw">分类：</span>
			<select id="typename" name="typename" class="easyui-combobox"
				data-options="onSelect: searchByType" editable="false"
				panelHeight="auto" style="width: 70px;">
				<option value="">全部</option>
				<c:forEach items="${todealTypeList}" var="todealType">
					<option value="${todealType}">${todealType}</option>
				</c:forEach>
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