// 记录论坛浏览量
$(function() {
	$.post(glogbalData.contextPath + "/webindex/visit", {
		"objectguid" : $("#objectGuid").val(),
		"objecttype" : $("#objectType").val()
	}, function(data) {
	});
});