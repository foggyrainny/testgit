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
                                        <td valign="bottom"><h3 style="letter-spacing:1px;">企业信息操作页面</h3></td>
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
                        <!-- 添加企业信息开始 -->
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="96%">
                                <table width="100%">
                                    <tr>
                                        <td colspan="2">
                                            <form action="${ctx }/EntpMain/saveEntpMain" method="post">
                                                <input type="hidden" name="entp_id" value="${entpMain.entp_id} "/>
                                                <table width="100%"class="cont">
                                                	
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td width="15%">企业名称：</td>
                                                        <td width="25%"><input class="text" type="text" name="entp_name" id="entp_name" value="${entpMain.entp_name }" /></td>
                                                        <td>设置企业名称</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>标识简称：</td>
                                                        <td width="20%"><input class="text" type="text" name="entp_sname" id="entp_sname" value="${entpMain.entp_sname}" /></td>
                                                        <td>设置标识简称</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>设置英文名称：</td>
                                                        <td width="20%"><input class="text" style="width:150px;" type="text" name="entp_ename" id="entp_ename" value="${entpMain.entp_ename}" /></td>
                                                        <td>设置英文名称</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                   <%--  <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>企业类型：</td>
                                                      <td>
                                                       	<input type="radio" name="entp_type" ${entpMain.entp_type eq 0 ? "checked='checked'":""}  value="0">个体 &nbsp;
                                                       	<input type="radio" name="entp_type" ${entpMain.entp_type eq 1 ? "checked='checked' ":""} value="1">个人独资&nbsp;
                                                       	<input type="radio" name="entp_type" ${entpMain.entp_type eq 2 ? "checked='checked'":""} value="2">合伙 &nbsp;
                                                       	<input type="radio" name="entp_type" ${entpMain.entp_type eq 3 ? "checked='checked'":""} value="3">公司&nbsp;
                                                       	<input type="radio" name="entp_type" ${entpMain.entp_type eq 4 ? "checked='checked'":""} value="4">	股份制
                                                       	</td>   
                                                        <td>企业类型</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr> --%>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业类型：</td>
                                                        <td>
                                                            <select  name="entp_type" id="entp_type" style="width:150px;">
                                                                <option value="">请选择...</option>
                                                                <option value="0" ${entpMain.entp_type eq 0 ? "selected='selected'":""}>个体</option>
                                                                <option value="1" ${entpMain.entp_type eq 1 ? "selected='selected'":""}>个人独资</option>
                                                                <option value="2" ${entpMain.entp_type eq 2 ? "selected='selected'":""}>合伙</option>
                                                                <option value="3" ${entpMain.entp_type eq 3 ? "selected='selected'":""}>公司</option>
                                                                <option value="4" ${entpMain.entp_type eq 4 ? "selected='selected'":""}>股份制</option>
                                                            </select>
                                                        </td>
                                                        <td>设置企业类型</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                   <%--  <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业性质：</td>
														<td>
                                                       	<input type="radio" name="entp_kind" ${entpMain.entp_kind eq 0 ? "checked='checked'":""} value="0"> 全民所有制企业 &nbsp;
                                                       	<input type="radio" name="entp_kind" ${entpMain.entp_kind eq 1 ? "checked='checked' ":""} value="1"> 集体所有制企业&nbsp;
                                                       	<input type="radio" name="entp_kind" ${entpMain.entp_kind eq 2 ? "checked='checked'":""} value="2">私营企业 &nbsp;
                                                       	<input type="radio" name="entp_kind" ${entpMain.entp_kind eq 3 ? "checked='checked'":""} value="3"> 股份合作企业 &nbsp;
                                                       	<input type="radio" name="entp_kind" ${entpMain.entp_kind eq 4 ? "checked='checked'":""} value="4">多种经济成分联营的企业      
                                                       	</td>                                                        
                                                       	<td>设置企业性质</td>
                                                        <td>&nbsp;</td>
                                                    </tr> --%>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业性质：</td>
                                                        <td>
                                                            <select name="entp_kind" id="entp_kind" style="width:150px;">
                                                                <option value="">请选择...</option>
                                                                <option value="0" ${entpMain.entp_kind eq 0 ? "selected='selected'":""}>全民所有制企业</option>
                                                                <option value="1" ${entpMain.entp_kind eq 1 ? "selected='selected'":""}>集体所有制企业</option>
                                                                <option value="2" ${entpMain.entp_kind eq 2 ? "selected='selected'":""}>私营企业</option>
                                                                <option value="3" ${entpMain.entp_kind eq 3 ? "selected='selected'":""}>股份合作企业</option>
                                                                <option value="4" ${entpMain.entp_kind eq 4 ? "selected='selected'":""}>多种经济成分联营的企业</option>
                                                            </select>
                                                        </td>
                                                        <td>设置企业性质</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>主营产品：</td>
                                                       	<td width="20%"><input class="text" style="width:150px;" type="text" name="main_pd" id="main_pd" value="${entpMain.main_pd}" /></td>
                                                        <td>设置主营产品</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业规模 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="entp_size" id="entp_size" value="${entpMain.entp_size}" /></td>
                                                        <td>设置企业规模</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业执照 ：</td>
                                                        <td width="20%"><input class="text" style="width:150px;" type="text" name="entp_licence" id="entp_licence" value="${entpMain.entp_licence}" /></td>
                                                        <td>设置企业执照</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业法人 ：</td>
                                                        <td><input type="text" name="corporation" id="corporation" value="${entpMain.corporation }"/></td>
                                                        <td>设置企业法人</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业地址 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:300px;" type="text" name="addr" id="addr" value="${entpMain.addr}" /></td>
                                                        <td>设置企业地址</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>邮政编码 ：</td>
                                                        <td>
                                                       	<input type="text" name="postcode"  id="postcode" value="${entpMain.postcode }"/>&nbsp;
                                                       	</td>
                                                        <td>设置邮政编码 </td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业联系人 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="linkman" id="linkman" value="${entpMain.linkman}" /></td>
                                                        <td>设置企业联系人</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>电话 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="tel" id="tel" value="${entpMain.tel}" /></td>
                                                        <td>设置电话</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>传真 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="fax" id="fax" value="${entpMain.fax}" /></td>
                                                        <td>设置传真</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业网址 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="www" id="www" value="${entpMain.www}" /></td>
                                                        <td>设置企业网址</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业邮箱 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="email" id="email" value="${entpMain.email}" /></td>
                                                        <td>设置企业邮箱</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>状态标志 ：</td>
                                                       	 <td><input type="text" id="info_state" name="info_state" value="${entpMain.info_state }"/></td>
                                                        <td>设置状态标志</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>授权标志 ：</td>
                                                       	 <td><input type="text" id="sq_serial" name="sq_serial" value="${entpMain.sq_serial }"/></td>
                                                        <td>设置授权标志</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                             <!--        
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>产品属性：</td>
                                                        <td>
                                                            <select>
                                                                <option selected="true">请选择...</option>
                                                                <option>顶级栏目</option>
                                                                <option>公司动态</option>
                                                                <option>产品展示</option>
                                                                <option>关于我们</option>
                                                            </select>
                                                        </td>
                                                        <td>设置产品属性</td>
                                                        <td>&nbsp;</td>
                                                    </tr>-->
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
		$("#entp_name").attr("datatype","Require").attr("msg","必选项");
		$("#entp_type").attr("datatype","Require").attr("msg","必选项");
		$("#entp_kind").attr("datatype","Require").attr("msg","必选项");
		$("#main_pd").attr("datatype","Require").attr("msg","必选项");
		$("#corporation").attr("datatype","Require").attr("msg","必选项");
		$("#linkman").attr("datatype","Require").attr("msg","必选项");
		$("#tel").attr("datatype","Require").attr("msg","必选项");
		$("#email").attr("datatype","Email").attr("msg","请填写正确的邮箱地址");
		$("#sq_serial").attr("datatype","Require").attr("msg","必选项");
		
		$(document.forms[0]).submit(function(){
			return Validator.Validate(this, 3);
		});
	});
	</script>
</html>