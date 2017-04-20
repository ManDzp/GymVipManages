// 执行保存按钮
function saveAddUser() {
	var displayname = $('#fm').find("input[name=displayname]").val();
	var username = $('#fm').find("input[name=username]").val();
	var userpass = $('#fm').find("input[name=userpass]").val();
	var confirmpass = $('#fm').find("input[name=confirmpass]").val();

	if (displayname.length==0) {
		$.messager.alert('提示', "昵称未填写！");
		return false;
	}

	if (username.length==0) {
		$.messager.alert('提示', "登陆账号未填写！");
		return false;
	}

	if (confirmpass.length==0) {
		$.messager.alert('提示', "登陆密码未填写！");
		return false;
	}

	if (userpass.length==0) {
		$.messager.alert('提示', "确认密码未填写！");
		return false;
	}

	if (userpass != confirmpass) {
		$.messager.alert('提示', "两次密码输入不一致！");
		return false;
	}

	$.messager.confirm('确认', '确定保存信息吗？', function(r) {
		if (r) {
			$.post(glogbalData.contextPath + "/webindex/isSameName", {
				"userName" : $("#username").val()
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					$.messager.alert('错误', "登陆账号已存在！");
				} else {
					// 添加操作
					$('#fm').form('submit', {
						url : glogbalData.contextPath + '/webindex/register',
						contentType : 'application/json',
						type : "POST",
						success : function(data) {
							var ret = eval('(' + data + ')');
							if (ret.success) {
								$.messager.alert('提示', "注册成功，请登录！", "info", function() {
									window.location.href = glogbalData.contextPath + '/login';
								});
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
