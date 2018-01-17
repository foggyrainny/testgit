<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
  
  <body style="height: 100%">
    <button  class="layui-btn" onclick="toInsert(0);"><spring:message code="menu.buttonadd" /></button>
    <div class="layui-form">
	  <table id="treetable1" class="layui-table  table-resize">
	    <colgroup>
		 <col width="30%">
	      <col width="18%">
	      <col width="8%">
	      <col width="8%">
	      <col width="18%">
	      <col width="26%">
	      <col>
	    </colgroup>
	    <thead>
	      <tr>
	        <th><spring:message code="menu.name" /></th>
	        <th><spring:message code="menu.url" /></th>
	        <th><spring:message code="menu.sort" /></th>
	        <th><spring:message code="menu.show" /></th>
	        <th><spring:message code="menu.updatedate" /></th>
	        <th><spring:message code="op" /></th>
	      </tr> 
	    </thead>
	    <tbody id="tbody1"></tbody>
	  </table>
	</div>
	
	   <script type="text/javascript">
  		var treetable = null;
	 
        var option = {
            theme:'vsStyle',
            expandLevel : 1,
           	beforeExpand : function($treeTable, id) {
                //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
                if ($('.' + id, $treeTable).length) { return; }
               	var html = getChildsNode(id,true);
                $treeTable.addChilds(html);
            },
            onSelect : function($treeTable, id) {
                window.console && console.log('onSelect:' + id);
            }

        };
        //获取菜单子节点
        function getChildsNode(pid,getvalue){
        	var rlist="";
			commmonAjax({
				url : '<%=basePath%>/menu/getMenuList.do',
				async:false,
				data:{parentId:pid},
				success : function(result) {
					if (result.code == 200) {
						var data = result.data;
						console.log(data);
						if(data.length > 0) {
							$.each(data, function (i){
								var item = data[i];
								var isShow = item.isShow==1?'<spring:message code="menu.show.1" />':'<spring:message code="menu.show.2" />';
								var modifyTime = item.modifyTime == null ? "" : dateToStr(item.modifyTime,"yyyy-MM-dd hh:mm:ss");
								var menuUrl = item.menuUrl==null?'':item.menuUrl;
								var hasChild = item.childs!=0?'hasChild="true"':'';
								rlist += '<tr id="'+item.id+'" pid="'+item.parentId+'" '+hasChild+'><td><span controller="true">'+item.menuName+'</span></td>'
									+ '<td>'+menuUrl+'</td><td>'+item.sortNum+'</td><td>'+isShow+'</td><td>'+modifyTime+'</td>'
									+ '<td><a href="javascript:update('+item.id+');"><spring:message code="op.modify" /></a> <a href="javascript:delEntity('+item.id+');"><spring:message code="op.delete" /></a> <a href="javascript:toInsert('+item.id+');"><spring:message code="menu.add" /></a></td></tr>';
							});
						} else {
							rlist += '<tr><td ><spring:message code="prompted.notdata" /></td></tr>';	
						}
						if(pid == 0  && treetable == null)  {
							$("#tbody1").html(rlist);
						}
						if(treetable!=null && !getvalue) {
							treetable.setChilds(rlist);
						}
						return rlist;
					}
				}
			});
			return rlist;
		}
		
    	
    	//进入修改
    	function update(id){
    		layer.open({
  			  type: 2,
  			  title: '<spring:message code="menu.updatetitle" />',
  			  shadeClose: true,
  			  shade: 0.3,
  			  area: ['100%', '100%'],
			  offset: ['0px', '0px'],
  			  content: '<%=basePath%>menu/update.do?id='+id //iframe的url
  			});
    	}
    	
    	//进入添加下级 
    	function toInsert(id){
    		layer.open({
    			  type: 2,
    			  title: '<spring:message code="menu.addtitle" />',
    			  shadeClose: true,
    			  shade: 0.3,
    			  area: ['100%', '100%'],
    			  offset: ['0px', '0px'],
    			  content: '<%=basePath%>menu/add.do?parentId='+id //iframe的url
    			});
    	}
    	function delEntity(id){
    		layer.confirm('<spring:message code="menu.is.deltitle" />?', {icon: 3, title:'<spring:message code="op.prompt" />'}, function(index){
			  	commmonAjax({
					url : '<%=basePath%>menu/delete.do',
					type : 'POST',
					data : {id:id},
					success : function(result) {
						layer.msg(result.msg);
						if(result.code == 200){
							treetable.delNode(id);
						}
					},
				});
			});
    	}
    	
    	$(document).ready(function(){
   		   getChildsNode(0);
		
			treetable = $("#treetable1").treeTable(option);
   	   })
        
    	
    </script>
  </body>
</html>
