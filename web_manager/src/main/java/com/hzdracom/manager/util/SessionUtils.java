package com.hzdracom.manager.util;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hzdracom.manager.bean.DataAuthority;
import com.hzdracom.manager.bean.sys.Menu;
import com.hzdracom.manager.bean.sys.User;
import com.hzdracom.manager.constant.ContextConstant;

public class SessionUtils {
	/**
	 * 设置session的值
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setAttr(HttpServletRequest request, String key, Object value) {
		request.getSession(true).setAttribute(key, value);
	}

	/**
	 * 获取session的值
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	public static Object getAttr(HttpServletRequest request, String key) {
		return request.getSession(true).getAttribute(key);
	}

	/**
	 * 删除Session值
	 * 
	 * @param request
	 * @param key
	 */
	public static void removeAttr(HttpServletRequest request, String key) {
		request.getSession(true).removeAttribute(key);
	}

	/**
	 * 获得当前登录人员的凭证信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentUserId(HttpServletRequest request) throws Exception {
		Object obj = getAttr(request, ContextConstant.IDEN_CERT_KEY);
		if (null == obj) {
			return null;
		}
		return obj.toString();
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Menu> getCurrentUserMenus(HttpServletRequest request) throws Exception {
		Object obj = getAttr(request, ContextConstant.CURR_USER_MENU_MAP);
		if (null == obj) {
			return Collections.emptyMap();
		}
		return (Map<String,Menu>)obj;
	}
	
	
	
	public static User getCurrentUser(HttpServletRequest request) throws Exception {
		Object user = getAttr(request, ContextConstant.CURR_USER_INFO);
		if (null == user) {
			return null;
		}
		return (User)user;
	}
	
	
	public static DataAuthority getCurrDataAuthority(HttpServletRequest request) {
		
	//	return new DataAuthority(true);
		
	//	return new DataAuthority(1,1L, new Integer[]{1,2,3,4,5,6,7},1);
		Object obj = getAttr(request, ContextConstant.CURR_USER_DATA_AUTHORITY);
		if (null == obj) {
			return null;
		}
		if(obj instanceof DataAuthority){
			return  (DataAuthority)obj;
		}
		return new DataAuthority(false);
	}
	
}
