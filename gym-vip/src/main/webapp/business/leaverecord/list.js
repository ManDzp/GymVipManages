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

// 请销假类型格式化
function getLeaveTypeUrl(val, rec) {
	var value = "";
	switch (val) {
	case "0":
		value = "请假";
		break;
	case "1":
		value = "销假";
		break;
	}

	return getViewUrl(value, rec);
}
