package com.hzdracom.manager.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ReportUtil
{
	
	public static void getCharCode(HttpServletResponse response, HttpServletRequest request, String fileName3) throws UnsupportedEncodingException {
		
		fileName3 = new String(fileName3.getBytes("gb2312"), "ISO8859-1");
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName3 + ".xls");
		/*
		 * if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox")
		 * > 0 ||
		 * request.getHeader("User-Agent").toLowerCase().indexOf("chrome") > 0)
		 * {
		 * System.out.println("火狐.........");
		 * fileName3 = new String(fileName3.getBytes("UTF-8"), "ISO8859-1");//
		 * firefox浏览器
		 * }
		 * else if
		 * (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
		 * {
		 * System.out.println("IE..........");
		 * fileName3 = URLEncoder.encode(fileName3, "UTF-8");// IE浏览器 终极解决文件名乱码
		 * }
		 */
		//response.setContentType("application/vnd.ms-excel;charset=utf-8");
		//response.setHeader("Content-disposition", "attachment;filename=\"" + fileName3 + ".xls\"");
	}
}
