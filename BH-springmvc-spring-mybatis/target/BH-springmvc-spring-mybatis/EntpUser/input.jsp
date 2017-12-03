<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>    
<c:set var="ctx" value="${pageContext.request['contextPath']}" />     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品添加</title>
</head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/Style/skin.css" />
	<script type="text/javascript" src="${ctx}/static/calendar/4.8/WdatePicker.js"></script> 
</head>
    <body>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <!-- 头部开始 -->
            <tr>
                <td width="17" valign="top" background="${ctx}/static/Images/mail_left_bg.gif">
                    <img src="${ctx}/static/Images/left_top_right.gif" width="17" height="29" />
                </td>
                <td valign="top" background="${ctx}/static/Images/content_bg.gif">
                    <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" background="${ctx}/static/Images/content_bg.gif">
                        <tr><td height="31"><div class="title">添加栏目</div></td></tr>
                    </table>
                </td>
                <td width="16" valign="top" background="${ctx}/static/Images/mail_right_bg.gif"><img src="${ctx}/static/Images/nav_right_bg.gif" width="16" height="29" /></td>
            </tr>
            <!-- 中间部分开始 -->
            <tr>
                <!--第一行左边框-->
                <td valign="middle" background="${ctx}/static/Images/mail_left_bg.gif">&nbsp;</td>
                <!--第一行中间内容-->
                <td valign="top" bgcolor="#F7F8F9">
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <!-- 空白行-->
                        <tr><td colspan="2" valign="top">&nbsp;</td><td>&nbsp;</td><td valign="top">&nbsp;</td></tr>
                        <tr>
                            <td colspan="4">
                                <table>
                                    <tr>
                                        <td width="100" align="center"><img src="${ctx}/static/Images/mime.gif" /></td>
                                        <td valign="bottom"><h3 style="letter-spacing:1px;">产品修改或添加页面</h3></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <!-- 一条线 -->
                        <tr>
                            <td height="40" colspan="4">
                                <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                    <tr><td></td></tr>
                                </table>
                            </td>
                        </tr>
                        <!-- 添加产品开始 -->
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="96%">
                                <table width="100%">
                                    <tr>
                                        <td colspan="2">
                                            <form action="${ctx }/EntpUser/saveEntpUser" method="post">
                                                <input type="hidden" name="user_id" value="${entpUser.user_id} "/>
                                                <table width="100%"class="cont">
                                                	
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td width="15%">企业用户名：</td>
                                                        <td width="25%"><input class="text" type="text" name="user_name" id="user_name" value="${entpUser.user_name }" /></td>
                                                        <td>设置企业用户名</td>                                                       <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>用户密码：</td>
                                                        <td width="20%"><input class="text" type="text" name="pass_word" id="pass_word" value="${entpUser.pass_word}" /></td>
                                                        <td>设置用户密码</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                 
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>用户类型：</td>
                                                        <td width="20%">
                                                        		<select name="user_type" id="user_type" style="width:150px;">
                                                        			<option value="1" ${entpUser.user_type eq 1?"selected=''selected":""}>高层管理人员</option>
                                                        			<option value="2" ${entpUser.user_type eq 2?"selected=''selected":""}>门店经理</option>
                                                        			<option value="3" ${entpUser.user_type eq 3?"selected=''selected":""}>普通职员</option>
                                                        			<option value="4" ${entpUser.user_type eq 4?"selected=''selected":""}>仓库管理员</option>
                                                        		</select>
                                                        <td>设置用户类型</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>所属企业编号：</td>
                                                      <td>
                                                       		<select name="entp_id" id="entp_id">
                                                       			<option>请选择...</option>
                                                       		<c:forEach var="entpMain" items="${entpMainList}" varStatus="vs">
                                                       			
                                                       			<option value="${entpMain.entp_id}" ${entpMain.entp_id eq entpUser.entp_id? "selected='selected'":""} >
                                                       			
                                                       					${entpMain.entp_id} 
                                                       					
                                                       			</option>
                                                       			
                                                       		</c:forEach>
                                                       		</select>
                                                      	
                                                       	<%-- <input type="text" name="entp_id"  id="entp_id" style="width:150px;" value="${entpUser.entp_id }"> --%>
                                                       	</td>   
                                                        <td>设置所属企业编号</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>真实姓名：</td>
														<td>
                                                       	<input type="text" name="realname" id="realname" style="width:150px;" value="${entpUser.realname}">
                                                       	</td>                                                        
                                                       	<td>设置真实姓名</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>用户状态：</td>
                                                       	<td width="20%">
                                                       	<select name="user_state" id="user_state" style="width:150px;">
                                                       		<option value="0" ${entpUser.user_state eq 0? "selected='selected'":""}>在线</option>
                                                       		<option value="1" ${entpUser.user_state eq 1? "selected='selected'":""}>离线</option>
                                                       	</select>
                                                        <td>设置用户状态</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                   <%--  <tr>
                                                        <td>&nbsp;</td>
                                                        <td>最后登录ip ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="last_login_ip" id="last_login_ip" value="${entpUser.last_login_ip}" /></td>
                                                        <td>设置最后登录ip</td>
                                                        <td>&nbsp;</td>
                                                    
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>最后登录时间 ：</td>
                                                        <td>
                                                        <fmt:formatDate  var="cc" pattern="yyyy-MM-dd HH:mm:ss" value="${entpUser.last_login_time}"/> 
                                                       	<input  name="last_login_time" id="last_login_time" value="${cc}"     style="width:150px"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'new Date()',errDealMode:'0',skin:'default'})" /><br/>
                                                       	</td>
                                                        <td>最后登录时间</td>
                                                        <td>&nbsp;</td>
                                                    </tr> --%>
                                                   
                                             
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td colspan="3"><input class="btn" type="submit" value="提交" /></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                </table>
                                            </form>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td width="2%">&nbsp;</td>
                        </tr>
                        <!-- 添加产品结束 -->
                       <tr>
                            <td height="40" colspan="4">
                                <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                    <tr><td></td></tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="51%" class="left_txt">
                                <img src="${ctx}/static/Images/icon_mail.gif" width="16" height="11"> 客户服务邮箱：rainman@foxmail.com<br />
                                <img src="${ctx}/static/Images/icon_phone.gif" width="17" height="14"> 官方网站：<a href="http://www.mycodes.net/" target="_blank">源码之家</a>
                            </td>
                            <td>&nbsp;</td><td>&nbsp;</td>
                        </tr>
                    </table>
                </td>
                <td background="${ctx}/static/Images/mail_right_bg.gif">&nbsp;</td>
            </tr>
            <!-- 底部部分 -->
            <tr>
                <td valign="bottom" background="${ctx}/static/Images/mail_left_bg.gif">
                    <img src="${ctx}/static/Images/buttom_left.gif" width="17" height="17" />
                </td>
                <td background="${ctx}/static/Images/buttom_bgs.gif">
                    <img src="${ctx}/static/Images/buttom_bgs.gif" width="17" height="17">
                </td>
                <td valign="bottom" background="${ctx}/static/Images/mail_right_bg.gif">
                    <img src="${ctx}/static/Images/buttom_right.gif" width="16" height="17" />
                </td>           
            </tr>
        </table>
    </body>
    <script src="${ctx}/static/jquery/${jquery_version}/jquery.min.js"></script> 
	<script src="${ctx}/static/bootstrap/${bootstrap_version}/js/bootstrap.min.js"></script> 
	<script src="${ctx}/static/jquery.validate/${jquery_validate_version}/jquery.validate.min.js"></script> 
	<script src="${ctx}/static/jquery.validate/${jquery_validate_version}/localization/messages_zh.min.js"></script> 
	<script src="${ctx}/static/jquery.form/${jquery_form_version}/jquery.form.min.js"></script> 
	<script src="${ctx}/static/validator/${validator_version}/validator.js"></script> 
	<script>
	$(function(){
		$("#uuid").attr("msg","必选项");
		$("#pd_name").attr("msg","必选项");
		$("#entp_id").attr("datatype","Number").attr("msg","必须是数字 ");
		
 		$("input[name='pd_type']").attr("datatype","Group").attr("msg","必选项");
 		$("input[name='model_id']").attr("datatype","Group").attr("msg","必选项");
 		$("#main_image").attr("msg","必选项");
 		$("input[name='audit_state']").attr("datatype","Group").attr("msg","必选项");
 		$("#audit_user_id").attr("msg","必选项");
 		/* $("input[name='sex']").attr("datatype","Group").attr("msg","必选项");
		$("input[name='ismarried']").attr("datatype","Group").attr("msg","必选项");	
		$("#mobile").attr("datatype","Mobile").attr("msg","手机号码不正确 "); */
		$(document.forms[0]).submit(function(){
			return Validator.Validate(this, 3);
		});
	});
	</script>
</html>