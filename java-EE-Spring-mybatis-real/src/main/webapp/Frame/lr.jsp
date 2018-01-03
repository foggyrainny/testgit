<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>${cfg.appName}</title>
<style type="text/css">
<!--
.console-menu-dragbar { width: 6px; height: 100%; position: absolute; right: 0; top: 0; background: url(${ctx}/static/project/images/switch/s_bg_0.jpg) 0 0; overflow: hidden; }
.drag-menu-contraction { cursor: pointer; width: 6px; height: 78px; position: absolute; right: 0px; background: url(${ctx}/static/project/images/switch/s_0.gif); z-index: 10; }
.drag-menu-contraction:hover { background: url(${ctx}/static/project/images/switch/s_0_hover.gif); }
.drag-menu-contraction.up { background: url(${ctx}/static/project/images/switch/s_1.gif); }
.drag-menu-contraction.up:hover { background: url(${ctx}/static/project/images/switch/s_1_hover.gif); }
-->
</style>
</head>
<body>
<div class="drag-menu-contraction" title="隐藏列表"></div>
<div class="console-menu-dragbar"></div>
<script src="${ctx}/static/jquery/1.11.1/jquery.min.js"></script> 
<script type="text/javascript">//<![CDATA[
var TITLE_HIDE = "隐藏列表";
var TITLE_SHOW = "展开列表";
var mainFrameset = top.document.getElementById("mainFrameset");
$(".console-menu-dragbar").css({"height":$(window).height()});

var $switch = $(".drag-menu-contraction");
$switch.css({
    "top": ($(window).height() / 2 - 39) + "px" 
}).click(function(){
	$(this).toggleClass("up");
	if ($(this).attr("title") == TITLE_HIDE) {
		$(this).attr("title", TITLE_SHOW);
		mainFrameset.cols = "0,6,*";
	} else {
		$(this).attr("title", TITLE_HIDE);
		mainFrameset.cols = "200,6,*";
	}
});
//]]></script>
</body>
</html>
