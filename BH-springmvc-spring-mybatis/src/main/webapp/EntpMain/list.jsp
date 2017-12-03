<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>    
<c:set var="ctx" value="${pageContext.request['contextPath']}" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品销售企业信息表</title>
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
                                        <td valign="bottom"><h3 style="letter-spacing:1px;">产品销售企业信息表</h3></td>
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
                                            <form action="${ctx }/EntpMain/listEntpMain" method="post">
                                         <input type="button" onclick="location.href='addEntpMain'" value="添加" />    
                                      &nbsp; &nbsp; 企业名：<input type="text" name="entp_name"  value="${entpMain.entp_name }" />
									  &nbsp; &nbsp;主营产品 ： <input type="text" name="main_pd"  value="${entpMain.main_pd }" />
									  &nbsp; &nbsp; 联系人： <input type="text" name="linkman"  value="${entpMain.linkman }" />
									   &nbsp; &nbsp; 法人代表： <input type="text" name="corporation"  value="${entpMain.corporation }" />
                                      &nbsp; &nbsp;<input type="submit" value="搜索"/>
                                      &nbsp; &nbsp;<input type="reset" value="重置"/>
                                              </form>
                                             
                                                <table width="100%"   class="cont tr_color">
                                                    <tr>
                                                    	<th width="5%" style="text-align: center"><input
                        type="checkbox" class="icheck" name="check-all" id="check-all" value="${EntpMain.entp_id}"/></th>
                                                        <th>企业编号</th>
                                                        <th>企业名称</th>
                                                        <th>标识简称</th>
                                                        <th>企业类型</th>
                                                        <th>主营产品</th>
                                                        <th>企业性质</th>
                                                        <th>经营规模</th>
                                                        <th>法人代表</th>
                                                        <th>联系人</th>
                                                        <th>电话</th>
                                                        <th>授权标志</th>
                                                        <th>添加时间</th>
                                                        <th>操作</th>
                                                    </tr>
          <tbody>
          <c:forEach var="entpMain" items="${entpMainList}" varStatus="vs">
          <tr>
            <td align="center">
            <input type="checkbox" class="icheck" name="ids" id="id-${entpMain.entp_id}" value="${entpMain.entp_id}" >
            </td>
            
            <td align="center"><a href="${ctx }/EntpMain/list2EntpMain?entp_id=${entpMain.entp_id}" style="color:#238E68">${entpMain.entp_id}</a></td>
            <td align="center">${entpMain.entp_name}</td>
            <td align="center">${entpMain.entp_sname}</td>
            <td align="center">
               <c:choose>
            	 <c:when test="${entpMain.entp_type eq 0 }">个体</c:when>
            	 <c:when test="${entpMain.entp_type eq 1 }">个人独资</c:when>
            	 <c:when test="${entpMain.entp_type eq 2 }">合伙</c:when>
            	 <c:when test="${entpMain.entp_type eq 3 }">公司</c:when>
            	 <c:when test="${entpMain.entp_type eq 4 }">股份制</c:when>
             </c:choose>   
            </td> 
  				
            <td align="center">${entpMain.main_pd}</td> 
            <td align="center">
              <c:if test="${entpMain.entp_kind eq 0}">全民所有制企业</c:if>
              <c:if test="${entpMain.entp_kind eq 1}"> 集体所有制企业</c:if>
              <c:if test="${entpMain.entp_kind eq 2}">私营企业</c:if>
              <c:if test="${entpMain.entp_kind eq 3}">股份合作企业   </c:if>
              <c:if test="${entpMain.entp_kind eq 4}">多种经济成分联营的企业  </c:if>
            </td> 
			<td align="center">${entpMain.entp_size}</td> 
            <td align="center">${entpMain.corporation}</td>
            <td align="center">${entpMain.linkman}</td>
            <td align="center">${entpMain.tel}</td>
            <td align="center">${entpMain.sq_serial } </td>
			<td align="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entpMain.add_date}" /></td>
			<td align="center">
				<input type="button"  onclick="location.href='deleteEntpMain?entp_id=${entpMain.entp_id}'" value="删除"/>
				<input type="button"  onclick="location.href='updateEntpMain?entp_id=${entpMain.entp_id}'" value="修改"/>
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
       
        
        
    <form action="listEntpMain" method="post">
	<tr>
		<td colspan="10" align="center">
			共${pager.recordCount}条记录    每页显示${pager.pageSize}条    分${pager.pageCount}页/第${pager.currentPage}页
		   	 <a style="cursor:pointer;text-decoration: underline;" href="listEntpMain?requestPage=${pager.firstPage}">首页</a>
		   	 
		   	 <c:choose>
		   	 	<c:when test="${pager.currentPage le 1}">
		   	 		<span style="color:grey">上一页</span>
		   	 	</c:when>
		   	 	<c:otherwise>
		   	 		<a style="cursor:pointer;text-decoration: underline;" href="listEntpMain?requestPage=${pager.priviousPage}">上一页</a>
		   	 	</c:otherwise>
		   	 </c:choose>
		   	 
		   	 <c:choose>
		   	 	<c:when test="${pager.currentPage ge pager.pageCount}">
		   	 		<span style="color:grey">下一页</span>
		   	 	</c:when>
		   	 	<c:otherwise>
		   	 		<a style="cursor:pointer;text-decoration: underline;" href="listEntpMain?requestPage=${pager.nextPage}">下一页</a>
		   	 	</c:otherwise>
		   	 </c:choose>
		   	 
		   	 <a style="cursor:pointer;text-decoration: underline;" href="listEntpMain?requestPage=${pager.lastPage}">尾页</a>
		   	 <input style="text-align:center;border: 1px solid #CCCCCC;" type="text" name="requestPage" onchange="this.value=(new RegExp('^[0-9]*$').test(this.value)) ? this.value : 1" value="${pager.requestPage}" size="2" id="user_requestPage"/>
		   	 <input type="submit" value="转到"  id="sb"/>
		</td>
	</tr>
	
</form>	
</table>
    </body>
    
    <script type="text/javascript">
    
    </script>
</html>
