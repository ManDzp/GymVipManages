//点击查看页链接
function getViewUrl(val, rec) {
	var value = (val || "");
	return value;
}

// 日期格式化
function getDateUrl(val, rec) {
	var value = getSmpFormatDateByLong(val, "yyyy-MM-dd hh:mm:ss");
	return getViewUrl(value, rec);
}

// 金额格式化
function getMoneyUrl(val, rec) {
	var value = (val || "0");
	return getViewUrl(value, rec);
}

// 返回列表页面
function doBack() {
	closeIFrameDialog();
}
