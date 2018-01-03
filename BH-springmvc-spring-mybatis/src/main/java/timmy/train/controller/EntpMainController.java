package timmy.train.controller;

import java.util.Date;
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
import timmy.train.service.EntpMainService;
import timmy.train.util.Pager;
@Controller
@RequestMapping(value="/EntpMain")
public class EntpMainController extends CoreController{
	
	private static final Logger logger=Logger.getLogger(EntpMainController.class);
	
	private Pager pager=new Pager();
	@Autowired
	private EntpMainService entpMainService;
	
	@RequestMapping(value="/listEntpMain")
	public String list(HttpServletRequest request,	@ModelAttribute("entpMain") EntpMain entpMain,ModelMap model) throws Exception{
	    	
			int recordCount = entpMainService.getCount(entpMain);	
			String requestPager=request.getParameter("requestPage");
			logger.info("Action¼ÇÂ¼×ÜÊý"+recordCount);
		
			pager.init(recordCount, pager.getPageSize(), requestPager);
			
			entpMain.getMap().put("first", pager.getFirstRow().toString());
			entpMain.getMap().put("count", pager.getRowCount().toString());

			List<EntpMain> entpMainList = entpMainService.getPaginatedList(entpMain, pager.getFirstRow(), pager.getRowCount());
			
			request.setAttribute("pager", pager);
			request.setAttribute("entpMain", entpMain);
			request.setAttribute("entpMainList", entpMainList); 
			
	        return "EntpMain/list";
	    }
	
	@RequestMapping(value="/list2EntpMain")
	public String  list2(HttpServletRequest request) throws Exception{
		
		String entp_id=request.getParameter("entp_id");
		EntpMain entpMain=entpMainService.getOne(Long.valueOf(entp_id));
		
		request.setAttribute("entpMain", entpMain);
		logger.info("entpMain=================="+entp_id);
		return "EntpMain/list2";
	}
	
	@RequestMapping(value="/addEntpMain")
	public String  add(){
		
		return "EntpMain/input";
	}
	
	 @RequestMapping(value="/updateEntpMain")
	 public ModelAndView edit(HttpServletRequest request,@RequestParam Long entp_id,ModelMap model) throws Exception{
	    	model.addAttribute("entpMain", entpMainService.getOne(entp_id)); 
	    	logger.info("-------------------------------pdinfo="+entpMainService.getOne(entp_id));
	    	return new ModelAndView("EntpMain/input",model);

	    }
	
	@RequestMapping(value="/deleteEntpMain")
	public String delete(HttpServletRequest request,@RequestParam Long entp_id, ModelMap model) throws Exception{
		
		entpMainService.remove(entp_id);
		
		return "redirect:/EntpMain/listEntpMain";
	}
	@RequestMapping(value="/saveEntpMain")
	public String update(HttpServletRequest request,@ModelAttribute("entpMain") EntpMain entpMain ,ModelMap moddel)throws Exception{
		if(entpMain.getEntp_id()!=null){
		
				 entpMainService.modify(entpMain);
			}else{
				entpMain.setAdd_date(new Date());
			     entpMainService.create(entpMain);
			}
		
		return "redirect:/EntpMain/listEntpMain";
	}
	
}
