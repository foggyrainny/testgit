package timmy.train.controller;

import java.math.BigInteger;
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
import timmy.train.entity.PdInfo;
import timmy.train.entity.PdModel;
import timmy.train.entity.PdType;
import timmy.train.service.EntpMainService;
import timmy.train.service.PdInfoService;
import timmy.train.service.PdModelService;
import timmy.train.service.PdTypeService;
import timmy.train.util.Pager;
@Controller
@RequestMapping(value="/PdInfo")
public class PdInfoController extends CoreController{
	
	private static final Logger logger=Logger.getLogger(PdInfoController.class);
	
	@Autowired
	private EntpMainService entpMainService;
	
	@Autowired
	private PdInfoService pdInfoService;
	
	@Autowired
	private PdModelService pdModelService;
	
	@Autowired
	private PdTypeService pdTypeService;
	
	@RequestMapping(value="/listPdInfo")
	public String list(HttpServletRequest request,	@ModelAttribute("pdInfo") PdInfo pdInfo,@ModelAttribute("pager") Pager pager,ModelMap model) throws Exception{
	    	
			int recordCount = pdInfoService.getCount(pdInfo);	
			String requestPage=request.getParameter("requestPage");
			logger.info("Action¼ÇÂ¼×ÜÊý"+recordCount);
		
			pager.init(recordCount, pager.getPageSize(), requestPage);
			
			pdInfo.getMap().put("first", pager.getFirstRow().toString());
			pdInfo.getMap().put("count", pager.getRowCount().toString());

			List<PdInfo> pdInfoList = pdInfoService.getPaginatedList(pdInfo, pager.getFirstRow(), pager.getRowCount());
			
			request.setAttribute("pager", pager);
			request.setAttribute("pdInfo", pdInfo);
			request.setAttribute("pdInfoList", pdInfoList); 
			
	        return "PdInfo/list";
	    }
	@RequestMapping(value="/addPdInfo")
	public String  add(HttpServletRequest request,@ModelAttribute("entpMain") EntpMain entpMain,	@ModelAttribute("pdModel") PdModel pdModel,@ModelAttribute("pdType") PdType pdType,ModelMap model) throws Exception{
		request.setAttribute("entpMainList", entpMainService.getList(entpMain)); 
		request.setAttribute("pdModelList", pdModelService.getList(pdModel)); 
		request.setAttribute("pdTypeList", pdTypeService.getList(pdType)); 
		return "PdInfo/input";
	}
	
	 @RequestMapping(value="/updatePdInfo")
	 public ModelAndView edit(HttpServletRequest request,@ModelAttribute("entpMain") EntpMain entpMain,@RequestParam BigInteger pd_id,@ModelAttribute("pdModel") PdModel pdModel,@ModelAttribute("pdType") PdType pdType,ModelMap model) throws Exception{
		 	request.setAttribute("entpMainList", entpMainService.getList(entpMain)); 
		 	request.setAttribute("pdModelList", pdModelService.getList(pdModel)); 
			request.setAttribute("pdTypeList", pdTypeService.getList(pdType));
		 	model.addAttribute("pdInfo", pdInfoService.getOne(pd_id)); 
	    	logger.info("-------------------------------pdinfo="+pdInfoService.getOne(pd_id));
	    	return new ModelAndView("PdInfo/input",model);

	    }
	
	@RequestMapping(value="/deletePdInfo")
	public String delete(HttpServletRequest request,@RequestParam BigInteger pd_id, ModelMap model) throws Exception{
		
		pdInfoService.remove(pd_id);
		
		return "redirect:/PdInfo/listPdInfo";
	}
	@RequestMapping(value="/savePdInfo")
	public String update(HttpServletRequest request,@ModelAttribute("pdInfo") PdInfo pdInfo ,ModelMap moddel)throws Exception{
		if(pdInfo.getPd_id()!=null){
		
				 pdInfoService.modify(pdInfo);
			}else{
			     pdInfoService.create(pdInfo);
			}
		
		return "redirect:/PdInfo/listPdInfo";
	}
	
}
