<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>test</title>
</head>
<body>
         <form action="listUers.do">
             <table>
                 <tr>
                     <td>编号</td>
                     <td>用户名</td>
                     <td>密码</td>
                     <td>真实姓名</td>
                 </tr>
              <c:forEach  var="users" items="${usersList}" varStatus="vs">
                     <td >${vs.count}</td>
                     <td>${users.name}</td>
                     <td>${users.password}</td>
                     <td>${users.realname}</td>
              </c:forEach>
                 <tr></tr>
             </table>

         </form>
</body>
</html>
