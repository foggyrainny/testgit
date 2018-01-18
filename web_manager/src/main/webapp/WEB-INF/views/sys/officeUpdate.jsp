<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	
    <title>机构管理</title>
  	
  	<%@include file="../../../resource.jsp" %>
    
   <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>js/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>/js/bootstrap-fileinput/themes/explorer/theme.css" media="all" rel="stylesheet" type="text/css"/>
    <script src="<%=basePath%>js/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/bootstrap-fileinput/js/locales/zh.js" type="text/javascript"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
    <style type="text/css">
    	.site-demo-upload img {
		    width: 200px;
		    height: 200px;
		}
		.layui-upload-button input {
		    position: absolute;
		    left: 0;
		    top: 0;
		    z-index: 10;
		    font-size: 100px;
		    width: 100%;
		    height: 100%;
		}
		.Validform_checktip{
			display: inline-block;
			width: 90px;
			overflow: visible;
		}
		.layui-form-label{
			width: 150px;
		}
		body{
			padding: 20px;
		}
		.progress {
			display: none;
		}
		a:hover {
		    color: #777;
		}
		a, a:hover, a:focus {
			color: #333333;
		    text-decoration: none;
		    outline: none;
		    -webkit-transition: all 0.3s;
		    transition: all 0.3s;
		    -moz-transition: all 0.3s;
		    -o-transition: all 0.3s;
		}
	</style>
  </head>
  
  <body>
    <div id="addOrUpdate">
		<fieldset class="layui-elem-field">
		  <div class="layui-field-box">
		  <form id="menuObj" class="layui-form" method="post" action="doAddOrUpdate.do">
		  
		  <input type="hidden" id="Id" name="Id" value="">
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.superior" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline">
		      <input type="text" id="parentId" datatype="*1-100"  autocomplete="off" placeholder="" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item area_select">
			    <label class="layui-form-label"><spring:message code="office.parent" /></label><span class="required_field" >*</span>
			    <div class="layui-input-inline">
			      <select class="area-prov" lay-search="">
			        <option value=''><spring:message code="office.provincial" /></option>
			      </select>
			    </div>
			    <div class="layui-input-inline"  lay-search="" >
			      <select class="area-district" >
			        <option value=''><spring:message code="office.city" /></option>
			      </select>
			    </div>
			   <div class="layui-input-inline" lay-search="" >
			      <select  class="area-county" name="areaId" datatype="*1-100" nullmsg = "">
			        <option value='' ><spring:message code="office.area" /></option>
			      </select>
			    </div>
			   <div class="Validform_checktip"></div>
		  </div>
		  
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.name" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline" >
		      <input type="text" id="name" name="name" datatype="s1-100" placeholder="<spring:message code="office.pname" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.code" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline" >
		      <input type="text" id="code" name="code" datatype="s1-100" placeholder="<spring:message code="office.pcode" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.type" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline">
		      <select id="type" name="type" >
		        <option value="1" selected><spring:message code="office.type.1" /></option>
		        <option value="2"><spring:message code="office.type.2" /></option>
		      </select>
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.level" /></label>
		    <div class="layui-input-inline">
		      <select id="level" name="level" >
		        <option value="1" selected><spring:message code="office.level.1" /></option>
		        <option value="2"><spring:message code="office.level.2" /></option>
		        <option value="3"><spring:message code="office.level.3" /></option>
		        <option value="4"><spring:message code="office.level.4" /></option>
		      </select>
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.address" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="address" name="address" placeholder="<spring:message code="office.paddress" />" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.zip" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="zipCode" name="zipCode" datatype="/^\s*$/|p" placeholder="<spring:message code="office.pzip" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.master" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="master" name="master"  placeholder="<spring:message code="office.pmaster" />" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.phone" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="phone" name="phone" datatype="/^\s*$/|m" placeholder="<spring:message code="office.pphone" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.fax" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="fax" name="fax" datatype="/^\s*$/|/^(\d{3,4}-)?\d{7,8}$/" datatype="/^\s*$/|m" placeholder="<spring:message code="office.pfax" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.email" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="email" name="email" datatype="/^\s*$/|e" placeholder="<spring:message code="office.pemail" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.url" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="url" name="url" datatype="/^\s*$/|url" placeholder="<spring:message code="office.purl" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		   <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.logo" /></label>
		    <div class="layui-input-inline" style="width: 400px;">
		    	<input id="file-upload" name="file-upload" class="file" type="file" data-min-file-count="1" >
		    	<input type="hidden" id="logoUrl" name="logoUrl" value="" class="layui-input">
			</div>
		  </div>
		  
		   <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.xc" /></label>
		    <div class="layui-input-inline" style="width: 400px;">
		    	<input id="file-upload2" class="file" type="file" data-min-file-count="1" >
		    	<input type="hidden" id="xcUrl" name="xcUrl" value="" class="layui-input">
			</div>
		  </div>
		  
		   <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.background" /></label>
		    <div class="layui-input-inline" style="width: 400px;">
		    	<input id="file-upload3" class="file" type="file" data-min-file-count="1" >
			    <input type="hidden" id="backgroundUrl" name="backgroundUrl" value="" class="layui-input">
			</div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label tb"><spring:message code="op.remarks" /></label>
		    <div class="layui-input-inline" style="width: 40%;">
		     <textarea id="remark" name="remark"  class="layui-textarea"></textarea>
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-filter="formDemo" type="submit"><spring:message code="op.submit" /></button>
		      <button type="reset" class="layui-btn layui-btn-primary" onclick="doClose();"><spring:message code="op.cancel" /></button>
		    </div>
		  </div>
		  
		</form>
		 </div>
		</fieldset>
	</div>
  </body>
  <script type="text/javascript">
    $(function (){
		officeTree({selector:"#parentId",inputName:"parentId",async: false,url:"<%=basePath%>/selector/officeTree.do?type=all",
	   			dataFilter:function(treeId, parentNode, responseData){
					return responseData.data;
				}
			});
	 });	
  </script>
  <script type="text/javascript">
  	var officeId = ${officeId };
  	var parentId = ${parentId };
	
  	window.onload = function (){
  		if(officeId == -1){//新增
  			$("#Id").val(-1);
  			fileUploadAdd();
  		    areaSelect();
  			treeChkDisabledThis(parentId,"parentId_tree");
  	     }else {//修改
	  		$.ajax({
					url : 'getOffice.do',
					type : 'POST',
					data : {id:officeId},
					async : false,
					dataType : 'json',
					timeout : 10000,
					error : function() {layer.msg('<spring:message code="prompted.request" />');},
					success : function(result) {
						if (result.code == 200) {
							var data = result.data;
							$("#Id").val(data.id);
							//$("#parentId").val(data.parentId);
							//$("#areaId").val(data.areaId);
							$("#name").val(data.name);
							$("#code").val(data.code);
							$("#type").val(data.type);
							$("#level").val(data.level);
							$("#address").val(data.address);
							$("#zipCode").val(data.zipCode);
							$("#master").val(data.master);
							$("#phone").val(data.phone);
							$("#fax").val(data.fax);
							$("#email").val(data.email);
							$("#url").val(data.url);
							$("#logoUrl").val(data.logoUrl);
							$("#xcUrl").val(data.xcUrl);
							$("#backgroundUrl").val(data.backgroundUrl);
							$("#remark").val(data.remark);
							
							//归属地区
							areaSelect({
								provInit:function(selector,form,obj){
									console.log("provInit:function");
									$(selector).find("option[value="+ data.provEn +"]").attr("selected","selected");
									form.render('select');
									layui.event.call(this, 'form', 'select', {elem: selector,value:$(selector).val(),othis:null});
								},
								districtInit:function(selector,form){
									console.log("districtInit");
									console.log(selector);
									$(selector).find("option[value="+ data.districtEn +"]").attr("selected","selected");
									form.render('select');
									layui.event.call(this, 'form', 'select', {elem: selector,value:$(selector).val(),othis:null});
								},
								countyInit:function(selector,form){
									console.log("countyInit");
									$(selector).find("option[value="+ data.areaId +"]").attr("selected","selected");
									form.render('select');
								}
							});
							
							fileUploadUpd(data.logoUrl,data.xcUrl,data.backgroundUrl);
							//layui.form().render();
							//上级机构
							treeChkDisabled(data.id,data.parentId,"parentId_tree");
						} else {
							layer.msg(result.msg);
						}
					}
				});
		}
  	}
  
  	function fileUploadAdd(){
	 		$("#file-upload").fileinput({
		        language:'zh',//语言
		        dropZoneEnabled:false,
		        uploadUrl:'<%=basePath%>/upload/image.do',
		        maxFileCount: 1,
		        allowedFileExtensions: ['jpg', 'png', 'gif'],
		        overwriteInitial: false,
		        showRemove:false,
		        dropZoneEnabled: false,  
		        initialPreviewShowDelete:true,  
		        initialPreviewAsData: true,
		        showPreview:true,//是否预览图片
		        showUpload:false,//是否显示上传按钮
		        autoReplace:true//是否替换当前图片
		    }).on("filebatchselected", function(event, files) {
		    	// 如果要自动上传， 请使用以下注解
		    	//$(this).selection.clear();
		    	$(this).fileinput("upload");
		    	$(this).disable();
		    }).on("fileuploaded", function(event, data,pid,i) {
		    	console.log("fileuploaded");
		    	data = data.response;
		    	console.log(data);
		    	console.log(event);
		    	if(data.code == 200){
		    		//$(this).next().val(data.data);
		    		$("#logoUrl").val(data.data);
		    	}
		    }).on("filedeleted",function(vKey, jqXHR, extraData){
		    }).on("filecleared",function(event){
		    	$(".image-upload-input").remove();
		    }).on("filesuccessremove",function(event,id,obj){
		    	console.log("filesuccessremove 上传成功后移除");
		    	$("#"+id).remove();
		    }).on("filebatchuploadsuccess",function(event,data){
		    	console.log("filebatchuploadsuccess");
		    	console.log(data);
	    	});	
	    	
	    	
	    	$("#file-upload2").fileinput({
		        language:'zh',//语言
		        dropZoneEnabled:false,
		        uploadUrl:'<%=basePath%>/upload/image.do',
		        maxFileCount: 1,
		        allowedFileExtensions: ['jpg', 'png', 'gif'],
		        overwriteInitial: false,
		        showRemove:false,
		        dropZoneEnabled: false,  
		        initialPreviewShowDelete:true,  
		        initialPreviewAsData: true,
		        showPreview:true,//是否预览图片
		        
		        //showCaption:false,//是否显示jianjie
		        showUpload:false,//是否显示上传按钮
		        autoReplace:true//是否替换当前图片
		    }).on("filebatchselected", function(event, files) {
		    	// 如果要自动上传， 请使用以下注解
		    	//$(this).selection.clear();
		    	$(this).fileinput("upload");
		    	$(this).disable();
		    }).on("fileuploaded", function(event, data,pid,i) {
		    	console.log("fileuploaded");
		    	data = data.response;
		    	console.log(data);
		    	console.log(event);
		    	if(data.code == 200){
		    		//$(this).next().val(data.data);
		    		$("#xcUrl").val(data.data);
		    	}
		    }).on("filedeleted",function(vKey, jqXHR, extraData){
		    }).on("filecleared",function(event){
		    	$(".image-upload-input").remove();
		    }).on("filesuccessremove",function(event,id,obj){
		    	console.log("filesuccessremove 上传成功后移除");
		    	$("#"+id).remove();
		    }).on("filebatchuploadsuccess",function(event,data){
		    	console.log("filebatchuploadsuccess");
		    	console.log(data);
	    	});	
	    	
	    	$("#file-upload3").fileinput({
		        language:'zh',//语言
		        dropZoneEnabled:false,
		        uploadUrl:'<%=basePath%>/upload/image.do',
		        maxFileCount: 1,
		        allowedFileExtensions: ['jpg', 'png', 'gif'],
		        overwriteInitial: false,
		        showRemove:false,
		        dropZoneEnabled: false,  
		        initialPreviewShowDelete:true,  
		        initialPreviewAsData: true,
		        showPreview:true,//是否预览图片
		        showUpload:false,//是否显示上传按钮
		        autoReplace:true//是否替换当前图片
		    }).on("filebatchselected", function(event, files) {
		    	// 如果要自动上传， 请使用以下注解
		    	//$(this).selection.clear();
		    	$(this).fileinput("upload");
		    	$(this).disable();
		    }).on("fileuploaded", function(event, data,pid,i) {
		    	console.log("fileuploaded");
		    	data = data.response;
		    	console.log(data);
		    	console.log(event);
		    	if(data.code == 200){
		    		//$(this).next().val(data.data);
		    		$("#backgroundUrl").val(data.data);
		    	}
		    }).on("filedeleted",function(vKey, jqXHR, extraData){
		    }).on("filecleared",function(event){
		    	$(".image-upload-input").remove();
		    }).on("filesuccessremove",function(event,id,obj){
		    	console.log("filesuccessremove 上传成功后移除");
		    	$("#"+id).remove();
		    }).on("filebatchuploadsuccess",function(event,data){
		    	console.log("filebatchuploadsuccess");
		    	console.log(data);
	    	});	
	 	}
	 	
	 	function fileUploadUpd(logoUrl,xcUrl,backgroundUrl){
	 		$("#file-upload").fileinput({
	 			initialPreview: [
				    logoUrl
				],
				initialPreviewAsData: true,
		        language:'zh',//语言
		        dropZoneEnabled:false,
		        uploadUrl:'<%=basePath%>/upload/image.do',
		        maxFileCount: 1,
		        allowedFileExtensions: ['jpg', 'png', 'gif'],
		        overwriteInitial: false,
		        showRemove:false,
		        dropZoneEnabled: false,  
		        initialPreviewShowDelete:true,  
		        initialPreviewAsData: true,
		        showPreview:true,//是否预览图片
		        showUpload:false,//是否显示上传按钮
		        autoReplace:true//是否替换当前图片
		    }).on("filebatchselected", function(event, files) {
		    	// 如果要自动上传， 请使用以下注解
		    	//$(this).selection.clear();
		    	$(this).fileinput("upload");
		    	$(this).disable();
		    }).on("fileuploaded", function(event, data,pid,i) {
		    	console.log("fileuploaded");
		    	data = data.response;
		    	console.log(data);
		    	console.log(event);
		    	if(data.code == 200){
		    		//$(this).next().val(data.data);
		    		$("#logoUrl").val(data.data);
		    	}
		    }).on("filedeleted",function(vKey, jqXHR, extraData){
		    }).on("filecleared",function(event){
		    	$(".image-upload-input").remove();
		    }).on("filesuccessremove",function(event,id,obj){
		    	console.log("filesuccessremove 上传成功后移除");
		    	$("#"+id).remove();
		    }).on("filebatchuploadsuccess",function(event,data){
		    	console.log("filebatchuploadsuccess");
		    	console.log(data);
	    	});	
	    	
	    	
	    	$("#file-upload2").fileinput({
	    		initialPreview: [
				    xcUrl
				],
				initialPreviewAsData: true,
		        language:'zh',//语言
		        dropZoneEnabled:false,
		        uploadUrl:'<%=basePath%>/upload/image.do',
		        maxFileCount: 1,
		        allowedFileExtensions: ['jpg', 'png', 'gif'],
		        overwriteInitial: false,
		        showRemove:false,
		        dropZoneEnabled: false,  
		        initialPreviewShowDelete:true,  
		        initialPreviewAsData: true,
		        showPreview:true,//是否预览图片
		        
		        //showCaption:false,//是否显示jianjie
		        showUpload:false,//是否显示上传按钮
		        autoReplace:true//是否替换当前图片
		    }).on("filebatchselected", function(event, files) {
		    	// 如果要自动上传， 请使用以下注解
		    	//$(this).selection.clear();
		    	$(this).fileinput("upload");
		    	$(this).disable();
		    }).on("fileuploaded", function(event, data,pid,i) {
		    	data = data.response;
		    	if(data.code == 200){
		    		//$(this).next().val(data.data);
		    		$("#xcUrl").val(data.data);
		    	}
		    }).on("filedeleted",function(vKey, jqXHR, extraData){
		    }).on("filecleared",function(event){
		    	$(".image-upload-input").remove();
		    }).on("filesuccessremove",function(event,id,obj){
		    	console.log("filesuccessremove 上传成功后移除");
		    	$("#"+id).remove();
		    }).on("filebatchuploadsuccess",function(event,data){
		    	console.log(data);
	    	});	
	    	
	    	$("#file-upload3").fileinput({
	    		initialPreview: [
				    backgroundUrl
				],
				initialPreviewAsData: true,
		        language:'zh',//语言
		        dropZoneEnabled:false,
		        uploadUrl:'<%=basePath%>/upload/image.do',
		        maxFileCount: 1,
		        allowedFileExtensions: ['jpg', 'png', 'gif'],
		        overwriteInitial: false,
		        showRemove:false,
		        dropZoneEnabled: false,  
		        initialPreviewShowDelete:true,  
		        initialPreviewAsData: true,
		        showPreview:true,//是否预览图片
		        showUpload:false,//是否显示上传按钮
		        autoReplace:true//是否替换当前图片
		    }).on("filebatchselected", function(event, files) {
		    	// 如果要自动上传， 请使用以下注解
		    	//$(this).selection.clear();
		    	$(this).fileinput("upload");
		    	$(this).disable();
		    }).on("fileuploaded", function(event, data,pid,i) {
		    	data = data.response;
		    	if(data.code == 200){
		    		//$(this).next().val(data.data);
		    		$("#backgroundUrl").val(data.data);
		    	}
		    }).on("filedeleted",function(vKey, jqXHR, extraData){
		    }).on("filecleared",function(event){
		    	$(".image-upload-input").remove();
		    }).on("filesuccessremove",function(event,id,obj){
		    	console.log("filesuccessremove 上传成功后移除");
		    	$("#"+id).remove();
		    }).on("filebatchuploadsuccess",function(event,data){
		    	console.log("filebatchuploadsuccess");
		    	console.log(data);
	    	});	
	 	}
	 	
	 	//监听表单提交
    	layui.use('form', function(){
		  	var form = layui.form();
		  	form.on('submit', function(data){
		    	/* layer.msg(JSON.stringify(data.field));
		    	doAddOrUpdate(data.field); */
		    	return false;
		  	}); 
		});
		
	 	
	 	
	 	$(document).ready(function(){
			ajaxSubmitFormAndValid("menuObj",function(data){
				if(data.code == 200) {
					history.go(0);
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
					parent.layer.msg("<spring:message code="prompted.success" />");
				} else {
					layer.msg(data.msg);
				}
			},function(msg,o,cssctl){
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				console.log(o.obj);	
				var objtip=o.obj.parent(".layui-input-inline,.layui-input-block").nextAll(".Validform_checktip");
				if(objtip.length > 1) {
					objtip = objtip[0];
				}	
				cssctl(objtip,o.type);
					objtip.text(msg);
				}else{
					/* var objtip=o.obj.find("#msgdemo");
					cssctl(objtip,o.type);
					objtip.text(msg); */
				}
			});
		});
		
		layui.use('upload', function(){
		  layui.upload({
		    url: '' //上传接口
		    ,success: function(res){ //上传成功后的回调
		      console.log(res)
		    }
		  });
		  
		  layui.upload({
		    url: '/test/upload.json'
		    ,elem: '#test' //指定原始元素，默认直接查找class="layui-upload-file"
		    ,method: 'get' //上传接口的http类型
		    ,success: function(res){
		      LAY_demo_upload.src = res.url;
		    }
		  });
		});
		
		function doAddOrUpdate(data){
			$.ajax({
				url : 'doAddOrUpdate.do',
				type : 'POST',
				data : data,
				async : false,
				dataType : 'json',
				timeout : 10000,
				error : function() {layer.msg('<spring:message code="prompted.request" />');},
				success : function(result) {
					if (result.code == 200) {
						layer.msg("<spring:message code='prompted.success' />");
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index);
						//重新加载数据及树桩表格
						//getEntityList();
			  			parent.nowEntity();
					} else {
						layer.msg(result.msg);
					}
				}
			});
		}
		
		//新增数据，添加下级时使用
    	function treeChkDisabledThis(parentId,treeId){
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			if(parentId == null || parentId == 0) {
				return;
			}
			var nodes = zTree.getNodeByParam("id", parentId);
			// 选中父节点
			zTree.checkNode(nodes, true, true,true);
			$("#"+treeId).trigger("tree.select.change");
    	}
    	
    	//修改数据时    设置选中   且自身及以下不可选
    	function treeChkDisabled(id,parentId,treeId){
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			// 设置自身节点不能选
			zTree.setChkDisabled(zTree.getNodeByParam("id", id),true,false ,true );
			if(parentId == null || parentId == 0) {
				return;
			}
			var nodes = zTree.getNodeByParam("id", parentId);
			// 选中父节点
			zTree.checkNode(nodes, true, true,true);
			$("#"+treeId).trigger("tree.select.change");
    	}
    	
    	function doClose(){
    		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index);
    	}
  </script>
</html>
