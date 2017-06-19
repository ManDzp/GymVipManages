<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/deco/left.css">

<div style="width: 240px; height: 100%; overflow: hidden;"
	id="bbsAccrdion" border="true" fit="true" class="easyui-accordion">
	<div title="查询会员资料">
		<ul>
			<li>
				<div class="main-item">
					<img src='${ctx}/deco/img/iconlf01.gif' align="absmiddle" width='5' height='5' />
					<a href="javascript:void(0);" onclick="menuClick('${ctx}/member/searchlist', 'iframepage')">
						<span>查询会员资料</span>
					</a>
				</div>
			</li>
		</ul>
	</div>
	<div title="系统管理">
		<ul>
			<li>
				<div class="main-item">
					<img src='${ctx}/deco/img/iconlf01.gif' align="absmiddle" width='5' height='5' />
					<a href="javascript:void(0);" onclick="menuClick('${ctx}/member/list', 'iframepage')">
						<span>会员资料</span>
					</a>
				</div>
			</li>
			<li>
				<div class="main-item">
					<img src='${ctx}/deco/img/iconlf01.gif' align="absmiddle" width='5' height='5' />
					<a href="javascript:void(0);" onclick="menuClick('${ctx}/member/historylist', 'iframepage')">
						<span>会员封存资料</span>
					</a>
				</div>
			</li>
            <li>
                <div class="main-item">
                    <img src='${ctx}/deco/img/iconlf01.gif' align="absmiddle" width='5' height='5' />
                    <a href="javascript:void(0);" onclick="menuClick('${ctx}/goods/list', 'iframepage')">
                        <span>商品信息</span>
                    </a>
                </div>
            </li>
            <li>
                <div class="main-item">
                    <img src='${ctx}/deco/img/iconlf01.gif' align="absmiddle" width='5' height='5' />
                    <a href="javascript:void(0);" onclick="menuClick('${ctx}/prize/list', 'iframepage')">
                        <span>奖品信息</span>
                    </a>
                </div>
            </li>
		</ul>
	</div>
</div>

<script type="text/javascript" src="${ctx}/deco/loadAccordion.js"></script>