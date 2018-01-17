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
    <title>更新角色</title>
   
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
  
  <body>
    <div id="addOrUpdate">
		<fieldset class="layui-elem-field">
		  <div class="layui-field-box">
		  <form id="form2" class="layui-form" action="role/doAddOrUpdate.do" method="post">
		   <input type="hidden" id="Id" name="id" value="-1">
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="office.ascription" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline">
		      <input type="text" id="officeId2" datatype="*1-100"  autocomplete="off" placeholder="" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		   <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="role.name" /></label><span class="required_field" >*</span>
		    <div class="layui-input-inline" >
		      <input type="text" id="roleName2" name="roleName" datatype="s1-100" autocomplete="off" class="layui-input">
		    </div>
		    <div class="Validform_checktip"></div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label"><spring:message code="role.menupermissions" /></label>
		    <div class="layui-input-inline">
		      	<div id="treeDemo1" class="ztree"></div>  
		      	<input type="hidden" id="menus" name="menus" value="">
		    </div>
		    
		    <label class="layui-form-label"><spring:message code="role.datapermissions" /></label>
		    <div class="layui-input-inline">
		      	<div id="treeDemo2" class="ztree"></div>
		      	<input type="hidden" id="offices" name="offices" value="">
		    </div>
		    
		    <div class="layui-input-inline" id="note" style="display:none;margin-left:60px;margin-top:10px;font-size:12px;">
				  <span class="layui-input-inline"><spring:message code="select.tick" />;</span>
				  </br></br> </br>
				  <span class="layui-input-inline"><spring:message code="select.tick2" />;</span>
				  </br></br>
				  <img alt="" src="<%=basePath%>images/check1.png">
				  <img alt="" src="<%=basePath%>images/check2.png">
			</div>
		  </div>
		  
		  
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button type="button"  class="layui-btn"  onclick="doAdd();"><spring:message code="op.submit" /></button>
		      <button type="button" class="layui-btn layui-btn-primary" onclick="closeT()"><spring:message code="op.cancel" /></button>
		    </div>
		  </div>
		</form>
		 </div>
		</fieldset>
	</div>
	 <script type="text/javascript">
    var officeArray= new Array();
    
    
    $(document).ready(function(){
    	treeData();
		officeTree({selector:"#officeId2",inputName:"officeId",url:"<%=basePath%>/selector/office.do?type=role",
			dataFilter:function(treeId, parentNode, responseData){
				return responseData.data;
			}
		});
		initData(${id});
    })
    
    	
    	function initData(id){
    		
    	
	    	commmonAjax({url:"role/getRole.do",data:{id:-1},success:function(result){
				if(result.code == 200) {
					$.each((result.data.offices+"").split(","),function(index,e){
							var ids = e.split("-");
							officeArray[ids[0]+""] = ids[1];
					});
					layui.form().render();
				}
			 }});
    	
    	
    		if(id == -1){
    			return;
    		}
    		
    		
    		
    		
    		commmonAjax({url:"role/getRole.do",data:{id:id},success:function(result){
				if(result.code == 200) {
					var data = result.data;
					$("#Id").val(data.id);
					$("#roleName2").val(data.roleName);
					
					
					
					updateTree("officeId2_tree",data.officeId)
					SelectNode("treeDemo1",data.menus,true);
					SelectNode("treeDemo2",data.offices,false);
					layui.form().render();
				}
			}});
    	}
    	
    	function doAdd(){
			 var zTree = $.fn.zTree.getZTreeObj("treeDemo1");
             var nodes=zTree.getChangeCheckedNodes(true);  
             var menus = "";  
             if(nodes.length==0){  
                 layer.msg("请选择菜单权限！");  
                 return false;  
             }  
             for (var i = 0; i < nodes.length; i++) {  
                 menus += nodes[i].id +',';  
             }  
             menus = menus.substring(0,menus.lastIndexOf(","));
             $("#menus").val(menus);
             
             
             zTree = $.fn.zTree.getZTreeObj("treeDemo2");
             //nodes=zTree.getChangeCheckedNodes(true); 
             nodes = zTree.getCheckedNodes(true);
             
             
             var offices = "";
             if(nodes.length==0){  
                 layer.msg("请选择数据权限！");  
                 return false;  
             }
             for (var i = 0; i < nodes.length; i++) {
             	var halfCheck = nodes[i].getCheckStatus();
             	if (halfCheck.half){
             		offices += nodes[i].id+'-0' +',';
             	} else {
             		var sta = true;  //表示下級全部禁用
             		//  查看下級是否全部禁用
             		for(var child in nodes[i].children){
             			if(!nodes[i].children[child].chkDisabled){
             				sta = false;
             				break;
             			}
             		}
             		if(sta) {
             			offices += nodes[i].id+'-2' +',';
             		} else {
             			offices += nodes[i].id+'-0' +',';
             		}
             	}
             }
             offices = offices.substring(0,offices.lastIndexOf(","));
             $("#offices").val(offices);
             
             $("#form2").submit();
		}
    	
    	function treeData(){
	        commmonAjax({
	            type:'post',  
	            url:'role/getMenuList.do',
	            async:false,
	            success:function(result){  
	                var setting = {
	                	callback:{
	                		onClick:zTreeOnClick1,
	                		onExpand:openNode,//展开节点回调函数
	                		beforeCollapse:closeNode//折叠节点回调函数
	                	}, 
	                    data: {
							simpleData: {
								enable: true,
								idKey: "id",
								pIdKey: "parentId",
								rootPId: -1
							}
						},
	                    check: {
	                        enable: true  
	                    }  
	                };
	                 var zTreeNodes=result.data;
	                zTreeObj = $.fn.zTree.init($("#treeDemo1"), setting, zTreeNodes); 
	            }  
	        });
			
	         commmonAjax({
	            type:'post',  
	            url:'role/getOfficeList.do',
	            async:false,
	            success:function(result){  
	                var setting = {
	                	callback:{
	                		onClick:zTreeOnClick2,
	                	    beforeCheck:zTreeOnCheck2,
	                	    onExpand:openNode,//展开节点回调函数
	                		beforeCollapse:closeNode//折叠节点回调函数
	                	},
	                    data: {
							simpleData: {
								enable: true,
								idKey: "id",
								pIdKey: "parentId",
								rootPId: -1
							}
						},
	                    check: {
	                        enable: true,
							chkboxType: { "Y": "s", "N": "s" }
	                    }
	                };
	                 var zTreeNodes=result.data;
	                zTreeObj = $.fn.zTree.init($("#treeDemo2"), setting, zTreeNodes); 
	                //SelectNode();  
	            }  
	        });
		}
		
		
		
		//菜单权限点击事件
		function zTreeOnClick1(event, treeId, treeNode){
			console.log(treeNode);
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			zTree.checkNode(treeNode, !treeNode.checked, true, true);
			//alert(treeNode.id + ", " + treeNode.name +","+treeId);
		}
		
		//数据权限点击事件
		function zTreeOnClick2(event, treeId, treeNode){
			
			var checkAuth = true;
			if(officeArray[treeNode.id]==0) {
				checkAuth = false;
			}
			
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			console.log(treeNode.getCheckStatus());
			var halfCheck = treeNode.getCheckStatus();
			if(!halfCheck.checked && !halfCheck.half){//未勾
				zTree.checkNode(treeNode, true, null, true,false);
			} else if(treeNode.checked && halfCheck.half){//半勾
				zTree.checkNode(treeNode, true, true, true,false);
				if(checkAuth) {
					treeChkDisabled(zTree,treeNode,true);//设置禁用子节点	
				}	
			} else if(treeNode.checked && !halfCheck.half){//实勾
				treeChkDisabled(zTree,treeNode,false);//取消禁用
				zTree.checkNode(treeNode, false, true, true,false);
			} else if(!treeNode.checked && halfCheck.half){// 阴影
				zTree.checkNode(treeNode, true, null, true,false);
			}
			return true;
			//alert(treeNode.id + ", " + treeNode.name +","+treeId);
		}
		
		//数据权限点击事件
		function zTreeOnCheck2(treeId, treeNode){
			
			var checkAuth = true;
			if(officeArray[treeNode.id]==0) {
				checkAuth = false;
			}
			
			 var zTree = $.fn.zTree.getZTreeObj(treeId);
			console.log(treeNode.getCheckStatus());
			var halfCheck = treeNode.getCheckStatus();
			if(!halfCheck.checked && !halfCheck.half){//未勾
				zTree.checkNode(treeNode, true, null, true,false);
			} else if(treeNode.checked && halfCheck.half){//半勾
				zTree.checkNode(treeNode, true, true, true,false);
				//treeChkDisabled(zTree,treeNode,true);//设置禁用子节点
				if(checkAuth) {
					treeChkDisabled(zTree,treeNode,true);//设置禁用子节点	
				}
			} else if(treeNode.checked && !halfCheck.half){//实勾
				treeChkDisabled(zTree,treeNode,false);//取消禁用
				zTree.checkNode(treeNode, false, true, true,false);
			} else if(!treeNode.checked && halfCheck.half){// 阴影
				zTree.checkNode(treeNode, true, null, true,false);
			}
			return false;
		}
		
		
		function onCheck2(treeId, treeNode){
			console.log(treeNode);
			return true;
		}
		
		//设置tree选项是否禁用（迭代方法）
		function treeChkDisabled(zTree,treeNode,b){
			var childrenNodes = treeNode.children;
			if (childrenNodes) {
	            for (var i = 0; i < childrenNodes.length; i++) {
	            	if(childrenNodes[i].children){
	            		treeChkDisabled(zTree,childrenNodes[i],b);
	            	}
	                zTree.setChkDisabled(childrenNodes[i], b);
	            }
	        }
		}
		
		function SelectNode(treeId,objes,b){
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			//treeObj.checkAllNodes(false);
			
			var ids = objes.split(",");
			$.each(ids, function (index,e){
				var off = e.split("-");
				var treenode = treeObj.getNodeByParam("id", off[0], null);
				//console.log(treenode);
				if(treenode != null ){
	            	//treeObj.expandNode(treenode, true, b, true);  
	           		if(off[1] == 1) {
	            		treeObj.checkNode(treenode,true,true ,false);
	            		treeChkDisabled(treeObj,treenode,true);
	            	} else {
	            		treeObj.checkNode(treenode,true,false ,false);
	            	}
            	}
			});
		}
		
		function updateTree(treeId,id){
    		var zTree = $.fn.zTree.getZTreeObj(treeId);
			var nodes = zTree.getNodeByParam("id", id);
			// 选中父节点
			if(nodes != null ){
				zTree.checkNode(nodes, true,false,false);
			}
			$("#"+treeId).trigger("tree.select.change");
    	}
    	
    	layui.use('form', function(){
		  	var form = layui.form();
		  	form.on('submit(formDemo)', function(data){
		    	//layer.msg(JSON.stringify(data.field));
		    	//doAddOrUpdate(data.field);
		    	return false;
		  	}); 
		});
		
		function closeT(){
			//parent.getEntityList();
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭  
		}
		
		$(document).ready(function(){
		
			ajaxSubmitFormAndValid("form2",function(data){
			if(data.code == 200) {
				parent.getEntityList();
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				//layer.closeAll();
				layer.msg("更新成功");
			} else {
				layer.msg(data.msg);
			}
		},function(msg,o,cssctl){
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
			console.log(o.obj);	
			var objtip=o.obj.parent(".layui-input-inline,.layui-input-block").nextAll(".Validform_checktip");
			if(objtip.length > 1) {
				objtip = objtip[0];
			}	
			cssctl(objtip,o.type);
				objtip.text(msg);
			}else{
				/* var objtip=o.obj.find("#msgdemo");
				cssctl(objtip,o.type);
				objtip.text(msg); */
			}
		});
		
	})
	
    // 顶级节点展开，显示备注div
	function openNode(event, treeId, treeNode){
			if(treeId == "treeDemo1" && treeNode.id == 0){//菜单权限zTree
    			$("#note").show();
    		}
			
			if(treeId == "treeDemo2" && treeNode.id == 1){//数据权限zTree
    			$("#note").show();
    		}
		}
		
	// 顶级节点折叠，隐藏备注div
	function closeNode( treeId, treeNode,event){
		var attr1 =$("#treeDemo1_1_switch").hasClass("root_close");
		var attr2 =$("#treeDemo2_1_switch").hasClass("root_close");
		if(treeId == "treeDemo1" && treeNode.id == 0 &&  attr2 ){//菜单权限zTree
			$("#note").hide();
		}
		if(treeId == "treeDemo2" && treeNode.id == 1 &&  attr1 ){//数据权限zTree
			$("#note").hide();
		}
		
	}	
	
    </script>
  </body>
</html>
