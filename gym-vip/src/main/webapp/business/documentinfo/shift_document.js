/**
 * 
 */
function sendDocument() {
	$.messager.confirm('确认', '确定转移板块吗？', function(r) {
		if (r) {
			$('#fm').form('submit', {
				url : 'send',
				contentType : 'application/json',
				type : "POST",
				success : function(data) {
					var ret = eval('(' + data + ')');
					if (ret.success) {// 返回成功
						parent.location.reload();
					} else {
						$.messager.alert('提示', "转移失败！");
					}

				}
			});
		}
	});
}

// 取消按钮
function cancelSendFlow() {
	parent.layer.closeAll();
}
//查看
function viewcolumn(guid) {
	// 弹出窗口的url
	var openUrl = "shiftdocumentinfo?guid=" + guid;
	// layer方式-打开
	layer.open({
		type : 2,
		title : '板块列表',
		shade : [ 0.6, '#F5F5F5' ],
		area : [ '400px', '200px' ],
		content : openUrl
	});
}