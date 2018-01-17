package com.hzdracom.manager.controller.sys;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hzdracom.manager.annotation.AuthenPassport;
import com.hzdracom.manager.annotation.Permission;
import com.hzdracom.manager.bean.DataAuthority;
import com.hzdracom.manager.bean.form.UserForm;
import com.hzdracom.manager.bean.form.UserSettiingForm;
import com.hzdracom.manager.bean.sys.PageMsg;
import com.hzdracom.manager.bean.sys.User;
import com.hzdracom.manager.controller.BaseController;
import com.hzdracom.manager.service.sys.IRoleService;
import com.hzdracom.manager.service.sys.IUserService;
import com.hzdracom.manager.util.DateUtil;
import com.hzdracom.manager.util.MD5Util;
import com.hzdracom.manager.util.SessionUtils;
import com.hzdracom.manager.util.Util;
import com.hzdracom.manager.util.excel.ExcelUtils;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Resource
	private IUserService userService;
	
	@Autowired
	private IRoleService  roleService;
	
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		return new ModelAndView("/sys/userList");
	}
	
	@RequestMapping(value = "/import", method = RequestMethod.GET)
	public ModelAndView userImport(){
		return new ModelAndView("/sys/userImport");
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	public void userList(HttpServletRequest request, HttpServletResponse response, UserForm user){
		try {
			DataAuthority dataAuthority =  SessionUtils.getCurrDataAuthority(request);
			user.setDataAuth(dataAuthority);
			
			PageMsg<User> list = userService.getUserList(user);
			respSuccessMsg(response, list, "查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
	}
	
	@AuthenPassport
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public void getUser(HttpServletRequest request, HttpServletResponse response, String userId){
		try {
			respSuccessMsg(response, userService.getUser(userId), "查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
	}
	
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public void addUser(HttpServletRequest request, HttpServletResponse response,User user) {
		try {
			user.setCreateBy(SessionUtils.getCurrentUserId(request));
			
			if (userService.addUser(user)) {
				respSuccessMsg(response, null, "添加成功");
			} else {
				respSuccessMsg(response, null, "添加失败，可能存在相同用户");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
	}
	
	//判断登录名重复
	
	@RequestMapping(value="qrylogin")
	public void qrySupplierNo(HttpServletResponse response, HttpServletRequest request) throws Exception{
		String param =request.getParameter("param");
		
		if(org.springframework.web.servlet.support.RequestContextUtils.getLocaleResolver(request) !=null && org.springframework.web.servlet.support.RequestContextUtils.getLocaleResolver(request).resolveLocale(request) !=null) {
			request.setAttribute("language",  org.springframework.web.servlet.support.RequestContextUtils.getLocaleResolver(request).resolveLocale(request).getLanguage());
		} else {
			request.setAttribute("language",  "zh");
		}
		String lan=(String) request.getAttribute("language");
		
		if(lan=="zh"){
			try {
				if (userService.selloginname(param)==0) {      
					respSuccessInfo(response,"登录名可以使用");
				}else{
					respErrorInfo(response, "登录名重复，请重试");
				}
			} catch (Exception e) {
				log.error("查询失败,异常={}", e.getMessage(),e);
				respErrorInfo(response,"查询失败,请稍后重试");
			}
		}else if(lan=="en"){
			try {
				if (userService.selloginname(param)==0) {      
					respSuccessInfo(response,"You can use");
				}else{
					respErrorInfo(response, "Repeat, try again");
				}
			} catch (Exception e) {
				log.error("Query failed, exception={}", e.getMessage(),e);
				respErrorInfo(response,"Query failed, please try again later");
			}
		}

	}
	@AuthenPassport
	@Permission
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response,User user){
		int id = user.getId();
		if (id >= 0 && id < 10) {
			respErrorMsg(response, "超级用户不可更新");
			return;
		}
		try {
			user.setUpdateBy(SessionUtils.getCurrentUserId(request));
			if (userService.update(user)) {
				respSuccessMsg(response, null, "更新成功");
			} else {
				respSuccessMsg(response, null, "更新失败，可能存在相同用户");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
	}
	
	@AuthenPassport
	@RequestMapping(value = "/roleSelect", method = RequestMethod.POST)
	public void roleSelect(HttpServletRequest request, HttpServletResponse response,String officeId){
		try {
			respSuccessMsg(response, userService.roleSelect(officeId), "更新成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
		
	}
	
	@AuthenPassport
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response,String userId){
		int id = Integer.valueOf(userId);
		if (id >= 0 && id < 10) {
			respErrorMsg(response, "超级用户不可更新");
			return;
		}
		try {
			if (userService.delete(userId)) {
				respSuccessMsg(response, null, "删除成功");
			} else {
				respSuccessMsg(response, null, "删除失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respErrorMsg(response, "系统异常");
		}
		
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, UserForm v) throws Exception{
		DataAuthority dataAuthority =  SessionUtils.getCurrDataAuthority(request);
		v.setDataAuth(dataAuthority);
		
		List<User> list = userService.userExecl(v);
		
		String[] titlekey = {"loginName", "userName", "roleName", "name", "parentName","modifyTimeStr"};
		String[] titleName = { "登录名", "姓名", "所属角色", "归属机构", "上级机构","修改时间"};
		Class[] clazz =  { String.class,String.class,String.class,String.class,String.class,String.class};
		Date date = new Date();
		String now = DateUtil.DateToString(date, 6);// 当前时间转化成字符串
		//String nowStr=DateUtil.formatYYYYMMDDHHMMSS(now, 2);
		String fileName = "用户信息_"+now;
		ExcelUtils.createExcel(request, response, fileName, titlekey, titleName, list, null,clazz);
	}
	
	@AuthenPassport
	@RequestMapping(value = "/usersetting", method = RequestMethod.GET)
	public ModelAndView  userSettingPage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", userService.getUser(SessionUtils.getCurrentUserId(request)));
		mv.setViewName("sys/user_setting");
		return mv;
	}
	
	@AuthenPassport
	@RequestMapping(value = "/usersetting", method = RequestMethod.POST)
	public void  userSetting(HttpServletRequest request,HttpServletResponse response,UserSettiingForm form) throws Exception{
		
		User user = SessionUtils.getCurrentUser(request);
		
		if(Util.isNotEmpty(form.getPassword())) {
			user = userService.checkUserLogin(user.getLoginName(), MD5Util.digest(form.getOldPass()));	
			if(user == null) {
				respErrorMsg(response, "旧密码不正确");
				return;
			}
			form.setPassword(MD5Util.digest(form.getPassword()));
		}
		form.setId(user.getId()+"");
		userService.updateUserInfo(form);
		respSuccessMsg(response, "更新成功", "更新成功");
	}
	
}
