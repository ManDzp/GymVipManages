/**
 * 
 */
//点击查看页链接
function getViewUrl(val, rec) {
	var value = (val || "");
	var clickFun = "openView('" + rec.guid + "');return false;";
	return "<a href='javascript:void(0);' onclick=\""
			+ clickFun + "\">" + value + "</a>";
}
//点击查看页链接
function getViewJudgeUrl(val, rec) {
	var value = (val || "");//选取内容	
	if(val=="0")
	{
		value="否";
	}
	else
	{
		value="是";
	}
	var clickFun = "openView('" + rec.guid + "');return false;";//调用ONCLICK方法
	return "<a href='javascript:void(0);' title='" + value + "' onclick=\""
			+ clickFun + "\">" + value + "</a>";//格式化内容
}
//日期格式化
function getDateUrl(val, rec) {
	var value = getSmpFormatDateByLong(val, "yyyy-MM-dd hh:mm:ss");

	return getViewUrl(value, rec);
}
