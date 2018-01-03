package timmy.train.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import timmy.train.entity.EntpMain;
import timmy.train.entity.EntpUser;
import timmy.train.service.EntpMainService;
import timmy.train.service.EntpUserService;
import timmy.train.util.Pager;
@Controller
@RequestMapping(value="/EntpUser")
public class EntpUserController extends CoreController{
	
	private static final Logger logger=Logger.getLogger(EntpUserController.class);
	
	private Pager pager=new Pager();
	
	@Autowired
	private EntpMainService entpMainService;
	@Autowired
	private EntpUserService entpUserService;
	
	@RequestMapping(value="/listEntpUser")
	public String list(HttpServletRequest request,	@ModelAttribute("entpUser") EntpUser entpUser,ModelMap model) throws Exception{
	    	
			int recordCount = entpUserService.getCount(entpUser);	
			String requestPager=request.getParameter("requestPage");
			logger.info("Action¼ÇÂ¼×ÜÊý"+recordCount);
		
			pager.init(recordCount, pager.getPageSize(), requestPager);
			
			entpUser.getMap().put("first", pager.getFirstRow().toString());
			entpUser.getMap().put("count", pager.getRowCount().toString());

			List<EntpUser> entpUserList = entpUserService.getPaginatedList(entpUser, pager.getFirstRow(), pager.getRowCount());
			
			request.setAttribute("pager", pager);
			request.setAttribute("entpUserList", entpUserList); 
			model.addAttribute("entpMain",new EntpMain());
	        return "EntpUser/list";
	    }
	@RequestMapping(value="/addEntpUser")
	public String  add(HttpServletRequest request,@ModelAttribute("entpMain") EntpMain entpMain) throws Exception{
		request.setAttribute("entpMainList", entpMainService.getList(new EntpMain()));
		return "EntpUser/input";
	}
	
	 @RequestMapping(value="/updateEntpUser")
	 public ModelAndView edit(HttpServletRequest request,@RequestParam Long user_id,@ModelAttribute("entpMain") EntpMain entpMain,ModelMap model) throws Exception{
	    	model.addAttribute("entpUser", entpUserService.getOne(user_id)); 
	    	request.setAttribute("entpMainList", entpMainService.getList(new EntpMain()));
	    	logger.info("-------------------------------pdinfo="+entpUserService.getOne(user_id));
	    	return new ModelAndView("EntpUser/input",model);

	    }
	
	@RequestMapping(value="/deleteEntpUser")
	public String delete(HttpServletRequest request,@RequestParam Long user_id, ModelMap model) throws Exception{
		
		entpUserService.remove(user_id);
		
		return "redirect:/EntpUser/listEntpUser";
	}
	@RequestMapping(value="/saveEntpUser")
	public String update(HttpServletRequest request,@ModelAttribute("entpUser") EntpUser entpUser ,ModelMap moddel)throws Exception{
		if(entpUser.getUser_id()!=null){
		
				 entpUserService.modify(entpUser);
			}else{
			     entpUserService.create(entpUser);
			}
		
		
		return "redirect:/EntpUser/listEntpUser";
	}
	
}
