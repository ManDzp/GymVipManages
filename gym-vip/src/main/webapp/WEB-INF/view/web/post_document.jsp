<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/support/include.inc.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:import url="/WEB-INF/support/meta.jsp"></c:import>
<title>${jsp_title}</title>

<link rel="stylesheet" type="text/css" href="${ctx}/business/web/common.css${res_v}" />
<link rel="stylesheet" type="text/css" href="${ctx}/business/web/publish.css${res_v}" />
<link href="${ctx}/business/web/main.css${res_v}" rel="stylesheet" type="text/css" />
<link href="${ctx}/business/web/index2.css${res_v}" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="<c:url value='/deco/jquery/jquery.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/deco/jquery-easyui/themes/bootstrap/easyui.css'/>">
<script type="text/javascript" src="<c:url value='/deco/jquery-easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/deco/jquery-easyui/locale/easyui-lang-zh_CN.js'/>"></script>

<script type="text/javascript" src="${ctx}/deco/ueditor2/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/deco/ueditor2/ueditor.all.js"></script>
<script type="text/javascript" src="${ctx}/deco/ueditor2/lang/zh-cn/zh-cn.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/business/web/post-document.js${res_v}"></script>
</head>

<body>
	<c:import url="web_top.jsp"></c:import>

		  <form id="fm" method="post" enctype="application/x-www-form-urlencoded">
     <div id="nav">
      <span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您好，欢迎来到莱西论坛！</span>
     </div>
    <div class="section-publish" style="width:970px;">
        <div class="subnav-wrap" >
            <div class="subnav">
                <div class="clearfix subnav-inner">
                    <div class="crumbnav" >
                        <a class="crumbnav-label" title="页面导航"></a>
                        <a href="${ctx}/webindex/web_index"><span>论坛首页</span></a>
                        <span class="separator">&raquo;</span>
                        <a href="${ctx}/webdocument/home_list?columnGuid=${column.guid}"><span>${column.title}</span></a>
                        <span class="separator">&raquo;</span>
                        <span>发表新主题</font></span>
                    </div>
                </div>
            </div>
        </div>
        <div id="main" class="main rightsidebar">
            <div class="clearfix main-inner">
                <div class="content">
                    <div class="clearfix content-inner">
                        <div class="content-main">
                            <div class="content-main-inner">
                                <div id="ap_review"></div>
                                <div class="formgroup publishform" id="topicdatas" style="width:945px;">
                                    <div class="formrow publishform-title" style="line-height:28px">
                                        <div><font style="font-size:12px;">标&nbsp;&nbsp;题：</font><input class="easyui-textbox" name="title"
												style="width: 878px; height: 30px; padding: 6px;" /></div>
                                        <div class="clearfix form-enter"  style="line-height:40px">
                                            <div>
                                                <font style="font-size:12px;">文章类别：</font><select class="easyui-combobox" editable="false" panelHeight="auto"
                                                 id="type"  name="type" style="width:100px; height:30px;">
                                                	 <option value="咨询" selected="selected">咨询</option> 
                                                	 <option value="建议">建议</option> 
                                                	 <option value="提示">提示</option>
                                                	 <option value="文件">文件</option> 
                                                	 </select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                	 <font style="font-size:12px;">作&nbsp;&nbsp;者：</font><input class="easyui-textbox" style="width:150px; height:30px;" name="author" type="text" id="author" tybpe="text" readonly="readonly" value="${contextUser.userName}" />
                                            </div>
                                        </div>
                                    </div>
                                    <div id="ap_topicdatas">
                                    <div>
                                    <textarea name="areacontent" id="areacontent" rows="20" style="WIDTH: 99%" ></textarea><br/>
                                    </div>
										<div class="btn" onclick="addDocument();">
											发表
										</div>
                               </div>
                                </div>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="display:none">
        <input name="columnguid" type="hidden" id="columnguid" value="${columnguid}" />
        <input name="columnname" type="hidden" id="columnname" value="${column.title}" />
    </div>
</form>

<c:import url="web_footer.jsp"></c:import>

</body>
</html>

<script type='text/javascript'>
	var editor;
	$(document).ready(function () {
		editor = UE.getEditor('areacontent', {
			initialFrameHeight: 200, initialFrameWidth: '100%',
			elementPathEnabled: false
		});
	});
</script>
