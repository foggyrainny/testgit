<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
    <base href="<%=basePath%>">
    <%@include file="../../../resource.jsp" %>
    
    <title>菜单管理</title>
    <style type="text/css">
    	.layui-form-label{width:100px};
    </style>
    
	<link rel="stylesheet" type="text/css" href="<%=basePathResource%>js/treetable/vsStyle/jquery.treeTable.css" media="all">
     
 
     <style type="text/css">
		.Validform_checktip{
			display: inline-block;
			width: 90px;
			overflow: visible;
		}
		body{
			padding: 20px;
		}
	</style>
  </head>
  
  <body>
    
	<div class="layui-field-box">
		  <form id="addOrUpdateForm" class="layui-form" method="post" action="<%=basePath%>/menu/update.do">
		  <input type="hidden" id="Id" name="Id" value="0" class="layui-input">
		   <input type="hidden" id="oldParentId"  value="0" class="layui-input">
		  <div class="layui-form-item" >
		    <label class="layui-form-label"><spring:message code="menu.parent" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline">
		      <select id="parentId" name="parentId"  lay-search>
		        <option value="0"><spring:message code="menu.geng" /></option>
		      </select>
		    </div>
		      <div class="Validform_checktip"></div>
		  </div>
		  <div class="layui-form-item" >
		    <label class="layui-form-label"><spring:message code="menu.name" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline" >
		      <input type="text" id="menuName" name="menuName" datatype="s1-20"   placeholder="<spring:message code='menu.pname' />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  <div class="layui-form-item" >
		    <label class="layui-form-label"><spring:message code="menu.nameen" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline" >
		      <input type="text" id="menuNameEn" name="menuNameEn" datatype="s1-50"   placeholder="<spring:message code='menu.pnameen' />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  <div class="layui-form-item" >
		    <label class="layui-form-label"><spring:message code="menu.url" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline" >
		      <input type="text" id="menuUrl" name="menuUrl"  datatype="s1-255|///" placeholder="<spring:message code='menu.purl' />" autocomplete="off" class="layui-input">
		    </div>
		     <div class="Validform_checktip"></div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="menu.sort" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="sortNum" name="sortNum" value="0"  placeholder="<spring:message code='menu.psort' />" autocomplete="off" class="layui-input">
		    </div>
		     <div class="Validform_checktip"></div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="menu.show" /></label>
		    <div class="layui-input-inline">
		      <input type="radio" id="isShow1" name="isShow" value="1" title="<spring:message code='menu.show.1' />" checked>
		      <input type="radio" id="isShow2" name="isShow" value="2" title="<spring:message code='menu.show.2' />">
		    </div>
		     <div class="Validform_checktip"></div>
		  </div>
		   <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="menu.html" /></label>
		    <div class="layui-input-inline">
		      <input type="text" id="htmlCode" name="htmlCode"  placeholder="<spring:message code='menu.phtml' />" autocomplete="off" class="layui-input">
		    </div>
		     <div class="Validform_checktip"></div>
		  </div>
		  
		 <div class="layui-form-item" style="margin-bottom: 0px" id="info1">
		    <label class="layui-form-label"><spring:message code="task.createtime" />：</label>
		    <label class="layui-form-label time-label"  id="createTime" style="width:140px"></label>
		    <label class="layui-form-label" style="width:100px"><spring:message code="menu.updatetime" />：</label>
		    <label class="layui-form-label time-label"  id="modifyTime" style="width:140px"></label>
		  </div>
		  </form>
		  <div class="layui-form-item">
		    <div class="layui-input-inline" style="left: 50px;">
		      <button class="layui-btn" type="submit" form="addOrUpdateForm" ><spring:message code="op.submit" /></button>
		 	 <button  class="layui-btn layui-btn-primary" onclick="parent.layer.close(parent.layer.getFrameIndex(window.name));"><spring:message code="op.cancel" /></button>
		    </div>
		  </div>
		 
		 </div>
	
	<script type="text/javascript">
	
	layui.use('form', function(){
	  	var form = layui.form();
	});
	
	$(document).ready(function(){
		ajaxSubmitFormAndValid("addOrUpdateForm",function(data){
			if(data.code == 200) {
				parent.layer.msg("操作成功");
				
				var parentId =  $("#parentId").val();
				var oldParentId =  $("#oldParentId").val();
				if(parentId == oldParentId) {
					parent.getChildsNode(parentId);
				} else if(parentId == 0 || oldParentId == 0) {
					parent.getChildsNode(0);
				} else {
					parent.getChildsNode(parentId);
					parent.getChildsNode(oldParentId);
				}
				parent.layer.close(parent.layer.getFrameIndex(window.name));
				
			}
		},function(msg,o,cssctl){
			if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				console.log(o.obj);	
				var objtip=o.obj.parent(".layui-input-inline").nextAll(".Validform_checktip");
				if(objtip.length > 1) {
					objtip = objtip[0];
				}	
				cssctl(objtip,o.type);
				objtip.text(msg);
			}
		});
	})
	
	
	</script>
	
	   <script type="text/javascript">
    
     	function getMenuAll(){
			commmonAjax({
				url : '<%=basePath%>/menu/getMenuAll.do',
				success : function(result) {
					if (result.code == 200) {
						var data = result.data;
						if(data.length > 0) {
							$.each(data, function (i){
								var item = data[i];
								$("select[name=parentId]").append("<option value='"+item.id+"'>"+item.menuName+"</option>");
							});
							var id = "${param.id}";
							if(id!=null && id !=undefined && id > 0) {
								update(id);
							}
						}
					}
				}
			});
		}
     	
     	$(document).ready(function(){
     		getMenuAll();
     	})
     	
    	//进入修改
    	function update(id){
    		commmonAjax({
				url : '<%=basePath%>/menu/getMenu.do',
				data : {Id:${param.id}},
				success : function(result) {
					if (result.code == 200) {
						var data = result.data;
						$("#Id").val(data.id);
						$("select[name=parentId]").val(data.parentId);
						$("#oldParentId").val(data.parentId);
						$("#menuName").val(data.menuName);
						$("#menuNameEn").val(data.menuNameEn);
						$("#menuUrl").val(data.menuUrl);
						$("#sortNum").val(data.sortNum);
						$("#htmlCode").val(data.htmlCode);
						if(data.isShow ==1){
							$("#isShow1").prop('checked',true);
						} else {
							$("#isShow2").prop('checked',true);
						}
						if(data.createTime != undefined){
							$("#createTime").html(dateToStr(data.createTime,"yyyy-MM-dd hh:mm:ss"));
						}
						if(data.modifyTime != undefined){
							$("#modifyTime").html(dateToStr(data.modifyTime,"yyyy-MM-dd hh:mm:ss"));
						}
						layui.form().render("select");
					} 
				}
			});
    	}
		
    </script>
	
  </body>
</html>
