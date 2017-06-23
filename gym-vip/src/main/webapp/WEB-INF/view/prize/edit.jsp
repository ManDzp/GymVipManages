<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>修改奖品信息</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/prize/prize.js${res_v}"></script>
</head>

<body>
    <div class="page-content">
        <!-- 创建菜单栏 -->
        <ywbar:viewMenu/>

        <!--创建表单信息  -->
        <form id="fm" method="post">
            <table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
                <tbody>
                    <tr>
                        <td class="tdbgcolor" colspan="4">奖品信息</td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>奖品编码：</td>
                        <td class="tdbgcolor35"><input id="prizeCode" name="prizeCode"
                            class="str" v_must="1" v_name="奖品编码" v_type="string"
                            value="${prize.prizeCode}" /></td>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>奖 品 名：</td>
                        <td class="tdbgcolor35"><input id="prizeName" name="prizeName"
                            class="str" v_must="1" v_name="奖品名" v_type="string"
                            value="${prize.prizeName}" /></td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>单　　位：</td>
                        <td class="tdbgcolor" colspan="3"><input id="prizeUnit" name="prizeUnit"
                            class="str" v_must="1" v_name="单位" v_type="string"
                            value="${prize.prizeUnit}" /></td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>状　　态：</td>
                        <td class="tdbgcolor" colspan="3">
                            <select id="prizeStatus" name="prizeStatus" class="easyui-combobox"
                                editable="false" panelHeight="auto" style="width: 70px;">
                                <option value="0" <c:if test="${prize.prizeStatus == '0'}">selected</c:if>>启用</option>
                                <option value="1" <c:if test="${prize.prizeStatus == '1'}">selected</c:if>>停用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor">备　　注：</td>
                        <td class="tdbgcolor" colspan="3">
                            <textarea id="prizeRemark" name="prizeRemark" style="width: 100%;" rows="4" cols="10">${prize.prizeRemark}</textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div style="display: none;">
                <input type="hidden" id="prizeId" name="prizeId" value="${prize.prizeId}" />
            </div>
        </form>
    </div>
</body>
</html>