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
    <title>客户管理</title>
    <script type="text/javascript">
        function getEntityList(){
        	var queryParam = getFormJson("#query_param");
        	console.log(queryParam);
			queryParam.curr = 1;
			queryParam.size = 10;
			commmonAjax({url:"getUserList.do",data:queryParam,success:function(data){
				if(data.code == 200) {
					tableData(data);
					commonPager({url:"getUserList.do",data:queryParam,total:data.data.totalNum,size:data.data.pageNum,curr:data.data.page,success:tableData});
				}
			}});
        }
        
        function tableData(data){
			list = data.data.listResult;
			var html = autoGenerateTableHtml("table",list);
			$("table").find("tbody").html(html);
		}
		
		function addEntity(){
			officeTree({selector:"#officeId2",inputName:"officeId",url:"<%=basePath%>/selector/office.do?type=role",
    			dataFilter:function(treeId, parentNode, responseData){
					return responseData.data;
				}
			});

			
			$("#form2").attr("action","addUser.do");//设置提交地址
			$("#Id").val("-1");
			
			
			$("#loginName1").removeAttr("name");
			$("#loginName1").removeAttr("datatype");
			$("#loginName_upd").hide();
			$("#loginName2").attr("name","loginName");
			$("#loginName2").attr("datatype","login");
			$("#loginName_add").show();
			
			$("#password").attr("datatype","*6-15");
   			$("#password2").attr("datatype","*");
			
   			$("#info1").hide();
   			$("#info2").hide();
			layer.open({
			  type: 1,
			  title :'<spring:message code="user.addtitle" />',
			  content: $('#addOrUpdate'),
			  closeBtn:0,
			  area: ['100%', '100%'],
			  offset: ['0px', '0px']
			});
			
			$("#officeId2_tree_select_input").on("change",function(){
				var officeId = $("#officeId2_tree_select_input").val();
				roleSelect(officeId);
			});
		}
		
    	//进入修改
    	function updEntity(id){
    	
    		$("#form2").attr("action","updateUser.do");//设置提交地址
    		
    		
    		$("#loginName2").removeAttr("name");
			$("#loginName2").removeAttr("datatype");
			$("#loginName_add").hide();
			$("#loginName1").attr("name","loginName");
			$("#loginName1").attr("datatype","login");
			$("#loginName_upd").show();
			
    		$("#password").removeAttr("datatype");
    		$("#password2").removeAttr("datatype");
    		$("#info1").show();
   			$("#info2").show();
    		$.ajax({
				url : 'getUser.do',
				type : 'POST',
				data : {userId:id},
				async : false,
				dataType : 'json',
				timeout : 10000,
				async:false,
				error : function() {layer.msg('<spring:message code="prompted.request" />');},
				success : function(result) {
					if (result.code == 200) {
						var data = result.data;
						$("#Id").val(data.id);
						updateTree("officeId2_tree", data.officeId);//设置归属机构
						roleSelect(data.officeId);//设置会员列表
						
						$("#loginName1").val(data.loginName);
						$("#userName").val(data.userName);
						$("#roleId").val(data.roleId);
						
						$("#email").val(data.email);
						$("#telephone").val(data.telephone);
						$("#phone").val(data.phone);
						$("#createTime").html(registerTime(data.createTime,null,null));
						if(data.modifyTime != undefined){
			    			$("#modifyTime").html(registerTime(data.modifyTime,null,null));
						}
						if(data.loginTime != undefined){
			    			$("#loginTime").html(registerTime(data.loginTime,null,null));
						}
						if(data.loginIp != undefined){
							var ip='IP:'+'&nbsp;&nbsp;'+data.loginIp;
						}else{
							var ip='IP:'+'&nbsp;&nbsp;';
						}
				    		$("#loginIp").html(ip);
					} else {
						layer.msg(result.msg);
					}
					layui.form().render();
				}
			});
    		layer.open({
			  type: 1,
			  title :'<spring:message code="user.updatetitle" />',
			  content: $('#addOrUpdate'),
			  closeBtn:0,
			  area: ['100%', '100%'],
			  offset: ['0px', '0px']
			});
			
			$("#officeId2_tree_select_input").on("change",function(){
				var officeId = $("#officeId2_tree_select_input").val();
				roleSelect(officeId);
			});
    	}
    	
    	function doAddOrUpdate(){
    		var password = $("#password").val();
    		if(password != "") {
    			password = $.md5(password);
    			$("#password3").val(password);
    		}
    		$("#form2").submit();
    	} 
    	
    	
    	 function delEntity(id){
    		layer.confirm('<spring:message code="user.titledel" />', {icon: 3, title:'<spring:message code="op.prompt" />'}, function(index){
			  $.ajax({
					url : 'delete.do',
					type : 'POST',
					data : {userId:id},
					async : false,
					dataType : 'json',
					timeout : 10000,
					error : function() {layer.msg('<spring:message code="prompted.request" />');},
					success : function(result) {
						layer.msg(result.msg);
					}
				});
    			getEntityList();
			});
    	}
    	
    	<%-- //打开导入数据弹窗
		function importUser(){
			layer.open({
				type: 2,
				title:'用户导入',
				shadeClose: true,//点击窗口外部，窗口关闭
			    shade: 0.8,
				area: ['600px', '90%'],
				content:'<%=basePath%>user/import.do'
			});
		} --%>
    	
    	function exportExcel(){
			var queryParam = getFormJson("#query_param");
			location.href="<%=basePath %>user/exportExcel.do?officeId="+queryParam.officeId+"&loginName="+queryParam.loginName+"&userName="+queryParam.userName;
		}
    	
    </script>
    
    
    
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
		body{
			padding: 20px;
		}
		.time-label{
		text-align: left;    
		padding: 10px 0px 10px 0px;
		width:140px;
		}
	</style>
  </head>
  
  <body style="padding: 20px;">
  <div>
  	<form action="" id="query_param" name="query_param" >
  	<div class="layui-form-item">
	    <label class="layui-form-label l4"><spring:message code="office.ascription" /></label>
	    <div class="layui-input-inline d16">
	      <input type="text" id="officeId" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	    
	    <label class="layui-form-label l4"><spring:message code="user.loginname" /></label>
	    <div class="layui-input-inline d16" >
	      <input type="text" name="loginName" autocomplete="off" class="layui-input">
	    </div>
	    
	    <label class="layui-form-label l3"><spring:message code="user.name" /></label>
	    <div class="layui-input-inline d16" >
	      <input type="text" name="userName"  autocomplete="off" class="layui-input">
	    </div>
	    
		<a class="layui-btn"  onclick="getEntityList()"><spring:message code="op.query" /></a>
   		<a class="layui-btn"  onclick="addEntity()"><spring:message code="op.add" /></a>
   		<!-- <a class="layui-btn"  onclick="importUser()">导入</a> -->
   		<a class="layui-btn"  onclick="exportExcel()"><spring:message code="op.export" /></a>
  	  </div>
  	  </form>
  </div>
    
  
<div class="layui-form">
  <table class="layui-table  table-resize" >
    <thead>
      <tr>
        <th data-field="loginName"><spring:message code="user.loginname" /></th>
        <th data-field="userName"><spring:message code="user.name" /></th>
        <th data-field="roleName"><spring:message code="user.userrole" /></th>
        <th data-field="name"><spring:message code="office.ascription" /></th>
        <th data-field="parentName"><spring:message code="office.superior" /></th>
        <th data-field="modifyTime" formatter="registerTime"><spring:message code="menu.updatetime" /></th>
        <th data-field="Id" formatter="optionFormatter" ><spring:message code="op" /></th>
      </tr> 
    </thead>
    <tbody>
    </tbody>
  </table>
   <div class="pager">
		<div id="pager" class="pager"></div>
	</div>
</div>

	<div id="addOrUpdate" style="display: none;">
		<fieldset class="layui-elem-field">
		  <div class="layui-field-box">
		  <form id="form2" class="layui-form" action="" method="post">
		   <input type="hidden" id="Id" name="Id" value="">
		   <input type="hidden" id="password3" name="password" value="">
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.ascription" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline">
		      <input type="text" id="officeId2" datatype="*1-100"  autocomplete="off" placeholder="" class="layui-input" onblur="">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item" id="loginName_upd" >
		    <label class="layui-form-label"><spring:message code="user.loginname" /></label><span class="required_field" >*</span><span style="color:#BCBCBC;font-size:10px;"><spring:message code="tips.check.loginname2"/></span>
		    <div class="layui-input-inline" >
		      <input type="text" id="loginName1" name="loginName" datatype="login" placeholder="<spring:message code="user.ploginname" />" autocomplete="off" class="layui-input">
		    </div>  
		    <div class="Validform_checktip"></div>
		  </div>
		  
		   <div class="layui-form-item" id="loginName_add">
		    <label class="layui-form-label"><spring:message code="user.loginname" /></label><span class="required_field" >*</span><span style="color:#BCBCBC;font-size:10px;"><spring:message code="tips.check.loginname2"/></span>
		    <div class="layui-input-inline" >
		      <input type="text" id="loginName2" name="loginName" datatype="login" placeholder="<spring:message code="user.ploginname" />" ajaxurl="<%=basePath%>/user/qrylogin.do" autocomplete="off" class="layui-input">
		    </div>  
		    <div class="Validform_checktip"></div>
		  </div>
		  

		  
		   <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="user.name" /></label><span class="required_field" >*</span><span style="color:#BCBCBC;font-size:10px;"><spring:message code="tips.check.name2"/></span>
		    <div class="layui-input-inline" >
		      <input type="text" id="userName" name="userName" datatype="*1-15" placeholder="<spring:message code="user.pname" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="password" /></label><span class="required_field" >*</span><span style="color:#BCBCBC;font-size:10px;"><spring:message code="tips.check.pwd"/></span>
		    <div class="layui-input-inline" >
		      <input type="password" id="password" name="pass" datatype="*6-15" placeholder="<spring:message code="user.ppassword" />" autocomplete="off" class="layui-input" onchange="passwordK();">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="user.confirmpassword" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline" >
		      <input type="password" id="password2"  datatype="*" placeholder="<spring:message code="user.pconfirmpassword" />" autocomplete="off" class="layui-input" recheck="pass" errormsg="<spring:message code="user.pconfirm" />" onchange="passwordK();">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="user.userrole" /></label><span class="required_field" >*</span><span style="color:#BCBCBC;font-size:10px;"><spring:message code="tips.check.role"/></span>
		    <div class="layui-input-inline">
		      <select id="roleId" name="roleId" lay-verify="" lay-filter="test"  lay-search datatype="*1-15">
				  <option value=""><spring:message code="user.puserrole" /></option>
				</select> 
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.email" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline" >
		      <input type="text" id="email" name="email" datatype="e" placeholder="<spring:message code="user.pemail" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="user.phone" /></label><span style="color:#BCBCBC;font-size:10px;"><spring:message code="tips.check.phone1"/></span>
		    <div class="layui-input-inline" >
		      <input type="text" id="telephone" name="telephone" datatype="/^\s*$/|/^0\d{2,3}-?\d{7,8}$/" placeholder="<spring:message code="user.ptel" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		   <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="user.tel" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="phone" name="phone" datatype="/^\s*$/|m" placeholder="<spring:message code="user.pphone" />" autocomplete="off"  class="layui-input">
		    </div>
		    </div>
		    
		  <div class="layui-form-item" style="margin-bottom: 0px" id="info1">
		    <label class="layui-form-label"><spring:message code="time.create" /></label>
		    <label class="layui-form-label time-label"  id="createTime"></label>
		    <label class="layui-form-label"style="width:100px"><spring:message code="menu.updatetime" /></label>
		    <label class="layui-form-label time-label"  id="modifyTime"></label>
		  </div>
		  <div class="layui-form-item" id="info2">
		    <label class="layui-form-label"><spring:message code="menu.logintime" /></label>
		    <label class="layui-form-label" style="text-align: left;    padding: 10px 0px 10px 0px;white-space:nowrap;" id="loginIp"></label>
		    <label class="layui-form-label" style="width:100px"><spring:message code="time" /></label>
		    <label class="layui-form-label time-label"  id="loginTime"></label>
		  </div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button type="button"  class="layui-btn" onclick="doAddOrUpdate();"><spring:message code="op.submit" /></button>
		      <button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll();"><spring:message code="op.cancel" /></button>
		    </div>
		  </div>
		</form>
		 </div>
		</fieldset>
	</div>
  </body>
  
  <script type="text/javascript">
  		$(function (){
  			getEntityList();
    		
    		officeTree({selector:"#officeId",inputName:"officeId",url:"<%=basePath%>/selector/office.do?type=role",
    			dataFilter:function(treeId, parentNode, responseData){
					return responseData.data;
				}
			});
			
			officeTree({selector:"#officeId2",inputName:"officeId",url:"<%=basePath%>/selector/office.do?type=role",
    			dataFilter:function(treeId, parentNode, responseData){
					return responseData.data;
				}
			});
  		});
    //监听表单提交
    	layui.use('form', function(){
		  	var form = layui.form();
		  	form.on('submit(formDemo)', function(data){
		    	return false;
		  	}); 
		});
		
    	function roleSelect(officeId){
    		console.log("选择结构，设置角色:"+officeId);	
		  if (officeId == null || officeId == "") {
		  	$("#roleId").html("<option value=''><spring:message code='user.puserrole' /></option>");
		  	return;
		  }
		  $.ajax({
				url : 'roleSelect.do',
				type : 'POST',
				data : {officeId:officeId},
				async : false,
				dataType : 'json',
				timeout : 10000,
				error : function() {layer.msg('请求失败,请稍后重试');},
				success : function(result) {
					if (result.code == 200) {
						$("#roleId").empty();
						var data = result.data;
						if(data && data.length > 0) {
							$.each(data, function (i){
								var item = data[i];
								$("#roleId").append("<option value='"+item.id+"'>"+item.roleName+"</option>");
							});
						} else {
							$("#roleId").append("<option value=''><spring:message code='user.puserrole' /></option>");
						}
						layui.form().render();
					}
				}
			});
    	}
    	
    	function updateTree(treeId,id){
    		var zTree = $.fn.zTree.getZTreeObj(treeId);
			var nodes = zTree.getNodeByParam("id", id);
			// 选中父节点
			zTree.checkNode(nodes, true, true,true);
			$("#"+treeId).trigger("tree.select.change");
    	}
    	
		function passwordK(){
    		var id = $("#Id").val();
    		if(id < 1){
    			return;
    		}
    		var pass = $("#password").val();
    		var pass2 = $("#password2").val();
    		
    		if(pass != "" || pass != ""){
    			$("#password").attr("datatype","*6-15");
    			$("#password2").attr("datatype","*");
    		}else{
    			$("#password").removeAttr("datatype");
    			$("#password2").removeAttr("datatype");
    		}
    	} 
    	
    	
    function registerTime(data,row,index){
    	if(data != undefined){
			return dateToStr(data,"yyyy-MM-dd hh:mm:ss")
    	}else{
    		return '';
    	}
	}
	
	function optionFormatter(data,row,index){
		console.log(row);
		return "<a href='javascript:void()'  onclick='updEntity("+row.id+")' ><spring:message code='op.modify' /></a>&nbsp;&nbsp; <a href='javascript:void()' onclick='delEntity("+row.id+")' ><spring:message code='op.delete' /></a>";
	}
	
	$(document).ready(function(){
		ajaxSubmitFormAndValid("form2",function(data){
			if(data.code == 200) {
				getEntityList();
				/* var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index); */
				layer.closeAll();
				parent.layer.msg("<spring:message code='prompted.success' />");
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
		},true);
	})
    </script>
</html>
