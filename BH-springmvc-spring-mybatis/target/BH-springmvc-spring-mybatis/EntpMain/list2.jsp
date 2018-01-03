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
<title>企业详细信息</title>
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
                                        <td valign="bottom"><h3 style="letter-spacing:1px;">企业详细信息页面</h3></td>
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
                                            <form action="${ctx }/EntpMain/listEntpMain" method="post">
                                                <input type="hidden" name="entp_id" value="${entpMain.entp_id} "/>
                                                <table width="100%"class="cont">
                                                	
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td width="15%">企业名称：</td>
                                                        <td width="25%">${entpMain.entp_name }</td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>标识简称：</td>
                                                        <td width="20%">${entpMain.entp_sname}</td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>设置英文名称：</td>
                                                        <td width="20%">${entpMain.entp_ename}</td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业类型：</td>
                                                        <td>
                                                           <c:choose>
											            	 <c:when test="${entpMain.entp_type eq 0 }">个体</c:when>
											            	 <c:when test="${entpMain.entp_type eq 1 }">个人独资</c:when>
											            	 <c:when test="${entpMain.entp_type eq 2 }">合伙</c:when>
											            	 <c:when test="${entpMain.entp_type eq 3 }">公司</c:when>
											            	 <c:when test="${entpMain.entp_type eq 4 }">股份制</c:when>
											             </c:choose>
                                                        </td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业性质：</td>
                                                        <td>
                                                            <c:if test="${entpMain.entp_kind eq 0}">全民所有制企业</c:if>
              												<c:if test="${entpMain.entp_kind eq 1}"> 集体所有制企业</c:if>
              												<c:if test="${entpMain.entp_kind eq 2}">私营企业</c:if>
              												<c:if test="${entpMain.entp_kind eq 3}">股份合作企业   </c:if>
              												<c:if test="${entpMain.entp_kind eq 4}">多种经济成分联营的企业  </c:if>
                                                        </td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>主营产品：</td>
                                                       	<td width="20%">${entpMain.main_pd}</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业规模 ：</td>
                                                       	 <td width="20%">${entpMain.entp_size}</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业执照 ：</td>
                                                        <td width="20%">${entpMain.entp_licence}</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业法人 ：</td>
                                                        <td>${entpMain.corporation }</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业地址 ：</td>
                                                       	 <td width="20%">${entpMain.addr}</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>邮政编码 ：</td>
                                                        <td>
                                                       	${entpMain.postcode }&nbsp;
                                                       	</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业联系人 ：</td>
                                                       	 <td width="20%">${entpMain.linkman}</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>电话 ：</td>
                                                       	 <td width="20%">${entpMain.tel}</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>传真 ：</td>
                                                       	 <td width="20%">${entpMain.fax}</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业网址 ：</td>
                                                       	 <td width="20%">${entpMain.www}</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>企业邮箱 ：</td>
                                                       	 <td width="20%">${entpMain.email}</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>添加时间 ：</td>
                                                        <td>
                                                        <fmt:formatDate   pattern="yyyy-MM-dd HH:mm:ss" value="${entpMain.add_date}"/> 
                                                       	</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>状态标志 ：</td>
                                                       	 <td>${entpMain.info_state }</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>授权标志 ：</td>
                                                       	 <td>${entpMain.sq_serial }</td>
                                                        <td></td>
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
                                                        <th colspan="3"><a href="javascript:history.go(-1);" style="color:#238E68">返回</a></th>
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