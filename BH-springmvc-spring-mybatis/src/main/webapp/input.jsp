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
                                            <form action="savePdInfo" method="post">
                                                <input type="hidden" name="pd_id" value="${pdInfo.pd_id} "/>
                                                <table width="100%"class="cont">
                                                	
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td width="15%">UUID编号：</td>
                                                        <td width="25%"><input class="text" type="text" name="uuid" value="${pdInfo.uuid }" /></td>
                                                        <td>设置UUID编号</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>产品名称：</td>
                                                        <td width="20%"><input class="text" type="text" name="pd_name" value="${pdInfo.pd_name}" /></td>
                                                        <td>设置产品名称</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                   <!--  <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>产品图片：</td>
                                                        <td width="20%"><input type="file" name="cat_name" /></td>
                                                        <td>上传产品图片</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr> -->
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>所属企业ID编号：</td>
                                                        <td width="20%"><input class="text" style="width:150px;" type="text" name="entp_id" value="${pdInfo.entp_id}" /></td>
                                                        <td>设置所属企业ID编号</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>产品类别：</td>
                                                      <td>
                                                       	<input type="radio" name="pd_type" ${pdInfo.pd_type eq 0 ? "checked='checked'":""}  value="0">优 &nbsp;
                                                       	<input type="radio" name="pd_type" ${pdInfo.pd_type eq 1 ? "checked='checked' ":""} value="1">良&nbsp;
                                                       	<input type="radio" name="pd_type" ${pdInfo.pd_type eq 2 ? "checked='checked'":""} value="2">一般 &nbsp;
                                                       	<input type="radio" name="pd_type" ${pdInfo.pd_type eq 3 ? "checked='checked'":""} value="3">次品&nbsp;
                                                       	<input type="radio" name="pd_type" ${pdInfo.pd_type eq 4 ? "checked='checked'":""} value="4">不合格
                                                       	</td>   
                                                        <td>填写产品类别</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>产品型号：</td>
														<td>
                                                       	<input type="radio" name="model_id" ${pdInfo.model_id eq 0 ? "checked='checked'":""} value="0">A型 &nbsp;
                                                       	<input type="radio" name="model_id" ${pdInfo.model_id eq 1 ? "checked='checked' ":""} value="1">B型&nbsp;
                                                       	<input type="radio" name="model_id" ${pdInfo.model_id eq 2 ? "checked='checked'":""} value="2">C型 &nbsp;
                                                       	<input type="radio" name="model_id" ${pdInfo.model_id eq 3 ? "checked='checked'":""} value="3">D型&nbsp;
                                                       	<input type="radio" name="model_id" ${pdInfo.model_id eq 4 ? "checked='checked'":""} value="4">E型
                                                       	</td>                                                        
                                                       	<td>设置产品型号</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>主图地址：</td>
                                                       	<td width="20%"><input class="text" style="width:300px;" type="text" name="main_image" value="${pdInfo.main_image}" /></td>
                                                        <td>设置主图地址</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>参考价格 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="price_ref" value="${pdInfo.price_ref}" /></td>
                                                        <td>设置参考价格</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                   <tr>
                                                        <td>&nbsp;</td>
                                                        <td>产品数量 ：</td>
                                                        <td width="20%"><input class="text" style="width:150px;" type="text" name="pd_num" value="${pdInfo.pd_num}" /></td>
                                                        <td>设置产品数量</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>浏览量 ：</td>
                                                        <td width="20%"><input class="text" style="width:150px;" type="text" name="view_count" value="${pdInfo.view_count}" /></td>
                                                        <td>设置浏览量</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>产品简介 ：</td>
                                                        <td><textarea name="pd_desc">${pdInfo.pd_desc }</textarea></td>
                                                        <td>设置产品简介</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>排序值 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="order_value" value="${pdInfo.order_value}" /></td>
                                                        <td>设置排序值</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <%--  <tr>
                                                        <td>&nbsp;</td>
                                                        <td>是否删除 ：</td>
                                                       	<td>
                                                       	<input type="radio" name="is_del" ${pdInfo.is_del eq 0 ? "checked='checked'":""} value="0">未删除 &nbsp;
                                                       	<input type="radio" name="is_del" ${pdInfo.is_del eq 1 ? "checked='checked'":""} value="1">已删除
                                                       	</td>
                                                        <td>设置是否删除</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>删除时间 ：</td>
                                                        <td>
                                                        <fmt:formatDate  var="bb" pattern="yyyy-MM-dd HH:mm:ss" value="${pdInfo.del_time}"/> 
                                                        
                                                        <input  name="del_time" id="" value="${bb}"  style="width:150px"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'new Date()',errDealMode:'0',skin:'default'})" /><br/>
                                                       
                                                        </td>
                                                        <td>设置删除时间</td>
                                                        <td>&nbsp;</td>
                                                    </tr> --%>
                                                    <%--  <tr>
                                                        <td>&nbsp;</td>
                                                        <td>删除人ID ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="del_user_id" value="${pdInfo.del_user_id}" /></td>
                                                        <td>设置删除人ID编号</td>
                                                        <td>&nbsp;</td>
                                                    </tr> --%>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>审核状态 ：</td>
                                                        <td>
                                                       	<input type="radio" name="audit_state" ${pdInfo.audit_state eq 0 ? "checked='checked'":""} value="0">审核未通过 &nbsp;
                                                       	<input type="radio" name="audit_state" ${pdInfo.audit_state eq 1 ? "checked='checked'":""} value="1">审核已通过
                                                       	</td>
                                                        <td>设置审核状态 </td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>审核人ID编号 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="audit_user_id" value="${pdInfo.audit_user_id}" /></td>
                                                        <td>设置审核人ID编号</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>审核时间 ：</td>
                                                        <td>
                                                                                           <fmt:formatDate  var="cc" pattern="yyyy-MM-dd HH:mm:ss" value="${pdInfo.audit_time}"/> 
                                                       	<input  name="audit_time" id="" value="${cc}"     style="width:150px"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'new Date()',errDealMode:'0',skin:'default'})" /><br/>
                                                       	</td>
                                                        <td>设置审核时间</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>审核说明 ：</td>
                                                       	 <td><textarea name="audit_desc">${pdInfo.audit_desc }</textarea></td>
                                                        <td>设置审核说明</td>
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
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>产品分类：</td>
                                                        <td>
                                                            <select>
                                                                <option selected="true">请选择...</option>
                                                                <option>顶级栏目</option>
                                                                <option>公司动态</option>
                                                                <option>产品展示</option>
                                                                <option>关于我们</option>
                                                            </select>
                                                        </td>
                                                        <td>设置产品分类</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>浏览器标题(title)：</td>
                                                        <td><input class="text" style="width:200px;" type="text" name="bro_name" value="" /></td>
                                                        <td>浏览器标题(Title)，有利于SEO</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>关键字(Keywords)：</td>
                                                        <td><textarea></textarea></td>
                                                        <td>设置关键字，便于查询</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>描述(Description)：</td>
                                                        <td><textarea></textarea></td>
                                                        <td>产品简短描述</td>
                                                        <td>&nbsp;</td>
                                                    </tr> -->
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
</html>