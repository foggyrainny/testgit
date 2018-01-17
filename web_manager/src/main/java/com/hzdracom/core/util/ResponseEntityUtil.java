package com.hzdracom.core.util;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @Title: ResponseEntityUtil.java
 * @Package com.hzdracom.util
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2014-5-27 下午9:12:17
 */
public class ResponseEntityUtil
{
	private static final Logger log = Logger.getLogger(ResponseEntityUtil.class);
	
	private static final Gson gson     = new Gson();
	
	private static final Gson gsonTime = new GsonBuilder().registerTypeAdapter(Date.class, new UtilDateSerializer()).setDateFormat(DateFormat.LONG).create();
	
	private static final Gson gsonData = new GsonBuilder().registerTypeAdapter(Date.class, new UtilDateSerializer()).setDateFormat(DateFormat.LONG).excludeFieldsWithoutExposeAnnotation().create();
	
	public static ResponseEntity<String> successResponse(ResultValue resultValue) {
		return successResponse(gson.toJson(resultValue));
	}
	
	public static ResponseEntity<String> successResponse(Gson gson,ResultValue resultValue) {
		return successResponse(gson.toJson(resultValue));
	}
	
	public static ResponseEntity<String> successResponseTime(ResultValue resultValue) {
		return successResponse(gsonTime.toJson(resultValue));
	}
	
	public static ResponseEntity<String> successResponseDate(ResultValue resultValue) {
		return successResponse(gsonData.toJson(resultValue));
	}
	
	private static ResponseEntity<String> successResponse(String json) {
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("text", "html", Charset.forName("utf-8"));
		headers.setContentType(mediaType);
		log.info("[SEND-OK]:"+json);
		return new ResponseEntity<String>(json, headers, HttpStatus.OK);
	}
	
}
