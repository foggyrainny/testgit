<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
  <head>
    <meta name="renderer" content="webkit">
    <base href="<%=basePath%>">
    <title>光伏电站管理平台</title>
    
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css" media="all">
	
	<%@include file="../../resource.jsp" %>

	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePathResource%>css/adminstyle.css" media="all">
	
	
   <style type="text/css">
	   
   </style>
   </head>
<body  >


<div class="layui-canvs"></div>
<div class="layui-layout layui-layout-login">
	<h1>
		 <strong>光伏电站管理平台</strong>
		 <em>Management System</em>
	</h1>
	
	<form action="login.do" method="post" id="loginForm"  class=" " >
		<div class="layui-btn-group" style="text-align: right;width: 100%;">
			<a href="<%=basePath%>login.do?locale=zh_CN"  class="layui-btn layui-btn-small lang lang-zh_CN " ><spring:message code="chinese" /></a>  
			<a href="<%=basePath%>login.do?locale=en_US"  class="layui-btn layui-btn-small lang layui-btn-primary lang-en_US" ><spring:message code="english" /></a>  
		</div>
		<div class="layui-user-icon larry-login">
			 <input type="text"  name="account" placeholder="<spring:message code="account" />"  value="admin" sucmsg="" class="login_txtbx" style="height: 36px;width: 100%;" nullmsg="用户名不能为空"  datatype="s5-16" />
		</div>
		<div class="layui-pwd-icon larry-login">
			 <input type="password"  name="login_pwd" placeholder="<spring:message code="password" />"  value="123456" sucmsg="" class="login_txtbx"  style="height: 36px;width: 100%;" nullmsg="密码不能为空" datatype="s5-100" />
			 <input type="hidden"  name="enPwd" value="" class=""/>
		</div>
	   <!--  <div class="layui-val-icon larry-login">
	    	<div class="layui-code-box">
	    		<input type="text" id="code" name="code" placeholder="验证码" maxlength="4" class="login_txtbx">
	            <img src="imgverifycode.do" alt="" class="verifyImg" id="verifyImg" onclick="javascript:this.src='imgverifycode.do?'+Math.random();">
	    	</div>
	    </div> -->
	    <div class="layui-submit larry-login">
	    	<input type="submit"  value="<spring:message code="login" />" class="submit_btn"/>
	    </div>
    </form>
    <div class="layui-login-text">
    	<p>©2017  上海岩芯电子科技有限公司  V1.0.0.0623_alpha</p>
     
    </div>
</div>

<script type="text/javascript" src="<%=basePath%>js/layui/lay/dest/layui.all.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jsplug/jparticle.jquery.js"></script>
<script type="text/javascript">

$(document).ready(function () {
	
/* 	var tipmsg={//默认提示文字;
			tit:"",
			w:{
				"*":"不能为空！",
				"*6-16":"请填写6到16位任意字符！",
				"n":"请填写数字！",
				"n6-16":"请填写6到16位数字！",
				"s":"不能输入特殊字符！",
				"s6-18":"请填写6到18位字符！",
				"p":"请填写邮政编码！",
				"m":"请填写手机号码！",
				"e":"邮箱地址格式不对！",
				"url":"请填写网址！"
			},
			def:"请填写正确信息！",
			undef:"datatype未定义！",
			reck:"两次输入的内容不一致！",
			r:"通过验证！",
			c:"正在检测信息…",
			s:"请{填写|选择}{0|信息}！",
			v:"所填信息没有经过验证，请稍后…",
			p:"正在提交数据…"
		} */
		$.Tipmsg.r=null;
		$.Tipmsg.p=null;
	
	
	ajaxSubmitFormAndValid("loginForm",function(data){
		if(data.code == 200) {
			location.href = "index.do";
		}else {
			layer.msg(data.msg);
		}
	},function(msg){
		layer.msg(msg);
	});
	
	 $(".layui-canvs").width($(window).width());
	console.log(document.body.clientHeight);
	
	
	$(".layui-canvs").height(document.body.clientHeight > $(window).height() ?  document.body.clientHeight : $(window).height());
	$(".layui-canvs").jParticle({
		background: "#141414",
		color: "#E6E6E6"
	});
	
	
	var lang = getLangCookie(); 
	
	if( lang == null || lang == undefined ||  lang == '') {
		lang = 	'zh_CN';
	}
	$("a.lang").addClass("layui-btn-primary");
	$(".lang-"+lang).removeClass("layui-btn-primary");
});


$(window).resize(function(){ 
	 $(".layui-canvs").width($(window).width());
	 $(".layui-canvs").height(document.body.clientHeight > $(window).height() ?  document.body.clientHeight : $(window).height());
	 $(".layui-canvs").jParticle({
			background: "#141414",
			color: "#E6E6E6"
		});
})


function getLangCookie(){
	var name= "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE";
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)"); //正则匹配
    if(arr=document.cookie.match(reg)){
      return unescape(arr[2]);
    } else{
     	return null;
    }
} 

</script>
  </body>
</html>

