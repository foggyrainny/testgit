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
	<title>修改电站参数信息</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<%@include file="../../../resource.jsp" %>
    <script src="<%=basePath%>js/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/bootstrap-fileinput/js/locales/zh.js" type="text/javascript"></script>
	
	<style type="text/css">
		 .required_field{
		 	color:red;
		 	font-size:25px;
		 	margin-left:-10px;
		 }
		 .remark_field{
			float: left;
		    color: red;
		    font-size: 12px;
		 }
		
		 .layui-form-label{
				width:140px;
		 }
		 .Validform_checktip{
			display: inline-block;
			width: 90px;
			overflow: visible;
			 margin-left: -10px;
   			 margin-top: 10px;
	
		 }
	</style>
	
</head>
  
  <body style="padding:30px">
 		<section >
	  <form class="layui-form" action="<%=basePath%>/parm/power_up.do" method="post" id="create_form" >
 		<div class="layui-form-item">
 
  		<div class="layui-inline">
		    <label class="layui-form-label  l4"><spring:message code="parameter.name" /></label>
		    <div class="layui-input-inline">
      			<input type="text" name="name" value="${sysParm.name}"   style="width:300px;" class="layui-input" disabled="">
		    </div>
		   </div>
		   
	<c:if test="${sysParm.id == 'file_temp_dir' ||sysParm.id == 'update_file_temp_dir'}">
	<div class="layui-inline">
		 <label class="layui-form-label l3" ><spring:message code="parameter.value" /></label>
	     <div class="layui-input-inline" style="width: 135px; " id="v1">
	     <input type="text" name="value"  id="value" value="${sysParm.value}" datatype="/^[\u4E00-\u9FA5A-Za-z0-9_|=#^%!@$&*()~%+{}\/'[\]]{0,100}$/"     autocomplete="off" class="layui-input">
	     </div>
		<div id="errorAccount" style="color:red;display:inline;margin-left:5px;font-size: 10px;"></div>
	    <span class="remark_field" id="error" style="color:red;display:inline;margin-left:100px;"></span>
	     <div class="Validform_checktip"></div>
	</div>
</c:if>

	<c:if test="${sysParm.id == 'ftp_http_url' ||sysParm.id == 'update_ftp_http_url'}">
	<div class="layui-inline">
		 <label class="layui-form-label l3" ><spring:message code="parameter.value" /></label>
	     <div class="layui-input-inline" style="width: 135px; " id="v1">
	     <input type="text" name="value"  id="value" value="${sysParm.value}" datatype="/[a-zA-z]+://[^\s]*/"  errormsg="<spring:message code="parameter.error.4" />"   autocomplete="off" class="layui-input">
	     </div>
		<div id="errorAccount" style="color:red;display:inline;margin-left:5px;font-size: 10px;"></div>
	    <span class="remark_field" id="error" style="color:red;display:inline;margin-left:100px;"></span>
	     <div class="Validform_checktip"></div>
	</div>
</c:if>

<c:if test="${sysParm.id != 'file_temp_dir' && sysParm.id != 'update_file_temp_dir'}">
	<c:if test="${sysParm.id != 'ftp_http_url' && sysParm.id != 'update_ftp_http_url'}">
	
	  	<div class="layui-inline">
	    <label class="layui-form-label l3" ><spring:message code="parameter.value" /></label>
	    
	    <div class="layui-input-inline" style="width: 135px; " id="v1">
	      <input type="text" name="value"  id="value" value="${sysParm.value}" datatype="/^\d+(\.\d+)?$/"  errormsg="<spring:message code="parameter.error.1" />"   onblur="compare()"  autocomplete="off" class="layui-input">
	    </div>
	    
	      <div class="layui-form-mid" id="v2">~</div>
	      
	    <div class="layui-input-inline" style="width: 135px;" id="v3">
	      <input type="text"  name="value2"  id="value2" value="${sysParm.value2}"   datatype="/^\d+(\.\d+)?$/" min="" errormsg="<spring:message code="parameter.error.1" />" onblur="compare()" autocomplete="off"class="layui-input">
	    </div> 
	    
	    <div id="errorAccount" style="color:red;display:inline;margin-left:5px;font-size: 10px;"></div>
	    <span class="remark_field" style="margin-left: 120px;"><spring:message code="parameter.error.2" /></span> 
	    <span class="remark_field" id="error" style="color:red;display:inline;margin-left:100px;"></span>
	     <div id="aaa"class="Validform_checktip"></div>
	     
   	</div> 
	
	
	</c:if>
</c:if>
        
        <div class="layui-form-item layui-form-text">
    		<label class="layui-form-label l4"><spring:message code="parameter.description" /></label>
    		<div class="layui-input-block">
      		<textarea  class="layui-textarea"rows="3" cols="48" name="remark" style="width:300px;">${sysParm.remark}</textarea>
    		</div>
  		</div>
       
  		 <input type="hidden" name="id" value="${sysParm.id}">
     	 <input type="hidden"name="isrange"  id="range" value="${sysParm.isRange}">
      </div>
	</form>
	
		    <div class="layui-input-block">
		      <button id="bbb"class="layui-btn" type="submit" form="create_form" style="margin-left: 80px" id="btn"><spring:message code="op.submit" /></button>
		      <button type="" class="layui-btn layui-btn-primary" onclick="cancel()"><spring:message code="op.return" /></button>
		    </div>
  </section>
  <script type="text/javascript">

  		//提交表单
		$(document).ready(function(){
			var flag = $("input#range").val();
			if(flag!= 1){
			$("#v1").width("300px");
			$("#v2").remove();
			$("#v3").remove();
			}
			ajaxSubmitFormAndValid("create_form",function(data){
				if(data.code == 200) {
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
					parent.layer.msg("<spring:message code="op.modify.success" />");
					parent.queryData();
				}else {
					layer.msg(data.msg)
				}
			},function(msg,o,cssctl){
				if(!o.obj.is("form")){
					console.log(o.obj);	
					var objtip=o.obj.parent(".layui-input-inline").nextAll(".Validform_checktip");
					console.log(objtip);
					if(objtip.length > 1) {
						objtip = objtip[0];
					}	
					cssctl(objtip,o.type);
					objtip.text(msg);
				}else{
					var objtip=o.obj.find("#msgdemo");
					cssctl(objtip,o.type);
					objtip.text(msg);
				}
			});
		})
		//比较
		function compare(){
		var value1 =$("#value").val();
		var value2 =$("#value2").val();
		if(value1>value2){
			   $("#error").html("*<spring:message code="parameter.error.3" />");
			   $("#error").show();
			   $("#aaa").hide();
			   $("#bbb").attr({"disabled":"disabled"});
		}else{
			   $("#error").hide();
			   $("#aaa").show();
			 $("#bbb").removeAttr("disabled");
		}
	}
  		
	//取消	
	function cancel(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
  </script>

  </body>
</html>
