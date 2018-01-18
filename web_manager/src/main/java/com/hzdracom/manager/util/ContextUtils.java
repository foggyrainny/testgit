package com.hzdracom.manager.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ContextUtils {
	/**
	 * response输出字符串
	 * @param response
	 * @param str
	 * @throws Exception
	 */
	public static void respString(HttpServletResponse response, String str) throws Exception {
		try {
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
