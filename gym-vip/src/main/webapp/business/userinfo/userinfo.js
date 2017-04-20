//点击查看页链接
function getViewUrl(val, rec) {
	var value = (val || "");// 选取内容
	var clickFun = "openView('" + rec.userguid + "');return false;";// 调用ONCLICK方法
	return "<a href='javascript:void(0);' title='" + value + "' onclick=\""
			+ clickFun + "\">" + value + "</a>";// 格式化内容
}

// 打开新建界面
function openAdd(status) {
	openIFrameDialog("add");
}

// 修改后，跳转查看页面
function changeToView(userguid) {
	window.location.href = "view?userguid=" + userguid;
}

// 返回列表页面
function doBack() {
	closeIFrameDialog();
}

// 修改页返回列查看面
function viewBack(userguid) {
	openIFrameDialog("view?userguid=" + userguid);
}

// 打开查看页面
function openView(userguid) {
	openIFrameDialog("view?userguid=" + userguid);
}

// 打开修改页面
function openEdit(userguid) {
	window.location.href = "edit?userguid=" + userguid;
}

// 返回查看页
function backToView(userguid) {
	changeIFrameDialog("view?userguid=" + userguid);
}

// 执行保存按钮
function saveAddUser() {
	if (!check(fm)) {
		return;
	}

	var userpass = $('#fm').find("#userpass").val();
	var confirmpass = $('#fm').find("#confirmpass").val();

	if (userpass != confirmpass) {
		$.messager.alert('提示', "两次密码输入不一致！");
		return false;
	}

	$.messager.confirm('确认', '确定保存信息吗？', function(r) {
		if (r) {
			$.post("isSameName", {
				"userName" : $("#username").val()
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					$.messager.alert('错误', "登陆账号已存在！");
				} else {
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
	});
}

// 执行打开修改页后的保存按钮
function saveUpdateUser(userguid) {
	if (!check(fm)) {
		return;
	}
	//校验修改密码与确认密码
	var userpass = $('#fm').find("#userpass").val();
	var confirmpass = $('#fm').find("#confirmpass").val();

	if (userpass != confirmpass) {
		$.messager.alert('提示', "两次密码输入不一致！");
		return false;
	}
	
	$.messager.confirm('确认', '确定保存信息吗？', function(r) {
		if (r) {
			$.post("isSameName", {
				"userName" : $("#username").val(),
				"userGuid" : userguid
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					$.messager.alert('错误', "登陆账号已存在！");
				} else {
					// 添加操作
					$('#fm').form('submit', {
						url : 'update',
						contentType : 'application/json',
						type : "POST",
						success : function(data) {
							var ret = eval('(' + data + ')');
							if (ret.success) {
								changeToView(userguid);
							} else {
								$.messager.alert('错误', "修改版块信息失败！");
							}
						}
					});
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

	$.messager.confirm('确认', '确定将选中的账号删除吗？', function(r) {
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
function deleteOne(userguid) {
	$.messager.confirm('确认', '确定删除选中项吗？', function(r) {
		if (r) {
			$.post("deleteOne", {
				"userguid" : userguid,
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					doBack();
				} else {
					$.messager.alert('错误', "删除失败！");
				}
			});
		}
	});
}
