/**
 * 
 */
// 打开查看页面
function openView(guid) {
	openIFrameDialog("view?guid=" + guid);
}

//打开新建界面
function openAdd(columnguid) {
	window.open("postdocument?column=" + columnguid);
}

//帖子信息
function addDocument() {
	var title = $('#fm').find("input[name=title]").val();
	var content = editor.getContent();

	
	if (title.length==0) {
		$.messager.alert('提示', "标题未填写！");
		return false;
	}
	if (content==''||content==null) {
		$.messager.alert('提示', "帖子内容为空！");
		return false;
	}
	$('#fm').form('submit', {
		url : 'insert',
		contentType : 'application/json',
		type : "POST",
		success : function(data) {
			var ret = eval('(' + data + ')');
			if (ret.success) {
				window.location.href = "../webreply/replyview?guid=" + ret.message;
			} else {
				$.messager.alert('错误', "添加失败！");
			}
		}
	});
}