<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>${jsp_title}</title>

<link rel="stylesheet" type="text/css" href="${ctx}/business/web/basic.css${res_v}" />
<link rel="stylesheet" type="text/css" href="${ctx}/business/web/index2.css${res_v}" />
<link rel="stylesheet" type="text/css" href="${ctx}/business/columninfo/column.css${res_v}" />

<script type="text/javascript" src="<c:url value='/deco/jquery/jquery.min.js'/>"></script>
<script type="text/javascript" src="${ctx}/deco/dateformat.js${res_v}"></script>

<script type="text/javascript" src="${ctx}/business/web/visit.js${res_v}"></script>

<script type="text/javascript" src="${ctx}/deco/laypage/laypage.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/deco/laytpl/laytpl.js${res_v}"></script>

<script type="text/javascript">
	// 公用全局变量
	window.glogbalData = {
		contextPath : '${ctx}'
	};
</script>

</head>
<body>
<div id="page">
	<c:import url="web_top.jsp"></c:import>

	<div id="nav">
	    <div id="row1"><a href="${ctx}/webindex/web_index" style="color:#FFFFFF; font-size:14px;">首页</a>&nbsp;&gt;&gt;&nbsp;${title}</div>
	</div>


<div id="content1">
	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="970" height="31" colspan="3">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr class="sub">
						<td width="500" height="31" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;全部主题</td>
						<td width="140" height="31" align="center">作者</td>
						<td width="20" height="31"></td>
						<td width="70" height="31" align="center">最后发表</td>
						<td width="20" height="31"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="20" height="270"></td>
			<td width="930" height="270" valign="top" id="person_list"></td>
			<td width="20" height="270"></td>
		</tr>
	</table>
</div>

<div id="content2">
	<!----------页面跳转开始------------>
	<table cellpadding="0" cellspacing="0" border="0" width="970"
		height="30" align="center">
		<tbody>
		
			<tr>
				<td width="970" height="8" colspan="17"></td>
			</tr>
			<tr>
				<td width="23" height="33" align="center" valign="middle"
					class="pages"></td>
				<td width="426" class="pagerTitle"><div id="person_page"></div></td>
				<td width="438" height="33" align="center"></td>
				<c:choose>
				<c:when test="${!empty contextUser}">
				<td width="83" height="33" align="center" class="ft">
				   <a href="postdocument?columnguid=${columnGuid}" target="_blank" style="color:#fff;">发帖</a>
				</td>
				</c:when>
				<c:otherwise>
				<td width="83" height="33" align="center"></td>
				</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td width="970" height="9" colspan="17"></td>
			</tr>
		</tbody>
	</table>
	<!----------页面跳转结束------------>
</div>

	<c:import url="web_footer.jsp"></c:import>

</div>

<div style="display:none">
	<input type="hidden" id="objectGuid" value="${columnGuid}">
	<input type="hidden" id="objectType" value="1">
</div>

<script id="template_info" type="text/html">
{{# for(var i = 0, len = d.length; i < len; i++){ }}
<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-bottom-width: 1px;border-top-style: none;border-right-style: none;border-bottom-style: solid;border-left-style: none;border-bottom-color: #c2d5e3;">
	<tr>
		<td width="20" height="45" align="center"></td>
		<td width="500" height="45" align="left" ><strong class="nr"><a href="${ctx}/webreply/replyview?guid={{d[i].guid}}" target="_blank">{{#if(d[i].istop=="1") { }}<font style="color: #ff0000;">[置顶]</font>{{# } }}{{#if(d[i].iscream=="1") { }}<font style="color: #ffd700;">[精华]</font>{{# } }}
		[{{d[i].type}}]{{d[i].title}}</a></strong></td>
		<td width="200" height="45" align="center" class="zz">{{d[i].creator}}</td>
		<td width="10" height="45"></td>
		<td width="80" height="45" align="center" class="zz"><small>{{dateFormatShortShow(d[i].createtime)}}</small></td>
	</tr>
</table>
{{# } }}
</script>

<script type="text/javascript">

var demo = function(){};

$(function() {
	var template = document.getElementById('template_info').innerHTML;
	var tpl = laytpl(template);

	demo = function(curr){
		var rows = 10;// 一页显示条目数

		$.post('${ctx}/webdocument/getdocumentlist', {
			"params" : JSON.stringify({
				"columnguid" : "${columnGuid}"
			}),
			"page" : curr || 1,
			"rows" : rows
	    }, function(res){
			var html = "";
			var data = res.rows; 
			if (data && data.length) {
				html = tpl.render(data);
			}

			$("#person_list").html(html);

			var total = res.total;
			var pages = 1;
			if (total) {
				pages = parseInt((total - 1) / rows) + 1;
			}

	        //显示分页
	        laypage({
	            cont: 'person_page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
	            pages: pages, //通过后台拿到的总页数
	            curr: curr || 1, //当前页
	            jump: function(obj, first){ //触发分页后的回调
	                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
	                    demo(obj.curr);
	                }
	            }
	        });
	    });
	}
	//运行
	demo();
});
</script>

</body>
</html>
