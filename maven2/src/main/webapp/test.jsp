<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 2018/1/2
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/demo/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<body>
       <form id="ff"  action="register.jsp">

       <input type="text" class="easyui-validatebox" required="true" validType="TI"
       placeholder="用户名设置成功后不可修改" name="name" id="uName" maxlength="30"><br>
       <input type="submit" class="easyui-linkbutton" value="提交" >
       </form>
<script type="text/javascript" >
   /* $(function(){
        $('#ff input').each(function () {
            if ($(this).attr('required') || $(this).attr('validType'))
                $(this).validatebox();
        });

    })*/
    $.extend($.fn.validatebox.defaults.rules, {
        TI:{
            validator:function (value) {

                return /^[a-zA-Z][a-zA-Z0-9_]{5,29}$/.test(value);
            },message:"6-30位字母数字或“_”,字母开头"
        },
    })



    /*    $('#ff input').each(function () {
            alert("cs")
            if ($(this).attr('required') || $(this).attr('validType'))
                $(this).validatebox();
        });*/


        $("#ff").form( {
           url: "<%=request.getContextPath()%>/register.jsp",
            onSubmit: function () {
               var  b = $(this).form('validate');

                if (b) {
                    alert(b)
                    return b;
                }else {
                    alert("非"+b)
                    return b;
                }

                return b;

            }
        })


</script>
</body>
</html>
