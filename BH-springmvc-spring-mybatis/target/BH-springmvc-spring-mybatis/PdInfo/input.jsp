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
                                            <form action="${ctx }/PdInfo/savePdInfo" method="post">
                                                <input type="hidden" name="pd_id" value="${pdInfo.pd_id} "/>
                                                <table width="100%"class="cont">
                                                	
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td width="15%">UUID编号：</td>
                                                        <td width="25%"><input class="text" type="text" name="uuid" id="uuid" value="${pdInfo.uuid }" /></td>
                                                        <td>设置UUID编号</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>产品名称：</td>
                                                        <td width="20%"><input class="text" type="text" name="pd_name" id="pd_name" value="${pdInfo.pd_name}" /></td>
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
                                                        <td width="20%">
                                                        	<select name="entp_id" id="entp_id" >
                                                        		<option value="">请选择...</option>
                                                        	<c:forEach var="entpMain" items="${entpMainList}" >
                                                        		<option value="${entpMain.entp_id}" ${entpMain.entp_id eq pdInfo.entp_id?"selected='selected'":"" } >${entpMain.entp_id }</option>
                                                        	</c:forEach>
                                                        	
                                                        	</select>
                                                        
                                                        <%-- <input class="text" style="width:150px;" type="text" name="entp_id" id="entp_id" value="${pdInfo.entp_id}" /> --%>
                                                        </td>
                                                        <td>设置所属企业ID编号</td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>产品类别：</td>
                                                        <td>
                                                        	<select name="pd_type" id="pd_type">
                                                        		<option >请选择...</option>
                                                        	<c:forEach var="pdType" items="${pdTypeList}" >
                                                        		<option value="${pdType.pd_type}" ${pdType.pd_type eq pdInfo.pd_type?"selected='selected'":""}>${pdType.pd_type }</option>
                                                        	</c:forEach>
                                                        	
                                                        	</select>
                                                        	
                                                           <%-- <input type="text" name="pd_type" id="pd_type" value="${pdInfo.pd_type}"> --%>
                                                        </td>
                                                        <td>设置产品类别</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                      <tr>
                                                        <td>&nbsp;</td>
                                                        <td>产品型号：</td>
                                                        <td>
                                                        	<select name="model_id" id="model_id">
                                                        		<option>请选择...</option>
                                                        	<c:forEach var="pdModel" items="${pdModelList}">
                                                        		<option value="${pdModel.model_id}" ${pdModel.model_id eq pdInfo.model_id?"selected='selected'":"" }>${pdModel.model_id }</option>
                                                        	</c:forEach>
                                                        	</select>
                                                        	<%-- <input type="text" name="model_id" id="model_id" value="${pdInfo.model_id}"> --%>
                                                        </td>
                                                        <td>设置产品型号</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>主图地址：</td>
                                                       	<td width="20%"><input class="text" style="width:150px;" type="text" name="main_image" id="main_imgae" value="${pdInfo.main_image}" /></td>
                                                        <td>设置主图地址</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>参考价格 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="price_ref" id="price_ref" value="${pdInfo.price_ref}" /></td>
                                                        <td>设置参考价格</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>浏览量 ：</td>
                                                        <td width="20%"><input class="text" style="width:150px;" type="text" name="view_count" id="view_count" value="${pdInfo.view_count}" /></td>
                                                        <td>设置浏览量</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>产品数量 ：</td>
                                                        <td width="20%"><input class="text" style="width:150px;" type="text" name="pd_num" id="pd_num" value="${pdInfo.pd_num}" /></td>
                                                        <td>设置产品数量</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>产品简介 ：</td>
                                                        <td><textarea name="pd_desc" >${pdInfo.pd_desc }</textarea></td>
                                                        <td>设置产品简介</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>排序值 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="order_value" id="order_valueS" value="${pdInfo.order_value}" /></td>
                                                        <td>设置排序值</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>审核状态 ：</td>
                                                        <td>
                                                        <select name="audit_state" id="audit_state">
                                                       		<option value="">请选择...</option>
                                                        	<option value="0" ${pdInfo.audit_state eq 0 ? "selected='selected'":""}>审核未通过</option>
                                                         	<option value="1" ${pdInfo.audit_state eq 1 ? "selected='selected'":""}>审核已通过</option>
                                                        </select>
                                                       	</td>
                                                        <td>设置审核状态 </td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                     <tr>
                                                        <td>&nbsp;</td>
                                                        <td>审核人ID编号 ：</td>
                                                       	 <td width="20%"><input class="text" style="width:150px;" type="text" name="audit_user_id" id="audit_user_id" value="${pdInfo.audit_user_id}" /></td>
                                                        <td>设置审核人ID编号</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>审核时间 ：</td>
                                                        <td>
                                                        <fmt:formatDate  var="cc" pattern="yyyy-MM-dd HH:mm:ss" value="${pdInfo.audit_time}"/> 
                                                       	<input  name="audit_time" id="audit_time" value="${cc}"     style="width:150px"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'new Date()',errDealMode:'0',skin:'default'})" /><br/>
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
		$("#uuid").attr("datatype","Number").attr("datatype","Limit").attr("min","7").attr("max","7").attr("msg","编码只能是7位数字");
		$("#pd_name").attr("datatype","Require").attr("msg","必填项");
		$("#entp_id").attr("datatype","Number").attr("datatype","Limit").attr("min","5").attr("max","5").attr("msg","必须是6位数字 ");
		$("#pd_type").attr("datatype","Require").attr("msg","必选项");
		$("#model_id").attr("datatype","Require").attr("msg","必选项");
 		$("#main_image").attr("datatype","Require").attr("msg","必选项");
 		$("#audit_state").attr("datatype","Require").attr("msg","必选项");
 		$("#pd_num").attr("datatype","Require").attr("msg","必选项");
		
 		$(document.forms[0]).submit(function(){
			return Validator.Validate(this, 3);
		});
	});
	</script>
</html>