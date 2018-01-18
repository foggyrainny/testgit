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
    <title>区域管理</title>
    <script type="text/javascript">
  		var option = {
            theme:'vsStyle',
            expandLevel : 1,
           	beforeExpand : function($treeTable, id) {
                //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
                if ($('.' + id, $treeTable).length) { return; }
               	var html = getChildsNode(id);
                $treeTable.addChilds(html);
            },
            onSelect : function($treeTable, id) {
                window.console && console.log('onSelect:' + id);
            }
        };
    	$(function (){
    		getEntityList();
    	});
    	
        
        function getEntityList(){
        	var rlist = getChildsNode(0);
        	$("#tbody1").html(rlist);
        	$("#treetable1").unbind("click").unbind("mouseover").unbind("mouseout");
        	$("#treetable1").treeTable(option);
        }
       
        //获取菜单子节点
        function getChildsNode(pid){
        	var nameCn = $("#nameCn").val();
        	var rlist="";
			$.ajax({
				url : 'getAreaList.do',
				type : 'POST',
				data : {
					parentId:pid,
					nameCn:nameCn
				},
				async : false,
				dataType : 'json',
				timeout : 10000,
				error : function() {layer.msg('<spring:message code="prompted.request" />');},
				success : function(result) {
					if (result.code == 200) {
						var data = result.data;
						if(data.length > 0) {
							$.each(data, function (i){
								var item = data[i];
								var hasChild = item.hasChild!=0?'hasChild="true"':'';
								var code = item.hasChild!=0?'-':item.id;
								rlist += '<tr id="'+item.id+'" pid="'+item.parentId+'" '+hasChild+'><td><span controller="true">'+item.name+'</span></td>'
									+ '<td>'+code+'</td><td>'+item.longitude+'</td><td>'+item.latitude+'</td>';
							});
						} else {
							rlist += '<tr><td colspan="4" style="text-align:center;"><spring:message code="prompted.notdata" /></td></tr>';	
						}
					} else {
						layer.msg(result.data);
					}
				}
			});
			return rlist;
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
	</style>
  </head>
  
  <body style="padding: 20px;">
    <div>
    	<div class="layui-form-item">
	    
		    <label class="layui-form-label l4"><spring:message code="area.name" />：</label>
		    <div class="layui-input-inline d16" >
		      <input type="text" id="nameCn" name="nameCn" placeholder="<spring:message code='prompted.areaname' />" autocomplete="off" class="layui-input">
		    </div>
	    
		<a class="layui-btn"  onclick="getEntityList()"><spring:message code="op.query" /></a>
  	  </div>
    </div>
    <div class="layui-form">
	  <table id="treetable1" class="layui-table table-resize">
	    <colgroup>
	      <col width="35%">
	      <col width="25%">
	      <col width="20%">
	      <col width="20%">
	      <col>
	    </colgroup>
	    <thead>
	      <tr>
	        <th><spring:message code="area.name" /></th>
	        <th><spring:message code="area.code" /></th>
	        <th><spring:message code="area.longitude" /></th>
	        <th><spring:message code="area.latitude" /></th>
	      </tr> 
	    </thead>
	    <tbody id="tbody1"></tbody>
	  </table>
	</div>
  </body>
</html>
