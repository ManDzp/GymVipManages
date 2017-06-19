// 返回列表页面
function doBack() {
	closeIFrameDialog();
}

// 打开新建界面
function openAdd(status) {
	openIFrameDialog("add");
}

// 打开查看页面
function openView(guid) {
	openIFrameDialog("view?guid=" + guid);
}

// 打开修改页面
function openEdit(guid) {
	window.location.href = "edit?guid=" + guid;
}

// 修改后，跳转查看页面
function changeToView(guid) {
	window.location.href = "view?guid=" + guid;
}

// 执行保存按钮
function saveAddInfo() {
	if (!check(fm)) {
		return;
	}

	layer.confirm('确定保存信息吗？', function(index) {
		// 添加操作
		$('#fm').form('submit', {
			url : 'insert',
			contentType : 'application/json',
			type : "POST",
			success : function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					changeToView(ret.message);
				} else {
					layer.alert('添加失败！');
				}
			}
		});

		layer.close(index);
	});
}

// 执行打开修改页后的保存按钮
function saveUpdateInfo(guid) {
	if (!check(fm)) {
		return;
	}

	layer.confirm('确定保存信息吗？', function(index) {
		// 修改操作
		$('#fm').form('submit', {
			url : 'update',
			contentType : 'application/json',
			type : "POST",
			success : function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					changeToView(guid);
				} else {
					layer.alert('修改信息失败！');
				}
			}
		});

		layer.close(index);
	});
}

// 列表批量删除
function deleteList() {
	var rows = $('#grid').datagrid('getChecked');
	if (rows.length == 0) {
		layer.alert('没有选中项！');
		return;
	}

	layer.confirm('确定将选中的账号删除吗？', function(index) {
		var jsonText = JSON.stringify(rows);
		$.ajax({
			type : "POST",
			url : "deletelist",
			data : jsonText,
			contentType : 'application/json',
			success : function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					$("#grid").datagrid("reload");
					$("#grid").datagrid("acceptChanges");
				} else {
					layer.alert('删除失败！');
				}
			}
		});

		layer.close(index);
	});
}

// 查看页删除
function deleteOne(guid) {
	layer.confirm('确定删除选中项吗？', function(index) {
		$.post("deleteOne", {
			"guid" : guid
		}, function(data) {
			var ret = eval('(' + data + ')');
			if (ret.success) {
				doBack();
			} else {
				layer.alert('删除失败！');
			}
		});

		layer.close(index);
	});
}
