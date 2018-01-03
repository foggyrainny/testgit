<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>    
<c:set var="ctx" value="${pageContext.request['contextPath']}" />      
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="${ctx }/static/Js/prototype.lite.js" type="text/javascript"></script>
    <script src="${ctx }/static/Js/moo.fx.js" type="text/javascript"></script>
    <script src="${ctx }/static/Js/moo.fx.pack.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/Style/skin.css" />
    <script type="text/javascript">
        window.onload = function () {
            var contents = document.getElementsByClassName('content');
            var toggles = document.getElementsByClassName('type');

            var myAccordion = new fx.Accordion(
            toggles, contents, {opacity: true, duration: 400}
            );
            myAccordion.showThisHideOpen(contents[0]);
            for(var i=0; i<document .getElementsByTagName("a").length; i++){
                var dl_a = document.getElementsByTagName("a")[i];
                    dl_a.onfocus=function(){
                        this.blur();
                    }
            }
        }
    </script>
</head>

<body>
    <table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
        <tr>
            <td width="182" valign="top">
                <div id="container">
                    <h1 class="type"><a href="javascript:void(0)">销售企业管理</a></h1>
                     <div class="content">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><img src="${ctx}/static/Images/menu_top_line.gif" width="182" height="5" /></td>
                            </tr>
                        </table>
                        <ul class="RM">
                            <li><a href="${ctx}/EntpMain/listEntpMain" target="main">企业信息管理</a></li>
                            <li><a href="${ctx}/EntpUser/listEntpUser" target="main">企业用户管理</a></li>
                        </ul>
                    </div> 
                    <h1 class="type"><a href="javascript:void(0)" target="main">产品管理</a></h1>
                     <div class="content">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><img src="${ctx}/static/Images/menu_top_line.gif" width="182" height="5" /></td>
                            </tr>
                        </table>
                        <ul class="RM">
                            <li><a href="${ctx}/PdInfo/listPdInfo" target="main">产品信息</a></li>
                            <li><a href="${ctx}/PdModel/listPdModel" target="main">产品型号</a></li>
                            <li><a href="${ctx}/PdType/listPdType" target="main">产品类别</a></li>
                        </ul>
                    </div> 
                  <%--   <h1 class="type"><a href="${ctx}/PdInfo/listPdInfo" target="main">产品信息表</a></h1> --%>
                   <!--  <div class="content">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><img src="./Images/menu_top_line.gif" width="182" height="5" /></td>
                            </tr>
                        </table>
                        <ul class="RM">
                            <li><a href="./order_1.html" target="main">待处理订单</a></li>
                            <li><a href="./order_2.html" target="main">处理中订单</a></li>
                            <li><a href="./order_3.html" target="main">已发货订单</a></li>
                            <li><a href="./order_4.html" target="main">已完成订单</a></li>
                        </ul>
                    </div> -->
                    <!-- *********** -->
                    
                   <h1 class="type"><a href="${ctx}/SysOperLog/listSysOperLog" target="main">系统操作日志</a></h1> 
                   <!--  <div class="content">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><img src="./Images/menu_top_line.gif" width="182" height="5" /></td>
                            </tr>
                        </table>
                        <ul class="RM"> 
                            <li><a href="./mem_reg.html" target="main">注册设置</a></li>
                            <li><a href="./mem_chk.html" target="main">审核设置</a></li>
                            <li><a href="./mem_add.html" target="main">添加会员</a></li>
                            <li><a href="./mem_list.html" target="main">会员管理</a></li>
                        </ul>
                    </div>
                    <h1 class="type"><a href="javascript:void(0)">系统设置</a></h1>
                    <div class="content">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><img src="./Images/menu_top_line.gif" width="182" height="5" /></td>
                            </tr>
                        </table>
                        <ul class="RM">
                            <li><a href="./sys.html" target="main">网站设置</a></li>
                            <li><a href="./admin.html" target="main">管理员设置</a></li>
                            <li><a href="javascript:void(0)" target="main">模板设置</a></li>
                        </ul>
                    </div>
                   
                    <h1 class="type"><a href="javascript:void(0)">其它设置</a></h1>
                    <div class="content">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><img src="${ctx }/static/Images/menu_top_line.gif" width="182" height="5" /></td>
                            </tr>
                        </table>
                        <ul class="RM">
                            <li><a href="javascript:void(0)" target="main">友情连接</a></li>
                            <li><a href="javascript:void(0)" target="main">在线留言</a></li>
                            <li><a href="javascript:void(0)" target="main">网站投票</a></li>
                            <li><a href="javascript:void(0)" target="main">邮箱设置</a></li>
                            <li><a href="javascript:void(0)" target="main">图片上传</a></li>
                        </ul>
                    </div> 
                    <!-- *********** -->
                </div>
            </td>
        </tr>
    </table>
</body>
</html>
