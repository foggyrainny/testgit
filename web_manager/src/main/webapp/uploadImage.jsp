<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String basePath2 = request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>电站管理</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<%@include file="../../../resource.jsp" %>
	
	
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>js/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>/js/bootstrap-fileinput/themes/explorer/theme.css" media="all" rel="stylesheet" type="text/css"/>
    <script src="<%=basePath%>js/bootstrap-fileinput/js/fileinput.js?t=xxxdsd" type="text/javascript"></script>
    <script src="<%=basePath%>js/bootstrap-fileinput/js/locales/zh.js" type="text/javascript"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	
	<style type="text/css">
		.layui-form-label{
			width:140px;
		}
		.Validform_checktip{
			display: inline-block;
			width: 90px;
			overflow: visible;
		}
	</style>
</head>
<body style="padding: 30px;">


<section >


  
    <div class="layui-form-item">
	    <div class="layui-inline">
	      	<label class="layui-form-label"><spring:message code="station.hpimage" />：</label>
  			<div class="layui-input-inline" style="width: 500px;">   
  				<!-- <img id="upload_img" src="" width="400px" height="250px"> -->
    		<!-- 	<input type="file" name="file" class="layui-upload-file" id="test" multiple="multiple"> -->
    			<input id="file-upload" name="file-upload" class="file" type="file" size="1"  data-min-file-count="1">
    		</div>
	    </div>
  </div>
   
	  
</section>


<script>
	$(document).on('ready', function () {
		
		$("#file-upload").fileinput({
	        language:'zh',
	        dropZoneEnabled:false,
	        uploadUrl:'<%=basePath%>/upload/image.do',
	        maxFileCount:1,
	        minFileCount:1,
	        allowedFileExtensions: ['jpg', 'png', 'gif'],
	        overwriteInitial: true,
	        showRemove:false,
	        showUpload:false,
	        maxPreviewSize:1,
	        dropZoneEnabled: false,  
	        initialPreviewShowDelete:true, 
	        validateInitialCount:true,
	        initialPreviewAsData: true<%-- ,
	        initialPreview: [  // 预加载
	             "http://lorempixel.com/1920/1080/transport/1",
	             "http://lorempixel.com/1920/1080/transport/2",
	             "http://lorempixel.com/1920/1080/transport/3",
	        ], 
	        initialPreviewConfig: [  // 预加载配置信息
	        	{url: '<%=basePath%>/upload/image.do', key: 1},
	            {url: '<%=basePath%>/upload/image.do', key: 2},
	            {url: '<%=basePath%>/upload/image.do', key: 3},
	        ] --%>
	    }).on("filebatchselected", function(event, files) {
	    	// 如果要自动上传， 请使用以下注解
	    	$(this).fileinput("upload");
	    }).on("fileuploaded", function(event, data,pid,i) {  // 图片上传后回调
	    	if(data.code == 200){
	    		$(document).append("<input type='hidden'  class='image-upload-input'  id='"+pid+"'  form='create_from' name='imageUrl'  value='"+data.data+"'  />");
	    	}
	   	 try{
	   		$(this).fileinput("disabled"); 
	   		 }catch(err){}	
	    
	    }).on("filedeleted",function(vKey, jqXHR, extraData){ // 图片预加载的图片删除回调
	    }).on("filecleared",function(event){   // 图片清空回调
	    	$(".image-upload-input").remove();
	    
	    }).on("filesuccessremove",function(event,id,obj){  // 单个文件删除回调
	    	console.log("filesuccessremove 上传成功后移除");
	    	$("#"+id).remove();
	    	 $(this).fileinput("enable");
	    });
		
		
		/* {caption: array_element.fileIdFile.fileName, // 展示的文件名  
            width: '120px',   
            url: '/eim/project/deleteFile.do', // 删除url  
            key: array_element.id, // 删除是Ajax向后台传递的参数  
            extra: {id: 100}  
        } */
		
		
    });
</script>


</body>
</html>