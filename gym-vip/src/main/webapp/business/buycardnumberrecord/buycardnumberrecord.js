// 返回列表页面
function doBack() {
	closeIFrameDialog();
}

// 打开查看页面
function openView(guid) {
	openIFrameDialog("view?guid=" + guid);
}
