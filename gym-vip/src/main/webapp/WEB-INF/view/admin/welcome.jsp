<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>欢迎来到${systemConfig.systemTitle}</title>

<%@ include file="/WEB-INF/support/common.jsp"%>
</head>

<body>
	<div class="page-content">
		<div style="width:100%; height:200px; border:0px solid #000;">
			<div style="margin-top: 220px; text-align: center;">
				<span style="font-size: 30px;">欢迎来到${systemConfig.systemTitle}！</span>
				<div id="time"></div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function showNowTime() {
			var nowTimeStr = new Date().toLocaleString() + ' 星期' + '日一二三四五六'.charAt(new Date().getDay());
			document.getElementById('time').innerHTML = nowTimeStr;
		}

		$(function() {
			setInterval("showNowTime()", 1000);
		});
	</script>
</body>
</html>