<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 set//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理系统</title>
<script type="text/javascript">
	//<![CDATA[
	if (self != top) {
		top.location = self.location;
	}
	//]]>
</script>
</head>
<frameset rows="50,*" cols="*" border="0" framespacing="0">
	<frame src="${ctx}/Frame/top.jsp" name="frame-top" id="frame-top"
		scrolling="no" noresize="noresize" />
	<frameset cols="220,6,*" id="mainFrameset" framespacing="0"
		frameborder="no" border="0">
		<frame src="${ctx}/Frame/left.jsp" name="frame-left" id="frame-left" title="left"
			scrolling="auto" />
		<frame src="${ctx}/Frame/lr.jsp" name="frame-lr" id="frame-lr" title="lr"
			scrolling="no" noresize="noresize" />
		<frame src="${ctx}/Frame/main.jsp" name="frame-main" id="frame-main" title="main" />
		<frame src="${ctx}/Frame/bottom.jsp" name="frame-bottom" id="frame-bottom"
			scrolling="no" noresize="noresize" />
		<noframes>
			<body>对不起，您的浏览器不支持框架
			</body>
		</noframes>
	</frameset>
</frameset>
</html>