// 执行保存按钮
function saveAddReply() {
	var content = editor.getContent();

	if (content == null || content == "") {
		$.messager.alert('错误', "请添加回复内容！");
		return false;
	}

	// 添加操作
	$('#fm').form('submit', {
		url : 'insert',
		contentType : 'application/json',
		type : "POST",
		success : function(data) {
			var ret = eval('(' + data + ')');
			if (ret.success) {
				window.location.reload();
			} else {
				$.messager.alert('错误', "添加失败！");
			}
		}
	});
}