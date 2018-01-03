<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${ctx }/static/Style/skin.css" />
    <script type="text/javascript">
        function logout() {
            if(window.confirm('您确定要退出吗？')) {
                top.location = '${ctx}/login.jsp';
            }
        } 
        function logout2() {
            
                top.location = 'index.jsp';
            
        }     
    </script>
</head>
    <body>
        <table cellpadding="0" width="100%" height="64" background="${ctx }/static/Images/top_top_bg.gif">
            <tr valign="top">
                <td width="50%"><a href="javascript:void(0)"><img style="border:none"  /></a></td> 
                <td width="30%" style="padding-top:13px;font:15px Arial,SimSun,sans-serif;color:#FFF">管理员：<b></b> 您好，感谢登陆使用安徽百货后台管理系统！</td>
                <td style="padding-top:10px;" align="center"><a href="javascript:void(0)"><img style="border:none" src="${ctx }/static/Images/index.gif" onclick="logout2();"/></a></td>
                <td style="padding-top:10px;" align="center"><a href="javascript:void(0)"><img style="border:none" src="${ctx }/static/Images/out.gif" onclick="logout();" /></td>
            </tr>
        </table>
    </body>
</html>