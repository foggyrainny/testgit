package com.hzdracom.manager.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.hzdracom.core.util.Util;
import com.hzdracom.manager.annotation.Permission;
import com.hzdracom.manager.bean.sys.Menu;
import com.hzdracom.manager.bean.sys.User;
import com.hzdracom.manager.service.sys.IMenuService;
import com.hzdracom.manager.util.ContextUtils;
import com.hzdracom.manager.util.SessionUtils;
import com.hzdracom.manager.util.StringUtil;

public class PermisInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = LoggerFactory.getLogger(PermisInterceptor.class);

	@Resource
	private IMenuService menuService;

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception arg3) throws Exception {

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2,
			ModelAndView view) throws Exception {
		// 如果返回的视图不为NULL，则查找该视图下当前登录人员能够访问的资源，并装载至视图中返回
		/*if (view != null) {
			String userId = SessionUtils.getCurrentUserId(request);
			//可缓存优化×××××××××××
			if (!(StringUtil.isEmpty(userId) || StringUtil.isEmpty(view.getViewName()))) {
				List<String> htmlCodes = menuService.qryUserHtmlCode(userId);
				for (String htmlCode : htmlCodes) {
					view.addObject(htmlCode, true);
				}
			}
		}*/
	}
	

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// 如果拦截的是方法，检查是否有Permission注解，如果有注解则验证是否登录，再验证是否有权限访问注解内标识的资源
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			Permission permission = method.getMethodAnnotation(Permission.class);
			if (null == permission || permission.validate() == false) {
				return true;
			}
			User user = SessionUtils.getCurrentUser(request);
			String path = request.getServletPath();
			if (user!=null) {
				// 判断用户是否可以访问这个链接地址
				log.info("执行权限处理拦截开始" + path);
				
				if(user.getRoleId() <= 1) {
					return true;
				}
				Map<String, Menu> map = SessionUtils.getCurrentUserMenus(request);
				if (map.containsKey(path.replaceAll("^/{1,}", "").replaceAll("/{1,}", "/"))) {
					return true;
				}
			} else {
				// 未登录的跳转到未登录提示页面
				log.error("未登录的跳转到未登录提示页面" + request.getContextPath());
				response.getWriter().write(
						"<script language='javascript'>top.location.href='"
								+ request.getContextPath() + "/login.do'</script>");
			}
			log.info("执行权限处理拦截无权限" + path);
			
			
			String head = request.getHeader("x-requested-with"); 
			
			
			String menuName = "";
			
			String url = request.getRequestURI().replace(request.getContextPath(), "");
			
			 if(url .matches("^/{1,20}.*$")){
	           	 url = url.replaceAll("^/{1,20}", "");
	          }
			
			 Menu menu = menuService.getMenuEntity(url);
			
			 if(menu !=null) {
				 menuName = menu.getMenuName();
			 } else {
				 menuName = url;
			 }
			 
			if(Util.isEmpty(head)) {
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
				response.sendRedirect(basePath + "no_permission.jsp?name="+menuName);
				return false;
			}
			
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 300);
			map.put("data", "没有权限，请联系管理员开通["+menuName+"]");
			map.put("msg", "没有权限，请联系管理员开通["+menuName+"]");
			response.setContentType("application/json");
			ContextUtils.respString(response, JSON.toJSONString(map));
			return false;
		}
		return true;
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	
	public static void main(String[] args) {
		String url = "/syslog/getSyslogList.do";
		 if(url .matches("^/{1,20}.*$")){
           	 url = url.replaceAll("^/{1,20}", "");
          }
		
		 System.out.println(url);
	}
}
