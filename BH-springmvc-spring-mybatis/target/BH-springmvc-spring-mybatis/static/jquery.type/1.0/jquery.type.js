/**
 * TypeSelect jQuery Version
 * JSON style : {'t1':'v1','t2':'v2'}
 * @author raky [redlight168@gmail.com]
 * @see http://yuxisanren.iteye.com/blog/1895807
 * @version 1.0
 * 
 * @author redlight168@gmail.com
 */

function fillSelectList(jsonString,selectObject,selectValue){
	var str = "";
	jsonSting = eval("("+jsonString+")");
	selectValue = selectValue.replace("true","1").replace("false","0");
	$.each(jsonSting,function(k,v){ 
		if(k == selectValue){			
			str = "<option value='" + k + "' selected>"+ v +"</option>";
		}else{
			str = "<option value='" + k + "'>"+ v +"</option>";
		}
		selectObject.append(str);
	});  
}

function pullTypValueByKey(jsonString,classString){
	$(classString).each(function() {
		var key = $(this).text().replace("true","1").replace("false","0");
		var jsonObject = eval("("+jsonString+")");		
		$(this).html(jsonObject[''+key+'']);
	});	
}