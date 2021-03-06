<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查看会员资料</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<style type="text/css">
ul, li {
	list-style: none;
}

.member-info-record {
	padding-top: 20px;
}

.member-info-record-ul li {
	float: left;
	width: 31%;
	height: 250px;
	padding: 0 1%;
}

/* 会员照片大小 */
.member-info-img {
	/* width: 150px; */
	height: 200px;
}
</style>

<script type="text/javascript" src="${ctx}/deco/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/member/member.js${res_v}"></script>
</head>

<body>
	<div class="page-content">
		<!-- 创建菜单栏 -->
		<ywbar:viewMenu/>

		<!--创建表单信息  -->
		<form id="fm" method="post" enctype="application/x-www-form-urlencoded">
			<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
				<tbody>
					<tr>
						<td class="tdbgcolor" colspan="4">会员卡资料</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">会员卡号：</td>
						<td class="tdbgcolor35"><label>${memberInfo.cardnumber}</label></td>
						<td class="lefttdbgcolor">会员类型：</td>
						<td class="tdbgcolor35"><label>
							<c:if test="${memberInfo.cardtype==0}">时间卡</c:if>
							<c:if test="${memberInfo.cardtype==1}">次卡</c:if>
						</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">开卡日期：</td>
						<td class="tdbgcolor35"><label><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${memberInfo.activetime}" /></label></td>
						<td class="lefttdbgcolor">到期日期：</td>
						<td class="tdbgcolor35"><label class="view-info-important"><fmt:formatDate pattern="yyyy-MM-dd" value="${memberInfo.expiretime}" /></label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">卡内余额：</td>
						<td class="tdbgcolor35"><label>${memberInfo.balance}</label></td>
						<td class="lefttdbgcolor">累计消费：</td>
						<td class="tdbgcolor35"><label>${memberInfo.consumption}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">会员积分：</td>
						<td class="tdbgcolor35">
							<label>${memberInfo.points}</label>
							<c:if test="${deletetype == '0' && memberInfo.status > 1}">
							<a class="view-button" href="javascript:void(0);" onclick="openSaveCardPoints('${memberInfo.guid}')">获取积分</a>

							<c:if test="${memberInfo.cardtype == '0'}">
							<!-- 时间卡 -->
							<a class="view-button" href="javascript:void(0);" onclick="openPointsExchangeTime('${memberInfo.guid}', '<fmt:formatDate pattern="yyyy-MM-dd" value="${memberInfo.expiretime}" />')">兑换时间</a>
							</c:if>

							</c:if>
						</td>
						<td class="lefttdbgcolor">当前状态：</td>
						<td class="tdbgcolor35"><label class="view-info-important">
							<c:if test="${memberInfo.status==0}">初始状态</c:if>
							<c:if test="${memberInfo.status==1}">待开卡</c:if>
							<c:if test="${memberInfo.status==2}">正常</c:if>
							<c:if test="${memberInfo.status==3}">请假</c:if>
							<c:if test="${memberInfo.status==4}">到期</c:if>
						</label></td>
					</tr>

				<c:if test="${memberInfo.cardtype == '1'}">
				<!-- 次卡 -->
					<tr>
						<td class="lefttdbgcolor">可用次数：</td>
						<td class="tdbgcolor35"><label>${memberInfo.times}</label></td>
						<td class="lefttdbgcolor">已用次数：</td>
						<td class="tdbgcolor35"><label>${memberInfo.usedtimes}</label></td>
					</tr>
				</c:if>

					<tr>
						<td class="tdbgcolor" colspan="4">会员资料</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">会员名称：</td>
						<td class="tdbgcolor35"><label>${memberInfo.fullname}</label></td>
						<td class="tdbgcolor" colspan="2" rowspan="6" style="text-align: center;">${imgHtml}</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">会员性别：</td>
						<td class="tdbgcolor35"><label>
							<c:if test="${memberInfo.sex==0}">男</c:if>
							<c:if test="${memberInfo.sex==1}">女</c:if>
						</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">国　　籍：</td>
						<td class="tdbgcolor35"><label>${memberInfo.nationality}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">证件号码：</td>
						<td class="tdbgcolor35"><label>${memberInfo.identitycard}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">生　　日：</td>
						<td class="tdbgcolor35"><label><fmt:formatDate pattern="yyyy-MM-dd" value="${memberInfo.birthday}" /></label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">手 机 号：</td>
						<td class="tdbgcolor35"><label>${memberInfo.mobile}</label></td>
					</tr>
				</tbody>
			</table>

			<div class="member-info-record">
				<ul class="member-info-record-ul">

					<!-- 充值记录 -->
					<li>
						<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
							<tbody>
								<tr>
									<td class="tdbgcolor70 view-info-important" colspan="2">充值记录</td>
									<td class="lefttdbgcolor30" style="text-align: center;"><a href="javascript:void(0);" onclick="openIFrameDialog('${ctx}/record/chargerecord/list?memberguid=${memberInfo.guid}');">更多</a></td>
								</tr>

								<tr>
									<td class="lefttdbgcolor30" style="text-align: center;">充值金额</td>
									<td class="tdbgcolor70" style="text-align: center;" colspan="2">充值时间</td>
								</tr>
								<c:forEach items="${chargeRecordList}" var="chargeRecord">
								<tr>
									<td class="lefttdbgcolor30" style="text-align: center;">${chargeRecord.money}</td>
									<td class="tdbgcolor70" style="text-align: center;" colspan="2"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${chargeRecord.createtime}" /></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</li>

			<c:if test="${memberInfo.cardtype == '0'}">
			<!-- 时间卡 -->

				<c:if test="${memberInfo.status > 1}">
					<!-- 签到记录 -->
					<li>
						<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
							<tbody>
								<tr>
									<td class="tdbgcolor70 view-info-important">签到记录</td>
									<td class="lefttdbgcolor30" style="text-align: center;"><a href="javascript:void(0);" onclick="openIFrameDialog('${ctx}/record/signrecord/list?memberguid=${memberInfo.guid}');">更多</a></td>
								</tr>
								<tr>
									<td class="tdbgcolor" colspan="2">签到时间</td>
								</tr>
								<c:forEach items="${signRecordList}" var="signRecord">
								<tr>
									<td class="tdbgcolor" colspan="2"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${signRecord.createtime}" /></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</li>
				</c:if>

				<c:if test="${memberInfo.status > 1}">
					<!-- 续卡记录 -->
					<li>
						<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
							<tbody>
								<tr>
									<td class="tdbgcolor70 view-info-important">续卡记录</td>
									<td class="lefttdbgcolor30" style="text-align: center;"><a href="javascript:void(0);" onclick="openIFrameDialog('${ctx}/record/continuecardrecord/list?memberguid=${memberInfo.guid}');">更多</a></td>
								</tr>
							</tbody>
						</table>

						<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
							<tbody>
								<tr>
									<td class="lefttdbgcolor" style="text-align: center;">消费金额</td>
									<td class="tdbgcolor35" style="text-align: center;">到期日期</td>
									<td class="tdbgcolor35" style="text-align: center;">续卡时间</td>
								</tr>
								<c:forEach items="${continueCardRecordList}" var="continueCardRecord">
								<tr>
									<td class="lefttdbgcolor" style="text-align: center;">${continueCardRecord.money}</td>
									<td class="tdbgcolor35" style="text-align: center;"><fmt:formatDate pattern="yyyy-MM-dd" value="${continueCardRecord.expiretime}" /></td>
									<td class="tdbgcolor35" style="text-align: center;"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${continueCardRecord.createtime}" /></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</li>
				</c:if>

					<!-- 买卡记录 -->
					<li>
						<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
							<tbody>
								<tr>
									<td class="tdbgcolor70 view-info-important" colspan="2">买卡记录</td>
									<td class="lefttdbgcolor30" style="text-align: center;"><a href="javascript:void(0);" onclick="openIFrameDialog('${ctx}/record/buycardrecord/list?memberguid=${memberInfo.guid}');">更多</a></td>
								</tr>

								<tr>
									<td class="lefttdbgcolor30" style="text-align: center;">消费金额</td>
									<td class="tdbgcolor70" style="text-align: center;" colspan="2">买卡时间</td>
								</tr>
								<c:forEach items="${buyCardRecordList}" var="buyCardRecord">
								<tr>
									<td class="lefttdbgcolor30" style="text-align: center;">${buyCardRecord.money}</td>
									<td class="tdbgcolor70" style="text-align: center;" colspan="2"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${buyCardRecord.createtime}" /></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</li>

					<!-- 开卡记录 -->
					<li>
						<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
							<tbody>
								<tr>
									<td class="tdbgcolor70 view-info-important">开卡记录</td>
									<td class="lefttdbgcolor30" style="text-align: center;"><a href="javascript:void(0);" onclick="openIFrameDialog('${ctx}/record/activecardrecord/list?memberguid=${memberInfo.guid}');">更多</a></td>
								</tr>
							</tbody>
						</table>

						<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
							<tbody>
								<tr>
									<td class="tdbgcolor" style="width: 50%; text-align: center;">开卡日期</td>
									<td class="tdbgcolor" style="width: 50%; text-align: center;">到期日期</td>
								</tr>
								<c:forEach items="${activeCardRecordList}" var="activeCardRecord">
								<tr>
									<td class="tdbgcolor" style="width: 50%; text-align: center;"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${activeCardRecord.activetime}" /></td>
									<td class="tdbgcolor" style="width: 50%; text-align: center;"><fmt:formatDate pattern="yyyy-MM-dd" value="${activeCardRecord.expiretime}" /></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</li>

				<c:if test="${memberInfo.status > 1}">
					<!-- 请销假记录 -->
					<li>
						<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
							<tbody>
								<tr>
									<td class="tdbgcolor70 view-info-important" colspan="2">请销假记录</td>
									<td class="lefttdbgcolor30" style="text-align: center;"><a href="javascript:void(0);" onclick="openIFrameDialog('${ctx}/record/leaverecord/list?memberguid=${memberInfo.guid}');">更多</a></td>
								</tr>

								<tr>
									<td class="lefttdbgcolor30" style="text-align: center;">类型</td>
									<td class="tdbgcolor70" style="text-align: center;" colspan="2">操作时间</td>
								</tr>
								<c:forEach items="${leaveRecordList}" var="leaveRecord">
								<tr>
									<td class="lefttdbgcolor30" style="text-align: center;">
										<c:if test="${leaveRecord.leavetype=='0'}">请假</c:if>
										<c:if test="${leaveRecord.leavetype=='1'}">销假</c:if>
									</td>
									<td class="tdbgcolor70" style="text-align: center;" colspan="2"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${leaveRecord.createtime}" /></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</li>
				</c:if>

				<c:if test="${memberInfo.status > 1}">
					<!-- 积分记录 -->
					<li>
						<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
							<tbody>
								<tr>
									<td class="tdbgcolor70 view-info-important" colspan="2">积分记录</td>
									<td class="lefttdbgcolor30" style="text-align: center;"><a href="javascript:void(0);" onclick="openIFrameDialog('${ctx}/record/pointsrecord/list?memberguid=${memberInfo.guid}');">更多</a></td>
								</tr>

								<tr>
									<td class="lefttdbgcolor30" style="text-align: center;">操作</td>
									<td class="tdbgcolor70" style="text-align: center;" colspan="2">操作时间</td>
								</tr>
								<c:forEach items="${pointsRecordList}" var="pointsRecord">
								<tr>
									<td class="lefttdbgcolor30" style="text-align: center;">
										<c:if test="${pointsRecord.pointtype=='0'}">获得${pointsRecord.points}积分</c:if>
										<c:if test="${pointsRecord.pointtype=='1'}">兑换${pointsRecord.points}积分至<fmt:formatDate pattern="yyyy-MM-dd" value="${pointsRecord.expiretime}" /></c:if>
									</td>
									<td class="tdbgcolor70" style="text-align: center;" colspan="2"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${pointsRecord.createtime}" /></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</li>
				</c:if>
			</c:if>

			<c:if test="${memberInfo.cardtype == '1'}">
			<!-- 次卡 -->
					<!-- 签到记录 -->
					<li>
						<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
							<tbody>
								<tr>
									<td class="tdbgcolor view-info-important">签到记录</td>
									<td class="lefttdbgcolor" style="text-align: center;"><a href="javascript:void(0);" onclick="openIFrameDialog('${ctx}/record/signrecord/list?memberguid=${memberInfo.guid}');">更多</a></td>
								</tr>
								<tr>
									<td class="tdbgcolor" colspan="2">签到时间</td>
								</tr>
								<c:forEach items="${signRecordList}" var="signRecord">
								<tr>
									<td class="tdbgcolor" colspan="2"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${signRecord.createtime}" /></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</li>

					<!-- 购买次数记录 -->
					<li>
						<table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
							<tbody>
								<tr>
									<td class="tdbgcolor view-info-important" colspan="3">购买次数记录</td>
									<td class="lefttdbgcolor" style="text-align: center;"><a href="javascript:void(0);" onclick="openIFrameDialog('${ctx}/record/buycardnumberrecord/list?memberguid=${memberInfo.guid}');">更多</a></td>
								</tr>

								<tr>
									<td class="tdbgcolor20" style="text-align: center;">消费金额</td>
									<td class="tdbgcolor20" style="text-align: center;">购买次数</td>
									<td class="tdbgcolor20" style="text-align: center;">到期日期</td>
									<td class="tdbgcolor20" style="text-align: center;" colspan="2">购买时间</td>
								</tr>
								<c:forEach items="${buyCardNumberRecordList}" var="buyCardNumberRecord">
								<tr>
									<td class="tdbgcolor20" style="text-align: center;">${buyCardNumberRecord.money}</td>
									<td class="tdbgcolor20" style="text-align: center;">${buyCardNumberRecord.number}</td>
									<td class="tdbgcolor20" style="text-align: center;"><fmt:formatDate pattern="yyyy-MM-dd" value="${buyCardNumberRecord.expiretime}" /></td>
									<td class="tdbgcolor20" style="text-align: center;" colspan="2"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${buyCardNumberRecord.createtime}" /></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</li>
			</c:if>

				</ul>
			</div>

			<ywtag:dialog/>

		</form>
	</div>
</body>
</html>