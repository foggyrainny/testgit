package com.hzdracom.manager.controller.sys;

import java.util.List;

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
import com.hzdracom.manager.bean.sys.Menu;
import com.hzdracom.manager.controller.BaseController;
import com.hzdracom.manager.service.sys.IMenuService;

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(MenuController.class);

	@Resource
	private IMenuService menuService;
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		return new ModelAndView("/sys/menuList");
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/getMenuList", method = RequestMethod.POST)
	public void getMenuList(HttpServletRequest request ,HttpServletResponse response,
			@RequestParam(required = true) Integer parentId) throws Exception{
			Menu menu = new Menu();
			menu.setParentId(parentId);
			List<Menu> list = menuService.qryMenuList(menu);
			respSuccessMsg(response, list, "1000");
	}
	
	@RequestMapping(value = "/getMenuAll", method = RequestMethod.POST)
	public void getMenuAll(HttpServletRequest request ,HttpServletResponse response) throws Exception{
			List<Menu> list = menuService.qryMenuAll();
			respSuccessMsg(response, list, "1000");
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/getMenu", method = RequestMethod.POST)
	public void getMenu(HttpServletRequest request ,HttpServletResponse response,
			@RequestParam(required = true) Integer Id) throws Exception{
			Menu menu = new Menu();
			menu.setId(Id);
			Menu list = menuService.qryMenu(menu);
			respSuccessMsg(response, list, "");
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addPage(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("sys/menu_add");
	}
	
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void doAdd(HttpServletRequest request, HttpServletResponse response,Menu menu) throws Exception{
		boolean b = menuService.addMenu(menu);
		if (b) {
			respSuccessMsg(response, null, "更新成功");
		} else {
			respSuccessMsg(response, null, "更新失败，可能存在相同菜单");
		}
	}
	
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updatePage(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("sys/menu_update");
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void doUpdate(HttpServletRequest request, HttpServletResponse response,Menu menu) throws Exception{
		boolean b = menuService.updateMenu(menu);
		if (b) {
			respSuccessMsg(response, null, "更新成功");
		} else {
			respSuccessMsg(response, null, "更新失败，可能存在相同菜单");
		}
	}
	



	/**
	 * 删除单个菜单
	 * @param request
	 * @param response
	 * @param id
	 */
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer id) {
		try {
			if (menuService.delMenu(id)) {
				respSuccessMsg(response, null, "删除成功");
			} else {
				respErrorMsg(response, "删除失败，需先删除下级菜单");
			}
		} catch (Exception e) {
			log.error("删除单个菜单,异常", e);
			e.printStackTrace();
			respErrorMsg(response, "删除失败，系统有异常，请联系管理员");
		}
	}

}
