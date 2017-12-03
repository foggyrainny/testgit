<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp" %>   
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>新安人才网</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/bootstrap-custom.min.css">
<style type="text/css">
.panel-body { padding: 5px; }
</style>
</head>
<body>
<div class="panel-group" id="accordion">

    <div class="panel panel-primary">
      <div class="panel-heading">
        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse-user"> <span class="glyphicon glyphicon-user"></span> &nbsp;用户管理 </a> </h4>
      </div>
      <div id="collapse-user" class="panel-collapse collapse">
        <div class="panel-body">
          <ul class="nav nav-pills nav-stacked">
            <li><a href="${ctx}/list_Users.do" target="frame-main"> <span class="glyphicon glyphicon-user"></span> &nbsp;用户管理</a></li>

          </ul>
        </div>
      </div>
    </div>
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse-course"> <span class="glyphicon glyphicon-th"></span> &nbsp;课程管理 </a> </h4>
      </div>
      <div id="collapse-course" class="panel-collapse collapse">
        <div class="panel-body">
          <ul class="nav nav-pills nav-stacked">
            <li><a href="../Course/list_Course.do" target="frame-main"> <span class="glyphicon glyphicon-th"></span> &nbsp;课程管理</a></li>
          </ul>
        </div>
      </div>
    </div>
   
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse-student"> <span class="glyphicon glyphicon-th"></span> &nbsp;学生管理 </a> </h4>
      </div>
      <div id="collapse-student" class="panel-collapse collapse">
        <div class="panel-body">
          <ul class="nav nav-pills nav-stacked">
            <li><a href="${ctx}/list_Student.do" target="frame-main"> <span class="glyphicon glyphicon-th"></span> &nbsp;学生管理</a></li>
          </ul>
        </div>
      </div>
    </div>
 
     <div class="panel panel-primary">
      <div class="panel-heading">
        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse-teacher"> <span class="glyphicon glyphicon-th"></span> &nbsp;教师管理 </a> </h4>
      </div>
      <div id="collapse-teacher" class="panel-collapse collapse">
        <div class="panel-body">
          <ul class="nav nav-pills nav-stacked">
            <li><a href="${ctx}/list_Teacher.do" target="frame-main"> <span class="glyphicon glyphicon-th"></span> &nbsp;教师管理</a></li>
          </ul>
        </div>
      </div>
    </div> 
    
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse-user-detail"> <span class="glyphicon glyphicon-th"></span> &nbsp;用户明细管理 </a> </h4>
      </div>
      <div id="collapse-user-detail" class="panel-collapse collapse">
        <div class="panel-body">
          <ul class="nav nav-pills nav-stacked">
            <li><a href="${ctx}/list_UserInfo.do" target="frame-main"> <span class="glyphicon glyphicon-th"></span> &nbsp;用户明细管理</a></li>
          </ul>
        </div>
      </div>
    </div>   
    
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse-product"> <span class="glyphicon glyphicon-th"></span> &nbsp;产品明细管理 </a> </h4>
      </div>
      <div id="collapse-product" class="panel-collapse collapse">
        <div class="panel-body">
          <ul class="nav nav-pills nav-stacked">
            <li><a href="${ctx}/list_Product.do" target="frame-main"> <span class="glyphicon glyphicon-th"></span> &nbsp;产品明细管理</a></li>
          </ul>
        </div>
      </div>
    </div>   
            
 
    <div class="panel panel-primary">
    <div class="panel-heading">
      <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse-profile"> <span class="glyphicon glyphicon-file"></span> &nbsp;个人资料管理 </a> </h4>
    </div>
    <div id="collapse-profile" class="panel-collapse collapse in">
      <div class="panel-body">
        <ul class="nav nav-pills nav-stacked">
          <li><a href="../user-profile.html" target="frame-main"> <span class="glyphicon glyphicon-file"></span> &nbsp;个人资料管理</a></li>
          <li><a href="../user-password.html" target="frame-main"> <span class="glyphicon glyphicon-lock"></span> &nbsp;密码修改</a></li>
          <li><a href="${ctx}/logout.do" target="_parent"> <span class="glyphicon glyphicon-off"></span> &nbsp;退出系统</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>
<script src="${ctx}/static/jquery/1.11.1/jquery.min.js"></script> 
<script src="${ctx}/static/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>