<%@ tag pageEncoding="UTF-8" description="bar" body-content="empty"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- for循环生成菜单 -->
<c:forEach items="${menulist}" var="mm">
	<div class="menu-button">
		<c:if test="${mm.menuType=='linkbutton'}">
			<a href="#" class="easyui-linkbutton" id="${mm.id}"
				data-options="iconCls:'${mm.bclass}',plain:true"
				onclick="${mm.itemClick}return false;"
				style="height: 29px;">${mm.displayName}</a>
		</c:if>
		<c:if test="${mm.menuType=='menubutton'}">
			<a href="#" class="easyui-menubutton"
				data-options="iconCls:'${mm.bclass}',plain:true"
				menu="#${mm.subMenuId}"
				style="height: 29px;">${mm.displayName}</a>
		</c:if>
	</div>
	<div class="datagrid-btn-separator"></div>
</c:forEach>

<!-- for循环生成 子菜单 -->
<c:forEach items="${menulist}" var="mm">
	<c:if test="${mm.menuType=='menubutton'}">
		<div id="${mm.subMenuId}" style="width: 150px;">
			<c:forEach items="${mm.subMenuList}" var="sub">
				<c:if test="${sub.menuType=='subbutton'}">
					<div onclick="${sub.itemClick}">${sub.displayName}</div>
				</c:if>
				<c:if test="${sub.menuType=='menusep'}">
					<div class="menu-sep"></div>
				</c:if>
			</c:forEach>
		</div>
	</c:if>
</c:forEach>