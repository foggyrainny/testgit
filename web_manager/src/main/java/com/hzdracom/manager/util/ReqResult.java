package com.hzdracom.manager.util;

/**
 * @类功能说明：结果返回类
 * @类修改者：qianfuqiang 2013-8-9
 * @修改日期：
 * @修改说明：
 * @公司名称：杭州龙骞科技有限公司
 * @作者：miaochao
 * @创建时间：2013-7-22
 * @update 2015-07-14
 * @版本：V1.1
 */
public class ReqResult {
	private int resultCode;       // 返回码
	private Object returnObject;  // 返回对象
	private String returnMsg;     // 返回提示信息

	// 系统级错误
	public static final int resultCode_Success = 1000;// 成功
	public static final int resultCode_no_permission = 1001;// 无操作权限
	public static final int resultCode_login_error = 1002;// 帐号验证失败
	public static final int resultCode_rand_error = 1003;// 验证码验证失败
	public static final int resultCode_not_login = 1004;// 用户未登录-跳到登录页面
	public static final int resultCode_param_error = 1005;// 参数格式错误
	public static final int resultCode_param_lost = 1006;// 参数缺失
	public static final int resultCode_norole = 1007;// 角色未分配
	public static final int resultCode_no_conditions = 1008;//不满足操作条件
	public static final int resultCode_seesion_timeout = 1009;//Session失效，需要重新登录
	public static final int resultCode_repeat_operat = 1010;//操作已发生

	// 用户模块错误
	public static final int resultCode_user_forbid = 2001;// 禁用/删除用户
	public static final int resultCode_ssouser_notfind = 2002;// sso用户未找到信息
	public static final int resultCode_user_existed = 2003;// 用户以存在

	//角色模块错误
	public static final int resultCode_role_forbid = 3001;// 不能删除自己所属角色
	
	//文件上传模块错误
	public static final int resultCode_file_size = 4001;// 上传文件大小超过限制
	public static final int resultCode_file_type = 4002;// 上传文件类型超过限制

	//联盟保单接口
	public static final int resultCode_alliance_init = 5000;	//初始状态		
	public static final int resultCode_alliance_success = 5001;	//成功
	public static final int resultCode_alliance_fail = 5002;	//失败	
	
	public static final int resultCode_msg = 9998;// 服务端返回提示信息
	public static final int resultCode_error = 9999;// 系统异常

	public static final String resultMessage_seesion_timeout = "由于您长时间未操作，为确保账户安全，请重新登录。";
	public static final String resultMessage_param_lost = "参数缺失";
	
	
	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
}
