<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <style type="text/css">
        .hides {
            display: none
        }
    </style>
</head>
<body>

<link rel="stylesheet" type="text/css"
      href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5.2/demo/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>

<div class="demo-info">
    <div class="demo-tip icon-tip"></div>
    <div>如何在验证框上显示另外一种提示信息.</div>
</div>
<div style="margin:10px 0;"></div>

<div class="easyui-panel" title="注册" style="width:750px;padding:20px">

    <table>
        <form id="ff" action="test.jsp" method="post">
            <tr>
                <td align="right">用户名：</td>
                <td>
                    <input type="text" class="easyui-validatebox" required="true" validType="name"
                           placeholder="用户名设置成功后不可修改" name="name" id="uName" maxlength="30">
                    <span style="color: #FF7F00">6至30位字母数字或“_”,字母开头</span>
                </td>
            </tr>
            <tr>
                <td align="right"> 密码：</td>
                <td><input type="password" class="easyui-validatebox"
                           data-options="required:true,validType:'account[6,20]'"
                           name="password" id="uPassword" placeholder="6-20位字母、数字或符号" maxlength="20"
                           onchange="colorChange(this)">
                    <span id="s1"><svg width="50" height="10"> <rect id="red" width="50" height="10"
                                                                     style="fill:red;stroke-width:1;stroke:white"/>
                  </svg>
                  <svg width="50" height="10"> <rect id="yellow" width="50" height="10" fill="grey" stroke-width="1"
                                                     stroke="white"/>
                  </svg>
                      <svg width="50" height="10"> <rect id="green" width="50" height="10"
                                                         style="fill:grey;stroke-width:1;stroke:white"/>
                  </svg>
                  </span>
                </td>
            </tr>
            <tr>
                <td align="right"> 确认密码：</td>
                <td><input type="password" class="easyui-validatebox" name="repassword" id="uRepassword"
                           placeholder="请再次输入您的登录密码" maxlength="20"
                           required="true" validType="equalTo['#uPassword']">
                </td>
            </tr>
            <tr>
                <td align="right"> 姓名：</td>
                <td><input type="text" class="easyui-validatebox" data-options="required:true,validType:'length[3,30]'"
                           name="realname" id="urealName" placeholder="请输入您的真实姓名">
                    <span><a style="color: #FF7F00;text-decoration: underline;" <%--shape="rect"--%> id="tips"
                             class="easyui-tooltip" <%--onmouseover="show1()"--%>>姓名填写规则</a></span>
                </td>
            </tr>
            <tr>
                <td align="right"> 证件类型：</td>
                <td><select name="idCardType" id="uIdCardType" onchange="cardType()" style="width: 153px"
                            class="easyui-validatebox">
                    <option value="1">二代身份证</option>
                    <option value="2">港澳通行证</option>
                    <option value="3">台湾通行证</option>
                    <option value="4">护照</option>
                </select></td>
            </tr>
            <tr>
                <td align="right">证件号码：</td>
                <td><input type="text" name="idCard" id="uIdCard" placeholder="请输入您的证件号"
                           class="easyui-validatebox easyui-tooltip" data-options="required:true,validType:'idcard'">
                </td>
            </tr>
            <tr id="tSex" style="display: none" data-options="required:true">
                <td align="right"> 性别：</td>
                <td><input class="easyui-validatebox " type="radio" name="sex" value="1">男
                    <input class="easyui-validatebox" type="radio" name="sex" value="2">女
                </td>
            </tr>
            <tr id="tCountry" style="display: none">
                <td align="right">国家/地区:</td>
                <td><select name="country" id="uCountry" style="width: 153px" class="easyui-validatebox"
                            data-options="required:true">
                    <option value="1">中国</option>
                    <option value="2">美国</option>
                    <option value="3">英国</option>
                    <option value="4">日本</option>
                    <option value="5">韩国</option>
                </select></td>
            </tr>
            <tr id="tBirthday" style="display: none">
                <td align="right">生日：</td>
                <td><input type="text" name="birthday" id="uBirthday" class="easyui-datebox"
                           data-options="required:true" style="width: 153px">
                </td>
            </tr>
            <tr>
                <td align="right"> 邮箱：</td>
                <td><input type="email" name="email" id="uEmail" placeholder="请填写您的邮箱地址" class="easyui-validatebox"
                           data-options="required:true,validType:'email'">
                </td>
            </tr>
            <tr>
                <td align="right"> 手机号码：</td>
                <td><input type="text" name="mobile" id="uMobile" placeholder="请输入您的手机号码" class="easyui-validatebox"
                           data-options="required:true,validType:'uTest'">
                    <span style="color: darkorange;font-size: 15px">请输入正确的手机号码，稍后我们会发送验证码到您的手机上</span>
                </td>
            </tr>
            <tr>
                <td align="right"> 旅客类型：</td>
                <td><select name="customerType" id="uCustomerType" style="width: 153px" class="easy-validatebox"
                            data-options="required:true" onchange="customerType_f(this)">
                    <option value="1">成人</option>
                    <option value="2">儿童</option>
                    <option value="3">学生</option>
                    <option value="4">伤残军人，伤残人民警察</option>
                </select></td>
            </tr>
            <tr id="tProvince" class="hides">
                <td align="right">学校所在省份：</td>
                <td><select id="uProvince" style="width: 153px" class="easyui-validatebox" data-options="required:true">
                    <option value="1">北京</option>
                    <option value="2">上海</option>
                    <option value="3">广东</option>
                    <option value="4">深圳</option>
                    <option value="5">山东</option>
                </select></td>
            </tr>
            <tr id="tSchool" class="hides">
                <td align="right">学校名称：</td>
                <td><input type="text" id="uSchool" name="school" width="153px" class="easyui-validatebox"
                           data-options="required:true"></td>
            </tr>
            <tr id="tCollege" class="hides">
                <td align="right">院系：</td>
                <td><input type="text" id="uCollege" name="college"></td>
            </tr>
            <tr id="tClass" class="hides">
                <td align="right">班级：</td>
                <td><input type="text" id="uClass" name="class"></td>
            </tr>
            <tr id="tNo" class="hides">
                <td align="right">学号：</td>
                <td><input type="text" id="uNo" name="no" class="easyui-validatebox" data-options="required:true"></td>
            </tr>
            <tr id="tSYear" class="hides">
                <td align="right">学制：</td>
                <td><select id="uSYear" name="SYear" style="width: 153px" class="easyui-validatebox"
                            data-options="required:true">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                </select></td>
            </tr>
            <tr id="tSyear1" class="hides">
                <td align="right">入学年份：</td>
                <td><select id="uSyears" name="syears" style="width: 153px" class="easyui-validatebox"
                            data-options="required:true">
                    <option value="1">2017</option>
                    <option value="2">2016</option>
                    <option value="3">2015</option>
                    <option value="4">2014</option>
                    <option value="5">2013</option>
                    <option value="6">2012</option>
                    <option value="7">2011</option>
                    <option value="8">2010</option>
                    <option value="9">2009</option>
                    <option value="10">2008</option>
                </select></td>
            </tr>
            <tr id="pCard" class="hides">
                <td align="right">优惠卡号：</td>
                <td><input type="text" id="uPcard" name="pcard" class="easyui-validatebox"></td>
            </tr>
            <tr id="pSection" class="hides">
                <td align="right">优惠区间：</td>
                <td><input type="text" id="uPsection1" name="psection1" style="width: 70px" class="easyui-validatebox"
                           data-options="required:true">-
                    <input type="text" id="uPsection2" name="psection2" style="width: 70px" class="easyui-validatebox"
                           data-options="required:true">
                </td>
            </tr>
            <tr>
                <td colspan="16" align="center">
                    <input type="checkbox" value="1" class="easyui-validatebox" data-options="required:true">我已阅读并同意遵守
                    《中国铁路客户服务中心网站服务条款》
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="提交" class="easyui-linkbutton" onclick="submitForm()"></td>
            </tr>
        </form>
    </table>
</div>
</body>

<script type="text/javascript">

    function cardType() {
        var idcardType = document.getElementById("uIdCardType");
        var idcard = document.getElementById("uIdCard");
        var sex = document.getElementsByName("sex");
        var birthday = document.getElementById("uBirthday");
        var country = document.getElementById("uCountry");


        var tSex = document.getElementById("tSex");
        var tBirthday = document.getElementById("tBirthday");
        var tCountry = document.getElementById("tCountry");

        for (var i = 0; i < idcardType.options.length; i++) {

            if (idcardType.options[i].selected) {

                if (idcardType.options[i].value == 1) {
                    tBirthday.style.display = "none";
                    tCountry.style.display = "none";
                    tSex.style.display = "none";
                    country.value = "1";

                    if (/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)/) {
                        birthday.value = idcard.substr(7, 14);

                        var a = idcard.charAt(16);
                        if (a % 2 == 0) {
                            sex.value = "1";
                        } else {
                            sex.value = "2";
                        }
                    }
                }

                if (idcardType.options[i].value == 2 || idcardType.options[i].value == 3) {

                    tCountry.style.display = "none";
                    tBirthday.style.display = "table-row";
                    tSex.style.display = "";
                    country.value = "1";
                }
                if (idcardType.options[i].value == 4) {
                    tCountry.style.display = "";
                    tBirthday.style.display = "";
                    tSex.style.display = "";
                }

            }
        }
    }

    $(function () {
        /*  $("#uPassword").validatebox();
          $("#uName").validatebox();
          $("#uRepassword").validatebox();
          $("#uIdCard").validatebox();
          $("#uMobile").validatebox();*/

        $('#ff input').each(function () {
            if ($(this).attr('required') || $(this).attr('validType'))
                $(this).validatebox();
        })

        $("#tips").tooltip({
            position: "bottom",
            content: "<span style='color: darkorange'>" +
            "1.确认姓名中有生僻字无法输入时，可用生僻字拼音或者同音字替代。<br>" +
            "2.输入姓名保存后，偶遇无法正常显示的汉字，可用该汉字的拼音或者同音字重新修改后保存。<br>" +
            "3.姓名中有繁体字无法输入时，可用简体字替代。<br>" +
            "4.姓名较长，汉字和英文字符合计超过30个字符（一个汉字算两个字符）的，需按姓名中第一<br>" +
            "个汉字或英文字符开始，按顺序连续输入连续输入30个字符（空格不输入），其中英文输入时<br>" +
            "不区分大小写。<br>" +
            "5.姓名中有\".\"或者\"▪\"时，请准确识别证件原件上的\".\"或者\"▪\"，准确输入。</span>",


        });
        /*$("#uIdCard").tooltip({
            position:"top",
            content:"<span style='color: darkorange '>芜湖大司马</span>",
            showEvent:"onfocus"
            });*/

    })

    $.extend($.fn.validatebox.defaults.rules, {
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
        },
        name: {
            validator: function (value) {

                return /^[a-zA-Z][a-zA-Z0-9_]{5,29}$/.test(value);
            }, message: "6-30位字母数字或“_”,字母开头"
        },
        equalTo: {//验证是否正确确认密码
            validator: function (value, param) {
                return $(param[0]).val() === value;

            }, message: "您两次输入的密码不一致"

        },
        idcard: {
            validator: function (value) {
                var idcardType = document.getElementById("uIdCardType");
                var idcard = document.getElementById("uIdCard");
                for (var i = 0; i < idcardType.length; i++) {
                    if (idcardType.options[i].selected) {
                        if (idcardType.options[i].value == 1) {//判断不是正确的身份证号码
                            if (!/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/.test(value)) {
                                $.fn.validatebox.defaults.rules.idcard.message = "请正确输入18位的身份证号码";
                                return false;
                            } else {
                                return true;
                            }

                        } else if (idcardType.options[i].value == 2) {//判断不是正确的港澳通行证
                            if (!/^[HMhm]{1}([0-9]{10}|[0-9]{8})$/.test(value)) {
                                $.fn.validatebox.defaults.rules.idcard.message = "请输入正确的港澳通行证号码";
                                return false;
                            } else {
                                return true;
                            }

                        } else if (idcardType.options[i].value == 3) {//判断不是正确的台湾通行证
                            if ((!/^[0-9]{8}$/.test(value)) && (!/^[0-9]{10}$/.test(value))) {

                                $.fn.validatebox.defaults.rules.idcard.message = "请输入正确的台湾通行证号码";
                                return false;
                            } else {
                                return true;
                            }

                        } else if (idcardType.options[i].value == 4) {//判断不是正确的护照

                            if ((!/^[a-zA-Z]{5,17}$/.test(value)) && (!/^[a-zA-Z0-9]{5,17}$/.test(value))) {
                                $.fn.validatebox.defaults.rules.idcard.message = "请输入正确的护照号码";
                                return false;

                            } else {
                                return true;
                            }
                        }

                    }

                }

            }, message: ""
        },
        uTest: {

            validator: function (value) {
                if (!/^1(3|5|8|7)\d{9}$/.test(value)) {
                    $.fn.validatebox.defaults.rules.uTest.message = "请输入正确的手机号码";
                    return false;

                } else {
                    return true;
                }
            }, message: ""

        }


    })

    function colorChange(password) {
        if (!/^(?![0-9]+$)(?![A-Za-z]+$)(?!_+$)[a-zA-Z0-9_]{6,20}$/.test(password.value)) {

            $("#red").css("fill", "red");
            $("#yellow").css("fill", "grey");
            $("#green").css("fill", "grey");
        } else if (/^(?=.*\d+.*)(?=.*[A-Za-z]+.*)(?=.*_.*)[0-9A-Za-z_]{6,20}$/.test(password.value)) {

            $("#red").css("fill", "red");
            $("#yellow").css("fill", "yellow");
            $("#green").css("fill", "green");
        } else {

            $("#red").css("fill", "red");
            $("#yellow").css("fill", "yellow");
            $("#green").css("fill", "grey");
        }

    }

    /* function show1() {

          $.messager.show({
              title:"rules",//消息框标题
              msg:"1.确认姓名中有生僻字无法输入时，可用生僻字拼音或者同音字替代。<br>" +
              "<p>2.输入姓名保存后，偶遇无法正常显示的汉字，可用该汉字的拼音或者同音字重新修改后保存。</p>" +
              "3.姓名中有繁体字无法输入时，可用简体字替代。<br>" +
              "<p>4.姓名较长，汉字和英文字符合计超过30个字符（一个汉字算两个字符）的，需按姓名中第一个汉字或英文字符开始，按顺序连续输入连续输入30个字符（空格不输入），其中英文输入时不区分大小写。</p>" +
              "5.姓名中有\".\"或者\"▪\"时，请准确识别证件原件上的\".\"或者\"▪\"，准确输入。",//消息框内容
              timeout:"10000",//自动关闭时间，单位毫秒
              showType:"show",//展示类型，还有淡出，
              style:"right=''," +
              "buttom=''",//定位消息框的位置,这里是中间
              width:"350",
              height:"280",
              showSpeed:"10"//展示速度，单位毫秒

          });
     }*/


    function customerType_f(ip) {

        var hidden = document.getElementsByClassName("hides");
        for (var i = 0; i < ip.options.length; i++) {
            if (ip.options[i].selected) {
                /*  for(var j=0;j<hidden.length;j++){
                     if(ip.options[i].value==3){

                         hidden[j].className="hidden2";//改变ClassName会出现BUG,改变时不能显示全部的相同的className的样式
                     } else {
                         hidden[j].className="hides3";
                     }

                  }}*/
                if (ip.options[i].value == 3) {

                    for (var j = 0; j < hidden.length; j++) {
                        hidden[j].style.display = "table-row";

                    }
                } else {
                    for (var j = 0; j < hidden.length; j++) {
                        hidden[j].style.display = "none";
                        hidden[j].valid = 0;
                    }
                }
            }

        }

    }


    $("#ff").form('submit', {
        url: "test.jsp",
        onSubmit: function () {

            return $(this).form('enableValidation').form('validate');

        }
        /*success:function(data){

         /!*  if (data > 0) {
                $.messager.show({
                    title: '提示消息',
                    msg: '提交成功',
                    showType: 'show',
                    timeout: 1000,
                    style: {
                        right: '',
                        bottom: ''
                    }
                });
                 // 重新载入当前页面数据
                //关闭窗口
            }
            else {*!/
                $.messager.alert('提示信息', data, 'warning');
            /!* }*!/
        }*/
    });


</script>
</html>
