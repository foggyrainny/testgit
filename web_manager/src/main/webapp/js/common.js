
/**
 * 日期格式化
 */
Date.prototype.format = function (format) {
  var o = {
    "M+": this.getMonth() + 1, // month
    "d+": this.getDate(), // day
    "h+": this.getHours(), // hour
    "m+": this.getMinutes(), // minute
    "s+": this.getSeconds(), // second
    "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
    "S": this.getMilliseconds()
    // millisecond
  }
  if (/(y+)/.test(format))
    format = format.replace(RegExp.$1, (this.getFullYear() + "")
      .substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(format))
      format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
        : ("00" + o[k]).substr(("" + o[k]).length));
  return format;
}

/**
 * 表单ajax提交并校验功能
 */
function validSubmitFormAnfCallable(formId, callable) {
  $('#' + formId).Validform({
    tiptype: 2,
    ajaxPost: true,
    beforeSubmit: function (form) {
      var index = layer.load(0, {
        shade: false
      });
      var options = {
        success: function (e) {
          layer.close(index);
          if (typeof callable == "function") {
            callable(e);
          }
          layer.msg(e.msg);
          if (e.code == 200) {
            callable(e);
          }
        },
        error: function (e) {
          layer.close(index);
          layer.msg('操作失败');
        }
      };
      $(form[0]).ajaxSubmit(options);
      return false;
    }
  });
}

// 验证并提交指定Form
function validSubmitForm(formId) {
  $('#' + formId).Validform({
    tiptype: 2,
    ajaxPost: true,
    beforeSubmit: function (form) {
      var index = layer.load(0, {
        shade: false
      });
      var options = {
        success: function (e) {
          layer.close(index);
          layer.msg(e.msg);
        },
        error: function (e) {
          layer.close(index);
          layer.msg('操作失败');
        }
      };
      $(form[0]).ajaxSubmit(options);
      return false;
    }
  });
}

function ajaxSubmitFormAndValid(formId, callable, tipsType, showAllError) {

  if (showAllError == undefined || showAllError == null || showAllError == '') {
    showAllError = false;
  }
  var layIndex;
  // $.Tipmsg.r=null;
  return $("#" + formId).Validform({
    ajaxPost: true,
    tiptype: tipsType,
    showAllError: showAllError,
    beforeSubmit: function (form) {
      var inputs = $(form).find('input[name]');

      for (var i = 0; i < inputs.length; i++) {
        $(inputs[i]).val($(inputs[i]).val().trim());
        // 登录密码加密处理
        //console.log($(inputs[i]).attr("name"));
        if ($(inputs[i]).attr("name") == "login_pwd") {
          $(form).find('input[name=enPwd]').val(
            $.md5($(inputs[i]).val()));
          $(inputs[i]).val("");
        }
      }
      var textareas = $(form).find('textarea[name]');
      for (var i = 0; i < textareas.length; i++) {
        $(textareas[i]).val($(textareas[i]).val().trim());
      }
      layIndex = layer.load(0, {
        shade: false
      });
      return true;
    },
    callback: function (data) {
      layer.close(layIndex);
      callable(data);
      if (data.code != undefined && data.code != null && data.code != 200) {
        layer.msg(data.msg);
      }
    }
  });
}

/**
 * 分页相关函数
 * 
 */
// 分页处理
function commonPager(options) {
  var defaults = {
    datacont: 'dataContent',
    cont: 'pager',
    type: 'POST',
    url: '',
    data: {},
    timeout: 30000,
    total: 0,
    size: 10,
    mask: true
  };
  var op = $.extend({}, defaults, options);
  var total = parseInt(op.total); // 总记录数
  var size = parseInt(op.size); // 每页显示数
  var pages = Math.floor(total / size);
  if (total % size != 0) {
    pages++;
  }
  layui.laypage({
    cont: op.cont, // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div
    lang: _LANG,	// id="page1"></div>
    pages: pages, // 总页数
    curr: op.curr, // 初始化当前页
    skip: true, // 是否开启跳页
    skin: '#242424',
    total: total,
    groups: 5,// 连续显示分页数
    jump: function (e, first) { // 触发分页后的回调
      if (first)
        return;

      var queryParam = $.extend({}, {
        curr: e.curr,
        size: op.size
      }, op.data);
      queryParam.curr = e.curr
      var ajaxOptions = {
        type: op.type,
        dataType: 'json',
        url: op.url,
        data: queryParam,
        error: op.error || function (e) {
          layer.msg('请求错误');
        },
        success: op.success || function (e) {
          $("#" + op.datacont).html(e);
        }
      };
      // console.log(ajaxOptions);
      commmonAjax(ajaxOptions);
    }
  });
}

// 通用的ajax方法
function commmonAjax(options) {
  var defaults = {
    type: 'POST',
    cache: false,
    dataType: 'json',
    async: true,
    timeout: 60000
  };
  var op = $.extend({}, defaults, options);
  var index = layer.load(0, {
    shade: false
  });
  $.ajax({
    type: op.type,
    url: op.url,
    async: op.async,
    data: op.data || {},
    cache: op.cache,
    dataType: op.dataType,
    timeout: op.timeout,
    error: op.error || function (e) {
      layer.close(index);
      if (typeof op.error == 'function') {
        op.error(e);
      }
    },
    success: function (e) {
      layer.close(index);
      if (typeof op.success == 'function') {
         try {
    	  op.success(e);
         } catch(e){}
      }
      if (e.code == 998) {
        // console.log(top.location.href);
        top.location.href = _SIZE_BASE_PATH + 'login.do';
        return;
      }
      if (e.code != undefined && e.code != 200 && e.code != "200") {
        // console.log(e);
        layer.msg(e.msg);
      }
    }
  });
}

/**
 * 自动生成 table中数据的格式， 数组的字段值取自于 th 的data-field 字段 th的 formatter 可格式化成需要的样式
 */
function autoGenerateTableHtml(tableSelect, list) {

  var table = $(tableSelect);
  var ths = $(table).find("th[data-field]");
  var html = "";
  for (var i = 0; i < list.length; i++) {
    html += "<tr>";
    for (var j = 0; j < ths.length; j++) {
      var th = ths[j];
      var field = $(th).attr("data-field");
      var formatter = $(th).attr("formatter");
      var title = $(th).attr("data-title");
      var textAlignCenter = $(th).attr("data-text-align-center");
      var value = "";
      if (formatter != undefined && formatter != null) {
        // value, row, index
        value = callFunction(formatter, [list[i][field], list[i], i]);
      } else {
        value = list[i][field] == undefined ? "" : list[i][field];
      }

      /*表单数据内容溢出隐藏变为省略号，class属性在resource.jsp 中设置
       if (title != undefined) {
        html += "<td  class='omit' title='" + value + "'>";
      } else if (textAlignCenter != undefined) {
        html += "<td style='text-align:center;'>";
      } else {
        html += "<td>";
      }*/
      
      //此处修改为所有表单数据自动换行，class属性在resource.jsp 中设置
      if (title != undefined) {
    	  html += "<td  class='line-feed' title='" + value + "'>";
      } else if (textAlignCenter != undefined) {
    	  html += "<td class='line-feed' style='text-align:center;'>";
      } else {
    	  html += "<td class='line-feed'>";
      }

      html += value + "</td>";
    }
    html += "</tr>";
  }
  return html;
}

function autoFillData(tableSelect, data) {

  var doms = $(tableSelect);
  var value = "";
  for (var i = 0; i < doms.length; i++) {
    var dom = doms[i];
    var field = $(dom).attr("data-field");
    if (field == undefined || field == null || field == "") {
      continue;
    }
    var formatter = $(dom).attr("formatter");
    if (formatter != undefined && formatter != null) {
      value = callFunction(formatter, [data[field], data, i]);
    } else {
      value = data[field] == undefined ? "" : data[field];
    }
    if (dom.nodeName == "INPUT" || dom.nodeName == "SELECT") {
      $(dom).val(value);
    } else {
      $(dom).html(value);
    }

  }
}

function callFunction(functionName, value) {
  // 创建函数对象，并调用
  var fun = eval(functionName);
  return fun.call(this, value[0], value[1], value[2]);
}

function dateToStr(date, format) {
  if (date == undefined || date == null || date == '') {
    return '';
  }
  if (format == undefined || format == "" || format == null) {
    return new Date(parseInt(date)).format("yyyy-MM-dd hh:mm:ss");
  }
  return new Date(parseInt(date)).format(format);
}



function officeTree(options) {


  var defaultOp = {
    selector: ".tree",
    checkboxType: {
      "Y": "sp",
      "N": "sp"
    },
    chkStyle: "radio",
    url: "",
    otherParam: {},
    idKey: "id",
    pIdKey: "parentId",
    inputName: "name",
    dataFilter: function () {


      // return eval(childNodes.jsonToString);
    },
    callback: {},
    rootPId: 0
  };

  var op = $.extend({}, defaultOp, options);

  function beforeClick(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    zTree.checkNode(treeNode, !treeNode.checked, null, true);
    return false;
  }
  function onCheck(event, treeId, treeNode) {
    var allCheckesNodesFinal = []
    if (treeNode.checked) {
      var zTree = $.fn.zTree.getZTreeObj(treeId);
      var sNodes = zTree.getCheckedNodes(true);
      allCheckesNodes.push(treeNode)
      if (sNodes.length > 0) {
        var level = sNodes[0].level;
      }
      checkesNodes = sNodes
      if (zTree.setting.check.chkStyle === "radio") {
        divFadeOutA();
      }
    } else {
      for (let i = 0; i < allCheckesNodes.length; i++) {
        if (allCheckesNodes[i].checked) {
          allCheckesNodesFinal.push(allCheckesNodes[i])
        }
      }
      allCheckesNodes = allCheckesNodesFinal
    }
    // console.log(`allCheckesNodes:${JSON.stringify(allCheckesNodes)}`)
  }

  var checkesNodes = []
  var allCheckesNodes = [];


  var setting = {
    check: {
      enable: true,
      chkStyle: op.chkStyle,
      chkboxType: op.chkboxType,
      radioType: "all"
    },
    async: {
      enable: true,
      url: op.url,
      otherParam: op.otherParam,
      dataFilter: op.dataFilter,
      dataType: "json"
    },
    view: {
      dblClickExpand: false,
      showLine: true,
      selectedMulti: false,
    },
    data: {
      simpleData: {
        enable: true,
        idKey: op.idKey,
        pIdKey: op.pIdKey,
        rootPId: op.rootPId
      }
    },
    callback: $.extend({}, {
      beforeClick: beforeClick,
      onCheck: onCheck
    }, op.callback)
  };

  var selector = op.selector.replace("#", "").replace(".", "");

  var treeSelectId = "#" + selector + "_tree_select";
  var treeSelectInputId = "#" + selector + "_tree_select_input";
  var treeId = "#" + selector + "_tree";

  var treeInputId = "#" + selector + "_tree_input";

  // $()

  var inputHtml = "<input  class='tree_select_input' name='" + op.inputName
    + "' type='hidden' title='haha' id='" + treeSelectInputId.replace("#", "")
    + "' >";
  var selectHtml = "<section id='"
    + selector
    + "_tree_select' data-tree-id='"
    + treeId
    + "'  data-id-key='"
    + op.idKey
    + "'   data-target='"
    + op.selector
    + "'  class='tree_select' style='top:100%; min-width:200px;position: absolute; background: white;max-height: 300px;"
    + "overflow:hidden; border: 1px solid #ccc; box-shadow: 0 6px 12px rgba(0,0,0,.175); display: none; z-index: 100000;'>"
    + "</section>";
  var selectInput = "<div class='treeInput' style='position: absolute;'></div><input id='keyword' class='treeInput' placeholder='请输入关键字'>";
  var treeUL = "<ul id='"
    + selector
    + "_tree' class='ztree' style='margin-top:5px;width:100%;height:85%; overflow:auto; max-width: inherit; overflow-x:visibility '></ul>"
  var treeUL2 = "<ul id='"
    + selector
    + "_tree2' class='ztree' style='margin-top:5px;width:100%;height:85%; overflow:auto; max-width: inherit; overflow-x:visibility '></ul>"

  var t = $(op.selector);

  // t.append(inputHtml);
  $(inputHtml).insertAfter(t);
  // 插入
  // console.log(t);
  $(selectHtml).insertAfter(t);

  $(treeSelectId).append(selectInput);
  $(treeSelectId).append(treeUL);
  $(treeSelectId).append(treeUL2);

  $(treeSelectId).data(op);
  var temp = ""
  var treeObj = $.fn.zTree.init($(treeId), setting, []);
  if (treeObj.setting.check.chkStyle === "radio") {
    allNodes = treeObj.getNodesByParam("isHidden", false);
    treeObj2 = $.fn.zTree.init($(treeId + "2"), setting, allNodes);//定义新树控件
    $(treeId).css("display", "none")
  }



  var cpLock = false;
  var myKeyword = document.querySelector('#keyword');
  myKeyword.addEventListener('click', function () {
    if (treeObj.setting.check.chkStyle === "checkbox") {
      filterNoneShow();
    }
  });
  myKeyword.addEventListener('compositionstart', function () {
    cpLock = true;
  });
  myKeyword.addEventListener('compositionend', function () {
    cpLock = false;
    let _keywords = $("#keyword").val();
    if (!cpLock && _keywords !== '') {
      setTimeout(function () {
        if ($("#keyword").change(function () { return false })) {
          filter();
        }
      }, 1500)
    }
  });
  myKeyword.addEventListener('input', function () {
    if (!cpLock) {
      setTimeout(function () {
        if ($("#keyword").change(function () { return false })) {
          filter();
        }
      }, 1500)
    }
  });

  var showNodes = [];	//用于存储显示的节点
  var hiddenNodes = [];	//用于存储隐藏的节点


  function checkboxFilter() {
    // console.log("用了checkboxFilter");
    treeObj.showNodes(hiddenNodes);//显示隐藏

    function checkboxFilterFuncm(node) { //查找不符合条件的叶子节点

      return !node.checked;
    };

    hiddenNodes = treeObj.getNodesByFilter(checkboxFilterFuncm);//获取不符合条件的叶子结点

    treeObj.hideNodes(hiddenNodes);//隐藏不符合条件的叶子结点

  }


  function filter() {//过滤ztree显示数据
    // console.log("用了filter")
    if (treeObj.setting.check.chkStyle === "radio") {
      showNodes = treeObj.getNodesByFilter(filterFunc);
      treeObj2 = $.fn.zTree.init($(treeId + "2"), setting, showNodes);//定义新树控件

      var checkNodes = [];
      var checkNodes = treeObj2.getNodesByParam("isHidden", false);//获取符合条件的子结点
      var checkNodesArry = treeObj2.transformToArray(treeObj2.getNodes());//获取符合条件的子结点
      // console.log(checkNodesArry)
      // var showedNodes = [];
      var selectVal = $(treeSelectInputId).val();
      var selectNode = treeObj2.getNodesByParam("level", "0");
      // console.log(`selectNode${selectNode}`)
      treeObj2.expandNode(selectNode[0], true, false, true);
      // treeObj2.expandAll(true);
      if (selectVal !== '') {
        // showedNodes = selectVal.split(",")
        for (var i = 0; i < checkesNodes.length; i++) {
          for (var j = 0; j < checkNodes.length; j++) {
            if (checkesNodes[i].id === checkNodes[j].id) {
              treeObj2.checkNode(checkNodes[j], true, true);
            };
          }
        };
      }
    } else {
      treeObj.showNodes(hiddenNodes);//显示隐藏
      // console.log(`filterhiddenNodes:${JSON.stringify(hiddenNodes.length)}`)
      // for (var i = 0, l = hiddenNodes.length; i < l; i++) {
      //   treeObj.checkNode(hiddenNodes[i], false, false, false);
      // }

      function filterFuncm(node) { //查找不符合条件的叶子节点
        var _keywords = $("#keyword").val();
        if (node.isParent || node.name.indexOf(_keywords) != -1) return false;
        return true;
      };

      hiddenNodes = treeObj.getNodesByFilter(filterFuncm);//获取不符合条件的叶子结点

      treeObj.hideNodes(hiddenNodes);//隐藏不符合条件的叶子结点


    }

  };
  function filterFunc(node) {//查找条件的子节点
    let _mykeywords = $("#keyword").val();
    if (node.isParent) {
      if (checkParent(node)) {
        if (node.name.indexOf(_mykeywords) != -1) {
          return true;
        } else {
          return false;
        }
      } else {
        return false
      }
    } else {
      if (checkParent(node)) {
        if (node.name.indexOf(_mykeywords) != -1) {
          return true;
        } else {
          return false;
        }
      } else {
        return false
      }
    }
    function checkParent(node) {//查找父级有无关键字
      if (node.getParentNode() !== null) {
        if (node.getParentNode().name.indexOf(_mykeywords) != -1) {
          return false
        } else {
          if (checkParent(node.getParentNode())) {
            return true
          } else {
            return false
          }
        }
      } else {
        return true
      }
    }
  };

  function filterNoneShow() {//过滤ztree显示数据
    if (treeObj.setting.check.chkStyle === "radio") {
      showNodes = treeObj.getNodesByFilter(filterFunc);
      treeObj2 = $.fn.zTree.init($(treeId + "2"), setting, showNodes);//定义新树控件
      var checkNodes = [];
      var checkNodes = treeObj2.getNodesByParam("isHidden", false);//获取符合条件的子结点
      var checkNodesArry = treeObj2.transformToArray(treeObj2.getNodes());//获取符合条件的子结点
      var selectVal = $(treeSelectInputId).val();
      var selectNode = treeObj2.getNodesByParam("level", "0");
    } else {
      treeObj.showNodes(hiddenNodes);//显示隐藏
      // console.log(`filterhiddenNodes:${JSON.stringify(hiddenNodes.length)}`)
      // for (var i = 0, l = hiddenNodes.length; i < l; i++) {
      //   treeObj.checkNode(hiddenNodes[i], false, false, false);
      // }
      function filterFuncm(node) { //查找不符合条件的叶子节点
        var _keywords = $("#keyword").val();
        if (node.isParent || node.name.indexOf(_keywords) != -1) return false;
        return true;
      };
      hiddenNodes = treeObj.getNodesByFilter(filterFuncm);//获取不符合条件的叶子结点
      treeObj.hideNodes(hiddenNodes);//隐藏不符合条件的叶子结点
    }
  };




  $(op.selector).on("click", function (event) {
    event.preventDefault();
    event.stopPropagation();
    if (treeObj.setting.check.chkStyle === "radio") {
      filter();
    } else {
      if ($(treeSelectInputId).val() === "") { filter(); } else { checkboxFilter(); }

    }


    // treeObj2.showNodes(hiddenNodes);

    var sele = "#" + selector + "_tree_select";
    if (!$(sele).is(":hidden")) {
      return;
    }
    $(treeSelectId).show();
    var maxHeight = $(window).height() - $(treeSelectId).offset().top;
    $(treeSelectId).height(maxHeight - 5);
    $(treeId).width($(treeSelectId).width());
  });

  $(treeSelectId).on("tree.select.change", function (e) {
    // treeObj.showNodes(hiddenNodes);
    var idKey = $(this).data("idKey");
    var inputName = $(this).data("inputName");
    var selector = $(this).data("selector");
    var target = $(this);
    var taregtInput = $($(this).data("target")); // 设置目标区域
    var treeId = $(target).data("tree-id").replace("#", "")
      .replace(".", "");
    if (treeObj.setting.check.chkStyle === "radio") {
      var object = $.fn.zTree.getZTreeObj(treeId + "2").getCheckedNodes();
    } else {
      var object = allCheckesNodes
      //   var object = $.fn.zTree.getZTreeObj(treeId).getCheckedNodes();
    }
    // var object = []
    // for (var i = 0; i < allCheckesNodes.length; i++) {
    //   if (allCheckesNodes[i].checked) {
    //     object.push(allCheckesNodes[i])
    //   }
    // }
    // console.log(`object:${JSON.stringify(object)}`)
    // var object2 = $.fn.zTree.getZTreeObj(treeId+"2").getCheckedNodes();
    var texts = "", values = "";
    for (var i = 0; i < object.length; i++) {
      texts += object[i].name + ",";
      values += object[i][idKey] + ",";
    }

    if (texts.match(",$")) {
      texts = texts.substr(0, texts.length - 1);
    }
    if (values.match(",$")) {
      values = values.substr(0, values.length - 1);
    }

    $(selector).val(texts);
    $(selector).trigger("blur");

    $(selector + "_tree_select_input").val(values);
    $(selector).attr("title", texts);

    // $("input.tree_select_input[name="+inputName+"]").val(values);

    // 修改时
    if ($(this).is(":hidden")) {
      return;
    }

    $(selector + "_tree_select_input").trigger("change");
    // $("input.tree_select_input[name="+inputName+"]").trigger("change");
    if (!$(this).is(":hidden")) {
      $(target).hide(100);
    }
  })

}
function divFadeOut(event) {
  var targets = $('.tree_select'); // 设置目标区域
  for (var i = 0; i < targets.length; i++) {
    var target = $(targets[i]);
    var taregtInput = $($(targets[i]).data("target")); // 设置目标区域
    if (!target.is(event.target)
      && target.has(event.target).length === 0
      && !taregtInput.is(event.target)
      && taregtInput.has(event.target).length === 0) {
      $(target).trigger("tree.select.change");
      $("#keyword").val('');
    }
  }
}
function divFadeOutA(event) {//设置点击单选框
  var targets = $('.tree_select'); // 设置目标区域
  for (var i = 0; i < targets.length; i++) {
    var target = $(targets[i]);
    $(target).trigger("tree.select.change");
    $("#keyword").val('');
  }
}
// 点击空白处隐藏弹出层，下面为滑动消失效果和淡出消失效果。
$(document).click(divFadeOut);







// 将form中的值转换为键值对。
function getFormJson(frm) {
  var o = {};
  var a = $(frm).serializeArray();
  // console.log(a);
  $.each(a, function () {
    if (this.name == "password") {
      this.value = $.md5(this.value)
    }
    if (o[this.name] !== undefined) {
      if (!o[this.name].push) {
        o[this.name] = [o[this.name]];
      }
      o[this.name].push(this.value.trim() || '');
    } else {
      o[this.name] = this.value.trim() || '';
    }
  });
  return o;
}

function areaSelect(option) {

  var op = {
    selector: ".area_select",
    prov: ".area-prov",
    district: ".area-district",
    county: ".area-county",
    formSelector: "form",
    provInit: function () {
    },
    districtInit: function () {
    },
    countyInit: function () {
    }

  }
  op = $.extend(op, option);

  layui.use('form', function () {
    var form = layui.form(); // 只有执行了这一步，部分表单元素才会修饰成功
    commmonAjax({
      url: "../selector/area.do",
      success: function (data) {
        if (data.code == 200) {
          data = data.data;
          var html = "<option value=''>" + tips.prov + "</option>";
          for (var i = 0; i < data.length; i++) {
            html += "<option value='" + data[i].provEn + "' >"
              + data[i].provCn + "</option>"
          }
          $(op.selector).find(op.prov).html(html);
          form.render('select');
          op.provInit(op.prov, form, $(op.prov));
          $(op.prov).trigger("change");
        }
      }
    })
    form.on('select', function (data) {
      // 省有更新
      if ($(data.elem).hasClass(op.prov.replace(".", ""))) {
        // console.log("省有更新，重新加載市信息");
        // $(op.selector).find(op.district).html("");
        $(op.selector).find(op.county).html(
          "<option value=''>" + tips.select + "</option>");
        commmonAjax({
          url: "../selector/area.do",
          data: {
            provEn: data.value
          },
          success: function (data) {
            if (data.code == 200) {
              data = data.data;
              var html = "<option value='' >" + tips.district + "</option>";
              for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].districtEn
                  + "' >" + data[i].districtCn
                  + "</option>"
              }
              $(op.selector).find(op.district).html(html);
              form.render('select');
              op.districtInit(op.district, form);
              $(op.district).trigger("change");
            }
          }
        })
      }

      if ($(data.elem).hasClass(op.district.replace(".", ""))) {
        // console.log("市有更新")
        // $(op.selector).find(op.county).html("");
        commmonAjax({
          url: "../selector/area.do",
          data: {
            districtEn: data.value
          },
          success: function (data) {
            if (data.code == 200) {
              data = data.data;
              var html = "<option value='' >" + tips.county + "</option>";
              for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].areaId
                  + "' >" + data[i].nameCn + "</option>"
              }
              $(op.selector).find(op.county).html(html);
              form.render('select');
              op.countyInit(op.county, form);
              $(op.county).trigger("change");
            }
          }
        })
      }
    });
    form.render('select');
  });

}

//文本域字数限制
function limitInputNum(textArea, numItem, max) {


  /*if(max ==null || max == undefined || max == "" || isNaN(max)){
       max = numItem.text();
   }*/

  if (max == null || max == undefined || max == "" || isNaN(max)) {
    max = 140;
  }
  numItem.next().html("/" + max);

  textArea[0].setAttribute("maxlength", max);
  curLength = textArea.val().length;

  if (max - curLength < 0) {
    textArea.val(textArea.val().substring(0, max))
    curLength = textArea.val().length;
  }
  numItem.text(max - curLength);

  textArea.on('input propertychange', function () {
    var _value = $(this).val().replace(/\n/gi, "");
    numItem.text(max - _value.length);
  });
  //textArea.trigger("input");
}

