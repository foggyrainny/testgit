package com.hzdracom.manager.constant;

import com.hzdracom.manager.util.Configuration;

public class ContextConstant {

	/** 人员身份凭证键名 **/
	public static final String IDEN_CERT_KEY = "current_user_cert";
	
	/** 人员身份**/
	public static final String CURR_USER_INFO = "current_user_info";
	
	/** 人员身份**/
	public static final String CURR_USER_MENU_MAP = "CURR_USER_MENU_MAP";
	
	/** 人员身份**/
	public static final String CURR_USER_MENU_HTML_CODE = "CURR_USER_MENU_HTML_CODE";
	
	public static final String CURR_USER_DATA_AUTHORITY  = "CURR_USER_DATA_AUTHORITY";

	/** 登录图片验证码  */
	public static final String LOGIN_IMAGE_CODE = "LOGIN_IMAGE_CODE";
	
	/** 文件上传路径 **/
	public static String FILE_UPLOAD_URL = Configuration.readConfigString("fileUpload","system");
}
