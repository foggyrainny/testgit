<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String basePath2 = request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
  <head>
<head>
	<meta charset="UTF-8">
	<title>电站参数</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<%@include file="../../../resource.jsp" %>
	<style type="text/css">
	  /*  .ellipsis_div{   
    overflow:hidden;   
    text-overflow:ellipsis;   
    white-space:nowrap;   
  }     */
	</style>	
  </head>
  
  <body style=" padding: 20px">
 	 <section>
	 <form action="" id="query_paraminfo" name="query_paraminfo" >
	 <div class="layui-form-item">
	 <div class="layui-inline">
      <label class="layui-form-label l4"><spring:message code="parameter.name" />：</label>
      <div class="layui-input-inline d16" >
          <input type="text"  name="name"   autocomplete="off"  class="layui-input" />
      </div>
    </div>
 	
		<a class="layui-btn" onclick="queryData()"><spring:message code="op.query" /></a>
		</div>
	  </form>
	</section>

	<section  >
		<div class="layui-form">
		  <table class="layui-table  table-resize" style="table-layout:fixed;"   >
		    <thead>
			      <tr  >
			         <th  width="20%" ><spring:message code="parameter.name" /></th>  
			         <th   width="20%" ><spring:message code="parameter.value" /></th>
			         <th  width="20%" ><spring:message code="parameter.description" /></th>
			         <th   width="5%" ><spring:message code="op" /></th>
			      </tr> 
		    </thead>
		    <tbody ></tbody>
		  </table>
		  <div class="pager">
			<div id="pager" class="pager"></div>
		  </div>
		</div>
	</section>
	
	<script type="text/javascript">
	
	//供应商列表信息查询
	$(document).ready(function(){
		//查询数据
		queryData();
	})
	
	function  queryData (){
		var queryParam = getFormJson("#query_paraminfo");
		commmonAjax({url:"./powerStation.do",data:queryParam,success:function(data){
			if(data.code == 200) {
				dataTable(data);
				//渲染表单 
				   layui.use('form', function(){
					  var $ = layui.jquery, form = layui.form();
					}); 
			}
		}})
	}
	
	//生成表格数据
	 function dataTable(data){
		list = data.data;
		 var html = '';
		if(list.length>0){
			$.each(list, function (i){
				var item = list[i];
				var id = item.id;
				if(id == undefined ){
				   id='';
				}
				var name = item.name;
				if(name == undefined ){
				   name='';
				}
				var value = item.value;
				if(value == undefined ){
				   value='';
				}
				var value2 = item.value2;
				if(value2 == undefined ){
				   value2='';
				}
				var remark = item.remark;
				if(remark == undefined ){
				   remark='';
				}
				var isrange = item.isRange;
				
				 if(isrange==0){
					var v1='<td>'+value+'</td>'
				} else if(isrange==1){
					var v1='<td >'+ value+"     ~     "+value2+'</td>'
				}
	
			/* html += '<tr>'																									
			+ 		'<td > <div   class=ellipsis_div title='+ name +' >'+ name +'</div></td>'+v1+'<td > <div   class=ellipsis_div title='+ remark +' >'+ remark +'</div></td>'
			+ 		'<td ><a href= "javascript:updatepower('+"'"+id+"'"+'); "><spring:message code="op.modify" /></a></td>'
			+ '</tr>'; */
			//自动换行
			html += '<tr>'																									
			+ 		'<td class="line-feed"> <div    title='+ name +' >'+ name +'</div></td>'+v1+'<td class="line-feed"> <div  title='+ remark +' >'+ remark +'</div></td>'
			+ 		'<td class="line-feed"><a href= "javascript:updatepower('+"'"+id+"'"+'); "><spring:message code="op.modify" /></a></td>'
			+ '</tr>';
					 	 			 
			});	
			$("table").find("tbody").html(html);
			  layui.use('form', function(){
				 form = layui.form();
			 }); 
		}else{
			html += '<tr><td colspan="4" align="center"><spring:message code="op.nodata" /></td></tr>';
			$("table").find("tbody").html(html);
		}
	}
	
	
		//修改弹窗
	function updatepower(id){
		layer.open({
		  type: 2,
		  title: '<spring:message code="parameter.modify.station" />',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['650px', '90%'],
		  content: '<%=basePath%>parm/power_up.do?id='+id
		}); 
	}
	
	</script>
  </body>
</html>
