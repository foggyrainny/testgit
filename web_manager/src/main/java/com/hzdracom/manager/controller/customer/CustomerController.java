 package com.hzdracom.manager.controller.customer; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzdracom.manager.annotation.AuthenPassport;
import com.hzdracom.manager.annotation.Permission;
import com.hzdracom.manager.controller.BaseController;

/** 
 * @Title: CustomerController.java
 * @Package com.hzdracom.manager.controller.customer 
 * @Description: TODO(添加描述) 
 * @author 刘章 
 * @date 2017年10月27日 上午9:22:23 
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController
{
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		return new ModelAndView("/customer/customerList");
	}
}

