/**
 * 帖子管理
 */
// 返回列表页面
function doBack() {
	closeIFrameDialog();
}

// 查看页面，返回列表页面
function viewBack() {
	closeIFrameDialog();
}
// 打开新建界面
function openAdd() {
	openIFrameDialog("add");
}

// 打开查看页面
function openView(guid) {
	openIFrameDialog("view?guid=" + guid);
}

// 跳转到查看页面
function changeToTab(guid) {
	openIFrameDialog("view?guid=" + guid);
}

// 修改后，跳转查看页面
function changeToView(guid) {
	window.location.href = "view?guid=" + guid;
}

// 跳转到回复页
function changeToReply(guid) {
	window.location.href = "../replyinfo/reply_list?docguid=" + guid;
}

// 打开修改页面
function openEdit(guid) {
	window.location.href = "edit?guid=" + guid;
}

// 检验
function checkForm() {
	return check(fm);
}

// 执行保存按钮
function addDocumentInfo() {
	if (!checkForm()) {
		return;
	}
	$.messager.confirm('确认', '确定保存信息吗？', function(r) {
		if (r) {
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
						$.messager.alert('错误', "添加失败！");
					}
				}
			});
		}
	});
}
// 执行保存按钮
function editDocumentInfo(guid) {
	if (!checkForm()) {
		return;
	}
	$.messager.confirm('确认', '确定保存信息吗？', function(r) {
		if (r) {
			$('#fm').form('submit', {
				url : 'update',
				contentType : 'application/json',
				type : "POST",
				success : function(data) {
					var ret = eval('(' + data + ')');
					if (ret.success) {
						changeToView(guid);
					} else {
						$.messager.alert('错误', "修改帖子信息失败！");
					}
				}
			});
		}
	});
}

// 列表批量删除
function deleteList() {
	var rows = $('#grid').datagrid('getChecked');
	if (rows.length == 0) {
		$.messager.alert('提示', "没有选中项");
		return;
	}

	$.messager.confirm('确认', '确定将选中项删除吗？', function(r) {
		if (r) {
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
						$.messager.alert('错误', "删除失败！");
					}
				}
			});
		}
	});
}

// 查看页删除
function deleteOne(guid) {
	$.messager.confirm('确认', '确定删除选中项吗？', function(r) {
		if (r) {
			$.post("delete", {
				"guid" : guid
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					viewBack();
				} else {
					$.messager.alert('错误', "删除失败！");
				}
			});
		}
	});
}

// 置顶操作
function updateIsTop(guid) {
	$.messager.confirm('确认', '确定置顶操作吗？', function(r) {
		if (r) {
			$.post("updateistop", {
				"guid" : guid,
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					$.messager.alert('错误', "置顶失败！");
				}
			});
		}
	});
}

// 取消置顶
function updateNotIsTop(guid) {
	$.messager.confirm('确认', '确定取消置顶操作吗？', function(r) {
		if (r) {
			$.post("updatenotistop", {
				"guid" : guid,
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					$.messager.alert('错误', "取消置顶失败！");
				}
			});
		}
	});
}
// 精华
function updateIsCream(guid) {
	$.messager.confirm('确认', '确定精华操作吗？', function(r) {
		if (r) {
			$.post("updateiscream", {
				"guid" : guid,
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					$.messager.alert('错误', "精华操作失败！");
				}
			});
		}
	});
}

// 取消精华操作
function updateNotIsCream(guid) {
	$.messager.confirm('确认', '确定取消精华操作吗？', function(r) {
		if (r) {
			$.post("updatenotiscream", {
				"guid" : guid,
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					$.messager.alert('错误', "取消精华失败！");
				}
			});
		}
	});
}
