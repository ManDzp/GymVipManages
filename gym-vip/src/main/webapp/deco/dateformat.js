//Long型转yyyy-MM-dd hh:mm:ss
function dateFormatShow(val, row, index) {
	if (val == null) {
		return "";
	}
	return getSmpFormatDateByLong(val, "yyyy-MM-dd hh:mm");
}

//Long型转yyyy-MM-dd hh:mm:ss
function dateFormatShow1(val, row, index) {
	if (val == null) {
		return "";
	}
	return getSmpFormatDateByLong(val, "yyyy/MM/dd hh:mm:ss");
}
// Long型转yyyy-MM-dd
function dateFormatShortShow(val, row, index) {
	if (val == null) {
		return "";
	}
	return getSmpFormatDateByLong(val, "yyyy-MM-dd");
}

// 扩展Date的format方法
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	}
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
/**
 * 转换日期对象为日期字符串
 * 
 * @param date
 *            日期对象
 * @param isFull
 *            是否为完整的日期数据, 为true时, 格式如"2000-03-05 01:05:04" 为false时, 格式如
 *            "2000-03-05"
 * @return 符合要求的日期字符串
 */
function getSmpFormatDate(date, isFull) {
	var pattern = "";
	if (isFull == true || isFull == undefined) {
		pattern = "yyyy-MM-dd hh:mm:ss";
	} else {
		pattern = "yyyy-MM-dd hh:mm";
	}
	return getFormatDate(date, pattern);
}
/**
 * 转换当前日期对象为日期字符串
 * 
 * @param date
 *            日期对象
 * @param isFull
 *            是否为完整的日期数据, 为true时, 格式如"2000-03-05 01:05:04" 为false时, 格式如
 *            "2000-03-05"
 * @return 符合要求的日期字符串
 */

function getSmpFormatNowDate(isFull) {
	return getSmpFormatDate(new Date(), isFull);
}
/**
 * 转换long值为日期字符串
 * 
 * @param l
 *            long值
 * @param pattern
 *            时间格式
 * @return 符合要求的日期字符串
 */

function getSmpFormatDateByLong(l, pattern) {
	if (l == null) {
		return "";
	}
	return getFormatDate(new Date(l), pattern);
}
/**
 * 转换long值为日期字符串
 * 
 * @param l
 *            long值
 * @param pattern
 *            格式字符串,例如：yyyy-MM-dd hh:mm:ss
 * @return 符合要求的日期字符串
 */

function getFormatDateByLong(l, pattern) {
	return getFormatDate(new Date(l), pattern);
}
/**
 * 转换日期对象为日期字符串
 * 
 * @param l
 *            long值
 * @param pattern
 *            格式字符串,例如：yyyy-MM-dd hh:mm:ss
 * @return 符合要求的日期字符串
 */
function getFormatDate(date, pattern) {
	if (date == undefined) {
		date = new Date();
	}
	if (pattern == undefined) {
		pattern = "yyyy-MM-dd hh:mm:ss";
	}
	return date.format(pattern);
}
//计算时间差
Date.prototype.dateDiff = function(interval, endTime)
{
	switch (interval)
	{
	case "s"://计算秒差
		return parseInt((endTime - this) / 1000);
	case "n"://计算分差
		return parseInt((endTime - this) / 60000);
	case "h"://计算时差
		return parseInt((endTime - this) / 3600000);
	case "d"://计算日差
		return parseInt((endTime - this) / 86400000);
	case "w"://计算周差
		return parseInt((endTime - this) / (86400000 * 7));
	case "m"://计算月差
		return (endTime.getMonth() + 1)
				+ ((endTime.getFullYear() - this.getFullYear()) * 12)
				- (this.getMonth() + 1);
	case "y"://计算年差
		return endTime.getFullYear() - this.getFullYear();
	default://输入有误
		return undefined;

	}

}
