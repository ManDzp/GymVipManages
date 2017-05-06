<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>欢迎来到${systemConfig.systemTitle}</title>

<%@ include file="/WEB-INF/support/common.jsp"%>
<script type="text/javascript" src="${ctx}/deco/dateformat.js${res_v}"></script>

<style type="text/css">
.welcome-title {
	font-size: 40px;
	font-weight: bold;
    line-height: 70px;
}
.welcome-time {
	font-size: 20px;
}
</style>

</head>

<body>
	<div class="page-content">
		<div style="width:100%; height:200px; border:0px solid #000;">
			<div style="margin-top: 200px; text-align: center;">
				<span class="welcome-title">欢迎来到${systemConfig.systemTitle}！</span>
				<br>
				<span class="welcome-time"></span>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function showNowTime() {
			var nowTime = getSmpFormatDateByLong(new Date(), "yyyy年MM月dd日 hh时mm分ss秒");
			var nowTimeStr = nowTime + ' 星期' + '日一二三四五六'.charAt(new Date().getDay());
			$(".welcome-time").html(nowTimeStr);
		}

		$(function() {
			setInterval("showNowTime()", 1000);
		});
	</script>
</body>
</html>