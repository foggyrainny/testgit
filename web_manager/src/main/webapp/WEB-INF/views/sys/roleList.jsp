<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <%@include file="../../../resource.jsp" %>
    <title>角色管理</title>
    <script type="text/javascript">
    	//数据初始化  下移  文件末尾
    	
        function getEntityList(){
        	var queryParam = getFormJson("#query_param");
			queryParam.curr = 1;
			queryParam.size = 10;
			commmonAjax({url:"role/getRoleList.do",data:queryParam,success:function(data){
				if(data.code == 200) {
					tableData(data);
					commonPager({url:"role/getRoleList.do",data:queryParam,total:data.data.totalNum,size:data.data.pageNum,curr:data.data.page,success:tableData});
				}
			}});
        }
        
        function tableData(data){
			list = data.data.listResult;
			var html = autoGenerateTableHtml("table",list);
			$("table").find("tbody").html(html);
		}
		
    	//进入修改
    	function updEntity(id){
    		layer.open({
			  type: 2,
			  title :'<spring:message code="role.updatetitle" />',
			  content: 'role/update.do?id='+id,
			  closeBtn:0,
			  area: ['100%', '100%'],
			  offset: ['0px', '0px']
			});
    	}
    	//进入添加下级 
    	function addEntity(id){
    		layer.open({
			  type: 2,
			  title :'<spring:message code="role.addtitle" />',
			  content: 'role/update.do?id=-1',
			  closeBtn:0,
			  area: ['100%', '100%'],
			  offset: ['0px', '0px']
			});
    	}
    	
    	function delEntity(id){
    		layer.confirm('<spring:message code="role.title.del" />', {icon: 3, title:'<spring:message code="office.title.p" />'}, function(index){
			  //do something
				commmonAjax({url:"role/delete.do",data:{roleId:id},success:function(data){
					if(data.code == 200) {
						layer.msg(data.msg);
						//重新加载数据及树桩表格
				  		getEntityList();
					}
				}});
			});
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
			margin: 0px;
		}
	</style>
  </head>
  
  <body style="padding: 20px;">
  <div>
  	<form action="" id="query_param" name="query_param" >
  	<div class="layui-form-item">
	    <label class="layui-form-label l4"><spring:message code="office.ascription" />：</label>
	    <div class="layui-input-inline d16">
	      <input type="text" id="officeId" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	    
	    <label class="layui-form-label l4"><spring:message code="role.name" />：</label>
	    <div class="layui-input-inline d16" >
	      <input type="text" id="roleName" name="roleName" autocomplete="off" class="layui-input">
	    </div>
	    
		<a class="layui-btn"  onclick="getEntityList()"><spring:message code="op.query" /></a>
   		<a class="layui-btn"  onclick="addEntity()"><spring:message code="op.add" /></a>
  	  </div>
  	  </form>
  </div>
    
  

<div class="layui-form">
  <table class="layui-table   table-resize">
    <thead>
      <tr>
        <th data-field="roleName"><spring:message code="role.name" /></th>
        <th data-field="officeName"><spring:message code="office.ascription" /></th>
         <!-- <th data-field="roleLevel" formatter="typeFormatter">数据范围</th> -->
        <th data-field="createTime" formatter="registerTime"><spring:message code="time.create" /></th>
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

  </body>
  <script type="text/javascript">
  		$(function (){
    		getEntityList();
    		
    		officeTree({selector:"#officeId",inputName:"officeId",url:"<%=basePath%>/selector/office.do?type=role",
    																												  
    			dataFilter:function(treeId, parentNode, responseData){
					return responseData.data;
				}
			});
   		});
  
    function registerTime(data,row,index){
		return dateToStr(data,"yyyy-MM-dd hh:mm:ss")
	}
	
	function optionFormatter(data,row,index){
		console.log(row);
		return "<a href='javascript:void()'  onclick='updEntity("+row.id+")' ><spring:message code='op.modify' /></a>&nbsp;&nbsp; <a href='javascript:void()' onclick='delEntity("+row.id+")' ><spring:message code='op.delete' /></a>";
	}
	
    </script>
</html>
