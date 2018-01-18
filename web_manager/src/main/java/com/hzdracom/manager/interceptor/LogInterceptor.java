package com.hzdracom.manager.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hzdracom.core.util.Util;
import com.hzdracom.manager.annotation.AuthenPassport;
import com.hzdracom.manager.bean.Option;
import com.hzdracom.manager.bean.sys.Menu;
import com.hzdracom.manager.bean.sys.SysLog;
import com.hzdracom.manager.bean.sys.User;
import com.hzdracom.manager.service.sys.IMenuService;
import com.hzdracom.manager.service.sys.ISysLogService;
import com.hzdracom.manager.util.SessionUtils;
import com.hzdracom.manager.util.StringUtil;


/**
 *  
 * 
 * @author panke
 */
public class LogInterceptor implements HandlerInterceptor {

	private static Logger log = LoggerFactory.getLogger(LogInterceptor.class);
	@Resource
	private IMenuService menuService;
	@Resource
	private ISysLogService sysLogService;
	
	
	private static  final String NO_LOG_REG = "(.*syslog/.*\\.do.*)" + "|(welcome\\.do.*)" + "|(index\\.*)";
	
	//private static final  Map<String, String> OPTION_MAP = new HashMap<String, String>();
	
	private static final  String LOGIN_URL = "login.do";
	
	/*static{
		OPTION_MAP.put("/login.html", "");
	}*/
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		 try{  
		      if (handler instanceof HandlerMethod) {  
		      //   HandlerMethod methodHandler = (HandlerMethod) handler;  
		        
		         String url = request.getRequestURI().replace(request.getContextPath(), "");
		         
		         if(url .matches("^/{1,20}.*$")){
		           	 url = url.replaceAll("^/{1,20}", "");
		          }
		         
		         if(url.matches(NO_LOG_REG)){
			        	return ; 
			      }
		        
		     	HandlerMethod method = (HandlerMethod) handler;
		        Option op = Option.QUERY;
		     	AuthenPassport authPassport = method.getMethodAnnotation(AuthenPassport.class);
				if (authPassport != null) {
					op = authPassport.op() == null ? op :  authPassport.op();
				}
		         User user = SessionUtils.getCurrentUser(request);
		         SysLog log = new SysLog();
		         if(user !=null) {
		        	 log.setAccount(user.getLoginName());
		        	 log.setName(user.getUserName());
		         }
		        log.setOpType(op.getValue());
		         List<String> requestParams = new ArrayList<String>();
		         
		         for( Entry<String, String[]> entity  :  request.getParameterMap().entrySet()){
		        	if(entity == null){
		        		continue;
		        	}
		        	if(entity.getValue() == null || entity.getValue().length < 1) {
		        		requestParams.add(entity.getKey()+":");
		        	
		        	}  else if(entity.getValue().length == 1){
		        		requestParams.add(entity.getKey()+": "+entity.getValue()[0]+"");
		        	} else {
		        		requestParams.add(entity.getKey()+": "+Arrays.toString(entity.getValue()));
		        	}
		         }
		         
		         
		         if(url.matches(LOGIN_URL) && request.getMethod().equalsIgnoreCase("post")) {
		        	 Iterator<String> ite = requestParams.iterator();
		        	 for (;ite.hasNext();) {
		        		 String temp = ite.next();
		        		 if((temp+"").startsWith("pwd:")){
		        			 ite.remove();
		        		 }
					}
		        	 requestParams.add("pwd:******");
		        	 if(user != null){
		        		 log.setContent("登录成功|请求参数："+requestParams);
		        	 } else {
		        		 log.setContent("登录失败|请求参数："+requestParams);
		        	 }
		         } else if(request  instanceof MultipartHttpServletRequest) {
		        	 log.setContent("文件上传|请求URL："+url);
		         } else {
		        	 Menu menu = menuService.getMenuEntity(url);
			         if(menu!=null) {
			        	 log.setContent(menu.getMenuName()+"|请求URL："+url+"|请求参数："+requestParams);
			         } else {
			        	 LogInterceptor.log.info(" 日志记录 --->  url不存在与数据库{}",url);
			         }
		         }
		         if(log!=null && Util.isNotEmpty(log.getContent())){
		        	log.setIp(Util.getIpAddr(request));
		        	 sysLogService.addSysLog(log);
		         }
		      }
		 }catch(Exception e) {
			 e.printStackTrace();
		 } 
		 finally{  
		 }  
		
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("/syslog/getSyslogList.do".matches("(.*syslog/.*\\.do.*)"));
		System.out.println(NO_LOG_REG);
		
		String url = "/syslog/getSyslogList.do";
		if(url .matches("^/{1,20}.*$")){
			url = url.replaceAll("^/{1,20}", "");
	     }
			
		System.out.println(url);
		//String url = "////statuis";
		if(url .matches("^/{1,20}.*$")){
       	 url = url.replaceAll("^/{1,20}", "");
        }
		
		System.out.println(url);
        
		List<String> list = new ArrayList<String>();
		
		list.add("pwd:12345");
		list.add("123456");
		
		
		 Iterator<String> ite = list.iterator();
    	 
    	 for (;ite.hasNext();) {
    		 String temp = ite.next();
    		 if(temp.startsWith("pwd:")){
    			//list.remove(temp);
    			 ite.remove();
    		 }
    		 System.out.println(temp);
		}
    	 
    	 ite = list.iterator();
    	 for (;ite.hasNext();) {
    		 String temp = ite.next();
    		 System.out.println(temp);
		}
		
		
	}

}