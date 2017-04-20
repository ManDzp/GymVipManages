//页面加载完成后，处理左侧手风琴切换
$(function() {
	$(".easyui-accordion").accordion({
		onSelect : function(title, index) {
			var selectPanal = $(this).accordion('getSelected');
			var firstA = selectPanal.find("a:first");
			if (firstA) {
				firstA.click();
			}
		}
	});
})