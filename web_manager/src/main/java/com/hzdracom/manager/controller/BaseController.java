package com.hzdracom.manager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.hzdracom.manager.util.ContextUtils;

public class BaseController {

	private  Logger logger = LoggerFactory.getLogger(getClass());

	/** 返回状态键名 **/
	private static final String KEY_CODE = "code";
	/** 返回数据键名 **/
	private static final String KEY_DATA = "data";
	/** 返回信息键名 **/
	private static final String KEY_MSG = "msg";

	/** 代表成功的值 **/
	private static final String VALUE_SUCCESS = "200";
	/** 代表错误的值 **/
	private static final String VALUE_ERROR = "300";

	/** 系统异常 **/
	private static final String SYSTEM_ERROR = "999";
	
	/**
	 * 封装并以json返回成功执行的信息
	 * @param response
	 * @param data
	 * @param msg
	 */
	protected void respSuccessMsg(HttpServletResponse response, Object data, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put(KEY_DATA, data);
			map.put(KEY_CODE, VALUE_SUCCESS);
			map.put(KEY_MSG, msg);
			//response.setContentType("application/json");
			response.setContentType("text/html");
			
			ContextUtils.respString(response, JSON.toJSONString(map));
		} catch (Exception e) {
			logger.error("respSuccessMsg 异常",e);
		}
	}

	/**
	 * 封装并以json返回错误执行的信息
	 * @param response
	 * @param msg
	 */
	protected void respErrorMsg(HttpServletResponse response, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put(KEY_CODE, VALUE_ERROR);
			map.put(KEY_MSG, msg);
		//	response.setContentType("application/json");
			// 解決 IE9 JSON 出现下载的问题
			response.setContentType("text/html");
			ContextUtils.respString(response, JSON.toJSONString(map));
		} catch (Exception e) {
			logger.error("respErrorMsg 异常",e);
		}
	}
	
	/** 封装并以json返回错误执行的信息
	 * @param response
	 * @param msg
	 */
	private void respErrorMsg(HttpServletResponse response,String code, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put(KEY_CODE, code);
			map.put(KEY_MSG, msg);
			response.setContentType("text/html");
			//response.setContentType("application/json");
			ContextUtils.respString(response, JSON.toJSONString(map));
		} catch (Exception e) {
			logger.error("respErrorMsg 异常",e);
		}
	}
	
	 
	@ExceptionHandler({Exception.class})   
	public void exception(HttpServletRequest request,HttpServletResponse response,Exception e){
		logger.error("系统异常",e);
		respErrorMsg(response, SYSTEM_ERROR, "操作失败，请稍候再试");
	}
	
	
	
	
	private static final String KEY_INFO = "info";
	private static final String KEY_STATUS = "status";

	protected void respSuccessInfo(HttpServletResponse response, String info)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(KEY_STATUS, "y");
		map.put(KEY_INFO, info);
		response.setContentType("application/json");
		ContextUtils.respString(response, JSON.toJSONString(map));
	}

	protected void respErrorInfo(HttpServletResponse response, String info)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(KEY_STATUS, "n");
		map.put(KEY_INFO, info);
		response.setContentType("application/json");
		ContextUtils.respString(response, JSON.toJSONString(map));
	}

	
	
	
	
	
	
	
	
	
}
