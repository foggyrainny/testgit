<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/taglibs.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/${bootstrap_version}/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/${bootstrap_version}/css/bootstrap-theme.min.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/bootstrap-custom.min.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery.validate/${jquery_validate_version}/validate.min.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-icheck/${bootstrap_icheck_version}/skins/all.css" >
		 <script type="text/javascript" src="${ctx}/static/calendar/4.8/WdatePicker.js"></script> 
	</head>
	<body>
		<div  class="panel panel-primary">
			 <div class="panel-heading">
   				 <h3 class="panel-title"><span class="glyphicon glyphicon-user"></span> 教师信息 &gt;&gt; 编辑 </h3>
 			 </div>
 			 <div class="panel-body">
				<form action="saveTeacher.do" method="post" class="form-horizontal" commandName="model">
					<input type="hidden" name="id" value="${teacher.id}" />
					 <div class="form-group">
	       				 <label for="teacher.name" class="col-md-2 control-label">姓名 ：</label>
						<div class="col-md-6">
							<input type="text" id="name" name="name" value="${teacher.name}"  class="form-control" style="width:200px" minlength="2" required /><br/>
						</div>
					</div>
					 
					 <div class="form-group">
				        <label for="teacher.sex" class="col-md-2 control-label">性别：</label>
				        <div class="col-md-6 ">
				        	 <input type="radio" name="sex" ${teacher.sex eq 1 ? "checked='checked'" : ""} value="1" >男 &nbsp;
					   		 <input type="radio" name="sex" ${teacher.sex eq 2 ? "checked='checked'" : ""} value="2" >女 <br/>	     
				        </div>				       				        
			     	 </div>
					
					<div class="form-group">
				        <label for="ismarried" class="col-md-2 control-label">婚否：</label>
				        <div class="col-md-6 ">
				        	<input type="radio" name="ismarried" ${teacher.ismarried eq 1? "checked='checked'" : ""} value="1" checked/>已婚
				        	<input type="radio" name="ismarried" ${teacher.ismarried eq 2? "checked='checked'" : ""} value="2"  />未婚			     
				        </div>				       				        
			     	 </div>
			     	 	     	 

			     	  <div class="form-group">
	       				 <label for="position" class="col-md-2 control-label"  >职称 ：</label>
						<div class="col-md-6">
							<input type="text" id="position" name="position" value="${teacher.position}"  class="form-control" style="width:200px"  minlength="2" required /><br/>
						</div>
					</div>
					
					<div class="form-group">
	       				 <label for="age" class="col-md-2 control-label" >年龄 ：</label>
						<div class="col-md-6">
							<input type="text" id="age" name="age" value="${teacher.age}"  class="form-control" style="width:200px" digits:true required /><br/>
						</div>
					</div>
					
					<div class="form-group">
						 <fmt:formatDate  var="cc" pattern="yyyy-MM-dd" value="${teacher.hiredate}"/> 
	       				 <label for="hiredate" class="col-md-2 control-label">入职时间 ：</label>
						<div class="col-md-6">
							<input  name="hiredate" id="hiredate" value="${cc}"  class="form-control Wdate"   style="width:200px"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'new Date()',errDealMode:'0',skin:'default'})" /><br/>
						</div>
					</div>
					
					<div class="form-group">
	       				 <label for="subject" class="col-md-2 control-label" >所教课程 ：</label>
						<div class="col-md-6">
							<input type="text" name="subject" id="subject"  value="${teacher.subject}"  class="form-control" style="width:200px"  minlength="2" required /><br/>
						</div>
					</div>
					
					<div class="form-group">
	       				 <label for="mobile" class="col-md-2 control-label">电话 ：</label>
						<div class="col-md-6">
							<input type="text" name="mobile" id="mobile" value="${teacher.mobile}"  class="form-control" style="width:200px"  minlength="7"  digits:true required /><br/>
						</div>
					</div>
					
					 <div class="form-group">
				        <div class="col-md-offset-2 col-md-6">
				          <button type="submit" class="btn btn-large btn-success"> <span class="glyphicon glyphicon-ok"></span> 保存</button>
				          <button type="reset" class="btn btn-large btn-warning"> <span class="glyphicon glyphicon-repeat"></span> 重置</button>
				          <button type="button" class="btn btn-primary" onclick="history.back();"> <span class="glyphicon glyphicon-backward"></span> 返回 </button>
				        </div>
				      </div>
				</form>
			</div>		
		</div>	
	<script src="${ctx}/static/jquery/${jquery_version}/jquery.min.js"></script> 
	<script src="${ctx}/static/bootstrap/${bootstrap_version}/js/bootstrap.min.js"></script> 
	<script src="${ctx}/static/jquery.validate/${jquery_validate_version}/jquery.validate.min.js"></script> 
	<script src="${ctx}/static/jquery.validate/${jquery_validate_version}/localization/messages_zh.min.js"></script> 
	<script src="${ctx}/static/jquery.form/${jquery_form_version}/jquery.form.min.js"></script> 
	<script src="${ctx}/static/validator/${validator_version}/validator.js"></script> 
	<script>
	$(function(){
		$("#name").attr("datatype","Chinese").attr("msg","姓名必须是中文 ");
		$("#position").attr("datatype","Chinese").attr("msg","必须是中文 ");
		$("#subject").attr("datatype","Chinese").attr("msg","必须是中文 ");
		$("#age").attr("datatype","Range").attr("min","18").attr("max","90").attr("msg","必填项[18-90]");
		$("input[name='sex']").attr("datatype","Group").attr("msg","必选项");
		$("input[name='ismarried']").attr("datatype","Group").attr("msg","必选项");	
		$("#mobile").attr("datatype","Mobile").attr("msg","手机号码不正确 ");
		$(document.forms[0]).submit(function(){
			return Validator.Validate(this, 3);
		});
	});
	</script>
	   
	</body>
</html>