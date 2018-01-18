package cn.goodjobs.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.goodjobs.cms.entity.Users;
import cn.goodjobs.cms.service.UsersService;
import raky.ssh.controller.CoreController;


@Controller
public class UsersController extends CoreController {
	private final static Logger logger = Logger.getLogger(UsersController.class);
	
	@Autowired
	private UsersService usersService;

	/**
	 * 查询
	 * @param request
	 * @param users  @ModelAttribute 封装页面对象数据
	 * @param model  ModelMap 向页面推送数据
	 * @return
	 */
	@RequestMapping(value="/queryUsers")//请求url
    public String query(HttpServletRequest request,	@ModelAttribute("users") Users users,ModelMap model){
		logger.info("--------------------------query----------------------------");
    	model.addAttribute("usersList", usersService.getList(users)); 
        return "list";
    }
    
	/**
	 * 进入添加页面input.jsp
	 * @return
	 */
    @RequestMapping(value="/addUsers",method=RequestMethod.GET)
    public String add(){
    	return "input";
    }
    
    /**
     * 进入编译页面input.jsp
     * @param request
     * @param id    @RequestParam  封装客户url请求参数  如，editUsers?id=5
     * @param model
     * @return
     */
    @RequestMapping(value="/editUsers",method=RequestMethod.GET)
    public String edit1(HttpServletRequest request,@RequestParam Long id,ModelMap model){
    	model.addAttribute("users", usersService.getOne(id));    	
    	return "input";
    }

    /**
     * 保存数据到数据库，id为空，为添加，id不为空，为修改
     * @param request
     * @param users
     * @param model
     * @return
     */
	@RequestMapping(value="/saveUsers",method=RequestMethod.POST)
    public String save(HttpServletRequest request,	@ModelAttribute("users") Users users,ModelMap model){
	
		if(users.getId() == null){
			usersService.create(users);
		}else{
			usersService.modify(users);
		}
		
		return "redirect:queryUsers.action";//重定向
    }
    
	/**
	 * 删除数据
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
    @RequestMapping(value="/deleteUsers",method=RequestMethod.GET)
    public String delete(HttpServletRequest request,@RequestParam Long id,ModelMap model){
    	Users users = new Users();
    	users.setId(id);
    	usersService.remove(users);
    	return "redirect:queryUsers.action";
    }
}
