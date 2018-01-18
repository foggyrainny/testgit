 package com.hzdracom.core.util; 

import java.io.Serializable;

/** 
 * @Title: ResultValue.java
 * @Package com.hzdracom.core.util 
 * @Description: TODO(添加描述) 
 * @author 刘章 
 * @date 2016年6月29日 下午2:32:34 
 */
public class ResultValue
        implements
        Serializable
{

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
    private static final long serialVersionUID = -1600487189029348880L;
	
    private int code;
    
    private Object result;
    
    
    public static ResultValue success() {
		ResultValue resultValue = new ResultValue();
		resultValue.setCode(ResultCode.RC_1000);
		resultValue.setResult(ResultCode.getResultText(ResultCode.RC_1000));
		return resultValue;
	}
	
	public static ResultValue success(Object result) {
		ResultValue resultValue = new ResultValue();
		resultValue.setCode(ResultCode.RC_1000);
		resultValue.setResult(result);
		return resultValue;
	}
	
	public static ResultValue error() {
		ResultValue resultValue = new ResultValue();
		resultValue.setCode(ResultCode.RC_1001);
		resultValue.setResult(ResultCode.getResultText(ResultCode.RC_1001));
		return resultValue;
	}
	
	public static ResultValue error(int code) {
		ResultValue resultValue = new ResultValue();
		resultValue.setCode(code);
		resultValue.setResult(ResultCode.getResultText(code));
		return resultValue;
	}
	
	public static ResultValue error(int code,String result) {
		ResultValue resultValue = new ResultValue();
		resultValue.setCode(code);
		resultValue.setResult(result);
		return resultValue;
	}
 

	public int getCode() {
		
		return code;
	}

	public void setCode(int code) {
		
		this.code = code;
	}

	public Object getResult() {
		
		return result;
	}

	public void setResult(Object result) {
		
		this.result = result;
	}
    
    
}

