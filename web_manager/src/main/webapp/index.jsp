<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String basePath2 = request.getServerName()+":"+request.getServerPort()+path+"/";

String path2 =  request.getServerName()+":"+request.getServerPort()+path+"/";

%>


<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="../../../resource.jsp" %>
	
  </head>
  <body style="height: 100%;">
	 <div id="backpng" style="height:100%;width:100%;" ></div>
     <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script> 
     <script type="text/javascript" src="<%=basePath%>js/layui/layui.js"></script> 
	
<script type="text/javascript">

var websocket;

$(document).ready(function(){
	
	$("#backpng").css("background"," no-repeat fixed top url(<%=basePath%>images/welcome_"+parent._LANG+".png)");
	
 if(parent.websocket != undefined && parent.websocket != null) {
	 websocket = parent.websocket;
 	console.log("websocket:" + websocket);
 } else  {
	  websocket = new SockJS("<%=basePath%>/sockjs/webSocketServer");
		websocket.onopen = function (evnt) {
			console.log("webSocket 初始化成功");
			// window.top.layui.layer.msg("webSocket 初始化成功");
		};
		
		websocket.onmessage = function (evnt) {
		//   console.log("接收到消息");
		 //  console.log(evnt.data);  
		//   window.top.layui.layer.msg(evnt.data);
		//  $("#msgcount").append(event.data);
		   // 进行SMU 消息通知
		  try{
			  var  data = eval('(' + evnt.data + ')');  
			
			  if(data.type =='notify'){
				 // smuStatus(data.data);
			  } else if(data.type =='cmd') {

				  data = data.data;
				 /*  if(smu!=undefined){
					   var win = smu.eventList[data.msgId].win;
					  
					   win.$(win.document).trigger(data.msgId+"",[data.msgId,data]);
				   }  */
				  //  通知 可能会比消息发送还快，  so  延时三秒执行，
				   window.setTimeout(function(){dealCommand(data)},3000);
			  } else if(data.type =='cj'){
				  if(top.SERVER_CJ_DOCUMENT !=null) {
					  top.SERVER_CJ_DOCUMENT.doChange(data.data);
				  }
			  }
			 			 
		  }catch(err){
			  console.log(err);
		  }
		    
		};
		
		websocket.onerror = function (evnt) {
			console.log("错误")
			alert("初始化异常");
		};
		
		websocket.onclose = function (evnt) {
		}
 }



});

function  dealCommand(data,i){
	 if(i == null || i == undefined) {
		 i = 1;
	 }
	 
	 if(i>5) {
		 return;
	 }
	 
	 if(smu!=undefined){
		   var win = smu.eventList[data.msgId].win;
		   
		   if(win == null||win==undefined){
			   window.setTimeout(function(){dealCommand(data,++i)},3000);
		   	   return;
		   } 
		   win.$(win.document).trigger(data.msgId+"",[data.msgId,data]);
	   } 
}


function smuStatus(msg){
	top.layer.open({
		    title:'<spring:message code="sysnotifications" />'
	        ,type: 1
	        ,offset: 'rb' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
	        ,content: '<div style="padding: 20px 30px;"><spring:message code="smu.device" />['+ msg.smuId +']'+ (msg.type == 1 ? '<spring:message code="device.line" />' : '<spring:message code="offline" />') +'</div>'
	        ,btn: '<spring:message code="op.close" />'
	        ,time: 10000
	        ,btnAlign: 'c' //按钮居中
	        ,shade: 0 //不显示遮罩
	        ,yes: function(index){
	        	top.layer.close(index);
	        }
	      });
}

</script>

</body>
         
</html>
