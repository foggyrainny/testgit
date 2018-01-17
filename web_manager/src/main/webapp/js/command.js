/**
 *   指令JS 的封装， 主要依托于websocket 完成
 */


function command(name,cmd,url) {
	this.name=name;
	this.cmd=cmd;
	this.url=url;
}

/*//var queryTime = new command("读取时钟","queryTime","./smu/command/queryTime.do");
//var setTime = new command("设置时钟","setTime","./smu/command/setTime.do");
//var reset = new command("复位指令","reset","./smu/command/reset.do");
var network = new command("组网指令","network","./smu/command/network.do");
var stopNetwork = new command("停止组网","stopNetwork","./smu/command/stopNetwork.do");
var startReport = new command("启动实时上报","startReport","./smu/command/startReport.do");
var stopReport = new command("停止实时上报","stopReport","./smu/command/stopReport.do");
var clearInverter = new command("清空逆变器","clearInverter","./smu/command/clearInverter.do");
var paramConfig = new command("参数配置","paramConfig","./smu/command/paramConfig.do");
var queryConfigParam = new command("读取配置参数","queryConfigParam","./smu/command/queryConfigParam.do");
var queryInverterList = new command("读取逆变器列表","queryInverterList","./smu/command/queryInverterList.do");
var queryDebugStatus = new command("获取调试测试系统状","queryDebugStatus","./smu/command/queryDebugStatus.do");

var startInverter = new command("启动逆变器","startInverter","./smu/command/startInverter.do");
var stopInverter = new command("停止逆变器","stopInverter","./smu/command/stopInverter.do");

var addInverter = new command("新增逆变器","addInverter","./smu/command/addInverter.do");
var delInverter = new command("删除逆变器","delInverter","./smu/command/delInverter.do");

var inverterPowerClear = new command("电量清零","inverterPowerClear","./smu/command/inverterPowerClear.do");

var inverterQueryParamConfig = new command("读取逆变器参数","inverterQueryParamConfig","./smu/command/inverterQueryParamConfig.do");
var inverterParamConfig = new command("逆变器参数配置","inverterParamConfig","./smu/command/inverterParamConfig.do");
*/

var  smu;
if(parent.smu != undefined && parent.smu != null) {
	smu = parent.smu;
	console.log("parentSMU:" + smu);
} else  {
	console.log("smu:" + smu);
smu = {
	lang:{
		title:'系统提示',
		confirm:'确认',
		cancel:'取消',
		sending:'等待响应',
		sendtime:'发送时间',
		returntime:'相应时间',
		request:'请求数据',
		response:'返回数据',
		cmderror:'指令失败',
		cmdsuccess:'执行成功',
		cmdstatus:'执行状态',
		cmdname:'指令名称'
			//1 成功  2 失败  3进行中    -1:smuId为空  -2：指令无效  -3:指令未实现  -4:设备不在线
	},
	lanstatus:{
		"1":"成功",
		"2":"失败",
		"3":"进行中",
	    "-1":"smuId为空",
		"-2":"指令无效",
		"-3":"指令未实现",
		"-4":"设备不在线"
	},
	eventList: new Array(),
	cmdEven:function(win,obj,callable){
		this.win = win;
		this.obj = obj;
		this.callable = callbale;
	},
	cmd:{
		queryTime:new command("读取时钟","queryTime","./smu/command/queryTime.do"),
		setTime: new command("设置时钟","setTime","./smu/command/setTime.do"),
		reset: new command("复位指令","reset","./smu/command/reset.do"),
		network:new command("组网指令","network","./smu/command/network.do"),
		 stopNetwork:new command("停止组网","stopNetwork","./smu/command/stopNetwork.do"),
		 startReport:new command("启动实时上报","startReport","./smu/command/startReport.do"),
		 stopReport:new command("停止实时上报","stopReport","./smu/command/stopReport.do"),
		 clearInverter: new command("清空逆变器","clearInverter","./smu/command/clearInverter.do"),
		 paramConfig : new command("参数配置","paramConfig","./smu/command/paramConfig.do"),
		 queryConfigParam:new command("读取配置参数","queryConfigParam","./smu/command/queryConfigParam.do"),
		 queryInverterList:new command("读取逆变器列表","queryInverterList","./smu/command/queryInverterList.do"),
		 queryDebugStatus:new command("获取调试测试系统状","queryDebugStatus","./smu/command/queryDebugStatus.do"),
		 startInverter:new command("启动逆变器","startInverter","./smu/command/startInverter.do"),
		 stopInverter:new command("停止逆变器","stopInverter","./smu/command/stopInverter.do"),
		 addInverter:new command("新增逆变器","addInverter","./smu/command/addInverter.do"),
		 delInverter:new command("删除逆变器","delInverter","./smu/command/delInverter.do"),
		 inverterPowerClear:new command("电量清零","inverterPowerClear","./smu/command/inverterPowerClear.do"),
		 inverterQueryParamConfig:new command("读取逆变器参数","inverterQueryParamConfig","./smu/command/inverterQueryParamConfig.do"),
		 inverterParamConfig:new command("逆变器参数配置","inverterParamConfig","./smu/command/inverterParamConfig.do")
	},	
	queryTime: function(win,obj,logId,smuId,callback){
		//询问框
		var curCmd = smu.cmd.queryTime;
	//	layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		layer.confirm(smu.tips(curCmd.name,smuId), {icon: 3, title: smu.lang.title ,btn: [smu.lang.confirm,smu.lang.cancel] //按钮	
		}, function(index){
			layer.close(index);
			smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,callback:callback});
		}, function(){});
	},
	setTime: function(win,obj,logId,smuId,time){
		var curCmd = smu.cmd.setTime;
	//	layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？  时间:["+time+"]", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		layer.confirm(smu.tips(curCmd.name,smuId), {icon: 3, title: smu.lang.title ,btn: [smu.lang.confirm,smu.lang.cancel] //按钮	
		}, function(index){
			layer.close(index);
			/*win.layer.open({
				  type: 1,
				  shadeClose:true,
				  content: win.$("#smu_settime_div") //这里content是一个普通的String
				});*/
			smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,param:{time:time}});
		}, function(){});
	},
	reset: function(win,obj,logId,smuId){
		var curCmd = smu.cmd.reset;
	//	layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		layer.confirm(smu.tips(curCmd.name,smuId), {icon: 3, title: smu.lang.title ,btn: [smu.lang.confirm,smu.lang.cancel] 	
		}, function(index){
			layer.close(index);
			smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd});
		}, function(){});
	},
	network: function(win,obj,logId,smuId){
		var curCmd =  smu.cmd.network;
	//	layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		layer.confirm(smu.tips(curCmd.name,smuId), {icon: 3, title: smu.lang.title ,btn: [smu.lang.confirm,smu.lang.cancel]
		}, function(index){
			layer.close(index);
			smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd});
		}, function(){});
	},
	stopNetwork: function(win,obj,logId,smuId){
		var curCmd = smu.cmd.stopNetwork;
	//	layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		layer.confirm(smu.tips(curCmd.name,smuId), {icon: 3, title: smu.lang.title ,btn: [smu.lang.confirm,smu.lang.cancel]
		}, function(index){
			layer.close(index);
			smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd});
		}, function(){});
	},
	startReport: function(win,obj,logId,smuId){
		var curCmd =  smu.cmd.startReport;
	//	layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		layer.confirm(smu.tips(curCmd.name,smuId), {icon: 3, title: smu.lang.title ,btn: [smu.lang.confirm,smu.lang.cancel]	
		}, function(index){
			layer.close(index);
			smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd});
		}, function(){});
	},
	stopReport: function(win,obj,logId,smuId){
		var curCmd =  smu.cmd.stopReport;
	//	layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		layer.confirm(smu.tips(curCmd.name,smuId), {icon: 3, title: smu.lang.title ,btn: [smu.lang.confirm,smu.lang.cancel]
		}, function(index){
			layer.close(index);
			smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd});
		}, function(){});
	},
	clearInverter: function(win,obj,logId,smuId){
		var curCmd =  smu.cmd.clearInverter;
	//	layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		layer.confirm(smu.tips(curCmd.name,smuId), {icon: 3, title: smu.lang.title ,btn: [smu.lang.confirm,smu.lang.cancel]
		}, function(index){
			layer.close(index);
			smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd});
		}, function(){});
	},
	paramConfig: function(win,obj,logId,smuId,param){
		var curCmd =  smu.cmd.paramConfig;
		//console.log(param);
		smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,param:param});
		
/* 	layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		}, function(index){
			layer.close(index);
			
		}, function(){});*/
	},
	queryConfigParam: function(win,obj,logId,smuId,callback){
		var curCmd =  smu.cmd.queryConfigParam;
		// 查询SMU的配置参数
		smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,callback:callback});
		
		/*var curCmd = paramConfig;
		layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		}, function(index){
			layer.close(index);
			
		}, function(){});*/
	},
	queryInverterList:function(win,obj,logId,smuId,callback){
		var curCmd =  smu.cmd.queryInverterList;
		smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,callback:callback});
	},
	queryDebugStatus:function(win,obj,logId,smuId,hwid,callback){
		var curCmd =  smu.cmd.queryDebugStatus;
		smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,param:{hwid:hwid},cmd:curCmd,callback:callback});
	},
	addInverter:function(win,obj,logId,smuId,hwid,callback){
		var curCmd =  smu.cmd.addInverter;
		smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,param:{hwid:hwid},callback:callback});
	},
	delInverter:function(win,obj,logId,smuId,hwid,callback){
		var curCmd =  smu.cmd.delInverter;
		smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,param:{hwid:hwid},callback:callback});
		
	},
	startInverter:function(win,obj,logId,smuId,hwid,callback){
		var curCmd =  smu.cmd.startInverter;
//		layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？  微逆编号【"+hwid+"】   ", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		layer.confirm(smu.tips(curCmd.name,smuId,hwid), {icon: 3, title: smu.lang.title ,btn: [smu.lang.confirm,smu.lang.cancel]
		}, function(index){
			layer.close(index);
			smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,param:{hwid:hwid}});
		}, function(){});
		//smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,param:{hwid:hwid},callback:callback});
	},
	stopInverter:function(win,obj,logId,smuId,hwid,callback){
		var curCmd =  smu.cmd.stopInverter;
	//	layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？  微逆编号【"+hwid+"】   ", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		layer.confirm(smu.tips(curCmd.name,smuId,hwid), {icon: 3, title: smu.lang.title ,btn: [smu.lang.confirm,smu.lang.cancel]	
	}, function(index){
			layer.close(index);
			smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,param:{hwid:hwid}});
		}, function(){});
	},
	inverterPowerClear:function(win,obj,logId,smuId,hwid,callback){
		var curCmd =  smu.cmd.inverterPowerClear;
		//layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？  微逆编号【"+hwid+"】   ", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		layer.confirm(smu.tips(curCmd.name,smuId,hwid), {icon: 3, title: smu.lang.title ,btn: [smu.lang.confirm,smu.lang.cancel]	
		}, function(index){
			layer.close(index);
			smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,param:{hwid:hwid}});
		}, function(){});
	},
	inverterQueryParamConfig:function(win,obj,logId,smuId,hwid,callback){
		var curCmd  =  smu.cmd.inverterQueryParamConfig;
		smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,param:{hwid:hwid},callback:callback});
		/*var curCmd = inverterPowerClear;
		layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？  微逆编号【"+hwid+"】   ", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		}, function(index){
			layer.close(index);
			
		}, function(){});*/
	},
	inverterParamConfig:function(win,obj,logId,smuId,param,callback){
		var curCmd  =  smu.cmd.inverterParamConfig;
		smu.execute({win:win,smuId:smuId,obj:obj,logId:logId,cmd:curCmd,param:param,callback:callback});
		/*var curCmd = inverterPowerClear;
		layer.confirm("确认对设备【"+smuId+"】发出"+curCmd.name+"指令吗？  微逆编号【"+hwid+"】   ", {icon: 3, title:'系統提示',btn: ['确认','取消'] //按钮
		}, function(index){
			layer.close(index);
			
		}, function(){});*/
	},
	execute:function(op){
		
		if(op.param == null || op.param == undefined) {
			op.param = {};
		}
		op.param.smuId= op.smuId;
		
		commmonAjax({
			url:op.cmd.url,
			type:'post',
			data: op.param,
		    success:function(data){
		    	console.log(data);
		    	// 执行成功之后
		    	if(data.code == 200  || data.code == "200") {
		    		//  监听回调信息
		    		console.log(data.data.msgId);
		    		op.win.$(op.win.document).on(data.data.msgId+"",smu.callable);
		    		// 将数据存起来， 当回调时需要用到
		    		smu.eventList[data.data.msgId] = op;
		    		console.log(smu.eventList);
		    		
		    		smu.showLog(op.cmd,op.logId,data.data.msgId,data,op.param);
		    	}
		    }
		
		})
		
		//$.ajax({})
	},
	showLog:function(cmd,selector,msgId,data,param){
		
		var name = cmd.name;
		var startTime = dateToStr(data.data.startTime);
		var endTime = "";
		var request = /* data.data.command  +*/  "&nbsp;&nbsp;&nbsp;" + JSON.stringify(param)  ;
		var response = "";
		var status = smu.lang.sending;
		
		if(data.status <= 0){
			status =  smu.lang.cmderror;
			response = data.error;
		}
		
		var html = "	<blockquote class='layui-elem-quote'  ><div  id='"+msgId+"'  class='smu_order_info' >"
		 			+	"<div>"
						+	"<div style='display: inline-block;width: 22%;'><span>"+smu.lang.cmdname+"：</span><span class='span' style='padding-left: 20px;'  data-field='name'>"+name+"</span></div>" 
						+	"<div style='display: inline-block;width: 22%;'><span>"+smu.lang.sendtime+"：</span><span class='span' style='padding-left: 20px;'  data-field='startTime'>"+ startTime +"</span></div>" 
						+	"<div style='display: inline-block;width: 22%;'><span>"+smu.lang.returntime+"：</span><span class='span' style='padding-left: 20px;'  data-field='endTime'>"+endTime+"</span></div>" 
						+	"<div style='display: inline-block;width: 22%;'><span>"+smu.lang.cmdstatus+"：</span><span class='span' style='padding-left: 20px;'  data-field='status'>"+status+"</span></div>"
						+ " </div>"
					+	"<div>"
						+	"<div style='display: inline;'>"+smu.lang.request+"：</div>"  
						+	"<div  data-field='request'  style='display: inline; word-wrap: break-word; word-break: normal;'>"+request+"</div>" 
					+	"</div>"
					+	"<div>"
					 	+	"<div  style='display: inline;' >"+smu.lang.response+"：</div> " 
					 	+	"<div data-field='response' style='display: inline; word-wrap: break-word; word-break: normal;'>"+response+"</div>"
					+	"</div></blockquote>";
		smu.eventList[msgId].win.$(selector).prepend(html);
	},
	callable:function(e,msgId,data){
		
		console.log(data);
		
		var op = smu.eventList[msgId];
		if(op == null || op == undefined ) {
			console.log("msgId 不存在 " + msgId);
			return;
		}
		var log = op.win.$(op.logId).find("#"+msgId);
		if(log == null || log == undefined ) {
			console.log("log 不存在 " + logId);
			//return;
		} else {
		//   0 请求已发出  1 执行成功  2执行失败  -1 设备不存在
			 if(data.status == 1) {
				op.win.$(log).find("[data-field=status]").html(smu.lang.cmdsuccess);
				op.win.$(log).find("[data-field=endTime]").html(dateToStr(data.endTime));
				op.win.$(log).find("[data-field=response]").html(JSON.stringify(data.responseObj));
				//op.win.$(log).find("[data-field=request]").html( data.request);
				//op.win.$(log).find("[data-field=response]").html(data.response);
			} else if(data.status == 2) {
				op.win.$(log).find("[data-field=status]").html(smu.lang.cmderror);
			} else {
				op.win.$(log).find("[data-field=status]").html(smu.lanstatus[data.status]);
			}
		}
		
		if(typeof op.callback === "function"){
			op.callback(data);
		}
	}
}

console.log(smu);
};