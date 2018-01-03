<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 2018/1/3
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test2</title>
</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/demo/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<body>
<div style="padding:3px 2px;border-bottom:1px solid #ccc">Form Validation</div>
<form id="ff" method="post">
    <div>

        <input class="easyui-validatebox" type="text" name="name" required="true"></input>
    </div>
    <div>

        <input class="easyui-validatebox" type="text" name="email" required="true" validType="email"></input>
    </div>
    <div>

        <input class="easyui-validatebox" type="text" name="subject" required="true"></input>
    </div>
    <div>
        <label for="message">Message:</label>
        &lt;textarea name="message" style="height:60px;"&gt;&lt;/textarea&gt;
    </div>
    <div>
        <input type="submit" value="Submit">
    </div>
</form>
<script type="text/javascript" >
    $('#ff').form({
        url:'test.jsp',
        onSubmit:function(){
            return $(this).form('validate');
        },
        success:function(data){
            $.messager.alert('Info', data, 'info');
        }
    });


</script>


</body>
</html>
