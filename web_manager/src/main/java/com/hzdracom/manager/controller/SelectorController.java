/**
 * 
 */
package com.hzdracom.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzdracom.manager.bean.DataAuthority;
import com.hzdracom.manager.bean.Selector;
import com.hzdracom.manager.bean.sys.Area;
import com.hzdracom.manager.dao.sys.AreaDao;
import com.hzdracom.manager.service.sys.IOfficeService;
import com.hzdracom.manager.util.SessionUtils;

/** 
 *  Title: com.hzdracom.manager.controller
 *  Description: 下拉框
 *  Company: 杭州龙骞科技有限公司 
 *  @author  panke
 *  @date 2017年3月21日 
 */
@Controller
@RequestMapping("selector")
public class SelectorController extends BaseController{

	@Autowired
	private AreaDao  areaDao;
	
	@Autowired
	private IOfficeService officeService;
	
	
	/**
	 *  机构多选下拉框
	 *  @param type  all:全部结构  role: 用户所有用的机构  默认 user
	 */
	@RequestMapping("office")
	public void office(String type,HttpServletRequest request,HttpServletResponse response ){
		DataAuthority data = SessionUtils.getCurrDataAuthority(request);
		List<Selector> list = officeService.queryOfficeSelector(type, data.getRoleId());
		respSuccessMsg(response, list, "");
	}
	
	
	/**
	 *  天气
	 */
	@RequestMapping("area")
	public void area(HttpServletRequest request,HttpServletResponse response,Area form){
		respSuccessMsg(response, areaDao.queryAreaNames(form), "查询成功");
	}
	
	@RequestMapping("areaTree")
	public void areaTree(HttpServletRequest request,HttpServletResponse response,String type,String isSmu){
		respSuccessMsg(response, areaDao.getAreaTreeDown(), "查询成功");
	}
	
	/**
	 * 
	 * @param type  all:全部结构  role:角色所有用的机构 
	 * @description: 
	 * @date: 2017年3月28日 下午2:40:32
	 */
	@RequestMapping("officeTree")
	public void officeTree(HttpServletRequest request,HttpServletResponse response,String type){
		//DataAuthority dataAuth = SessionUtils.getCurrDataAuthority(request);
		
		Object list = officeService.getOfficeTreeDown(type);
		
		respSuccessMsg(response, list, "查询成功");
	}
		
} 
