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

import timmy.train.entity.PdType;
import timmy.train.service.PdTypeService;
import timmy.train.util.Pager;
@Controller
@RequestMapping(value="/PdType")
public class PdTypeController extends CoreController{
	
	private static final Logger logger=Logger.getLogger(PdTypeController.class);
	
	private Pager pager=new Pager();
	@Autowired
	private PdTypeService pdTypeService;
	
	@RequestMapping(value="/listPdType")
	public String list(HttpServletRequest request,	@ModelAttribute("pdType") PdType pdType,ModelMap model) throws Exception{
	    	
			int recordCount = pdTypeService.getCount(pdType);	
			String requestPager=request.getParameter("requestPage");
			logger.info("Action¼ÇÂ¼×ÜÊý"+recordCount);
		
			pager.init(recordCount, pager.getPageSize(), requestPager);
			
			pdType.getMap().put("first", pager.getFirstRow().toString());
			pdType.getMap().put("count", pager.getRowCount().toString());

			List<PdType> pdTypeList = pdTypeService.getPaginatedList(pdType, pager.getFirstRow(), pager.getRowCount());
			
			request.setAttribute("pager", pager);
			request.setAttribute("pdType", pdType);
			request.setAttribute("pdTypeList", pdTypeList); 
			
	        return "PdType/list";
	    }
	@RequestMapping(value="/addPdType")
	public String  add(){
		
		return "PdType/input";
	}
	
	@RequestMapping(value="/list2PdType")
	public String  list2(HttpServletRequest request) throws NumberFormatException, Exception{
		String pd_type=request.getParameter("pd_type");
		request.setAttribute("pdType", pdTypeService.getOne(Long.valueOf(pd_type)));
		return "PdType/list2";
	}
	
	 @RequestMapping(value="/updatePdType")
	 public ModelAndView edit(HttpServletRequest request,@RequestParam Long pd_type,ModelMap model) throws Exception{
	    	model.addAttribute("pdType", pdTypeService.getOne(pd_type)); 
	    	logger.info("-------------------------------pdinfo="+pdTypeService.getOne(pd_type));
	    	return new ModelAndView("PdType/input",model);

	    }
	
	@RequestMapping(value="/deletePdType")
	public String delete(HttpServletRequest request,@RequestParam Long pd_type, ModelMap model) throws Exception{
		
		pdTypeService.remove(pd_type);
		
		return "redirect:/PdType/listPdType";
	}
	@RequestMapping(value="/savePdType")
	public String update(HttpServletRequest request,@ModelAttribute("pdType") PdType pdType ,ModelMap moddel)throws Exception{
		if(pdType.getPd_type()!=null){
		
				 pdTypeService.modify(pdType);
			}else{
				 pdType.setAdd_date(new Date());
			     pdTypeService.create(pdType);
			}
		
		return "redirect:/PdType/listPdType";
	}
	
}
