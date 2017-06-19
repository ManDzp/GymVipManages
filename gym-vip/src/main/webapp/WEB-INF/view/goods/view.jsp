<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查看商品信息</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/goods/goods.js${res_v}"></script>
</head>

<body>
    <div class="page-content">
        <!-- 创建菜单栏 -->
        <ywbar:viewMenu/>

        <!--创建表单信息  -->
        <form id="fm">
            <table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
                <tbody>
                    <tr>
                        <td class="tdbgcolor" colspan="4">商品信息</td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor">商 品 名：</td>
                        <td class="tdbgcolor" colspan="3"><label>${goods.goodsName}</label></td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor">单　　位：</td>
                        <td class="tdbgcolor35"><label>${goods.goodsUnit}</label></td>
                        <td class="lefttdbgcolor">单　　价：</td>
                        <td class="tdbgcolor35"><label>${goods.goodsPrice}</label></td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor">状　　态：</td>
                        <td class="tdbgcolor" colspan="3">
	                        <label>
	                            <c:if test="${goods.goodsStatus == '0'}">启用</c:if>
	                            <c:if test="${goods.goodsStatus == '1'}">停用</c:if>
	                        </label>
                        </td>
                    </tr>
                </tbody>
            </table>

            <ywtag:dialog/>

        </form>
    </div>
</body>
</html>