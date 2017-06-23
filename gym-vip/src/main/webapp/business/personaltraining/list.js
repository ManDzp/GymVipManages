//点击查看页链接
function getViewUrl(val, rec) {
	var value = (val || "");
	var clickFun = "openView('" + rec.personalTrainingId + "');return false;";
	return "<a href='javascript:void(0);' onclick=\"" + clickFun + "\">"
			+ value + "</a>";
}

//点击查看页链接
function getStatusUrl(val, rec) {
	var value = "未知";
	switch (val) {
	case '0':
		value = "启用";
		break;
	case '1':
		value = "停用";
		break;
	default:
		value = "未知";
		break;
	}

	return getViewUrl(value, rec);
}
