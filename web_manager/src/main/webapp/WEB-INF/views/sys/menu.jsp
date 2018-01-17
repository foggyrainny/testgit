<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String basePath2 = request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>ä¸ªäººä¿¡æ¯</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<%@include file="../../../resource.jsp" %>
</head>
<body>
<section class="layui-larry-box">
	<div class="larry-personal">
	    <div class="layui-tab">
           <ul class="layui-tab-title">
		    	 <li class="layui-btn layui-this"><i class="layui-icon">&#xe60a;</i>新增</li>
		    	 <a class="layui-btn layui-btn-small larry-log-del"><i class="iconfont icon-huishouzhan1"></i>批量删除</a>
		    </ul>
            
            <div class="larry-separate"></div>
		    <div class="layui-tab-content larry-personal-body clearfix mylog-info-box">
                <div class="layui-tab-item layui-field-box layui-show">
                     <table class="layui-table table-hover" lay-even="" lay-skin="nob">
                          <thead>
                              <tr>
                                <th><input type="checkbox" id="selected-all"></th>
                              	<th> 菜单名称</th>
                               	<th>菜单地址</th>
                               	<th>菜单显示</th>
                                <th>上级菜单  </th>
                                <th>页面元素 </th>
                                <th>元素编码</th> 
                                <th>排序号 </th>
                                <th>创建时间</th> 
                                <th>操作</th>
                              </tr>
                          </thead>
                          <tbody>
                              <tr>
                                <td><input type="checkbox"></td>
                                <td>100</td>
                                <td>admin</td>
                                <td>2016-12-19</td>
                                <td>test</td>
                                <td>test</td>
                                <td>test</td>
                                <td>test</td>
                                <td>test</td>
                                <td>127.0.0.1</td>
                              </tr>
                          </tbody>
                     </table>
                     <div class="larry-table-page clearfix">
                          <a href="javascript:;" class="layui-btn layui-btn-small"><i class="iconfont icon-shanchu1"></i>å é¤</a>
				          <div id="page" class="page"></div>
			         </div>
			    </div>
		    </div>
		</div>
	</div>
</section>

<script type="text/javascript">
	layui.use(['jquery','layer','element','laypage'],function(){
	      window.jQuery = window.$ = layui.jquery;
	      window.layer = layui.layer;
          var element = layui.element(),
              laypage = layui.laypage;

            
          laypage({
					cont: 'page',
					pages: 10 ,
					groups: 5,
					jump: function(obj, first) {
						var curr = obj.curr;
						if(!first) {
						}
					}
				});
    });
</script>
</body>
</html>