<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
		
		</style>
	</head>
	<body>
	<h1 align="center">用户管理系统</h1>
	
	<form action="login.do"  method="post" style="margin:auto; width:360px;" >
	<span>${errors } &nbsp;  ${data }</span><br/>
	<span >姓名：<input type="text" name="username" value="${username }"/>${Uerror }<br/></span>
	<span>密码：<input type="password" name="password" value="${password }"/>${Perror }<br/></span>
	<span><input type="submit" value="提交"/>
	<input type="reset" value="重置"/></span>
	</form>
	
	</body>
</html>
    