package com.hzdracom.core.util;


/**
 * 全局返回码 1xx开头：表示一般成功或失败 
 * 2xx开头：表示安全检查失败 
 * 3xx开头：表示数据校验失败 
 */
public class ResultCode {

	/** 操作成功 */
	@ReadText(text = "操作成功")
	public static final int RC_1000 = 1000;
	
	/** 操作失败（如没有明确的错误原因，则返回此错误码） */
	@ReadText(text = "操作失败")
	public static final int RC_1001 = 1001;
	
	/** 操作成功 */
	@ReadText(text = "数据不存在或已停用")
	public static final int RC_1004 = 1004;
	
	/** 安全验证未通过 */
	@ReadText(text = "安全验证未通过")
	public static final int RC_2000 = 2000;
	
	/** IP未通过*/
	@ReadText(text = "IP未通过")
	public static final int RC_2001 = 2001;
	
	/** app_key无效*/
	@ReadText(text = "app_key无效")
	public static final int RC_2002 = 2002;
	
	/** openId未携带*/
	@ReadText(text = "openId未携带")
	public static final int RC_2003 = 2003;
	
	/** openId无效*/
	@ReadText(text = "openId无效")
	public static final int RC_2004 = 2004;
	
	/** app_key停用或未激活*/
	@ReadText(text = "app_key停用或未激活")
	public static final int RC_2005 = 2005;
	
	/** 接口无权限*/
	@ReadText(text = "接口无权限")
	public static final int RC_2050 = 2050;
	
	/** 必填参数为空（可附带返回多个错误的参数名，逗号分隔） */
	@ReadText(text = "必填参数为空")
	public static final int RC_3001 = 3001;

	/** 参数长度溢出（可附带返回多个错误的参数名，逗号分隔） */
	@ReadText(text = "参数长度溢出")
	public static final int RC_3002 = 3002;

	/** 参数格式错误（可附带返回多个错误的参数名，逗号分隔） */
	@ReadText(text = "参数格式错误")
	public static final int RC_3003 = 3003;

	/** 取值范围不对（可附带返回参数序号，从1开始） */
	@ReadText(text = "值不正确")
	public static final int RC_3004 = 3004;
			
	/** 数据格式有误 **/
	@ReadText(text = "数据格式有误")
	public static final int RC_5000 = 5000;
	
	/** 数据解密出错 **/
	@ReadText(text = "数据解密出错")
	public static final int RC_5001 = 5001;
	
	/** 接口名称为空 **/
	@ReadText(text = "接口名称为空")
	public static final int RC_5003 = 5003;
	
	/** 接口不存在 **/
	@ReadText(text = "接口不存在")
	public static final int RC_5004 = 5004;
	
	/** timestamp不对 **/
	@ReadText(text = "timestamp不对")
	public static final int RC_5005 = 5005;
	
	/** hashcode不对 **/
	@ReadText(text = "hashcode不对")
	public static final int RC_5006 = 5006;
		
	/** 接口请求频繁 **/
	@ReadText(text = "接口请求频繁")
	public static final int RC_6000 = 6000;

	/** 系统异常**/
	@ReadText(text = "系统异常")
	public static final int RC_9999 = 9999;
	
	/**
	 * 根据返回码取返回文本
	 * 
	 * @param resultCode
	 * @return
	 */
	public static String getResultText(int resultCode) {
		ReadText rst = null;
		try {
			rst = ResultCode.class.getField("RC_" + resultCode).getAnnotation(ReadText.class);
		} catch (Exception e) {
			return null;
		}
		return rst == null ? null : rst.text();
	}

	public static void main(String[] args) {
		System.out.println(ResultCode.getResultText(ResultCode.RC_1000));
	}
	
}
