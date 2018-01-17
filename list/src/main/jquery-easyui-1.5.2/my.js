(function($){
$.extend($.fn.validatebox.defaults.rules,{
    account:{
        validator:function (value,param) {
            if(!/^(?![0-9]+$)(?![A-Za-z]+$)(?!\_+$)[a-zA-Z0-9_]+$/.test(value)){
                $.fn.validatebox.defaults.rules.account.message="格式错误，密码必须是字母，数字，下划线中的两种及两种以上";
                return false;
            }else {
                if(value.length>param[1]||value.length<param[0]){
                    $.fn.validatebox.defaults.rules.account.message="密码长度必须在"+param[0]+"至"+param[1]+"个字符范围";
                    return false;

                }else {
                    return true;

                }
            }
        },message:""
    },
    name:{
        validator:function (value) {

            return /^[a-zA-Z][a-zA-Z0-9_]{5,29}$/.test(value);
        },message:"6-30位字母数字或“_”,字母开头"
    },
    equalTo:{//验证是否正确确认密码
        validator:function (value,param) {
            return $(param[0]).val()===value;

        },message:"您两次输入的密码不一致"

    },
    idcard:{
        validator:function (value) {
            var idcardType = document.getElementById("uIdCardType");
            var idcard =document.getElementById("uIdCard");
            for(var i=0;i<idcardType.length;i++){
                if(idcardType.options[i].selected){
                    if(idcardType.options[i].value==1){//判断不是正确的身份证号码
                        if(!/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/.test(value)){
                            $.fn.validatebox.defaults.rules.idcard.message="请正确输入18位的身份证号码";
                            return false;
                        }else{
                            return true;
                        }

                    }else if(idcardType.options[i].value==2){//判断不是正确的港澳通行证
                        if(!/^[HMhm]{1}([0-9]{10}|[0-9]{8})$/.test(value)){
                            $.fn.validatebox.defaults.rules.idcard.message="请输入正确的港澳通行证号码";
                            return false;
                        }else {
                            return true;
                        }

                    }else if(idcardType.options[i].value==3){//判断不是正确的台湾通行证
                        if((!/^[0-9]{8}$/.test(value))&&(!/^[0-9]{10}$/.test(value ))){

                            $.fn.validatebox.defaults.rules.idcard.message="请输入正确的台湾通行证号码";
                            return false;
                        }else {
                            return true;
                        }

                    }else if(idcardType.options[i].value==4){//判断不是正确的护照

                        if((!/^[a-zA-Z]{5,17}$/.test(value))&&(!/^[a-zA-Z0-9]{5,17}$/.test(value ))){
                            $.fn.validatebox.defaults.rules.idcard.message="请输入正确的护照号码";
                            return false;

                        }else{
                            return true;
                        }
                    }

                }

            }

        },message:""
    },
    uTest:{

        validator:function (value) {
            if(!/^1(3|5|8|7)\d{9}$/.test(value)){
                $.fn.validatebox.defaults.rules.uTest.message="请输入正确的手机号码";
                return false;

            }else {
                return true;
            }
        },message:""

    }
});
})(jQuery);
