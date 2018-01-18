package com.hzdracom.core.exception;

import com.hzdracom.core.util.ResultValue;


/**
 * 
* @ClassName: ApiException 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 刘章 
* @date 2016年6月29日 下午4:53:34 
*
 */
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = -7420755214537385831L;

	private int code;
	private String result;

	public ApiException(ResultValue resultValue) {
		this(resultValue.getCode(), (String) resultValue.getResult());
	}

	public ApiException(int code,String result) {
		super(code + ":" + result);
		this.code = code;
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public String getResult() {
		return result;
	}
	
}
