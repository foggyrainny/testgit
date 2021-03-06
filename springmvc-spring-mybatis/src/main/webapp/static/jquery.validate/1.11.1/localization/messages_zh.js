/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
jQuery.extend(jQuery.validator.messages, {
        required: "必选字段",
		remote: "请修正该字段 (数据重复).",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
		minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
		rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
		min: jQuery.validator.format("请输入一个最小为 {0} 的值")
});


////jQuery.extend(jQuery.validator.defaults, {
//$.validator.setDefaults({
//    highlight: function(element) {
//        $(element).closest('.form-group').addClass('has-error');
//    },
//    unhighlight: function(element) {
//        $(element).closest('.form-group').removeClass('has-error');
//    },
//    //errorElement: 'span',
//    //errorClass: 'help-block',
//    errorPlacement: function(error, element) {
//        if(element.parent('.form-group').length) {
//            error.insertAfter(element.parent());
//        } else {
//            error.insertAfter(element);
//        }
//    }
//});   

$.validator.setDefaults({
    showErrors : function(errorMap, errorList) {
        $.each(this.successList, function(index, value) {
        	//console.log(value);
        	$(value).closest('.form-group').removeClass('has-error');
            return $(value).popover("destroy");//in there, I changed 'hide' to 'destroy'
        });
        return $.each(errorList, function(index, value) {
        	//console.log(value);
        	$(value.element).closest('.form-group').addClass('has-error');
             
            var _popover;
            _popover = $(value.element).popover({
                trigger : "manual",
                container : "body",//please change it in your page
                content : value.message,
            });
            return $(value.element).popover("show");
        });
    }
});
