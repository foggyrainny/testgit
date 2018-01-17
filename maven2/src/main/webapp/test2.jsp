<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>test2</title>
</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/demo/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/my.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<body>
<div style="padding:3px 2px;border-bottom:1px solid #ccc">Form Validation</div>
<form id="ff" method="post">
    <input id="ss" class="easyui-searchbox" style="width:300px"
           data-options="searcher:qq,prompt:'请输入搜索内容',menu:'#mm'"/><br>
    <div>

        <input class="easyui-validatebox" type="text" name="name" required="true" onchange="vChange(this)"/>
    </div>
    <div>

        <input id="ee" class="easyui-validatebox" type="text" name="email" <%--required="true"--%> <%--validType="email"--%>/>
    </div>
    <div>

        <input class="easyui-validatebox" type="text" name="subject" required="true"/>
    </div>


    <div id="mm" style="width:120px">
        <div data-options="name:'all',iconCls:'icon-ok'">所有新闻</div>
        <div data-options="name:'sports'">体育新闻</div>
    </div>

        <input type="submit" value="Submit" onclick="submitForm()">
    </div>
</form>
<script type="text/javascript" >

    function qq(value,name){
        alert(value+":"+name)
    }
   /* $.extend($.fn.validatebox.defaults.rules, {
        TI:{
            validator:function (value) {

                return /^[a-zA-Z][a-zA-Z0-9_]{5,29}$/.test(value);
            },message:"6-30位字母数字或“_”,字母开头"
        }
    }*/
   function submitForm() {

       $('#ff').form({
           url:'test.jsp',
           onSubmit:function(){

               return $(this).form('validate');
           },
           success:function(data){
               $.messager.alert('Info', data, 'info');
           }
       });

   }

   function vChange(value) {

       if(value.value=="zd1234"){

           $("#ee").validatebox({required:true,validType:'email'});
       }else {
          /* $("#ee").validatebox("recover");*/
           $("#ee").validatebox({required:false,validType:null});
       }

   }

</script>


</body>
</html>
