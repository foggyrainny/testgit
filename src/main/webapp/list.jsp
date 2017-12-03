<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
	</head>
	<body>
	<form action="message.do" method="post">
		<table border="1" width="860px">
			<tr><th>姓名</th>
				<td><input type="text" name="name" value="${users.name}" /></td>
				<th>性别</th>
				<td><input type="radio" name="sex" ${users.sex eq 1? "checked ='checked'":"" } value="1" size="10" />男
					<input type="radio" name="sex" ${users.sex eq 2? "checked ='checked'":"" } value="2" size="10" />女
				</td>
				<th>电话</th>
				<td><input type="text" name="mobile" value="${users.mobile}" /></td>
				<th><input type="submit" value="查询" /> </th>
			</tr>
		</table>
	</form>
		<br/>
		<table border="1" width="860px">
			<tr>
				<th>序号</th>
				<th>用户名</th>
				<th>密码</th>
				<th>性别</th>
				<th>添加时间</th>
				<th>电话</th>
				<th>地址</th>
				<th>备注</th>
				<th width="100px"><input type="button" onclick="location.href='message.do?method=add'" value="添加"/></th>
			</tr>
			<c:forEach var="users" items="${usersList}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${users.name}</td>
					<td>${users.password}</td>
					<td>${users.sex eq 1 ? "男" : "女"}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${users.add_time}" /></td>
					<td>${users.mobile}</td>
					<td>${users.address}</td>
					<td>${users.meto}</td>
					<td>
						<a href="message.do?method=edit&&id=${users.id}">修改</a>&nbsp;
						<a href="message.do?method=delete&&id=${users.id}">删除</a>
					</td>
			</c:forEach>		
		</table>	
	</body>
</html>