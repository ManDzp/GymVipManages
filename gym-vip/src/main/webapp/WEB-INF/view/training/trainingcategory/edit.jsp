<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>修改训练分类</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/training/trainingcategory/trainingcategory.js${res_v}"></script>
</head>

<body>
    <div class="page-content">
        <!-- 创建菜单栏 -->
        <ywbar:viewMenu/>

        <!-- 创建表单信息 -->
        <form id="fm" method="post">
            <table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
                <tbody>
                    <tr>
                        <td class="tdbgcolor" colspan="4">训练分类</td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>训练分类编码：</td>
                        <td class="tdbgcolor35"><input id="code" name="code"
                            class="str" v_must="1" v_name="训练分类编码" v_type="string"
                            value="${trainingCategory.code}" /></td>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>训练分类名称：</td>
                        <td class="tdbgcolor35"><input id="name" name="name"
                            class="str" v_must="1" v_name="训练分类名称" v_type="string"
                            value="${trainingCategory.name}" /></td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>状　　态：</td>
                        <td class="tdbgcolor" colspan="3">
                            <select id="status" name="status" class="easyui-combobox"
                                editable="false" panelHeight="auto" style="width: 70px;">
                                <option value="0" <c:if test="${trainingCategory.status == '0'}">selected</c:if>>启用</option>
                                <option value="1" <c:if test="${trainingCategory.status == '1'}">selected</c:if>>停用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor">备　　注：</td>
                        <td class="tdbgcolor" colspan="3">
                            <textarea id="remark" name="remark" style="width: 100%;" rows="4" cols="10">${trainingCategory.remark}</textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div style="display: none;">
                <input type="hidden" id="id" name="id" value="${trainingCategory.id}" />
            </div>
        </form>
    </div>
</body>
</html>