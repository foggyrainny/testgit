<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	
    <title>机构管理</title>
  	
  	<%@include file="../../../resource.jsp" %>
    
   <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>js/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>/js/bootstrap-fileinput/themes/explorer/theme.css" media="all" rel="stylesheet" type="text/css"/>
    <script src="<%=basePath%>js/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/bootstrap-fileinput/js/locales/zh.js" type="text/javascript"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
    
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
        	$("#treetable1").treeTable(option);
        }
       
        //获取菜单子节点
        function getChildsNode(pid){
        	var rlist="";
			$.ajax({
				url : 'getOfficeList.do',
				type : 'POST',
				data : {parentId:pid},
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
								var type = item.type==1?'<spring:message code="office.type.1" />':'<spring:message code="office.type.2" />';
								var hasChild = item.hasChild!=0?'hasChild="true"':'';
								
								rlist += '<tr id="'+item.id+'" pid="'+item.parentId+'" '+hasChild+'><td><span controller="true">'+item.name+'</span></td>'
									+ '<td>'+item.code+'</td><td>'+item.areaName+'</td><td>'+type+'</td>'
									+ '<td><a href="javascript:update('+item.id+');"><spring:message code="op.modify" /></a> <a href="javascript:delEntity('+item.id+');"><spring:message code="op.delete" /></a> <a href="javascript:toInsert('+item.id+');"><spring:message code="office.add" /></a></td></tr>';
							});
						} else {
							rlist += '<tr><td colspan="5" style="text-align:center;"><spring:message code="prompted.notdata" /></td></tr>';	
						}
					} else {
						layer.msg(result.data);
					}
				}
			});
			return rlist;
		}
    	
    	//进入修改
    	function update(id){
    		if(id >= 0 && id <= 1){
    			layer.msg("<spring:message code='office.title.notupdate' />");
    			return;
    		}
    		layer.open({
			  type: 2,
			  title :'<spring:message code="office.title.update" />',
			  content: 'update.do?officeId='+id+'&parentId=0',
			  closeBtn:0,
			  area: ['100%', '100%'],
			  offset: ['0px', '0px']
			});
    	}
    	
    	//进入添加下级 
    	function toInsert(id){
    		layer.open({
			  type: 2,
			  title :'<spring:message code="office.sub.update" />',
			  content: 'update.do?parentId='+id+'&officeId=-1',
			  closeBtn:0,
			  area: ['100%', '100%'],
			  offset: ['0px', '0px']
			});
    	}
    	//新增机构信息
    	function toInsert1(id){
    		layer.open({
			  type: 2,
			  title :'<spring:message code="office.title.add" />',
			  content: 'update.do?parentId='+id+'&officeId=-1',
			  closeBtn:0,
			  area: ['100%', '100%'],
			  offset: ['0px', '0px']
			});
    	}
    	
    	
    	
    	function delEntity(id){
    		if(id >= 0 && id <= 1){
    			layer.msg("<spring:message code="office.title.notupdate" />");
    			return;
    		}
    		  layer.confirm('<spring:message code="office.title.isdelete" />', {icon: 3, title:'<spring:message code="office.title.p" />'}, function(index){
			  //do something
			  $.ajax({
					url : 'delete.do',
					type : 'POST',
					data : {id:id},
					async : false,
					dataType : 'json',
					timeout : 10000,
					error : function() {layer.msg('<spring:message code="prompted.request" />');},
					success : function(result) {
						layer.msg(result.msg);
						if(result.code == 200){
							history.go(0);
						}
					}
				});
			});
    	}
    	
    	
		function nowEntity(){
		    alert("--");
			history.go(0);
		}
		
		function doClose(){
			layer.closeAll();
			
			var treeObj = $.fn.zTree.getZTreeObj("parentId_tree");
    		treeObj.reAsyncChildNodes(null, "refresh");
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
		.layui-form-label{
			width: 150px;
		}
		body{
			padding: 20px;
		}
		.progress {
			display: none;
		}
		a:hover {
		    color: #777;
		}
		a, a:hover, a:focus {
			color: #333333;
		    text-decoration: none;
		    outline: none;
		    -webkit-transition: all 0.3s;
		    transition: all 0.3s;
		    -moz-transition: all 0.3s;
		    -o-transition: all 0.3s;
		}
	</style>
  </head>
  
  <body>
    <button class="layui-btn" onclick="toInsert1(0);"><spring:message code="office.addbutton" /></button>
    
    <div class="layui-form">
	  <table id="treetable1" class="layui-table">
	    <colgroup>
	      <col width="15%">
	      <col width="25%">
	      <col width="10%">
	      <col width="10%">
	      <col width="15%">
	      <col width="25%">
	      <col>
	    </colgroup>
	    <thead>
	      <tr>
	        <th><spring:message code="office.name" /></th>
	        <th><spring:message code="office.code" /></th>
	        <th><spring:message code="office.parent" /></th>
	        <th><spring:message code="office.type" /></th>
	        <th><spring:message code="op" /></th>
	      </tr> 
	    </thead>
	    <tbody id="tbody1"></tbody>
	  </table>
	</div>
	
	<div id="addOrUpdate" style="display: none;">
		<fieldset class="layui-elem-field">
		  <div class="layui-field-box">
		  <form id="menuObj" class="layui-form" method="post" action="doAddOrUpdate.do">
		  
		  <input type="hidden" id="Id" name="Id" value="">
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.superior" />：</label><span class="required_field" >*</span>
		    <div class="layui-input-inline">
		      <input type="text" id="parentId" datatype="*1-100"  autocomplete="off" placeholder="" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item area_select">
			    <label class="layui-form-label"><spring:message code="office.parent" />：</label><span class="required_field" >*</span>
			    <div class="layui-input-inline">
			      <select class="area-prov" lay-search="">
			        <option value=''><spring:message code="office.provincial" /></option>
			      </select>
			    </div>
			    <div class="layui-input-inline"  lay-search="" >
			      <select class="area-district" >
			        <option value=''><spring:message code="office.city" /></option>
			      </select>
			    </div>
			   <div class="layui-input-inline" lay-search="" >
			      <select  class="area-county" name="areaId" datatype="*1-100" nullmsg = "">
			        <option value='' ><spring:message code="office.area" /></option>
			      </select>
			    </div>
			   <div class="Validform_checktip"></div>
		  </div>
		  
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.name" />：</label><span class="required_field" >*</span>
		    <div class="layui-input-inline" >
		      <input type="text" id="name" name="name" datatype="s1-100" placeholder="<spring:message code="office.pname" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.code" />：</label><span class="required_field" >*</span>
		    <div class="layui-input-inline" >
		      <input type="text" id="code" name="code" datatype="s1-100" placeholder="<spring:message code="office.pcode" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.type" />：</label><span class="required_field" >*</span>
		    <div class="layui-input-inline">
		      <select id="type" name="type" >
		        <option value="1" selected><spring:message code="office.type.1" /></option>
		        <option value="2"><spring:message code="office.type.2" /></option>
		      </select>
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.level" />：</label>
		    <div class="layui-input-inline">
		      <select id="level" name="level" >
		        <option value="1" selected><spring:message code="office.level.1" /></option>
		        <option value="2"><spring:message code="office.level.2" /></option>
		        <option value="3"><spring:message code="office.level.3" /></option>
		        <option value="4"><spring:message code="office.level.4" /></option>
		      </select>
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.address" />：</label>
		    <div class="layui-input-inline" >
		      <input type="text" id="address" name="address" placeholder="<spring:message code="office.paddress" />" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.zip" />：</label>
		    <div class="layui-input-inline" >
		      <input type="text" id="zipCode" name="zipCode" datatype="/^\s*$/|p" placeholder="<spring:message code="office.pzip" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.master" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="master" name="master"  placeholder="<spring:message code="office.pmaster" />" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.phone" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="phone" name="phone" datatype="/^\s*$/|m" placeholder="<spring:message code="office.pphone" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.fax" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="fax" name="fax" datatype="/^\s*$/|/^(\d{3,4}-)?\d{7,8}$/" datatype="/^\s*$/|m" placeholder="<spring:message code="office.pfax" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.email" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="email" name="email" datatype="/^\s*$/|e" placeholder="<spring:message code="office.pemail" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.url" /></label>
		    <div class="layui-input-inline" >
		      <input type="text" id="url" name="url" datatype="/^\s*$/|url" placeholder="<spring:message code="office.purl" />" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		   <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.logo" />666：</label>
		    <div class="layui-input-inline" style="width: 400px;">
		    	<input id="file-upload" class="file" name="file-upload" value="666" type="file"  >
		    	<input type="hidden" id="logoUrl" name="logoUrl" value="" class="layui-input">
			</div>
		  </div>
		  
		   <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.xc" />：</label>
		    <div class="layui-input-inline" style="width: 400px;">
		    	<input id="file-upload2" class="file" type="file" data-min-file-count="1" >
		    	<input type="hidden" id="xcUrl" name="xcUrl" value="" class="layui-input">
			</div>
		  </div>
		  
		   <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.background" />：</label>
		    <div class="layui-input-inline" style="width: 400px;">
		    	<input id="file-upload3" class="file" type="file" data-min-file-count="1" >
			    <input type="hidden" id="backgroundUrl" name="backgroundUrl" value="" class="layui-input">
			</div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label tb"><spring:message code="op.remarks" />：</label>
		    <div class="layui-input-inline" style="width: 40%;">
		     <textarea id="remark" name="remark" placeholder="<spring:message code="office.pcontent" />" class="layui-textarea"></textarea>
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-filter="formDemo" type="submit"><spring:message code="op.submit" /></button>
		      <button type="reset" class="layui-btn layui-btn-primary" onclick="doClose();"><spring:message code="op.cancel" /></button>
		    </div>
		  </div>
		  
		</form>
		 </div>
		</fieldset>
	</div>
  </body>
</html>
