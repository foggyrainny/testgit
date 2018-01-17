/**
 * 
 */
package com.hzdracom.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 *  Title: com.websocket
 *  Description: 
 *  Company: 杭州龙骞科技有限公司 
 *  @author  panke
 *  @date 2017年3月8日 
 */
@Controller
@RequestMapping("/")
public class IndexController {

	
	@RequestMapping(value="index",produces="application/json;charset=utf8")
	public void index(HttpServletRequest request,HttpServletResponse response){
		
		
	}
}
