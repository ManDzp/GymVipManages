<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>修改帖子信息</title>

<%@ include file="/WEB-INF/support/common.jsp"%>

<script type="text/javascript" src="${ctx}/deco/ueditor2/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/deco/ueditor2/ueditor.all.js"></script>
<script type="text/javascript" src="${ctx}/deco/ueditor2/lang/zh-cn/zh-cn.js" charset="utf-8"></script>

<script type="text/javascript" src="${ctx}/deco/pubfunction.js"></script>
<script type="text/javascript" src="${ctx}/business/documentinfo/documentinfo.js${res_v}"></script>
<script type="text/javascript">
	//初始化
	$(function() {
		$('#type').combobox('setValue', '${documentinfo.type}');
		$('#istop').combobox('setValue', '${documentinfo.istop}');
		$('#iscream').combobox('setValue', '${documentinfo.iscream}');
	});
</script>
</head>

<body>
	<div class="page-content">
		<!-- 创建菜单栏 -->
		<ywbar:viewMenu/>

		<!--创建表单信息  -->
		<form id="fm" method="post" enctype="application/x-www-form-urlencoded">
			<table class="tablebgcolor" width="100%" cellpadding="1" cellspacing="1" border="0" style="margin: 2px;">
				<tbody>
					<tr>
						<td class="lefttdbgcolor"><font color="#ff0000">*</font>标题：</td>
						<td class="tdbgcolor" colspan="3"><input id="title" name="title" class="str" v_must="1" v_type="string" v_name="板块名称" value="${documentinfo.title}" /></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">帖子类型：</td>
						<td class="tdbgcolor35"><select class="easyui-combobox"
							editable="false" panelHeight="auto" id="type" name="type"
							style="width: 80px;">
								<option value="咨询">咨询</option>
								<option value="建议">建议</option>
								<option value="提示">提示</option>
								<option value="文件">文件</option>
						</select></td>
						<td class="lefttdbgcolor">发帖时间：</td>
						<td class="tdbgcolor35">
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm:dd" value="${documentinfo.createtime}" />
						</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">是否置顶：</td>
						<td class="tdbgcolor35"><select class="easyui-combobox" editable="false" panelHeight="auto" id="istop" name="istop"
							 style="width: 80px;">
								<option value="1">是</option>
								<option value="0">否</option>
						</select>
						</td>		
						<td class="lefttdbgcolor">是否精华：</td>
						<td class="tdbgcolor35"><select class="easyui-combobox" editable="false" panelHeight="auto" id="iscream" name="iscream"
							 style="width: 80px;">
								<option value="1">是</option>
								<option value="0">否</option>
						</select>
						</td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">发帖人：</td>
						<td class="tdbgcolor" colspan="3"><label>${documentinfo.creator}</label></td>
					</tr>
					<tr>
						<td class="lefttdbgcolor">内容：</td>
						<td class="tdbgcolor" colspan="3"><textarea id="areacontent" name="areacontent"
							style="width: 100%;" rows="3" cols="51">${documentinfo.areacontent}</textarea></td>
					</tr>
				</tbody>
			</table>
			<div style="display: none;">
				<input type="hidden" id="guid" name="guid" value="${documentinfo.guid}" />
			</div>
		</form>
	</div>

<script type='text/javascript'>
	var editor;
	$(document).ready(function () {
		editor = UE.getEditor('areacontent', {
			initialFrameHeight: 200, initialFrameWidth: '100%',
			elementPathEnabled: false
		});
	});
</script>
</body>
</html>