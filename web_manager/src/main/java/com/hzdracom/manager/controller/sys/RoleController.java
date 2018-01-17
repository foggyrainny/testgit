package com.hzdracom.manager.controller.sys;

import java.util.HashMap;
import java.util.Map;

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
import com.hzdracom.manager.bean.form.RoleForm;
import com.hzdracom.manager.bean.sys.PageMsg;
import com.hzdracom.manager.bean.sys.Role;
import com.hzdracom.manager.controller.BaseController;
import com.hzdracom.manager.service.sys.IRoleService;
import com.hzdracom.manager.util.SessionUtils;

@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@Resource
	private IRoleService roleService;
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		return new ModelAndView("/sys/roleList");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(String id){
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return new ModelAndView("/sys/roleUpdate", map);
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
	public void getRoleList(HttpServletRequest request, HttpServletResponse response, RoleForm form,int size,int curr){
		try {
			DataAuthority dataAuthority =  SessionUtils.getCurrDataAuthority(request);
			//dataAuthority.setSupperAdmin(false);
			form.setDataAuth(dataAuthority);
			
			PageMsg<Role> list = roleService.getRoleList(form);
			respSuccessMsg(response, list, "查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/getMenuList", method = RequestMethod.POST)
	public void getMenuList(HttpServletRequest request, HttpServletResponse response){
		try {
			DataAuthority dataAuthority =  SessionUtils.getCurrDataAuthority(request);
			
			respSuccessMsg(response, roleService.getMenuList(dataAuthority), "查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/getOfficeList", method = RequestMethod.POST)
	public void getOfficeList(HttpServletRequest request, HttpServletResponse response){
		try {
			DataAuthority dataAuthority =  SessionUtils.getCurrDataAuthority(request);
			
			respSuccessMsg(response, roleService.getOfficeList(dataAuthority), "查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/doAddOrUpdate", method = RequestMethod.POST)
	public void doAddOrUpdate(HttpServletRequest request, HttpServletResponse response, Role role){
		try {
			if (role.getId() >= 0 && role.getId() <= 1) {
				respErrorMsg(response, "超级管理角色，不可修改");
				return;
			}
			role.setCreateBy(SessionUtils.getCurrentUserId(request));
			role.setUpdateBy(SessionUtils.getCurrentUserId(request));
			if (roleService.doAddOrUpdate(role)) {
				respSuccessMsg(response, null, "更新成功");
			} else {
				respErrorMsg(response, "新增失败，可能存在重复角色");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
	}
	
	@AuthenPassport
	@RequestMapping(value = "/getRole", method = RequestMethod.POST)
	public void getRole(HttpServletRequest request, HttpServletResponse response, Role role){
		try {
			if(role.getId() == -1) {
				role.setId(SessionUtils.getCurrentUser(request).getRoleId());
			}
			Role r = roleService.getRole(role.getId());
			respSuccessMsg(response, r, "查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response, String roleId){
		try {
			int id = Integer.valueOf(roleId);
			if (id >= 0 && id <= 1) {
				respErrorMsg(response, "超级管理角色，不可删除");
				return;
			}
			if (roleService.delete(roleId)) {
				respSuccessMsg(response, null, "删除成功");
			} else {
				respSuccessMsg(response, null, "删除失败，此角色正在被使用");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
	}
}
