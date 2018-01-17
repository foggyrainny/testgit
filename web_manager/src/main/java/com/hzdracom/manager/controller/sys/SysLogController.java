package com.hzdracom.manager.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzdracom.manager.annotation.AuthenPassport;
import com.hzdracom.manager.annotation.Permission;
import com.hzdracom.manager.bean.DataAuthority;
import com.hzdracom.manager.bean.form.SyslogForm;
import com.hzdracom.manager.bean.sys.PageMsg;
import com.hzdracom.manager.bean.sys.SysLog;
import com.hzdracom.manager.controller.BaseController;
import com.hzdracom.manager.service.sys.ISysLogService;
import com.hzdracom.manager.util.SessionUtils;

@Controller
@RequestMapping(value = "/syslog")
public class SysLogController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(SysLogController.class);

	@Resource
	private ISysLogService sysLogService;
	
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		return new ModelAndView("/sys/syslogList");
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/getSyslogList", method = RequestMethod.POST)
	public void getSyslogList(HttpServletRequest request, HttpServletResponse response, SyslogForm form){
		try {
			DataAuthority dataAuthority =  SessionUtils.getCurrDataAuthority(request);
		//	dataAuthority.setSupperAdmin(false);
			form.setDataAuth(dataAuthority);
			
			PageMsg<SysLog> list = sysLogService.getSyslogList(form);
			respSuccessMsg(response, list, "查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*

	*//**
	 * @return
	 *//*
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchLog(HttpServletRequest request,SysLog form,String startTime,String endTime) {
		ModelAndView mv = new ModelAndView("/syslog/search");
		try{
		 Integer total = sysLogService.qrySysLogCount(form, startTime, endTime);
		 mv.addObject("total",total);
		} catch(Exception e) {
			log.error("系统日志查询异常",e);
		}
		return mv;
	}
	*//**
	 * @return
	 *//*
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchLog2(HttpServletRequest request,SysLog form,String startTime,String endTime) {
		ModelAndView mv = new ModelAndView("/syslog/search");
		try{
		  Integer count = sysLogService.qrySysLogCount(form, startTime, endTime);
		 mv.addObject("total",count);
		 mv.addObject("form",form);
		 mv.addObject("startTime",startTime);
		 mv.addObject("endTime", endTime);
		} catch(Exception e) {
			log.error("系统日志查询异常",e);
		}
		return mv;
	}

	*//**
	 * @return
	 *//*
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ModelAndView logList(HttpServletRequest request,SysLog form,int curr,int size,String startTime,String endTime) {
		ModelAndView mv = new ModelAndView("/syslog/list");
		try{
		   PageMsg<SysLog> pageObj = sysLogService.qrySysLogPageMsg(form, startTime, endTime, curr, size);
		 mv.addObject("pageObj",pageObj);
		} catch(Exception e) {
			log.error("系統日志查询异常",e);
		}
		return mv;
	}
	

	@RequestMapping(value = "/list/count")
	public void count(HttpServletRequest request,HttpServletResponse response,SysLog form,String startTime,String endTime) {
		try{
			 Integer total = sysLogService.qrySysLogCount(form, startTime, endTime);
			respSuccessMsg(response, total, "");
		} catch(Exception e) {
			log.error("系統日志查询异常",e);
			respErrorMsg(response, "查询失败");
		}
		
	}*/
}
