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

// 积分类型格式化
function getPointsTypeUrl(val, rec) {
	var value = "";
	switch (val) {
	case "0":
		value = "获取积分";
		break;
	case "1":
		value = "兑换积分";
		break;
	}

	return getViewUrl(value, rec);
}

// 金额格式化
function getPointsUrl(val, rec) {
	var value = (val || "0");
	return getViewUrl(value, rec);
}
