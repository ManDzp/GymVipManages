<!-- 导航栏-->
<%@ tag pageEncoding="UTF-8" description="bar" body-content="empty"%>
<%@ taglib tagdir="/WEB-INF/tags/menubar" prefix="ywbar"%>


<style>
.searchbar-wrapper{
	background-color: rgb(246, 246, 246);
    color: #0C66BC;
}
</style> 

<div id="list_toolbar">
	<div class="searchbar-wrapper">
		<div class="searchbar-info">
			<span class="searchbar-icon">&nbsp;</span>
			<span class="searchbar-title">${listTitle}</span>
		</div>
	</div>
</div>