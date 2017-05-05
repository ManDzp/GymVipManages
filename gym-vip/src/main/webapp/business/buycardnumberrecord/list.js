// 点击查看页链接
function getViewUrl(val, rec) {
	var value = (val || "");
	var clickFun = "openView('" + rec.guid + "');return false;";
	return "<a href='javascript:void(0);' onclick=\"" + clickFun + "\">"
			+ value + "</a>";
}

// 日期格式化
function getDateUrl(val, rec) {
	var value = getSmpFormatDateByLong(val, "yyyy-MM-dd hh:mm:ss");
	return getViewUrl(value, rec);
}

//日期格式化
function getShortDateUrl(val, rec) {
	var value = getSmpFormatDateByLong(val, "yyyy-MM-dd");
	return getViewUrl(value, rec);
}

// 金额格式化
function getMoneyUrl(val, rec) {
	var value = (val || "0");
	return getViewUrl(value, rec);
}

// 数值格式化
function getIntegerUrl(val, rec) {
	var value = (val || "0");
	return getViewUrl(value, rec);
}
