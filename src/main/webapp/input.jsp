<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>        
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
	<form action="message.do?method=save" method="post">
	<table border="1" cellspacing="0" width="900">
	<tr>
		<th>姓名</th>
		<th>密码</th>
		<th>性别</th>
		<th>手机</th>
		<th>地址</th>
		<th>备注</th>
	</tr>
	<tr>
		<td><input type="text" name="name" value="${users.name }"/></td>
		<td><input type="text" name="password" value="${users.password}"/></td>
		<td><input type="radio" name="sex" ${users.sex eq 1 ? "checked='checked'" : ""} value="1" >男 &nbsp;
	   		<input type="radio" name="sex" ${users.sex eq 2 ? "checked='checked'" : ""} value="2" >女 <br/></td>
		<td><input type="text" name="mobile" value="${users.mobile }"/></td>
		<td><input type="text" name="address" value="${users.address}"/></td>
		<td><textarea name="meto" >${users.meto}</textarea></td> 
	</tr>
	</table>
		<input type="hidden" name="id" value="${users.id }"/>
		<input type="submit" value="提交"/> 
		<input type="hidden" name="method" value="add"/>
	</form>
	
	</body>
</html>
    