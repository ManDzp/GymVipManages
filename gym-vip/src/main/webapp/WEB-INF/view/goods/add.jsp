<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>添加商品信息</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/goods/goods.js${res_v}"></script>
</head>

<body style="overflow-x: hidden; overflow-y: auto; _overflow-y: inherit;">
    <div id="contenFram">
        <!-- 创建菜单栏 -->
        <ywbar:viewMenu/>

        <!--创建表单信息  -->
        <form id="fm" method="post">
            <table class="tablebgcolor" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
                <tbody>
                    <tr>
                        <td class="tdbgcolor" colspan="4">商品信息</td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>商品编码：</td>
                        <td class="tdbgcolor35"><input id="goodsCode" name="goodsCode"
                            class="str" v_must="1" v_name="商品编码" v_type="string" /></td>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>商 品 名：</td>
                        <td class="tdbgcolor35"><input id="goodsName" name="goodsName"
                            class="str" v_must="1" v_name="商品名" v_type="string" /></td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>单　　位：</td>
                        <td class="tdbgcolor35"><input id="goodsUnit" name="goodsUnit"
                            class="str" v_must="1" v_name="单位" v_type="string" /></td>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>单　　价：</td>
                        <td class="tdbgcolor35"><input id="goodsPrice" name="goodsPrice"
                            class="str" v_must="1" v_name="单价" v_type="float" /></td>
                    </tr>
                    <tr>
                        <td class="lefttdbgcolor"><font color="#ff0000">*</font>状　　态：</td>
                        <td class="tdbgcolor" colspan="3">
                            <select id="goodsStatus" name="goodsStatus" class="easyui-combobox"
                                editable="false" panelHeight="auto" style="width: 70px;">
                                <option value="0">启用</option>
                                <option value="1">停用</option>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
</body>
</html>