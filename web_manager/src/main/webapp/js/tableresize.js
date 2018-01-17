(function ($) {
    "use strict";
    var  TableReSize =  function (element, options) {
    	console.log(element);
        var self = this;
        self.$element = $(element);
        self._init(options);
        
    };
    
    TableReSize.prototype = {
        constructor: TableReSize,
        _init: function (options) {
        	 var self = this;
        	self.options = options;
            self.headerTds =  $(self.$element).find("tr:eq(0)").find("th");
            self.mousedown = false;
            self.resizeable = false;
            self.targetTd;
            self.screenXStart =0;
            self.tdWidth = 0;
            self.headerWidth = 0;
            self.isfirst = true;
            for(var i = 0;i<self.headerTds.length;i++){
                $(self.headerTds[i]).on("mousedown",{self:self},self._onmousedown);
                $(self.headerTds[i]).on("mousemove",{self:self},self._onmousemove);
                $(self.headerTds[i]).on("mouseup",{self:self},self._onmouseup);
            }
        },
        _removeColGroup:function(){
        	var self = this;
        	  for(var i = 0;i<self.headerTds.length;i++){
              	$(self.headerTds[i]).width($(self.headerTds[i]).width());
              }
        	  $(self.$element).find("colgroup").remove();
        },
        _onmousedown:function(event){
        	var self = event.data.self;
            if (self.resizeable == true){
                var evt = event||window.event;
                self.mousedown = true;
                self.screenXStart = evt.screenX;
                self.tdWidth = $(self.targetTd).width();
                self.headerWidth = $(self.$element).width();
            }
         }, 
        _onmouseup:function(event){
        	var self = event.data.self;
        	  self.mousedown = false;
        },
        _onmousemove:function(event){
        	var self = event.data.self;
        	
            var evt =event||window.event;
            var srcObj = evt.target || evt.srcElement;
            var offsetX = evt.offsetX || (evt.clientX - srcObj.getBoundingClientRect().left);//这个比较关键，解决了Firefox无offsetX属性的问题
            if (self.mousedown == true){
                $(self.targetTd).width(self.tdWidth + (evt.screenX - self.screenXStart));///计算后的新的宽度
                $(self).width(self.headerWidth + (evt.screenX - self.screenXStart)) ;
            }else{
                var trObj = self.headerTds;
                if(srcObj.offsetWidth - offsetX <=4){//实际改变本单元格列宽
                	self.targetTd=srcObj;
                    self.resizeable = true;
                    console.log("truetruetruetrue");
                    srcObj.style.cursor='col-resize';//修改光标样式
                }else if(offsetX <=4 && srcObj.cellIndex > 0){//实际改变前一单元格列宽，但是表格左边框线不可拖动
                	console.log( srcObj.cellIndex);
                	 if(self.isfirst){
                 		self._removeColGroup();
                 		 self.isfirst = false;
                 	}
                	
                	self.targetTd=  self.headerTds[srcObj.cellIndex-1];
                    self.resizeable = true;
                    srcObj.style.cursor='col-resize';
                }else{
                	console.log("defalut")
                	self.resizeable = false;
                    srcObj.style.cursor='default';
                }
            }
        },
        getTarget:function(evt){
            return evt.target || evt.srcElement;
        }
    };

    $.fn.tableresize = function (option) {
       new TableReSize(this);
       
    };

    $.fn.tableresize.Constructor = TableReSize;
    
    $(document).ready(function (){
    	
    	 var $table = $("table.table-resize");
         if ($table.length) {
        	 $table.tableresize();
         }
    });
         
})(window.jQuery);

