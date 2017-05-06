//点击查看页链接
function getViewUrl(val, rec) {
	var value = (val || "");
	var clickFun = "openHistoryView('" + rec.guid + "');return false;";
	return "<a href='javascript:void(0);' onclick=\"" + clickFun + "\">"
			+ value + "</a>";
}

// 点击查看页链接
function getStatusUrl(val, rec) {
	var value = "未知";
	switch (val) {
	case 0:
		value = "初始";
		break;
	case 1:
		value = "待开卡";
		break;
	case 2:
		value = "正常";
		break;
	case 3:
		value = "请假";
		break;
	case 4:
		value = "到期";
		break;
	default:
		value = "未知";
		break;
	}

	return getViewUrl(value, rec);
}

