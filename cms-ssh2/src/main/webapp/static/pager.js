function Pager(form, recordCount, pageSize, currentPage, lang) {
    this.form = form;
    this.recordCount = recordCount;
    this.pageSize = pageSize;
    this.currentPage = currentPage;
    this.lang = lang || "cn";
    this._hiddenInputNameAndValues = [];
}
/**
 * to buile a html hidden input element
 * @param name element name and id
 * @param value element value
 */
Pager.prototype.addHiddenInputs = function (name, value) {
	this._hiddenInputNameAndValues[this._hiddenInputNameAndValues.length] = new Array(name, value);
};

/**
 * go to a specific page
 * @param the page
 */
Pager.prototype.gotoPage = function(page) {
	this.form.requestPage.value = page;
	this.form.submit();
};

/**
 * pager string
 */
Pager.prototype.toString = function (){
	var pager = this;
	var pagerStrings = [];
	var pageCount = Math.ceil(this.recordCount / this.pageSize);

	var previousPage = this.currentPage - 1;
	var firstPage = 1;
	var nextPage = this.currentPage + 1;
	var lastPage = pageCount;
	
	switch (this.lang) {
		case "cn":
			pagerStrings[pagerStrings.length] = '\u5171<span style="color:#FF0000"> ' + this.recordCount + ' </span>\u6761\u8bb0\u5f55 ';
			pagerStrings[pagerStrings.length] = '\u6bcf\u9875\u663e\u793a<span style="color:#FF0000"> ' + this.pageSize + ' </span>\u6761 ';
			pagerStrings[pagerStrings.length] = '\u5206<span style="color:#FF0000"> ' + pageCount + ' </span>\u9875 ';
			if (this.currentPage == 1 || pageCount <= 1) {
				pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> \u9996\u9875 \u4e0a\u4e00\u9875 </span>';
			} else {
				pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(1);">\u9996\u9875</span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + previousPage + ');">\u4e0a\u4e00\u9875</span> ';
			}
			if (this.currentPage == pageCount || pageCount <= 1) {
				pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> \u4e0b\u4e00\u9875 \u5c3e\u9875</span>';
			} else {
				pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + nextPage + ');">\u4e0b\u4e00\u9875</span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + lastPage + ');">\u5c3e\u9875</span> ';
			}
			pagerStrings[pagerStrings.length] = ' \u7b2c <input style="text-align:center;border: 1px solid #CCCCCC;" name="pager.requestPage" type="text" id="requestPage" size="4" maxlength="6" value="' + this.currentPage + '" onchange="this.value = isInteger(this.value) ? this.value : 1" /> \u9875 ';
			pagerStrings[pagerStrings.length] = '<input name="buttonsubmitPage" class="bgButton" type="submit" id="buttonsubmitPage" value="\u8f6c\u5230" onclick="this.form.requestPage.value = isInteger(this.form.requestPage.value) ? this.form.requestPage.value : 1" />';
			break;
		default:
			pagerStrings[pagerStrings.length] = 'Total <span style="color:#FF0000"> ' + this.recordCount + ' </span> ';
			pagerStrings[pagerStrings.length] = 'Page <span style="color:#FF0000"> ' + pageCount + ' </span> of ';
			pagerStrings[pagerStrings.length] = '<span style="color:#FF0000"> ' + this.pageSize + ' </span> ';
			if (this.currentPage == 1 || pageCount <= 1) {
				pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> [First Page] [Previous Page] </span>';
			} else {
				pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(1);"> [First Page] </span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + previousPage + ');"> [Previous Page] </span> ';
			}
			if (this.currentPage == pageCount || pageCount <= 1) {
				pagerStrings[pagerStrings.length] = '<span style="color:#CCCCCC;"> [Next Page] [Last Page] </span>';
			} else {
				pagerStrings[pagerStrings.length] = '<span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + nextPage + ');"> [Next Page] </span> <span style="cursor:pointer;text-decoration: underline;" onclick="pager.gotoPage(' + lastPage + ');"> [Last Page] </span> ';
			}
			pagerStrings[pagerStrings.length] = ' <input style="text-align:center;border: 1px solid #CCCCCC;" name="pager.requestPage" type="text" id="requestPage" size="4" maxlength="6" value="' + this.currentPage + '" onchange="this.value = isInteger(this.value) ? this.value : 1" /> ';
			pagerStrings[pagerStrings.length] = '<input name="buttonsubmitPage" class="bgButton" type="submit" id="buttonsubmitPage" value="go" onclick="this.form.requestPage.value = isInteger(this.form.requestPage.value) ? this.form.requestPage.value : 1" />';
			break;
	}
	var _hiddenInputStrings = [];
	for (var i = 0; i < this._hiddenInputNameAndValues.length; i++){
		_hiddenInputStrings[_hiddenInputStrings.length] = '<input name="' + this._hiddenInputNameAndValues[i][0] + '" type="hidden" id="' + this._hiddenInputNameAndValues[i][0] + '" value="' + this._hiddenInputNameAndValues[i][1] + '" />';
	}
	pagerStrings[pagerStrings.length] = _hiddenInputStrings.join("").toString();
	return pagerStrings.join("").toString();
};

function isInteger(value) {
	return /^[-\+]?\d+$/.test(value);
}

//-->