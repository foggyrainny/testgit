package com.hzdracom.manager.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.hzdracom.core.util.Util;
import com.hzdracom.manager.annotation.AuthenPassport;
import com.hzdracom.manager.util.ContextUtils;
import com.hzdracom.manager.util.SessionUtils;
import com.hzdracom.manager.util.StringUtil;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception arg3) throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2,
			ModelAndView view) throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// 如果拦截的是方法，查验是否有AuthenPassport注解，如果有注解则需要验证用户是否已登录
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			AuthenPassport authPassport = method.getMethodAnnotation(AuthenPassport.class);
			if (authPassport == null || authPassport.validate() == false) {
				return true;
			}
			String userId;
			try {
				userId = SessionUtils.getCurrentUserId(request);
			} catch (Exception e) {
				log.error(e.toString());
				userId = null;
			}
			if (!StringUtil.isEmpty(userId)) {
				return true;
			}
			
			log.error("未登录的跳转到未登录提示页面" + request.getContextPath());
			
			String head = request.getHeader("x-requested-with"); 
			if(Util.isEmpty(head)) {
				response.getWriter().write(
						"<script language='javascript'>top.location.href='" + request.getContextPath()
								+ "/login.do'</script>");
				return false;
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 998);
			map.put("data", "未登录,请先登录");
			map.put("msg", "未登录,请先登录");
			response.setContentType("application/json");
			ContextUtils.respString(response, JSON.toJSONString(map));
			return false;
		}
		return true;
	}
}
