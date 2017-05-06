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

// 打开查看页面
function openHistoryView(guid) {
	openIFrameDialog("historyview?guid=" + guid);
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
function saveAddMember() {
	if (!check(fm)) {
		return;
	}

	layer.confirm('确定保存信息吗？', function(index) {
		$.post("isExistCardNumber", {
			"cardNumber" : $("#cardnumber").val()
		}, function(data) {
			var ret = eval('(' + data + ')');
			if (ret.success) {
				layer.alert('会员卡号已存在！');
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
							layer.alert('添加失败！');
						}
					}
				});
			}
		});

		layer.close(index);
	});
}

// 执行打开修改页后的保存按钮
function saveUpdateMember(guid) {
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

// 打开充值页
function openCharge(guid) {
	var html = [
			'<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">',
			'<tbody>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>充值金额：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="str form-money" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>备注说明：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><textarea class="form-content" style="width: 100%;" rows="4" cols="10"></textarea></td>',
			'</tr>', '</tbody>', '</table>' ].join('');
	layer.open({
		type : 1,
		title : '充值',
		shade : [ 0.6, '#F5F5F5' ],
		area : [ '400px', '200px' ],
		btn : [ '充值', '关闭' ],
		content : html,
		success : function(layero, index) {
			layero.find('.form-money').focus();
		},
		yes : function(index, layero) {
			var money = layero.find('.form-money').val();
			var content = layero.find('.form-content').val();

			if (money === "") {
				layer.alert('充值金额必填！');
				return;
			} else if (!money.isPlus()) {
				layer.alert('充值金额格式不正确！');
				return;
			}
			if (content === "") {
				layer.alert('备注说明必填！');
				return;
			}

			$.post("charge", {
				"guid" : guid,
				"money" : money,
				"content" : content
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					layer.alert(ret.message);
				}
			});
		}
	});
}

// 打开买卡页
function openBuyCard(guid) {
	var html = [
			'<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">',
			'<tbody>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>消费金额：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="str form-money" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>备注说明：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><textarea class="form-content" style="width: 100%;" rows="4" cols="10"></textarea></td>',
			'</tr>', '</tbody>', '</table>' ].join('');
	layer.open({
		type : 1,
		title : '买卡',
		shade : [ 0.6, '#F5F5F5' ],
		area : [ '400px', '200px' ],
		btn : [ '买卡', '关闭' ],
		content : html,
		success : function(layero, index) {
			layero.find('.form-money').focus();
		},
		yes : function(index, layero) {
			var money = layero.find('.form-money').val();
			var content = layero.find('.form-content').val();

			if (money === "") {
				layer.alert('消费金额必填！');
				return;
			} else if (!money.isPlus()) {
				layer.alert('消费金额格式不正确！');
				return;
			}
			if (content === "") {
				layer.alert('备注说明必填！');
				return;
			}

			$.post("buyCard", {
				"guid" : guid,
				"money" : money,
				"content" : content
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					layer.alert(ret.message);
				}
			});
		}
	});
}

// 打开开卡页
function openActiveCard(guid, nowExpireTime) {
	var html = [
			'<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">',
			'<tbody>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>开卡时间：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="Wdate form-activetime"',
			' onclick="WdatePicker({ dateFmt: \'yyyy-MM-dd HH:mm:ss\' })" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>到期日期：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="Wdate form-expiretime"',
			' onclick="WdatePicker({ dateFmt: \'yyyy-MM-dd\', minDate: \''
					+ nowExpireTime + '\' })" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>备注说明：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><textarea class="form-content" style="width: 100%;" rows="4" cols="10"></textarea></td>',
			'</tr>', '</tbody>', '</table>' ].join('');
	layer.open({
		type : 1,
		title : '开卡',
		shade : [ 0.6, '#F5F5F5' ],
		area : [ '400px', '250px' ],
		btn : [ '开卡', '关闭' ],
		content : html,
		success : function(layero, index) {
		},
		yes : function(index, layero) {
			var activetime = layero.find('.form-activetime').val();
			var expiretime = layero.find('.form-expiretime').val();
			var content = layero.find('.form-content').val();

			if (activetime === "") {
				layer.alert('开卡时间必填！');
				return;
			} else if (!activetime.isDateTime()) {
				layer.alert('开卡时间格式不正确！');
				return;
			}
			if (expiretime === "") {
				layer.alert('到期日期必填！');
				return;
			} else if (!expiretime.isDate()) {
				layer.alert('到期日期格式不正确！');
				return;
			}
			if (content === "") {
				layer.alert('备注说明必填！');
				return;
			}

			$.post("activeCard", {
				"guid" : guid,
				"activetime" : activetime,
				"expiretime" : expiretime,
				"content" : content
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					layer.alert(ret.message);
				}
			});
		}
	});
}

// 打开续卡页
function openContinueCard(guid, nowExpireTime) {
	var html = [
			'<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">',
			'<tbody>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>消费金额：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="str form-money" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>到期日期：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="Wdate form-expiretime"',
			' onclick="WdatePicker({ dateFmt: \'yyyy-MM-dd\', minDate: \''
					+ nowExpireTime + '\' })" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>备注说明：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><textarea class="form-content" style="width: 100%;" rows="4" cols="10"></textarea></td>',
			'</tr>', '</tbody>', '</table>' ].join('');
	layer.open({
		type : 1,
		title : '续卡',
		shade : [ 0.6, '#F5F5F5' ],
		area : [ '400px', '250px' ],
		btn : [ '续卡', '关闭' ],
		content : html,
		success : function(layero, index) {
			layero.find('.form-money').focus();
		},
		yes : function(index, layero) {
			var money = layero.find('.form-money').val();
			var expiretime = layero.find('.form-expiretime').val();
			var content = layero.find('.form-content').val();

			if (money === "") {
				layer.alert('消费金额必填！');
				return;
			} else if (!money.isPlus()) {
				layer.alert('消费金额格式不正确！');
				return;
			}
			if (expiretime === "") {
				layer.alert('到期日期必填！');
				return;
			} else if (!expiretime.isDate()) {
				layer.alert('到期日期格式不正确！');
				return;
			}
			if (content === "") {
				layer.alert('备注说明必填！');
				return;
			}

			$.post("continueCard", {
				"guid" : guid,
				"money" : money,
				"expiretime" : expiretime,
				"content" : content
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					layer.alert(ret.message);
				}
			});
		}
	});
}

// 查看页签到
function signRecord(guid) {
	layer.confirm('确定签到吗？', function(index) {
		$.post("signRecord", {
			"guid" : guid
		}, function(data) {
			var ret = eval('(' + data + ')');
			if (ret.success) {
				layer.alert('签到成功！', function() {
					window.location.reload();
				});
			} else {
				layer.alert(ret.message);
			}
		});

		layer.close(index);
	});
}

// 查看页请假
function leaveApply(guid) {
	var html = [
			'<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">',
			'<tbody>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>备注说明：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><textarea class="form-content" style="width: 100%;" rows="4" cols="10"></textarea></td>',
			'</tr>', '</tbody>', '</table>' ].join('');
	layer.open({
		type : 1,
		title : '请假',
		shade : [ 0.6, '#F5F5F5' ],
		area : [ '400px', '200px' ],
		btn : [ '请假', '关闭' ],
		content : html,
		success : function(layero, index) {
			layero.find('.form-content').focus();
		},
		yes : function(index, layero) {
			var content = layero.find('.form-content').val();

			if (content === "") {
				layer.alert('备注说明必填！');
				return;
			}

			$.post("leaveApply", {
				"guid" : guid,
				"content" : content
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					layer.alert(ret.message);
				}
			});
		}
	});
}

// 查看页销假
function leaveBack(guid, nowExpireTime) {
	var html = [
			'<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">',
			'<tbody>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>到期日期：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="Wdate form-expiretime"',
			' onclick="WdatePicker({ dateFmt: \'yyyy-MM-dd\', minDate: \''
					+ nowExpireTime + '\' })" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>备注说明：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><textarea class="form-content" style="width: 100%;" rows="4" cols="10"></textarea></td>',
			'</tr>', '</tbody>', '</table>' ].join('');
	layer.open({
		type : 1,
		title : '销假',
		shade : [ 0.6, '#F5F5F5' ],
		area : [ '400px', '200px' ],
		btn : [ '销假', '关闭' ],
		content : html,
		success : function(layero, index) {
			layero.find('.form-expiretime').focus();
		},
		yes : function(index, layero) {
			var expiretime = layero.find('.form-expiretime').val();
			var content = layero.find('.form-content').val();

			if (expiretime === "") {
				layer.alert('到期日期必填！');
				return;
			} else if (!expiretime.isDate()) {
				layer.alert('到期日期格式不正确！');
				return;
			}
			if (content === "") {
				layer.alert('备注说明必填！');
				return;
			}

			$.post("leaveBack", {
				"guid" : guid,
				"expiretime" : expiretime,
				"content" : content
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					layer.alert(ret.message);
				}
			});
		}
	});
}

// 打开购买次数页
function openBuyCardNumber(guid, nowExpireTime) {
	var html = [
			'<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">',
			'<tbody>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>消费金额：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="str form-money" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>购买次数：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="str form-times" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>到期日期：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="Wdate form-expiretime"',
			' onclick="WdatePicker({ dateFmt: \'yyyy-MM-dd\', minDate: \''
					+ nowExpireTime + '\' })" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>备注说明：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><textarea class="form-content" style="width: 100%;" rows="4" cols="10"></textarea></td>',
			'</tr>', '</tbody>', '</table>' ].join('');
	layer.open({
		type : 1,
		title : '购买次数',
		shade : [ 0.6, '#F5F5F5' ],
		area : [ '400px', '270px' ],
		btn : [ '购买', '关闭' ],
		content : html,
		success : function(layero, index) {
			layero.find('.form-money').focus();
		},
		yes : function(index, layero) {
			var money = layero.find('.form-money').val();
			var times = layero.find('.form-times').val();
			var expiretime = layero.find('.form-expiretime').val();
			var content = layero.find('.form-content').val();

			if (money === "") {
				layer.alert('消费金额必填！');
				return;
			} else if (!money.isPlus()) {
				layer.alert('消费金额格式不正确！');
				return;
			}
			if (times === "") {
				layer.alert('购买次数必填！');
				return;
			} else if (!times.isPlusInt()) {
				layer.alert('购买次数格式不正确！');
				return;
			}
			if (expiretime === "") {
				layer.alert('到期日期必填！');
				return;
			} else if (!expiretime.isDate()) {
				layer.alert('到期日期格式不正确！');
				return;
			}
			if (content === "") {
				layer.alert('备注说明必填！');
				return;
			}

			$.post("buyCardNumber", {
				"guid" : guid,
				"money" : money,
				"times" : times,
				"expiretime" : expiretime,
				"content" : content
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					layer.alert(ret.message);
				}
			});
		}
	});
}

// 查看页次卡签到
function numberSignRecord(guid) {
	layer.confirm('确定签到吗？', function(index) {
		$.post("numberSignRecord", {
			"guid" : guid
		}, function(data) {
			var ret = eval('(' + data + ')');
			if (ret.success) {
				layer.alert('签到成功！', function() {
					window.location.reload();
				});
			} else {
				layer.alert(ret.message);
			}
		});

		layer.close(index);
	});
}

// 打开获取积分页
function openSaveCardPoints(guid) {
	var html = [
			'<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">',
			'<tbody>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>积　　分：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="str form-points" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>备注说明：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><textarea class="form-content" style="width: 100%;" rows="4" cols="10"></textarea></td>',
			'</tr>', '</tbody>', '</table>' ].join('');
	layer.open({
		type : 1,
		title : '获取积分',
		shade : [ 0.6, '#F5F5F5' ],
		area : [ '400px', '200px' ],
		btn : [ '保存', '关闭' ],
		content : html,
		success : function(layero, index) {
			layero.find('.form-points').focus();
		},
		yes : function(index, layero) {
			var points = layero.find('.form-points').val();
			var content = layero.find('.form-content').val();

			if (points === "") {
				layer.alert('积分必填！');
				return;
			} else if (!points.isPlus()) {
				layer.alert('积分格式不正确！');
				return;
			}
			if (content === "") {
				layer.alert('备注说明必填！');
				return;
			}

			$.post("saveCardPoints", {
				"guid" : guid,
				"points" : points,
				"content" : content
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					layer.alert(ret.message);
				}
			});
		}
	});
}

// 打开积分兑换时间页
function openPointsExchangeTime(guid, nowExpireTime) {
	var html = [
			'<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">',
			'<tbody>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>积　　分：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="str form-points" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>到期日期：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><input class="Wdate form-expiretime"',
			' onclick="WdatePicker({ dateFmt: \'yyyy-MM-dd\', minDate: \''
					+ nowExpireTime + '\' })" /></td>',
			'</tr>',
			'<tr>',
			'<td class="lefttdbgcolor" style="width: 30%;"><font color="#ff0000">*</font>备注说明：</td>',
			'<td class="tdbgcolor" style="width: 70%;"><textarea class="form-content" style="width: 100%;" rows="4" cols="10"></textarea></td>',
			'</tr>', '</tbody>', '</table>' ].join('');
	layer.open({
		type : 1,
		title : '积分兑换时间',
		shade : [ 0.6, '#F5F5F5' ],
		area : [ '400px', '250px' ],
		btn : [ '保存', '关闭' ],
		content : html,
		success : function(layero, index) {
			layero.find('.form-points').focus();
		},
		yes : function(index, layero) {
			var points = layero.find('.form-points').val();
			var expiretime = layero.find('.form-expiretime').val();
			var content = layero.find('.form-content').val();

			if (points === "") {
				layer.alert('积分必填！');
				return;
			} else if (!points.isPlus()) {
				layer.alert('积分格式不正确！');
				return;
			}
			if (expiretime === "") {
				layer.alert('到期日期必填！');
				return;
			} else if (!expiretime.isDate()) {
				layer.alert('到期日期格式不正确！');
				return;
			}
			if (content === "") {
				layer.alert('备注说明必填！');
				return;
			}

			$.post("pointsExchangeTime", {
				"guid" : guid,
				"points" : points,
				"expiretime" : expiretime,
				"content" : content
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					window.location.reload();
				} else {
					layer.alert(ret.message);
				}
			});
		}
	});
}
