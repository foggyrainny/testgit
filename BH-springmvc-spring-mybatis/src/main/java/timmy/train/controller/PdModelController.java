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

import timmy.train.entity.PdModel;
import timmy.train.service.PdModelService;
import timmy.train.util.Pager;
@Controller
@RequestMapping(value="/PdModel")
public class PdModelController extends CoreController{
	
	private static final Logger logger=Logger.getLogger(PdModelController.class);
	
	private Pager pager=new Pager();
	@Autowired
	private PdModelService pdModelService;
	
	@RequestMapping(value="/listPdModel")
	public String list(HttpServletRequest request,	@ModelAttribute("pdModel") PdModel pdModel,ModelMap model) throws Exception{
	    	
			int recordCount = pdModelService.getCount(pdModel);	
			String requestPager=request.getParameter("requestPage");
			logger.info("Action¼ÇÂ¼×ÜÊý"+recordCount);
		
			pager.init(recordCount, pager.getPageSize(), requestPager);
			
			pdModel.getMap().put("first", pager.getFirstRow().toString());
			pdModel.getMap().put("count", pager.getRowCount().toString());

			List<PdModel> pdModelList = pdModelService.getPaginatedList(pdModel, pager.getFirstRow(), pager.getRowCount());
			
			request.setAttribute("pager", pager);
			request.setAttribute("pdModel", pdModel);
			request.setAttribute("pdModelList", pdModelList); 
			
	        return "PdModel/list";
	    }
	@RequestMapping(value="/list2PdModel")
	public String  list2(HttpServletRequest request) throws NumberFormatException, Exception{
		String model_id=request.getParameter("model_id");
		request.setAttribute("pdModel", pdModelService.getOne(Long.valueOf(model_id)));
		return "PdModel/list2";
	}
	
	@RequestMapping(value="/addPdModel")
	public String  add(){
		
		
		return "PdModel/input";
	}
	
	 @RequestMapping(value="/updatePdModel")
	 public ModelAndView edit(HttpServletRequest request,@RequestParam Long model_id,ModelMap model) throws Exception{
	    	model.addAttribute("pdModel", pdModelService.getOne(model_id)); 
	    	logger.info("-------------------------------pdinfo="+pdModelService.getOne(model_id));
	    	return new ModelAndView("PdModel/input",model);

	    }
	
	@RequestMapping(value="/deletePdModel")
	public String delete(HttpServletRequest request,@RequestParam Long model_id, ModelMap model) throws Exception{
		
		pdModelService.remove(model_id);
		
		return "redirect:/PdModel/listPdModel";
	}
	@RequestMapping(value="/savePdModel")
	public String update(HttpServletRequest request,@ModelAttribute("pdModel") PdModel pdModel ,ModelMap moddel)throws Exception{
		if(pdModel.getModel_id()!=null){
		
				 pdModelService.modify(pdModel);
			}else{
				pdModel.setAdd_date(new Date());
			     pdModelService.create(pdModel);
			}
		
		return "redirect:/PdModel/listPdModel";
	}
	
}
