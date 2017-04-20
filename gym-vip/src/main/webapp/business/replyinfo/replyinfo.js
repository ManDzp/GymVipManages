//点击查看页链接
function getViewUrl(val, rec) {
	var value = (val || "");// 选取内容
	var clickFun = "openView('" + rec.guid + "');return false;";// 调用ONCLICK方法
	return "<a href='javascript:void(0);' title='" + value + "' onclick=\""
			+ clickFun + "\">" + value + "</a>";// 格式化内容
}

// 打开新建界面
function openAdd(status) {
	openIFrameDialog("add");
}

// 修改后，跳转查看页面
function changeToView(guid) {
	window.location.href = "view?guid=" + guid;
}

// 返回列表页面
function doBack() {
	closeIFrameDialog();
}

// 回复列表页返回到帖子查看页
function listBack(docguid) {
	window.location.href = "../document/view?guid=" + docguid;
}

// 修改页返回列查看面
function viewBack(guid) {
	openIFrameDialog("view?guid=" + guid);
}

// 打开查看页面
function openView(guid) {
	openIFrameDialog("view?guid=" + guid);
}

// 打开修改页面
function openEdit(guid) {
	window.location.href = "edit?guid=" + guid;
}

// 返回查看页
function backToView(guid) {
	changeIFrameDialog("view?guid=" + guid);
}


// 执行打开修改页后的保存按钮
function saveupdateType(guid) {
	if (!check(fm)) {
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
						$.messager.alert('错误', "修改回复信息失败！");
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
function deleteOne(guid) {
	$.messager.confirm('确认', '确定删除选中项吗？', function(r) {
		if (r) {
			$.post("deleteOne", {
				"guid" : guid,
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
//取出浏览量
function loadvisit() {
	var date = $('#datestr').val();

	$.post("loadvisit", {
		"selectdate" : date
	}, function(data) {
		if (data) {
			var html = "<table class='tablebgcolor' width='100%' cellSpacing='1'"
					+ " cellPadding='2' border='0'>";

			html += "<tr>";
			html += "<td class='tdbgcolor' style='width: 30%;'>浏览量：</td>";
			html += "<td class='tdbgcolor' style='width: 70%;'>" + (data.visitnum || "0") + "</td>";
			html += "</tr>";

			$("#result_div").html(html);
		} else {
			$("#result_div").html("<p class='p-title'>暂无相关记录！</p>");
		}
	});
}
//取出回复帖子数
function loadreplynum() {
	var date = $('#datestr').val();

	$.post("loadreplynum", {
		"selectdate" : date
	}, function(data) {
		if (data) {
			var html = "<table class='tablebgcolor' width='100%' cellSpacing='1'"
					+ " cellPadding='2' border='0'>";

			html += "<tr>";
			html += "<td class='tdbgcolor' style='width: 30%;'>回复帖子数：</td>";
			html += "<td class='tdbgcolor' style='width: 70%;'>" + (data.replynum || "0") + "</td>";
			html += "</tr>";

			$("#result_div").html(html);
		} else {
			$("#result_div").html("<p class='p-title'>暂无相关记录！</p>");
		}
	});
}
//取出发布帖子数
function somedaytongji() {
	var date = $('#datestr').val();

	$.post("somedaytongji", {
		"selectdate" : date
	}, function(data) {
		if (data) {
			var html = "<table class='tablebgcolor' width='100%' cellSpacing='1'"
					+ " cellPadding='2' border='0'>";

			html += "<tr>";
			html += "<td class='tdbgcolor' style='width: 30%;'>发布帖子数：</td>";
			html += "<td class='tdbgcolor' style='width: 70%;'>" + (data.somedaytongjinum || "0") + "</td>";
			html += "</tr>";

			$("#result_div").html(html);
		} else {
			$("#result_div").html("<p class='p-title'>暂无相关记录！</p>");
		}
	});
}
//取出删除帖子数
function deletetongji() {
	var date = $('#datestr').val();

	$.post("deletetongji", {
		"selectdate" : date
	}, function(data) {
		if (data) {
			var html = "<table class='tablebgcolor' width='100%' cellSpacing='1'"
					+ " cellPadding='2' border='0'>";

			html += "<tr>";
			html += "<td class='tdbgcolor' style='width: 30%;'>删除帖子数：</td>";
			html += "<td class='tdbgcolor' style='width: 70%;'>" + (data.deletetongjinum || "0") + "</td>";
			html += "</tr>";

			$("#result_div").html(html);
		} else {
			$("#result_div").html("<p class='p-title'>暂无相关记录！</p>");
		}
	});
}