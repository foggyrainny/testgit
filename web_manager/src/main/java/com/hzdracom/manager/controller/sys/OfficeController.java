package com.hzdracom.manager.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hzdracom.manager.annotation.AuthenPassport;
import com.hzdracom.manager.annotation.Permission;
import com.hzdracom.manager.bean.sys.Office;
import com.hzdracom.manager.controller.BaseController;
import com.hzdracom.manager.service.sys.IOfficeService;
import com.hzdracom.manager.util.SessionUtils;

/**
 * @title: OfficeController.java
 * @pacjage: com.hzdracom.manager.controller.sys
 * @description: TODO
 * @author: 高辉
 * @date: 2017年3月20日 下午4:00:00
 */
@Controller
@RequestMapping("/office")
public class OfficeController extends BaseController{
	
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(OfficeController.class);
	
	@Resource
	private IOfficeService service;
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		return new ModelAndView("/sys/officeList");
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request ,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("officeId", request.getParameter("officeId"));
		mv.addObject("parentId", request.getParameter("parentId"));
		mv.setViewName("sys/officeUpdate");
		return mv;
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/getOfficeList", method = RequestMethod.POST)
	public void getOfficeList(HttpServletRequest request ,HttpServletResponse response,
			@RequestParam(required = true) Integer parentId){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentId", parentId);
			List<Office> list = service.getOfficeList(map);
			
			//List<Menu> list = menuService.qryMenuList(menu);
			respSuccessMsg(response, list, "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			respErrorMsg(response, "系统异常");
		}
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/doAddOrUpdate", method = RequestMethod.POST)
	public void doAddOrUpdate(HttpServletRequest request, HttpServletResponse response,Office v){
		if (v.getId() <= 1 && v.getId() >= 0) {
			respErrorMsg(response, "顶级机构不可修改");
			return;
		}
		try {
			boolean b = service.doAddOrUpdate(v);
			if (b) {
				respSuccessMsg(response, null, "更新成功");
			} else {
				respErrorMsg(response, "更新失败，可能存在相同机构编码");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			respErrorMsg(response, "系统异常");
		}
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/getOffice", method = RequestMethod.POST)
	public void getOffice(HttpServletRequest request, HttpServletResponse response,int id){
		try {
			respSuccessMsg(response,service.getOffice(id),"查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			respErrorMsg(response, "系统异常");
		}
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response,int id){
		if (id <= 1 && id >= 0) {
			respErrorMsg(response, "顶级机构不可修改");
			return;
		}
		try {
			if (service.delete(id)) {
				respSuccessMsg(response,null,"删除成功");
			} else {
				respErrorMsg(response,"删除失败,需先删除下级机构");
			}
		} catch (Exception e) {
			// TODO: handle exception
			respErrorMsg(response, "系统异常");
		}
		
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/getOfficeTree", method = RequestMethod.POST)
	public void getOfficeTree(HttpServletRequest request, HttpServletResponse response){
		try {
			respSuccessMsg(response,service.getOfficeTree(),"查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			respErrorMsg(response, "系统异常");
		}
	}
	
}
