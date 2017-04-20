
// 打开删除理由页
function viewReason(guid) {
	// 弹出窗口的url
	var openUrl = "deleteReason?guid=" + guid;
	// layer方式-打开
	layer.open({
		type : 2,
		title : '删除理由',
		shade : [ 0.6, '#F5F5F5' ],
		area : [ '400px', '200px' ],
		content : openUrl
	});
}


// 删除并添加删除理由
function deleteWithReason() {
	$('#fm').form('submit', {
		url : 'deleteWithReason',
		contentType : 'application/json',
		type : "POST",
		success : function(data) {
			var ret = eval('(' + data + ')');
			if (ret.success) {
				parent.parent.location.reload();
			} else {
				$.messager.alert('错误', "添加删除理由失败！");
			}
		}
	});
}

// 取消按钮
function cancelDelete() {
	parent.layer.closeAll();
}

//打开删除理由页
function viewListReason() {

	var rows = $('#grid').datagrid('getChecked');
	if (rows.length == 0) {
		$.messager.alert('提示', "没有选中项");
		return;
	}

	var guidArr = [];
	
	for(var i = 0; i < rows.length; i++) {
		guidArr.push(rows[i].guid);
	}

	// 弹出窗口的url
	var openUrl = "deleteListReason?guids=" + guidArr.join(';');
	// layer方式-打开
	layer.open({
		type : 2,
		title : '删除理由',
		shade : [ 0.6, '#F5F5F5' ],
		area : [ '400px', '200px' ],
		content : openUrl
	});
}
//删除并添加删除理由
function deleteListWithReason() {
	$('#fm').form('submit', {
		url : 'deleteListWithReason',
		contentType : 'application/json',
		type : "POST",
		success : function(data) {
			var ret = eval('(' + data + ')');
			if (ret.success) {
				parent.location.reload();
			} else {
				$.messager.alert('错误', "添加删除理由失败！");
			}
		}
	});
}