<%@ tag pageEncoding="UTF-8" description="首页显示网址" body-content="empty"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 首页显示网址 -->
<iframe src="<c:url value='${centerIFrame}'/>" id="iframepage"
	frameborder="0" scrolling="no" marginheight="0" marginwidth="0"
	style="width: 100%;"></iframe>

<script>
	// 高度自适应
	function iframeResize() {
		var $iframepage = $("#iframepage");
		var $parent = $iframepage.parent();

		$iframepage.height($parent.height());
	}

	// 页面初始化完成后执行
	$(function() {
		iframeResize();
	});

	// 中心内容区域，随窗口大小改变，高度自适应
	var resizeTimer = null;
	$(window).resize(function () {
		if (resizeTimer) {
			clearTimeout(resizeTimer);
		}

		resizeTimer = setTimeout("iframeResize()", 250);
	});
</script>