/** ****************高级搜索******************** */

// 请求字段信息
function getFieldJSON(baseUrl, tableName) {
	$.ajax({
		type : "POST",
		url : baseUrl + "/advquery/getList",
		data : {
			"tablename" : tableName
		},
		dataType : 'json',
		async : false,
		success : function(data) {
			// 构造高级搜索查询内容
			initData(data);
		}
	});
}

// 构造高级搜索查询内容
function initData(list) {

	FieldList.removeAll();

	// 字段信息
	for (var index = 0; index < list.length; index++) {
		var field = list[index];
		var dictCode = field.dictgroupcode == "group1" ? ''
				: field.dictgroupcode;
		FieldList.add(new Field(field.fieldname, field.fieldtitle,
				field.datatype, field.length, dictCode, ''));
	}
	FieldList.add(new Field('', '请选择一个字段', '', '0', '', '')); // 空白，必须是最后加入
}

// 是否第一次点击“高级查询”，仅第一次构造div取数据
var isFirst = true;

// 点击高级查询，弹出高级查询框
function showAdvQueryDlg() {
	if (isFirst) {

		var advData = window.gridData.advData;
		var baseUrl = window.glogbalData.contextPath;

		// 获取字段数据
		getFieldJSON(baseUrl, advData.tableName);

		// 初始化
		initHighQuery();
		isFirst = false;
	}

	$('#dlgAdvQuery').dialog('open');
}

// 点击确定，执行查询
function doAdvQuery() {
	var arrReturn = interpreter();
	var sql = arrReturn[1];
	if (sql) {
		// 补充条件
		ywGrid.reloadGrid({
			"advSql" : sql
		});

		$('#dlgAdvQuery').dialog('close');
	}
}