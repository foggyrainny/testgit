/**
 * CascadeSelect jQuery Version
 * JSON style : [[t1,v1],[t2,v2]]
 * @author Jin,QingHua [jinqinghua@gmail.com]
 * @version 1.0
 * 
 * @author jinqinghua@gmail.com
 */
jQuery.fn.extend({
        /**
         * @param url                   the url for ajax request
         * @param reqParamName  the request parameter name
         * @param reqParamValue the request parameter value
         * @param isHideNoOptionSelect  is hide no option select, default is true
         */
        cs : function(url, reqParamName, reqParamValue, isHideNoOptionSelect) {
                if (null == isHideNoOptionSelect) isHideNoOptionSelect = true;
                if (null == url || $.trim(url).length == 0) {
                        alert("error! parameters[0] can not be empty!");
                        return;
                }
                if (null == reqParamName || $.trim(reqParamName).length == 0) {
                        return;
                }               
                if (null == reqParamValue || $.trim(reqParamValue).length == 0) {
                        return;
                }
                
                var attrSubElement = this.attr("subElement");
                var $subElement = $("#" + attrSubElement);
                this.change(function(){
                        hideAllSubElements($subElement);
                        if (null != attrSubElement) {
                                $subElement.cs(url, reqParamName, this.options[this.selectedIndex].value);      
                        }       
                });

                this[0].options.length = 0;
                var defaultText = this.attr("defaultText");
                var defaultValue = this.attr("defaultValue");
                var selectedValue = this.attr("selectedValue");
                if (defaultText != null && defaultValue != null) {
                        this[0].options[this[0].options.length] = new Option(defaultText, defaultValue);
                }
                $.ajaxSetup({async:false});
                var tvs; //text and value array
                var data = {}; data[reqParamName] = reqParamValue;
                $.getJSON(url, data, function(json){
                        tvs = json;
                });
                if (null == tvs || tvs.length == 0) {
                        return;
                }
                var selected = false;
		        for (var i = 0; i < tvs.length; i++) {
		            this[0].options[this[0].options.length] = new Option(tvs[i][0], tvs[i][1]);
		            if (selectedValue != null && selectedValue == tvs[i][1]) {
		                this[0].selectedIndex = this[0].options.length - 1;
		                selected = true;
		            }
		        } 
		        
		        if (this[0].options.length > 0) {
		        	$('#region\\.id').val(this[0].options[this[0].selectedIndex].value);
		            this.show();
		        }
		        
		        if (selected && null != attrSubElement) {
		                $subElement.cs(url, reqParamName, this[0].options[this[0].selectedIndex].value, isHideNoOptionSelect);
		        }
		        
		        function hideAllSubElements($select) {
		                if (this.multiple || !isHideNoOptionSelect) return;
		                $select.hide();
		                var attrSubElement= $select.attr("subElement");
		                if (null != attrSubElement){
		                        hideAllSubElements($("#" + attrSubElement));
		                }
		        }
        }
});