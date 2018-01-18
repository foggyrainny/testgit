/**
 * 
 */
(function($){
$.extend($.fn.validatebox.defaults.rules,{
	name:{
        validator:function (value) {

            return /^[a-zA-Z][a-zA-Z0-9_]{5,29}$/.test(value);
        },message:"6-30位字母数字或“_”,字母开头"
    },
    
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