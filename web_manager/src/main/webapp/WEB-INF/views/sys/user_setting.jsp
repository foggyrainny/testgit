<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="../../../resource.jsp" %>
    <title>个人设置</title>
   
   <style type="text/css">
   	
   .layui-form-label{
   		width:130px;
   }
   </style>
    <script type="text/javascript">
    	
	$(document).ready(function(){
		ajaxSubmitFormAndValid("form2",function(data){
			if(data.code == 200) {
				history.go(0);
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				parent.layer.msg("<spring:message code="prompted.success" />");
			} else {
				layer.msg(data.msg);
			}
		},function(msg,o,cssctl){
			if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
			console.log(o.obj);	
			var objtip=o.obj.parent(".layui-input-inline,.layui-input-block").nextAll(".Validform_checktip");
			if(objtip.length > 1) {
				objtip = objtip[0];
			}	
			cssctl(objtip,o.type);
				objtip.text(msg);
			}else{
			}
		});
	})
    </script>
    
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
  
<body >

<div style="text-align: center;" >
	
 	
	<div class="layui-field-box" style="margin: auto;">
	
		
		  
	<form id="form2" class="layui-form" action="<%=basePath%>user/usersetting.do" method="post">	  
	<fieldset class="layui-elem-field" style="width: 95%;margin: 10px;padding: 10px"> 	   
	<legend><spring:message code="Infomodif" /></legend>
	
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="user.loginname" />:</label>
		    <div class="layui-input-inline" >
		      <input type="text"  readonly="readonly" disabled="disabled"  value="${user.loginName }"   class="layui-input">
		    </div>
		    <label class="layui-form-label"><spring:message code="user.name" />：</label>
		    <div class="layui-input-inline" >
		      <input type="text" readonly="readonly"  disabled="disabled"   value="${user.userName}"  class="layui-input" >
		    </div>
		    
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="user.oldpassword" />：</label>
		    <div class="layui-input-inline" >
		      <input type="password"  name="oldPass" datatype="*6-15" ignore="ignore" placeholder="<spring:message code="tips.check.npwd" />"    class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		    <div class="layui-form-mid layui-word-aux"><spring:message code="tips.check.npwd" /></div>
		    
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="user.newpassword" />：</label>
		    <div class="layui-input-inline" >
		      <input type="password" id="password" name="password"  datatype="*6-15" ignore="ignore"  placeholder="<spring:message code="tips.check.npwd" />" autocomplete="off" class="layui-input" onchange="passwordK();">
		    </div>
		    <label class="layui-form-label"><spring:message code="user.confirmpassword" />：</label>
		    <div class="layui-input-inline" >
		      <input type="password" id="password2"  datatype="*"  ignore="ignore" placeholder="<spring:message code="tips.check.npwd" />" autocomplete="off" class="layui-input" recheck="password" errormsg="<spring:message code="tips.check.cpwd" />" onchange="passwordK();">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="user.email" />：</label>
		    <div class="layui-input-inline" >
		      <input type="text"  name="email" datatype="e"  value="${user.email}"  placeholder="<spring:message code="user.pemail" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="user.tel" />：</label>
		    <div class="layui-input-inline" >
		      <input type="text" name="telephone" value="${user.telephone }" datatype="/^\s*$/|/^0\d{2,3}-?\d{7,8}$/" placeholder="<spring:message code="user.ptel" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		   <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="user.phone" />：</label>
		    <div class="layui-input-inline" >
		      <input type="text"  name="phone" value="${user.phone}" datatype="/^\s*$/|m"  placeholder="<spring:message code="user.pphone" />" autocomplete="off"  class="layui-input">
		    </div>
		    </div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button type="submit"  class="layui-btn" ><spring:message code="op.submit" /></button>
		      <button type="reset" class="layui-btn layui-btn-primary" onclick="parent.layer.closeAll();"><spring:message code="op.cancel" /></button>
		    </div>
		  </div>
		</form>
	</div>
		 
	</div>	
  </body>
</html>
