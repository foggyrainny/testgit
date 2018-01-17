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
    <title>角色管理</title>
    
    
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
	</style>
  </head>
  
  <body style="padding: 20px;">
  <div>
  	<form action="" id="query_param" name="query_param" class='layui-form' >
  	<div class="layui-form-item">
	    <label class="layui-form-label l4"><spring:message code="user.loginname" />：</label>
	    <div class="layui-input-inline d16" >
	      <input type="text" id="account" name="account" placeholder="<spring:message code="prompted.loginname" />" autocomplete="off" class="layui-input">
	    </div>
	    
	  <%--   <label class="layui-form-label l3"><spring:message code="user.name" />：</label> --%>
	    <!-- <label class="layui-form-label l3">姓名：</label>
	    <div class="layui-input-inline d16" >
	      <input type="text" id="name" name="name" placeholder="<spring:message code="prompted.username" />" autocomplete="off" class="layui-input">
	    </div> -->
	    
	    <label class="layui-form-label l4"><spring:message code="log.optype" />：</label>
	    <div class="layui-input-inline d16" >
	    	<select name="opType">
<!-- 	    	0:登录 1:单纯的进入页面， 不查询数据时\\r\\n2:查询3:刪除4:添加5:修改 -->
	    		<option value="-1"><spring:message code="op.allselect" /></option>
	    		<option value="0" ><spring:message code="log.type.login" /></option>
	    		<option value="2" ><spring:message code="op.query" /></option>
	    		<option value="4" ><spring:message code="op.add" /></option>
	    		<option value="5" ><spring:message code="op.modify" /></option>
	    		<option value="3" ><spring:message code="op.delete" /></option>
	    		<option value="1" ><spring:message code="log.type.inpage" /></option>
	    		<option value="6" ><spring:message code="log.type.cmd" /></option>
	    		
	    	</select>
	    </div>
	    
	     <div class="layui-inline">
	     <label class="layui-form-label l4"><spring:message code="log.operationtime" />：</label>
	    <div class="layui-input-inline d16">
	      <input class="layui-input" placeholder="<spring:message code="label.startdate" />" id="LAY_demorange_s" name="startTime">
	    </div>
	    <div class="layui-input-inline d16">
	      <input class="layui-input" placeholder="<spring:message code="label.enddate" />" id="LAY_demorange_e" name="endTime">
	    </div>
	    </div>
	    
		<a class="layui-btn"  onclick="getEntityList()"><spring:message code="op.query" /></a>
  	  </div>
  	  </form>
  </div>
    
  
<div class="layui-form">
  <table class="layui-table table-resize" id="tableList  table-resize" >
    <colgroup>
    	<col width="10%"/>
    	<col width="10%"/>
    	<col width="8%"/>
    	<col width="15%"/>
    	<col width="50%"/>
    </colgroup>
    <thead>
      <tr>
        <th data-field="account"><spring:message code="user.loginname" /></th>
        <th data-field="name"><spring:message code="user.name" /></th>
         <th data-field="opType" formatter="opTypeFormatter"><spring:message code="log.optype" /></th>
        <th data-field="operationtime" formatter="registerTime"><spring:message code="log.operationtime" /></th>
        <th data-field="content"><spring:message code="log.operationcontent" /></th>
      </tr> 
    </thead>
    <tbody>
    </tbody>
  </table>
   <div class="pager">
		<div id="pager" class="pager"></div>
	</div>
</div>
  </body>
  
  <script type="text/javascript">
    	$(function (){
    		getEntityList();
    	});
    	
    	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  
		  var start = {
		    min: '1999-06-16 23:59:59'
		    ,max: '2099-06-16 23:59:59'
		    ,istoday: false
		    ,choose: function(datas){
		      end.min = datas; //开始日选好后，重置结束日的最小日期
		      end.start = datas //将结束日的初始值设定为开始日
		    }
		  };
		  
		  var end = {
		    min: '1999-06-16 23:59:59'
		    ,max: '2099-06-16 23:59:59'
		    ,istoday: false
		    ,choose: function(datas){
		      start.max = datas; //结束日选好后，重置开始日的最大日期
		    }
		  };
		    document.getElementById('LAY_demorange_s').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  }
		  document.getElementById('LAY_demorange_e').onclick = function(){
		    end.elem = this
		    laydate(end);
		  }
		  
		});
    	
        function getEntityList(){
        	var queryParam = getFormJson("#query_param");
			queryParam.curr = 1;
			queryParam.size = 10;
			if(queryParam.endTime.length > 0) {
				queryParam.endTime = queryParam.endTime + " 23:59:59";
			}
			
			commmonAjax({url:"getSyslogList.do",data:queryParam,success:function(data){
				if(data.code == 200) {
					tableData(data);
					commonPager({url:"getSyslogList.do",data:queryParam,total:data.data.totalNum,size:data.data.pageNum,curr:data.data.page,success:tableData});
				}
			}});
        }
        
        function tableData(data){
			list = data.data.listResult;
			var html = '';
			if(list == null || list.length == 0) {
				html = "<tr><td colspan='5' style='text-align:center' >"+NOT_DATA_MSG+"</td><tr>";
			} else {
				html = autoGenerateTableHtml("table",list);
			}
			$("table").find("tbody").html(html);
		}
		
    	
    	//监听表单提交
    	layui.use('form', function(){
		  	var form = layui.form();
		  	form.on('submit(formDemo)', function(data){
		    	//layer.msg(JSON.stringify(data.field));
		    	//doAddOrUpdate(data.field);
		    	return false;
		  	}); 
		});
    </script>
    
    <script type="text/javascript">
    function registerTime(data,row,index){
		return dateToStr(data,"yyyy-MM-dd hh:mm:ss")
	}

	$(document).ready(function(){
		
			ajaxSubmitFormAndValid("menuObj",function(data){
			if(data.code == 200) {
				history.go(0);
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
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
		});
		
		})

    function opTypeFormatter(opType){
		switch(opType){
			case 0: return "<spring:message code='log.type.login' />";
			case 1: return "<spring:message code='log.type.inpage' />";
			case 2: return "<spring:message code='op.query' />";
			case 3: return "<spring:message code='op.delete' />";
			case 4: return "<spring:message code='op.add' />";
			case 5: return "<spring:message code='op.modify' />";
			case 6: return "<spring:message code='log.type.cmd' />";
			default:return '';
		}    
		return '';
    }

    </script>
</html>
