<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/bootstrap-custom.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery.validate/1.11.1/validate.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-icheck/1.0.2/skins/all.css" >
</head>
<body>
<!--  模块导航 -->
 <div class="panel panel-primary">
 	<div class="panel-heading">
 	<h3 class="panel-title"><span class="glyphicon glyphicon-home">&nbsp;</span>教师资源 &gt;教师管理</h3>
 	</div>
 </div>
<!--  查询表单 -->
<form class="form-inline" method="post" action="listTeacher.do">
<div class="panel panel-primary panel-heading">
  <fieldset>
    <div class="form-group">
      <label for="f_LIKE_S_realname">姓名：</label>
    </div>
    <div class="form-group">
      <input id="f_LIKE_S_realname" name="name"  value="${teacher.name}"  type="text" class="form-control" style="width:120px;">
    </div>   
    
    <div class="form-group">
      <label for="f_LIKE_S_realname">职称：</label>
    </div>
    <div class="form-group">
      <input id="f_LIKE_S_realname" name="position"  value="${teacher.position}"  type="text" class="form-control" style="width:120px;">
    </div>
    
     <div class="form-group">
      <label for="f_LIKE_S_realname">年龄：</label>
    </div>
    <div class="form-group">
      <input id="f_LIKE_S_realname" name="start_age"  value="${map.start_age}"  type="text" class="form-control" style="width:120px;">至
      <input id="f_LIKE_S_realname" name="end_age"  value="${map.end_age}"  type="text" class="form-control" style="width:120px;">
    </div>   
       
    <div class="form-group">
      <button id="btn-search" name="btn-search" class="btn btn-primary" type="submit"> <span class="glyphicon glyphicon-search"></span> 搜索</button>
      <button id="btn-reset" name="btn-reset" class="btn btn-warning" type="reset"> <span class="glyphicon glyphicon-repeat"></span> 重置</button>
    </div>
  </fieldset>
 </div>
</form> 



<form method="post">
  <div class="panel panel-primary">
    <div class="panel-heading">
    	<span class="glyphicon glyphicon-list">&nbsp;</span>        
    	<button type="button" class="btn btn-success" onclick="location.href='addTeacher.do'" ><span class="glyphicon glyphicon-plus"></span>添加</button>
  		<button type="button" class="btn btn-danger"  ><span class="glyphicon glyphicon-remove"></span>删除</button>
    </div>
    <table class="table table-striped table-bordered table-hover">
      <thead>
        <tr>
          <th width="5%" style="text-align:center"><input type="checkbox" class="icheck"  name="check-all" id="check-all" /></th>
          <th >序号</th>
          <th >姓名</th>
          <th >婚否</th>
          <th >性别</th>
          <th>职称</th>
		  <th>年龄</th>
		  <th>科目</th>
		  <th>入职时间</th>
		  <th>电话</th>
        </tr>
      </thead>
      <tbody>
         <c:forEach var="teacher" items="${teacherList}" varStatus="vs">
          <tr>
            <td align="center"><input type="checkbox" class="icheck" name="ids" id="ids" ></td>
            <td>${vs.count}</td>
            <td>${teacher.name}</td>
				<td>${teacher.ismarried eq 1 ? "已婚":"未婚"}</td>
				<td>${teacher.sex eq 1 ? "男" : "女"}</td>
				<td>${teacher.position}</td>
				<td>${teacher.age}</td>
				<td>${teacher.subject}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${teacher.hiredate}" /></td>
				<td>${teacher.mobile}</td>           
            <td >
      			<a  href="inputTeacher.do?id=${teacher.id}" style="cursor:pointer" >修改</a>&nbsp;            	
      			<a  href="deleteTeacher.do?id=${teacher.id}" style="cursor:pointer" >删除</a>    	      	
            </td>
          </tr>
      	</c:forEach>
      </tbody>
    </table>
    <div class="panel-footer" style="text-align:center">      
    </div>
  </div>
</form>

<form action="listTeacher.do" method="post">
	<table  align="center" border="0" cellspacing="0" width="860px">	
	<tr>
		<td colspan="10" align="center">
			共${pager.recordCount}条记录    每页显示${pager.pageSize}条    分${pager.pageCount}页/第${pager.currentPage}页
		   	 <a style="cursor:pointer;text-decoration: underline;" href="listTeacher.do?requestPage=${pager.firstPage}">首页</a>
		   	 
		   	 <c:choose>
		   	 	<c:when test="${pager.currentPage le 1}">
		   	 		<span style="color:grey">上一页</span>
		   	 	</c:when>
		   	 	<c:otherwise>
		   	 		<a style="cursor:pointer;text-decoration: underline;" href="listTeacher.do?requestPage=${pager.priviousPage}">上一页</a>
		   	 	</c:otherwise>
		   	 </c:choose>
		   	 
		   	 <c:choose>
		   	 	<c:when test="${pager.currentPage ge pager.pageCount}">
		   	 		<span style="color:grey">下一页</span>
		   	 	</c:when>
		   	 	<c:otherwise>
		   	 		<a style="cursor:pointer;text-decoration: underline;" href="listTeacher.do?requestPage=${pager.nextPage}">下一页</a>
		   	 	</c:otherwise>
		   	 </c:choose>
		   	 
		   	 <a style="cursor:pointer;text-decoration: underline;" href="listTeacher.do?requestPage=${pager.lastPage}">尾页</a>
		   	 <input style="text-align:center;border: 1px solid #CCCCCC;" type="text" name="requestPage" onchange="this.value=(new RegExp('^[0-9]*$').test(this.value)) ? this.value : 1" value="${pager.requestPage}" size="2" id="user_requestPage"/>
		   	 <input type="submit" value="转到"  id="sb"/>
		</td>
	</tr>
	</table>
</form>	
		
<script src="${ctx}/static/jquery/${jquery_version}/jquery.min.js"></script>
<script type="text/javascript">
	function validate(value){
	    var reg = new RegExp("^[0-9]*$");
	 	if(!reg.test(value)){
	     alert("请输入数字!");
	 	}
	}
	<%--当填入数字大于总页数时将requestPage值设置为pageCount --%>
	$("#user_requestPage").change(function(){
		var pc = ${pager.pageCount};
		var user_rp = $("#user_requestPage").val();
    	if(user_rp > pc) {
    		alert("总页数只有"+ pc + "页");
    		 $("#user_requestPage").val(${pager.currentPage});
    	}
	}); 
</script>
</body>
</html>