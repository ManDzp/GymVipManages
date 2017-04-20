<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>${jsp_title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<link rel="stylesheet" type="text/css" href="${ctx}/business/web/index3.css${res_v}" />
	<link rel="stylesheet" type="text/css" href="${ctx}/business/web/index4.css${res_v}" />
	<link rel="stylesheet" type="text/css" href="${ctx}/business/columninfo/column.css${res_v}" />

	<script type="text/javascript" src="<c:url value='/deco/jquery/jquery.min.js'/>"></script>
	<script type="text/javascript" src="${ctx}/deco/dateformat.js${res_v}"></script>
	<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>

	<script type="text/javascript" src="${ctx}/deco/laytpl/laytpl.js${res_v}"></script>
	<script type="text/javascript" src="${ctx}/deco/laypage/laypage.js${res_v}"></script>

	<link rel="stylesheet" type="text/css" href="<c:url value='/deco/jquery-easyui/themes/icon.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/deco/jquery-easyui/themes/bootstrap/easyui.css'/>">

	<script type="text/javascript" src="<c:url value='/deco/jquery-easyui/jquery.easyui.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/deco/jquery-easyui/locale/easyui-lang-zh_CN.js'/>"></script>

	<script type="text/javascript" src="${ctx}/deco/ueditor2/ueditor.config.js"></script>
	<script type="text/javascript" src="${ctx}/deco/ueditor2/ueditor.all.js"></script>
	<script type="text/javascript" src="${ctx}/deco/ueditor2/lang/zh-cn/zh-cn.js" charset="utf-8"></script>

	<script type="text/javascript" src="${ctx}/business/web/visit.js${res_v}"></script>
	<script type="text/javascript" src="${ctx}/business/web/newreply.js${res_v}"></script>

<script type="text/javascript">
	// 公用全局变量
	window.glogbalData = {
		contextPath : '${ctx}'
	};
</script>

<style type="text/css">
.picBox
{
  width:100%;
  padding-left:10px;
  padding-top:10px;    
}
.picBox img
{
  max-width:400px;  
  max-height:300px;
  height:300px;
  width:400px;
  width:expression(document.body.clientWidth>400?"400px":"auto");
  height:expression(document.body.clientHeight>300?"300px":"auto");
  overflow:hidden;
}
.picBox p {
    font-size: 16px;
    margin: 5px 0px;
}
</style>
<body>
	<c:import url="web_top.jsp"></c:import>

	<div id="nav">
	    <div id="row1"><a href="${ctx}/webindex/web_index" style="color:#FFFFFF; font-size:14px;">首页</a>&nbsp;&gt;&gt;&nbsp;${documentInfo.columnname}</div>
	</div>


<div id="content3">
	<table cellpadding="0" cellspacing="0" border="0" >
		<tr><td width="970" height="31" class="sub" colspan="2">
			&nbsp;&nbsp;&nbsp;
			<c:if test="${documentInfo.istop == '1'}"><strong class="c">【置顶】</strong></c:if>
			<c:if test="${documentInfo.iscream == '1'}"><strong class="c">【精华】</strong></c:if>
			<label>${documentInfo.title}</label></td></tr>
		<tr><td width="970" height="274">
   		<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="165" class="bg" align="center" valign="top">
				<div style="margin-top: 5px;"><strong class="c">楼主</strong></div>
				<div style="margin-top: 10px;">
				<img src="${ctx}/deco/img/web/louzhu.jpg" style="vertical-align: middle;" />
				<strong style="font-size:14px; font-weight:normal; color:#000; vertical-align: middle;"><label>${documentInfo.creator}</label></strong>
				</div>
			</td>
			<td width="805" height="274" valign="top">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
				<td width="775" height="32" valign="middle" class="b">&nbsp;&nbsp;
					<img src="${ctx}/deco/img/web/biao.jpg" style="vertical-align: middle;" />
					<label style="vertical-align: middle;">&nbsp;发表于:${createtime}</label>
				</td>
				<td width="30" height="32" valign="middle" class="b" ></td>
				</tr>
				<tr><td width="805" valign="top" colspan="2" class="picBox"><div>
				<div>&nbsp;</div>
				<div><strong><span style="font-size: 16px"><label>${documentInfo.areacontent}</label></span></strong></div>
				</div>
				<div>&nbsp;</div>
				</td></tr>
     			<tr><td height="20" valign="top" colspan="2" >&nbsp;</td></tr>
     			</table>
			</td>
    	</tr>
		</table>
		</td></tr>
    </table>
    <!----------第一块结束------------>
</div>


<div id="person_list"></div>


<div id="content2">
	<!----------页面跳转开始------------>
	<table cellpadding="0" cellspacing="0" border="0" width="970" height="30" align="center">
		<tr>
			<td height="8" colspan="19"></td>
		</tr>
		<tr>
			<td width="23" height="33" align="center" valign="middle" class="pages"></td>
			<td class="pagerTitle"><div id="person_page"></div></td>
			<td width="310" height="33" align="center"></td>
		</tr>
		<tr>
			<td height="9" colspan="17"></td>
		</tr>
	</table>
	<!----------页面跳转结束------------>
</div>



<div id="content4">
	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="35" height="31" align="center" class="sub"><img src="${ctx}/deco/img/web/huifu.jpg" /></td>
			<td width="935" height="31" class="sub">快速回复</td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
		  <form id="fm" method="post" enctype="application/x-www-form-urlencoded">
				<table cellpadding="0" cellspacing="0" border="0" width="970" align="center">
					<tr>
						<td width="45" valign="top"></td>
						<td valign="top" width="880">
							<table cellpadding="0" cellspacing="0" border="0" align="center">
								<tr>
									<td valign="top" height="10" width="880"></td>
								</tr>
								<tr style="display : none">
									<td valign="top" height="20" width="880">
										<input style="WIDTH: 99%" id="title" tabindex="100" name="title" type="text" value="Re:${documentInfo.title}" />
									</td>
								</tr>
								<tr>
									<td valign="top" height="10" width="880"></td>
								</tr>
								<tr>
									<td height="168" width="880" valign="top">
										<table cellpadding="0" cellspacing="0" width="880" height="185" style="border: 1px solid #d7d7d7;">
											<tr>
												<td height="154" width="880" valign="top">
													<textarea name="replycontent" id="replycontent" cols="130" rows="13">
												</textarea></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td valign="top" height="5"></td>
								</tr>
								<tr>
									<td valign="top" height="33">
										<c:if test="${!empty contextUser}">
											<table cellpadding="0" cellspacing="0" border="0"
												width="907" height="33" align="center">
												<tr>
													<td height="33" width="83" class="ft" align="center">

														<a onclick="saveAddReply()" style="cursor: pointer;">提交</a>

													</td>
													<td height="33" width="824"></td>
												</tr>
											</table>
										</c:if>
										<c:if test="${empty contextUser}">
											<table cellpadding="0" cellspacing="0" border="0"
												width="907" height="33" align="center">
												<tr>
													<td height="33" width="83" class="ft" align="center">

														<a href="${ctx}/login" style="cursor: pointer; color: #FFF;">请登录</a>

													</td>
													<td height="33" width="824"></td>
												</tr>
											</table>
										</c:if>
									</td>
								</tr>
							</table>
						</td>
						<td valign="top" width="45"></td>
					</tr>
				</table>

				<div style="display:none">
					<input type="hidden" id="docguid" name="docguid" value="${documentInfo.guid}">
					<input type="hidden" id="columnguid" name="columnguid" value="${documentInfo.columnguid}">
				</div>
			</form>
			</td>
		</tr>
	</table>
</div>

	<c:import url="web_footer.jsp"></c:import>

<div style="display:none">
	<input type="hidden" id="objectGuid" value="${documentInfo.guid}">
	<input type="hidden" id="objectType" value="2">
</div>

<!-- i的意思是游标，当前页的第几楼 -->
<script id="template_info" type="text/html">
{{# for(var i = 0, len = d.length; i < len; i++){ }}
<div id="content3">
	<table cellpadding="0" cellspacing="0" border="0" >
		<tr><td width="970" height="31" class="sub" colspan="2">&nbsp;&nbsp;&nbsp;</td></tr>
		<tr><td width="970" height="274">
		<table cellpadding="0" cellspacing="0" border="0">
		<tr>
		<td width="165" class="bg" align="center" valign="top">
			<div style="margin-top: 5px;"><strong class="c">({{ (curPage - 1) * rows + i + 1 }}楼)</strong></div>
			<div style="margin-top: 10px;">
			<img src="${ctx}/deco/img/web/louzhu.jpg" style="vertical-align: middle;" />
			<strong style="font-size:14px; font-weight:normal; color:#000; vertical-align: middle;">{{d[i].creator}}</strong>
			</div>
		</td>
		<td width="805" height="274" valign="top">
		<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="775" height="32" valign="middle" class="b">&nbsp;&nbsp;
				<img src="${ctx}/deco/img/web/biao.jpg" style="vertical-align: middle;" />
				<label style="vertical-align: middle;">&nbsp;发表于:{{dateFormatShow1(d[i].createtime)}}</label>
			</td>
		</tr>
		<tr><td width="805" valign="top" class="picBox"><div>{{d[i].replycontent}}</div></td></tr>
		<tr><td height="20" valign="top" colspan="2" >&nbsp;</td></tr>
		</table>
		</td>
		</tr></table>
		</td></tr>
	</table>
</div>

{{# } }}
</script>

<script type="text/javascript">

var demo = function(){};
var rows = 10;// 一页显示的帖子回复数是10
var curPage = 1;//当前第几页

$(function() {
	var template = document.getElementById('template_info').innerHTML;
	var tpl = laytpl(template);

	demo = function(curr){
		curPage = curr || 1;

		$.post('${ctx}/webreply/getreplylist', {
			"params" : JSON.stringify({
				"docguid" : "${documentInfo.guid}"
			}),
			"page" : curPage,
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
	            curr: curPage, //当前页
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

<script type='text/javascript'>
	var editor;
	$(document).ready(function () {
		editor = UE.getEditor('replycontent', {
			initialFrameHeight: 200, initialFrameWidth: '100%',
			elementPathEnabled: false
		});
	});
</script>
</body>
</html>

