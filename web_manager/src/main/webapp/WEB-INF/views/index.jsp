<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

  <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

        <!DOCTYPE html>
        <html lang="en">

        <head>
          <base href="<%=basePath%>">
          <meta charset="UTF-8">
          <title>光伏电站后台管理系统</title>
          <meta name="renderer" content="webkit">
          <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
          <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
          <meta name="apple-mobile-web-app-status-bar-style" content="black">
          <meta name="apple-mobile-web-app-capable" content="yes">
          <meta name="format-detection" content="telephone=no">

          <%@include file="../../resource.jsp" %>
            <link rel="stylesheet" type="text/css" href="<%=basePath%>css/adminstyle.css" media="all">
            <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        </head>

        <body>


          <div class="layui-layout layui-layout-admin" id="layui_layout">
            <!-- 顶部区域 -->




            <!-- 左侧侧边导航开始 -->
            <div class="layui-side layui-side-bg layui-larry-side" id="larry-side">
              <div class="layui-side-scroll" id="larry-nav-side" lay-filter="side">
                <div class="user-photo">
                  <!-- <a class="img" title="<spring:message code="myavatar" />" >
                    <img src="images/user_head.png" class="" style=" background-color: bisque;"/></a> -->
                  <p style="font-size: 16px;font-weight:700"><strong>光伏电站管理平台
                     <!-- ${current_user_info.userName} -->
                    </strong></p>
                </div>
                <!-- 左侧菜单 -->
                <ul class="layui-nav layui-nav-tree">
                  <!-- <li class="layui-nav-item layui-this">
                    <a href="javascript:;" data-url="main.html">
                        <i class="layui-icon" >&#xe622;</i>
                      <span>后台首页</span>
                    </a>
                  </li> -->
                  <!-- 用户管理 -->

                  <c:forEach items="${menus}" varStatus="i" var="menu">

                    <li class="layui-nav-item">
                      <a href="javascript:;">
                          <i class="layui-icon" >&#xe622;</i>
                          <c:if test="${language == 'zh' }">
                            <span>${menu.menuName}</span>
                          </c:if>
                        <c:if test="${language == 'en' }">
                            <span>${menu.menuNameEn}</span>
                          </c:if>
                          
                        <c:if test="${!empty  menu.childMenuList}">  
                        <em class="layui-nav-more"></em>
                        </c:if>
                      </a>
                      <c:forEach items="${menu.childMenuList}" varStatus="i" var="subMenu">
                        <dl class="layui-nav-child">
                          <dd>
                            <a href="javascript:;" data-url="<%=basePath%>${subMenu.menuUrl}">
                            <i class="layui-icon" >&#xe622;</i>
                              <c:if test="${language == 'zh' }">
                                <span>${subMenu.menuName}</span>
                              </c:if>
                            <c:if test="${language == 'en' }">
                                <span>${subMenu.menuNameEn}</span>
                              </c:if>
                            </a>
                          </dd>
                        </dl>
                      </c:forEach>
                    </li>
                  </c:forEach>
                </ul>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
              </div>
            </div>

            <!-- 左侧侧边导航结束 -->


            <!-- 右侧主体内容 -->
            <div class="layui-body" id="larry-body" style="bottom: 0;border-left: solid 2px #1AA094;">
              <div class="layui-tab layui-tab-card larry-tab-box" id="larry-tab" lay-filter="demo" lay-allowClose="true" style="margin: 0px 0px;">
                <div class="layui-header header header-demo">
                  <div class="layui-main">
                    <!-- logo区域 -->
                    <div class="admin-logo-box">
                      <a class="logo" href="#" title="logo">

                        <!-- <img alt="" src="../images/user.jpg"> -->

                      </a>
                      <div class="larry-side-menu">
                        <i id="iconLeftRight" class="fa fa-angle-double-left" style="font-size:15px" aria-hidden="true"></i>
                      </div>
                    </div>

                    <!-- 顶级菜单区域 -->
                    <!-- <div class="layui-larry-menu">
                                <ul class="layui-nav clearfix">
                                      <li class="layui-nav-item layui-this">
                                        <a href="javascirpt:;"><i class="iconfont icon-wangzhanguanli"></i>内容管理</a>
                                    </li>
                                    <li class="layui-nav-item">
                                        <a href="javascirpt:;"><i class="iconfont icon-weixin3"></i>微信公众</a>
                                    </li>
                                    <li class="layui-nav-item">
                                        <a href="javascirpt:;"><i class="iconfont icon-ht_expand"></i>扩展模块</a>
                                    </li>
                                </ul>
                            </div> -->
                    <!-- 右侧导航 -->
                    <ul class="layui-nav larry-header-item">

                      <li class="layui-nav-item" style="padding-right: 20px">
                        <p>
                          <spring:message code="hello" /> <strong>${current_user_info.userName}</strong> ,
                          <spring:message code="welcomelogin" />
                        </p>
                      </li>

                      <li class="layui-nav-item">
                        <a href="javasrcipt:void()" onclick="openUserSetting()"> <i class="layui-icon"  style="font-size: 18px; ">&#xe614;</i>  <spring:message code="personalsettings" /></a>
                      </li>
                      <li class="layui-nav-item">
                        <a href="javascript:void()" onclick="exit()">
                                <i class="iconfont icon-exit"></i>
                              <spring:message code="signout" />
                            </a>
                      </li>
                    </ul>
                  </div>
                </div>
                <ul class="layui-tab-title">
                  <li class="layui-this" id="admin-home">
                    <!-- <i class="iconfont icon-diannao1"></i> --><em><spring:message code="backgroundhome" /></em></li>
                </ul>
                <div class="layui-tab-content" style="min-height:100px">
                  <div class="layui-tab-item layui-show">
                    <iframe class="larry-iframe" data-id='0' src="index.jsp"></iframe>
                  </div>
                </div>
              </div>
            </div>

            <!-- 底部区域 -->
            <div class="layui-footer " style="text-align: center;line-height: 15px;height: 20px;">
              <p class="">
                <!-- &copy;2017  上海岩芯电子科技有限公司  V1.0.0.0623_alpha -->
                &copy;2017&nbsp;&nbsp;&nbsp;
                <spring:message code="yanxinCompany" />&nbsp;&nbsp;&nbsp;V1.0.0.0623_alpha
              </p>
            </div>
          </div>

          <script type="text/javascript" src="<%=basePathResource%>js/larry.js"></script>
          <script type="text/javascript" src="<%=basePathResource%>js/index.js"></script>
          <!-- 锁屏 -->
          <%-- <div class="lock-screen" style="display: none;">
	<div id="locker" class="lock-wrapper">
		<div id="time"></div>
		<div class="lock-box center">
			<img src="<%=basePath%>images/userimg.jpg" alt="">
            <h1>admin</h1>
            <duv class="form-group col-lg-12">
              <input type="password" placeholder='锁屏状态，请输入密码解锁' id="lock_password" class="form-control lock-input" autofocus name="lock_password">
              <button id="unlock" class="btn btn-lock">解锁</button>
            </duv>
            </div>
            </div>
            </div> --%>
            <!-- 菜单控件 -->
            <!-- <div class="larry-tab-menu">
	<span class="layui-btn larry-test">刷新</span>
</div> -->
            <!-- iframe框架刷新操作 -->
            <!-- <div id="refresh_iframe" class="layui-btn refresh_iframe">刷新</div> -->

            <script type="text/javascript">
              function exit() {
                layer.confirm("<spring:message code="op.sure.exitlogin" />", {
                  icon: 3, title: '<spring:message code="op.systitle" />', btn: ['<spring:message code="op.confirm" /> ', '<spring:message code="op.cancel" />'] //按钮
                }, function (index) {
                  window.location.href = "exit.do";
                }, function () { });
              }

              function openUserSetting() {
                layer.open({
                  type: 2,
                  title: ' <spring:message code="personalsettings" />',
                  shadeClose: false,
                  shade: 0.8,
                  area: ['70%', '80%'],
                  content: "<%=basePath%>user/usersetting.do" //iframe的url
                });
              }
            </script>

        </body>

        </html>