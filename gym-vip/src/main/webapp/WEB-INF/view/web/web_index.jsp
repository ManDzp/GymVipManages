<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>${jsp_title}</title>

<link rel="stylesheet" type="text/css" href="${ctx}/business/web/index.css${res_v}" />
<link rel="stylesheet" type="text/css" href="${ctx}/business/web/index2.css${res_v}" />

<script type="text/javascript" src="<c:url value='/deco/jquery/jquery.min.js'/>"></script>
<script type="text/javascript" src="${ctx}/deco/dateformat.js${res_v}"></script>

<script type="text/javascript" src="${ctx}/business/web/web-index.js${res_v}"></script>
<script type="text/javascript" src="${ctx}/business/web/visit.js${res_v}"></script>

<script type="text/javascript">
	// 公用全局变量
	window.glogbalData = {
		contextPath : '${ctx}'
	};
</script>

<body>
	<c:import url="web_top.jsp"></c:import>

	<div id="nav">
	    <div id="row1"><a href="${ctx}/webindex/web_index" style="color:#FFFFFF; font-size:14px;">首页</a></div>
	</div>

	<div class="clear"></div>
	<!--头-->

	<div class="tongji">
		<img src="${ctx}/deco/img/web/tongjiico.jpg" align="absbottom" />
		今日发帖：<span>${todaytongji}</span> | 昨日发帖：<span>${yestodaytongji}</span> | 论坛总贴：<span>${alltongji}</span>
	</div>
	<div class="clear"></div>

	<div class="twzx">
		<div class="zxft">
			<table width="970" border="0" cellspacing="0" cellpadding="0"
				height="32">
				<tbody>
					<tr>
						<td class="zxftbj1" width="15"></td>
						<td width="76" id="CSS_zxhflb1" class="zxftbj22">
							<div id="headlines1" onmousemove="getzxft();" align="center">
								<h1>最新发帖</h1>
							</div>
						</td>
						<td width="76" id="CSS_zxhflb2" class="zxftbj2">
							<div id="headlines2" onmousemove="getzxhf();" align="center">
								<h1>最新回复</h1>
							</div>
						</td>
						<td width="803" class="zxftbj3"></td>
					</tr>
				</tbody>
			</table>

			<div id="headlines">
				<table width="960" border="0" cellspacing="0" cellpadding="0"
					class="zxhflb" id="zxft_list" style="display: block;">
				</table>

				<table width="960" border="0" cellspacing="0" cellpadding="0"
					class="zxhflb" id="zxhf_list" style="display: none;">
				</table>
			</div>
		</div>
	</div>
	<div class="clear"></div>

	<!--中-->
	<div class="main">
		<div class="mainbody">
			<div class="mainbodytitle">
				<span>板块列表</span>
			</div>
			<div id="bklb_div" class="mainbodytitlecontent"></div>
		</div>
	</div>
	<div class="clear"></div>

	<c:import url="web_footer.jsp"></c:import>

	<div style="display:none">
		<input type="hidden" id="objectGuid" value="0">
		<input type="hidden" id="objectType" value="0">
	</div>
</body>
</html>
