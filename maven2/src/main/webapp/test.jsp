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
       <form id="ff" method="post">

       <input type="text" class="easyui-validatebox" required="true" validType="email"
       placeholder="用户名设置成功后不可修改" name="name" id="uName" maxlength="30"><br>
<%--
           <input type="password" class="easyui-validatebox"
                  data-options="required:true,validType:'account[6,20]'"
                  name="password" id="uPassword" placeholder="6-20位字母、数字或符号" maxlength="20"
                  ><br>--%>
       <input type="button" onclick="submitForm()" class="easyui-linkbutton" value="提交" >

       </form>
<script type="text/javascript" >

   /* $.extend($.fn.validatebox.defaults.rules, {
        TI:{
            validator:function (value) {

                return /^[a-zA-Z][a-zA-Z0-9_]{5,29}$/.test(value);
            },message:"6-30位字母数字或“_”,字母开头"
        },
        account: {
            validator: function (value, param) {
                if (!/^(?![0-9]+$)(?![A-Za-z]+$)(?!\_+$)[a-zA-Z0-9_]+$/.test(value)) {
                    $.fn.validatebox.defaults.rules.account.message = "格式错误，密码必须是字母，数字，下划线中的两种及两种以上";

                    return false;
                } else {
                    if (value.length > param[1] || value.length < param[0]) {
                        $.fn.validatebox.defaults.rules.account.message = "密码长度必须在" + param[0] + "至" + param[1] + "范围";

                        return false;

                    } else {

                        return true;

                    }
                }
            }, message: ""
        }
    })*/



    /*   $('#ff input').each(function () {

            if ($(this).attr('required') || $(this).attr('validType'))
                $(this).validatebox();
        });*/



        $("#ff").form('submit', {
           url: 'register.jsp',
            onSubmit: function () {
               var  b = $(this).form('validate');

                if (b) {

                    return b;
                }else {

                    return b;
                }
                return b;
            }
        })


</script>
</body>
</html>
