package com.hzdracom.manager.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzdracom.manager.annotation.AuthenPassport;
import com.hzdracom.manager.annotation.Permission;
import com.hzdracom.manager.bean.sys.Area;
import com.hzdracom.manager.controller.BaseController;
import com.hzdracom.manager.service.sys.IAreaService;

/**
 * @title: AreaController.java
 * @pacjage: com.hzdracom.manager.controller.sys
 * @description: TODO
 * @author: 高辉
 * @date: 2017年4月10日 上午10:19:30
 */
@Controller
@RequestMapping(value = "/area")
public class AreaController extends BaseController {
	@Autowired
	private IAreaService areaService;
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		return new ModelAndView("/sys/areaList");
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/getAreaList", method = RequestMethod.POST)
	public void getAreaList(HttpServletRequest request, HttpServletResponse response, Area area){
		try {
			/*DataAuthority dataAuthority =  SessionUtils.getCurrDataAuthority(request);
			dataAuthority.setSupperAdmin(false);
			user.setDataAuth(dataAuthority);*/
			
			List<Area> list = areaService.getAreaList(area);
			respSuccessMsg(response, list, "查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
	}
	
}
