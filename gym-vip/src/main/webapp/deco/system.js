//点击左侧菜单
function menuClick(url, frameId) {
	document.getElementById(frameId).src = encodeURI(url);
}

// 点击左侧菜单，右侧弹出窗口
function menuClickDialog(url, frameId) {
	document.getElementById(frameId).contentWindow
			.openIFrameDialog(encodeURI(url));
}

// 点击菜单整页刷新
function contactClick(url) {
	window.location.href = url;
}

function menuItemClick(url, target) {
	window.open(url, target);
}

// 点击菜单整页刷新
function moduleMenuClick(menuGuid, baseUrl) {
	$.post(baseUrl + "/menu/getMenuInfo", {
		"menuGuid" : menuGuid,
		"baseUrl" : baseUrl
	}, function(data) {
		var ret = eval('(' + data + ')');
		if (ret.success) {
			var menuInfo = ret.message.split(';');
			// window.location.href = menuInfo[0];
			menuItemClick(menuInfo[0], menuInfo[1]);
		} else {
			$.messager.alert('错误', "打开网页失败！");
		}
	});
}

// 屏幕列宽
function getWidth(percent) {
	return document.body.clientWidth * percent;
}

// 父层打开iframe窗体
function openIFrameDialog(url) {
	$("#dialog_iframe_grid").attr("src", url).show();
}

// 子层修改iframe窗体
function changeIFrameDialog(url) {
	changeIFrameUrl("#dialog_iframe_grid", url);
}

// 子层关闭iframe窗体
function closeIFrameDialog() {
	parent.$("#grid").datagrid('reload');
	parent.$("#dialog_iframe_grid").attr("src", "about:blank").hide();
}

// 子层修改tab窗体
function changeTabDialog(tabId, url) {
	changeIFrameUrl("#" + tabId, url);
}

// 更换当前所在iframe的url
function changeIFrameUrl(iframeId, url) {
	var $iframe = parent.$(iframeId);

	if ($iframe.length > 0) {
		$iframe.attr("src", url).show();
	} else {
		window.location.href = url;
	}
}

// 生成随机数
function getRandomNum() {
	var range = 10000;
	var rand = Math.random();
	return Math.round(range * rand);
}

// 查看页下载附件
function viewDownloadFile(url) {
	if (url == undefined || url == null || url == "") {
		return;
	}

	if (url.indexOf("?") > -1) {
		url += "&" + getRandomNum();
	} else {
		url += "?" + getRandomNum();
	}

	window.open(url);
}