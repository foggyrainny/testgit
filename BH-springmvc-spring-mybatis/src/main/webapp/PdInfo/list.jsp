<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>    
<c:set var="ctx" value="${pageContext.request['contextPath']}" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品表</title>
</head>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/Style/skin.css" />
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
                                        <td valign="bottom"><h3 style="letter-spacing:1px;">安徽百货公司产品信息表</h3></td>
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
                        <!-- 产品列表开始 -->
                        <tr>
                            <td width="0.1%">&nbsp;</td>
                            <td width="200%">
                                <table width="190%">
                                    <tr>
                                        <td colspan="2">
                                        <!--查询表单  -->
                                            <form action="${ctx }/PdInfo/listPdInfo" method="post">
                                         <input type="button" onclick="location.href='addPdInfo'" value="添加" />    
                                      &nbsp; &nbsp; 产品名：<input type="text" name="pd_name"  value="${pdInfo.pd_name }" />
									  &nbsp; &nbsp;审核状态 ： <input type="text" name="audit_state"  value="${pdInfo.audit_state }" />
									  &nbsp; &nbsp; 参考价格： <input type="text" name="price_ref"  value="${pdInfo.price_ref }" />
                                      &nbsp; &nbsp;<input type="submit" value="搜索"/>
                                      &nbsp; &nbsp;<input type="reset" value="重置"/>
                                              </form>
                                             
                                                <table width="100%"   class="cont tr_color">
                                                    <tr>
                                                    	<th>选中</th>
                                                        <th>序号</th>
                                                        <th>UUID</th>
                                                        <th>产品名称</th>
                                                        <th>所属企业ID</th>
                                                        <th>产品类别</th>
                                                        <th>产品型号</th>
                                                        <th>主图地址</th>
                                                        <th>参考价格</th>
                                                        <th>产品数量</th>
                                                        <th>浏览量</th>
                                                        <th>排序值</th>
                                                        <th>审核状态</th>
                                                        <th>审核人ID</th>
                                                        <th>审核时间</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    <tbody>
          <c:forEach var="pdInfo" items="${pdInfoList}" varStatus="vs">
          <tr>
            <td align="center">
            <input type="checkbox" class="icheck" name="ids" id="id-${pdInfo.pd_id}" value="${pdInfo.pd_id}" >
            </td>
            <td align="center">${vs.count}</td>
            <td align="center">${pdInfo.uuid}</td>
            <td align="center">${pdInfo.pd_name}</td>
            <td align="center">
            <a href="${ctx }/EntpMain/list2EntpMain?entp_id=${pdInfo.entp_id}" style="color:#238E68">${pdInfo.entp_id}</a>
            </td>
            <td align="center">
             <a href="${ctx}/PdType/list2PdType?pd_type=${pdInfo.pd_type}" style="color:#238E68">${pdInfo.pd_type}</a>
            
           </td> 
            <td align="center">
            <a href="${ctx}/PdModel/list2PdModel?model_id=${pdInfo.model_id}" style="color:#238E68">${pdInfo.model_id}</a>
            
            </td>
			<td align="center">${pdInfo.main_image}</td> 
			<td align="center">${pdInfo.price_ref}</td>
            <td align="center">${pdInfo.pd_num}</td>
            <td align="center">${pdInfo.view_count}</td>
			<td align="center">${pdInfo.order_value}</td> 
            <td align="center">${pdInfo.audit_state eq 1 ?"审核通过":"审核不通过"}
            </td>
			<td align="center">${pdInfo.audit_user_id}</td> 
			<td align="center">
				<fmt:formatDate pattern="yyyy-MM-dd " value="${pdInfo.audit_time}" /></td>
			<td align="center">
				<input type="button"  onclick="location.href='deletePdInfo?pd_id=${pdInfo.pd_id}'" value="删除"/>
				<input type="button"  onclick="location.href='updatePdInfo?pd_id=${pdInfo.pd_id}'" value="修改"/>
			</td>         
          </tr>
      </c:forEach>
      </tbody>
      </table>                                 
   		</td>
                        <td width="2%">&nbsp;</td>
                        </tr>
                        <!-- 产品列表结束 -->
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
    
    <!--分页表单开始 --> 
    <form action="listPdInfo" method="post">
	<tr>
		<td colspan="10" align="center">
			共${pager.recordCount}条记录    每页显示${pager.pageSize}条    分${pager.pageCount}页/第${pager.currentPage}页
		   	 <a style="cursor:pointer;text-decoration: underline;" href="listPdInfo?requestPage=${pager.firstPage}">首页</a>
		   	 
		   	 <c:choose>
		   	 	<c:when test="${pager.currentPage le 1}">
		   	 		<span style="color:grey">上一页</span></c:when>
		   	 	<c:otherwise>
		   	 		<a style="cursor:pointer;text-decoration: underline;" href="listPdInfo?requestPage=${pager.priviousPage}">上一页</a>
		   	 	</c:otherwise>
		   	 </c:choose>
		   	 
		   	 <c:choose>
		   	 	<c:when test="${pager.currentPage ge pager.pageCount}">
		   	 		<span style="color:grey">下一页</span>
		   	 	</c:when>
		   	 	<c:otherwise>
		   	 		<a style="cursor:pointer;text-decoration: underline;" href="listPdInfo?requestPage=${pager.nextPage}">下一页</a>
		   	 	</c:otherwise>
		   	 </c:choose>
		   	 
		   	 <a style="cursor:pointer;text-decoration: underline;" href="listPdInfo?requestPage=${pager.lastPage}">尾页</a>
		   	 <input style="text-align:center;border: 1px solid #CCCCCC;" type="text" name="requestPage" onchange="this.value=(new RegExp('^[0-9]*$').test(this.value)) ? this.value : 1" value="${pager.requestPage}" size="2" id="user_requestPage"/>
		   	 <input type="submit" value="转到"  id="sb"/>
		</td>
	</tr>
</form>	
</table>
</body>
</html>
