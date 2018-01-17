<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
	String pathResource = request.getContextPath();
	String basePathResource = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathResource+"/";
	
	if(org.springframework.web.servlet.support.RequestContextUtils.getLocaleResolver(request) !=null && org.springframework.web.servlet.support.RequestContextUtils.getLocaleResolver(request).resolveLocale(request) !=null) {
		request.setAttribute("language",  org.springframework.web.servlet.support.RequestContextUtils.getLocaleResolver(request).resolveLocale(request).getLanguage());
	} else {
		request.setAttribute("language",  "zh");
	}

	//  以下是为了对抗缓存锁的问题， 导致页面 卡死
	response.setHeader( "Cache-Control", "no-cache,no-store");//HTTP 1.1
	response.setDateHeader( "Expires", 0 ); //prevent caching at the proxy server
	response.setHeader( "Pragma", "no-cache" );  //HTTP 1.0  
%>
<script type="text/javascript">
	var  _SIZE_BASE_PATH = "<%=basePathResource%>";
	var  _LANG =  '<%=request.getAttribute("language")%>';
	var  SERVER_CJ_DOCUMENT = null;
</script>

	

	<link rel="stylesheet" type="text/css" href="<%=basePathResource%>css/validform_style.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePathResource%>css/layui/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePathResource%>css/global.css" media="all">
 	<link rel="stylesheet" type="text/css" href="<%=basePathResource%>/js/ztree/metro.css">
 
	<script type="text/javascript" src="<%=basePathResource%>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePathResource%>/js/layer/layer.js"></script>
	<script type="text/javascript" src="<%=basePathResource%>js/layui/layui.js"></script>
	<script type="text/javascript" src="<%=basePathResource%>js/layui/lay/modules/laypage.js"></script>
	<script type="text/javascript" src="<%=basePathResource%>js/treetable/jquery.treeTable.js"> </script> 
	<script type="text/javascript" src="<%=basePathResource%>js/Validform_v5.3.2/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=basePathResource%>/js/Validform_v5.3.2/Validform_Datatype.js"></script>
    <script type="text/javascript" src="<%=basePathResource%>/js/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="<%=basePathResource%>/js/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="<%=basePathResource%>/js/ztree/jquery.ztree.exedit.js"></script>
    <script type="text/javascript" src="<%=basePathResource%>/js/ztree/jquery.ztree.exhide.js"></script>
	<script type="text/javascript" src="<%=basePathResource%>js/ztree/jquery.ztree.exhide.js"></script>
	<script type="text/javascript" src="<%=basePathResource%>js/jquery.md5.js"></script>
	<script type="text/javascript" src="<%=basePathResource%>js/tableresize.js"></script>

	
	
	<!-- 处理 功能按钮的显示与隐藏 -->
	
	<script>
		var  NOT_DATA_MSG = '<spring:message code="op.nodata" />';
	    var currUserMenuHtmlCode;
		function hideHtmlCode(){
			try{
				if(currUserMenuHtmlCode == null) {
					var htmlCode = "${sessionScope.CURR_USER_MENU_HTML_CODE}";
					htmlCode = htmlCode.replace("[","").replace("]","")
					htmlCode = htmlCode.split(",");
					currUserMenuHtmlCode = new Array();
					for(var i = 0; i < htmlCode.length; i ++) {
						currUserMenuHtmlCode[$.trim(htmlCode[i])] = "1";
					}
					console.log(currUserMenuHtmlCode);
				}
				$("a[data-html-code]:hidden,button[data-html-code]:hidden").each(function(){
					var code = $(this).attr("data-html-code");
					console.log(code +"  " + currUserMenuHtmlCode[code]);
					if(currUserMenuHtmlCode[code] == "1"){
						$(this).show();
					} else {
						$(this).remove();
					}
					$(this).removeAttr("data-html-code");
				})
			}catch(err){
				console.log(err);
			}			
		}
		window.setInterval(hideHtmlCode, 100);
		
	</script>
	
	<script>
	 
		// 解決IE下 表单按钮无法提交的  bug
		$(document).ready(function(){
			console.log(navigator.appName);
			 if (navigator.appName == 'Microsoft Internet Explorer') {
	             $("button[form][type=submit]").on("click",function(){
	                $("#"+$(this).attr("form")).submit();
	             });
	             
	             if(window.setTimeout){
	            	 window.setTimeOut= window.setTimeout;
	             }
	          }
		})
		
		$(document).keydown(function (e) {//当按下按键时  
	    if (e.which == 13) {//.which属性判断按下的是哪个键，回车键的键位序号为13  
	        //$('button.searchBtn').trigger("click");//触发搜索按钮的点击事件  
	        if(typeof  queryData === 'function'){
	        	queryData();	
	        }
	        
	    }  
	});  	
		
		
	</script>
	
		<script type="text/javascript" src="<%=basePathResource%>js/command.js"></script>
	
	<script type="text/javascript">
	 	var tips={};
	 	tips.select="<spring:message code='op.select' />";
	 	tips.prov="<spring:message code='tips.select.prov' />";
	 	tips.district="<spring:message code='tips.select.district' />";
	 	tips.county="<spring:message code='tips.select.county' />";
	 	
	/* 迫于无奈  只能在此处 搞定指令js中的国际化问题了。。。 */
		smu.tips = function(name,smuId,invId,tmpn){
			 var tips = '<spring:message code="cmd.tips" />';
			 var tips2 = '<spring:message code="cmd.tips2" />';
			 var tips3 = '<spring:message code="cmd.tips3" />';
			 
			 if(tmpn == 3) {
				 return tips3.replace("#smuId",smuId).replace("#cmd",name).replace("#inv",invId);
			 }
			 if(invId!=null && invId!=undefined){
				 return tips2.replace("#smuId",smuId).replace("#cmd",name).replace("#inv",invId);
			 } else {
				 return tips.replace("#smuId",smuId).replace("#cmd",name);
			 }
		  }
		smu.lang.title= "<spring:message code='op.systitle' />";
		smu.lang.confirm = "<spring:message code='op.confirm' />";
		smu.lang.cancel= "<spring:message code='op.cancel'/>";

		smu.lang.cmdname = "<spring:message code='smu.cmd.name'/>";
		smu.lang.sendtime = "<spring:message code='smu.cmd.sendtime'/>";
		smu.lang.returntime ="<spring:message code='smu.cmd.returntime'/>";
		smu.lang.request ="<spring:message code='smu.cmd.request'/>";
		smu.lang.response = "<spring:message code='smu.cmd.response'/>";
		smu.lang.cmderror = "<spring:message code='smu.cmd.status.error'/>";
		smu.lang.cmdsuccess = "<spring:message code='smu.cmd.status.success'/>";
		smu.lang.cmdstatus = "<spring:message code='smu.cmd.status'/>";
		
		
		smu.cmd.queryTime.name="<spring:message code='smu.cmd.querytime'/>";
		smu.cmd.setTime.name="<spring:message code='smu.cmd.settime'/>";
		smu.cmd.reset.name="<spring:message code='smu.cmd.reset'/>";
		smu.cmd.network.name="<spring:message code='smu.cmd.network'/>";
		smu.cmd.stopNetwork.name="<spring:message code='smu.cmd.stopnetwork'/>";
		smu.cmd.startReport.name="<spring:message code='smu.cmd.startreport'/>";
		smu.cmd.stopReport.name="<spring:message code='smu.cmd.stopreport'/>";
		smu.cmd.clearInverter.name="<spring:message code='smu.cmd.clearinverter'/>";
		smu.cmd.paramConfig.name="<spring:message code='smu.cmd.paramconfig'/>";
		smu.cmd.queryConfigParam.name="<spring:message code='smu.cmd.queryconfigparam'/>";
		smu.cmd.queryInverterList.name="<spring:message code='smu.cmd.queryinverterlist'/>";
		smu.cmd.queryDebugStatus.name="<spring:message code='inv.cmd.querystatus'/>";
		smu.cmd.startInverter.name="<spring:message code='inv.cmd.startinv'/>";
		smu.cmd.stopInverter.name="<spring:message code='inv.cmd.stopinv'/>";
		smu.cmd.addInverter.name="<spring:message code='inv.cmd.addinv'/>";
		smu.cmd.delInverter.name="<spring:message code='inv.cmd.delinv'/>";
		smu.cmd.inverterPowerClear.name="<spring:message code='inv.cmd.energyclear'/>";
		smu.cmd.inverterQueryParamConfig.name="<spring:message code='inv.cmd.queryparamconfig'/>";
		smu.cmd.inverterParamConfig.name="<spring:message code='inv.cmd.invparamconfig'/>";
		
	</script>
	
		<script type="text/javascript" src="<%=basePathResource%>js/dateFormat.js"></script>
	    <script type="text/javascript" src="<%=basePathResource%>js/common.js"></script>

	
	<style>
		/** 
		 表单数据内容溢出隐藏变为省略号
		 .omit{white-space:nowrap; overflow:hidden; text-overflow:ellipsis;} 
		 */
		/** 表单数据内容自动换行*/
		.line-feed{word-break: break-all;word-wrap:break-word;}
		.l1{width:inherit;white-space: nowrap;padding:9px 5px;}
		.l2{width:inherit;white-space: nowrap;padding:9px 5px;}
		.l3{width:inherit;white-space: nowrap;padding:9px 5px;}
		.l4{width:inherit;white-space: nowrap;padding:9px 5px;}
		.l5{width:inherit;white-space: nowrap;padding:9px 5px;}
		.l6{width:inherit;white-space: nowrap;padding:9px 5px;}
		.l7{width:inherit;white-space: nowrap;padding:9px 5px;}
		.l8{width:inherit;white-space: nowrap;padding:9px 5px;}
		.d16{width: 160px !important;}
		.pager{text-align: right !important; margin: 10px !important; }
		
		.required_field{
		 	color:red;
		 	font-size:25px;
		 	margin-left:-10px;
		 }
		 
		 body {
    		font-size: 12px  !important;
		}
		
		.layui-table td {
    		font-size: 12px;
		}
		.layui-table th	{
			font-size: 12px;	
		}
		
		.layui-btn {
    		font-size: 12px;
		}
		
		a:hover {
   			 color: #0066FF;
		}
		
	</style>
	