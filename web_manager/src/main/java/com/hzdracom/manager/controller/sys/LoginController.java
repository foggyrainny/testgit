package com.hzdracom.manager.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.alibaba.fastjson.JSON;
import com.hzdracom.core.util.Util;
import com.hzdracom.manager.annotation.AuthenPassport;
import com.hzdracom.manager.bean.DataAuthority;
import com.hzdracom.manager.bean.Option;
import com.hzdracom.manager.bean.sys.Menu;
import com.hzdracom.manager.bean.sys.User;
import com.hzdracom.manager.constant.ContextConstant;
import com.hzdracom.manager.controller.BaseController;
import com.hzdracom.manager.service.sys.IMenuService;
import com.hzdracom.manager.service.sys.IRoleService;
import com.hzdracom.manager.service.sys.IUserService;
import com.hzdracom.manager.util.SessionUtils;
import com.hzdracom.manager.util.StringUtil;

@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	@Resource
	private IUserService userService;
	@Resource
	private IMenuService menuService;
	@Resource
	private IRoleService roleService;
	
	
	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLogin() {
		ModelAndView mv = new ModelAndView("/login");
		return mv;
	}
	
	/**
	 * 登录动作
	 * @param request
	 * @param response
	 * @param account 帐号
	 * @param pwd 密码
	 */
	@AuthenPassport(validate=false,op=Option.LOGIN)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void postLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String account,
			@RequestParam(required = true) String enPwd) {
		log.info("登录开始，account={},pwd={}", account, enPwd);
		try {
			User user = userService.checkUserLogin(account, enPwd.toLowerCase());
			if (user == null) {
				log.error("登录失败,account={}", account);
				respErrorMsg(response, "登录失败，请检查帐号和密码");
			} else {
				int userId = user.getId();
				log.info("登录成功,account={}", account);
				userService.updateUserLoginIp(Util.getIpAddr(request), userId+"");
				// 写数据到session，并跳转到主页
				SessionUtils.setAttr(request, ContextConstant.IDEN_CERT_KEY, userId);
				SessionUtils.setAttr(request, ContextConstant.CURR_USER_INFO, user);
				respSuccessMsg(response, true, "登录成功");
			}
		} catch (Exception e) {
			log.error("登录异常", e);
			respErrorMsg(response, "登录异常，请联系网站管理员");
		}
	}
	

	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public String exit(HttpServletRequest request, HttpServletResponse response) {
		SessionUtils.removeAttr(request, ContextConstant.IDEN_CERT_KEY);
		SessionUtils.removeAttr(request, "userInfo");
		//respSuccessMsg(response, null, "退出成功");
		return "redirect:/login.do";
	}

	/**
	 * 右侧欢迎页
	 * @return
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView getIndexRight() {
		ModelAndView mv = new ModelAndView("/welcome");
		return mv;
	}

	/**
	 * 登录成功后进入主页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@AuthenPassport(op=Option.QUERY)
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView getIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/index");
		// 获取左侧菜单
		User user = SessionUtils.getCurrentUser(request);
		List<Menu> menus = menuService.getLeftMenu(user.getId(),user.getRoleId());
		Set<String> menuHtmlCode = new HashSet<String>();
		Map<String, Menu> map = new HashMap<String, Menu>();
		String url = null;
		for (Menu menu : menus) {
			url =  menu.getMenuUrl();
			if(!Util.isEmpty(url)){
				 url = url.replaceAll("^/{1,}", "").replaceAll("/{2,}", "/");
			}
			map.put(url, menu);
			if(!StringUtil.isEmpty(menu.getHtmlCode())) {
				menuHtmlCode.add(menu.getHtmlCode());
			}
		}
		SessionUtils.setAttr(request, ContextConstant.CURR_USER_MENU_MAP, map);
		SessionUtils.setAttr(request, ContextConstant.CURR_USER_MENU_HTML_CODE, menuHtmlCode);
		DataAuthority dataAuthority = new DataAuthority();
		if(user.getRoleId() <= 10){
			dataAuthority.setSupperAdmin(true);
		} else {
			dataAuthority.setSupperAdmin(false);
			 List<Integer> officeId = roleService.queryRoleOfficeIds(user.getRoleId());
			if( officeId == null) {
				officeId = new ArrayList<Integer>();
			}
			 dataAuthority.setOfficeId(officeId.toArray(new Integer[]{}));
			 dataAuthority.setRoleLevel(1);
			 dataAuthority.setRoleId(user.getRoleId());
			 dataAuthority.setUserId(new Long(user.getId()));
		}
		SessionUtils.setAttr(request, ContextConstant.CURR_USER_DATA_AUTHORITY, dataAuthority);
		mv.addObject("menus", getMenuTree(menus));
		return mv;
	}
	
	  private static List<Menu>  getMenuTree(List<Menu> menus) {
	    	List<Menu> tree = new ArrayList<Menu>();
	    	Map<String, Menu> temp = new HashMap<String, Menu>();
	    	Menu parentMenu = null;
	    	List<Menu> sumMenuList = null;
	    	
	    	for (Menu menu : menus) {
	    		temp.put(""+menu.getId(), menu);
	    		//System.out.println(JSON.toJSONString(temp));
			}
	    	
	    	for (Menu menu : menus) {
	    		if(!"1".equals(menu.getIsShow())){
	    			continue;
	    		}
				if(menu.getParentId() ==null || menu.getParentId() == 0) {
					tree.add(menu);
				} else {
					parentMenu = temp.get(menu.getParentId()+"");
					if(parentMenu == null){
						continue;
					}
					sumMenuList = parentMenu.getChildMenuList();
					if(sumMenuList == null) {
						sumMenuList = new ArrayList<Menu>();
						parentMenu.setChildMenuList(sumMenuList);
					}
					sumMenuList.add(menu);
				//System.out.println(JSON.toJSONString(tree));
				}
			}
			return tree;
	    	
	    }
	    
	    
	    public static void main(String[] args) {
	    
	    	String url = "//xxx//xxx";
			
	    	url = url.replaceAll("^/{1,}", "").replaceAll("/{1,}", "/");
	    	
	    	System.out.println(url);
		}

}
