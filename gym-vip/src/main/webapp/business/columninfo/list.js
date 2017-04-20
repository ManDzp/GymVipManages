//点击查看页链接
function getViewUrl(val, rec) {
	var value = (val || "");
	var clickFun = "openView('" + rec.guid + "');return false;";
	return "<a href='javascript:void(0);' onclick=\""
			+ clickFun + "\">" + value + "</a>";
}
//板块状态
function getStatusUrl(val, rec) {
	// 状态 0：在编，1：发布；
	var value = "";
	if (val == "0") {
		value = "在编";
	} else if (val == "1") {
		value = "发布";
	}

	return getViewUrl(value, rec);
}