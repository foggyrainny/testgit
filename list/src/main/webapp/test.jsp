<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
  <link rel="stylesheet" type="text/css"
      href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/demo/demo.css">
  <script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/jquery.min.js"></script>    
  <script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>    
  <script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/my.js"></script>    
  <script type="text/javascript"
        src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>

<div class="demo-info">
    <div class="demo-tip icon-tip"></div>
    <div>如何在验证框上显示另外一种提示信息.</div>
</div>
<div style="margin:10px 0;"></div>
 <form id="ff"  method="post">
                  
              证件类型：  <select name="idCardType" id="uIdCardType" onchange="cardType()" style="width: 153px"
                            class="easyui-validatebox">
                    <option value="1">二代身份证</option>
                    <option value="2">港澳通行证</option>
                    <option value="3">台湾通行证</option>
                    <option value="4">护照</option>
                </select><br/>
        <input type="text" class="easyui-validatebox" required="true" validType="idcard"
            placeholder="请输入用户名"       name="name" id="uName" ><br/>
        <input type="submit" value="提交" onclick="submitForm()"/>
 </form>

<script type="text/javascript">
   
   function submitForm() {

    $('#ff').form({
        url:'index.jsp',
        onSubmit:function(){

            return $(this).form('validate');
        },
        success:function(data){
            $.messager.alert('Info', data, 'info');
        }
    });
}
</script>

</body>
</html>