/**
 * 
 */
package com.hzdracom.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 
 *  Title: com.hzdracom.manager.controller
 *  Description:  服务器端口检测
 *  Company: 杭州龙骞科技有限公司 
 *  @author  panke
 *  @date 2017年5月16日 
 */
@Controller
@RequestMapping("servertest")
public class ServerTestController extends BaseController{

	@RequestMapping("index")
	public  ModelAndView   index(){
		return new ModelAndView("servertest/server_test_index");
	}
	

	@RequestMapping("create")
	public  ModelAndView   create(){
		return new ModelAndView("servertest/server_test_new");
	}
	
	
	
	@RequestMapping("logIndex")
	public  ModelAndView   logIndex(){
		return new ModelAndView("servertest/server_test_log");
	}
	
}
