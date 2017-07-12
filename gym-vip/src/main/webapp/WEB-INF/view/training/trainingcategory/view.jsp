<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>查看训练分类</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/training/trainingcategory/trainingcategory.js${res_v}"></script>
</head>

<body>
    <div class="page-content">
        <!-- 创建菜单栏 -->
        <ywbar:viewMenu/>

        <!-- 创建表单信息 -->
        <form id="fm">
            <table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
                <tbody>
                    <tr>
                        <td class="tdbgcolor" colspan="4">训练分类</td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor">训练分类编码：</td>
                        <td class="tdbgcolor35"><label>${trainingCategory.code}</label></td>
                        <td class="lefttdbgcolor">训练分类名称：</td>
                        <td class="tdbgcolor35"><label>${trainingCategory.name}</label></td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor">状　　态：</td>
                        <td class="tdbgcolor" colspan="3">
	                        <label>
	                            <c:if test="${trainingCategory.status == '0'}">启用</c:if>
	                            <c:if test="${trainingCategory.status == '1'}">停用</c:if>
	                        </label>
                        </td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor">备　　注：</td>
                        <td class="tdbgcolor" colspan="3">
                            <textarea style="width: 100%;" rows="4" cols="10" readonly="readonly">${trainingCategory.remark}</textarea>
                        </td>
                    </tr>
                </tbody>
                </tbody>
            </table>

            <ywtag:dialog/>

        </form>
    </div>
</body>
</html>