(function(win) {
	var ywGrid = win.ywGrid || {};
	var properties = {
		fit : true,//
		fitColumns : true,//
		url : '',// 远程请求地址
		printurl : '',// 默认打印请求地址
		queryParams : {},// 查询参数
		toolbar : '#list_toolbar',// 顶部工具栏id
		pagination : true,// 显示分页工具栏
		pageSize : 20,// 初始化页面大小
		pageList : [ 20, 40, 60, 80, 100 ],// 初始化页面大小选择列表
		onClickRow : function(rowIndex, rowData) {// 在用户点击一行的时候触发
			$(this).datagrid('unselectRow', rowIndex);
		}
	};

	// 初始化
	ywGrid.init = function() {
		// 从前台传递过的列表条件
		var gridData = win.gridData || {};
		// 设置默认的查询参数
		properties.queryParams = gridData.defaultQueryParams;
		// 默认请求地址
		properties.url = glogbalData.contextPath + '/list/page';
		// 默认打印请求地址
		properties.printurl = glogbalData.contextPath + '/list/printlist';
	}

	// 初始加载数据
	ywGrid.loadGrid = function(param) {
		// 初始化参数
		ywGrid.init();

		properties = $.extend({}, properties, param);
		var columnSize = properties.columnSize || 0;// 列数

		properties.onLoadSuccess = function(data) {// 无数据提示
			if (data.rows.length == 0) {
				var body = $(this).data().datagrid.dc.body2;
				var html = '<tr><td width="'
						+ body.width()
						+ '" style="height: 25px; text-align: left;padding-left:5px;font-size:12px;" colspan="'
						+ columnSize + '">没有符合条件的记录存在</td></tr>';
				body.find('table tbody').append(html);
			}
		};

		// 加载数据
		$('#grid').datagrid(properties);
	}

	// 重新加载数据
	ywGrid.reloadGrid = function(otherParam) {
		var gridData = win.gridData || {};
		// 新的查询条件
		var paramsData = $.extend({}, gridData.defaultParam, otherParam);
		// 新的查询参数
		var queryParams = $.extend({}, gridData.defaultQueryParams, {
			"params" : JSON.stringify(paramsData)
		});
		// 加载数据
		$('#grid').datagrid('load', queryParams);
	}
	
	// 重新加载数据，排序号
	ywGrid.reloadGridOrder = function(otherParam, orderby) {
		var gridData = win.gridData || {};
		// 新的查询条件
		var paramsData = $.extend({}, gridData.defaultParam, otherParam);
		// 新的查询参数
		var queryParams = $.extend({}, gridData.defaultQueryParams, {
			"params" : JSON.stringify(paramsData),
			"orderby": orderby
		});
		// 加载数据
		$('#grid').datagrid('load', queryParams);
	}

	// 列表打印
	ywGrid.print = function(param) {
		var options = $("#grid").datagrid('options');
		var currentParams = options.queryParams;
		var sortName = options.sortName;
		var sortOrder = options.sortOrder;

		param.sort = sortName;
		param.order = sortOrder;

		// 新的查询参数
		var queryParams = $.extend({}, currentParams, param);

		// 打印地址
		var printurl = properties.printurl;

		$.ajax({
			type : "POST",
			url : printurl,
			data : JSON.stringify(queryParams),
			contentType : 'application/json',
			success : function(data) {
				if (data == '0') {
					$.messager.alert('提示', "打印配置出错！");
				} else if (data == '1') {
					$.messager.alert('提示', "无打印数据！");
				} else if (data) {
					window.open(data);
				}
			}
		});
	}

	win.ywGrid = ywGrid;
})(window);