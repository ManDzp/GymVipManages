<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>修改私教信息</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/personaltraining/personaltraining.js${res_v}"></script>
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
                        <td class="tdbgcolor" colspan="4">私教信息</td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>私教编码：</td>
                        <td class="tdbgcolor35"><input id="personalTrainingCode" name="personalTrainingCode"
                            class="str" v_must="1" v_name="私教编码" v_type="string"
                            value="${personalTraining.personalTrainingCode}" /></td>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>私教名称：</td>
                        <td class="tdbgcolor35"><input id="personalTrainingName" name="personalTrainingName"
                            class="str" v_must="1" v_name="私教名称" v_type="string"
                            value="${personalTraining.personalTrainingName}" /></td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>状　　态：</td>
                        <td class="tdbgcolor" colspan="3">
                            <select id="personalTrainingStatus" name="personalTrainingStatus" class="easyui-combobox"
                                editable="false" panelHeight="auto" style="width: 70px;">
                                <option value="0" <c:if test="${personalTraining.personalTrainingStatus == '0'}">selected</c:if>>启用</option>
                                <option value="1" <c:if test="${personalTraining.personalTrainingStatus == '1'}">selected</c:if>>停用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor">备　　注：</td>
                        <td class="tdbgcolor" colspan="3">
                            <textarea id="personalTrainingRemark" name="personalTrainingRemark" style="width: 100%;" rows="4" cols="10">${personalTraining.personalTrainingRemark}</textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div style="display: none;">
                <input type="hidden" id="personalTrainingId" name="personalTrainingId" value="${personalTraining.personalTrainingId}" />
            </div>
        </form>
    </div>
</body>
</html>