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

import timmy.train.entity.SysOperLog;
import timmy.train.service.SysOperLogService;
import timmy.train.util.Pager;
@Controller
@RequestMapping(value="/SysOperLog")
public class SysOperLogController extends CoreController{
	
	private static final Logger logger=Logger.getLogger(SysOperLogController.class);
	
	private Pager pager=new Pager();
	@Autowired
	private SysOperLogService sysOperLogService;
	
	@RequestMapping(value="/listSysOperLog")
	public String list(HttpServletRequest request,	@ModelAttribute("sysOperLog") SysOperLog sysOperLog,ModelMap model) throws Exception{
	    	
			int recordCount = sysOperLogService.getCount(sysOperLog);	
			String requestPager=request.getParameter("requestPage");
			logger.info("Action¼ÇÂ¼×ÜÊý"+recordCount);
		
			pager.init(recordCount, pager.getPageSize(), requestPager);
			
			sysOperLog.getMap().put("first", pager.getFirstRow().toString());
			sysOperLog.getMap().put("count", pager.getRowCount().toString());

			List<SysOperLog> sysOperLogList = sysOperLogService.getPaginatedList(sysOperLog, pager.getFirstRow(), pager.getRowCount());
			
			request.setAttribute("pager", pager);
			request.setAttribute("sysOperLog", sysOperLog);
			request.setAttribute("sysOperLogList", sysOperLogList); 
			
	        return "SysOperLog/list";
	    }
	@RequestMapping(value="/addSysOperLog")
	public String  add(){
		
		return "SysOperLog/input";
	}
	
	 @RequestMapping(value="/updateSysOperLog")
	 public ModelAndView edit(HttpServletRequest request,@RequestParam Long id,ModelMap model) throws Exception{
	    	model.addAttribute("sysOperLog", sysOperLogService.getOne(id)); 
	    	logger.info("-------------------------------pdinfo="+sysOperLogService.getOne(id));
	    	return new ModelAndView("SysOperLog/input",model);

	    }
	
	@RequestMapping(value="/deleteSysOperLog")
	public String delete(HttpServletRequest request,@RequestParam Long id, ModelMap model) throws Exception{
		
		sysOperLogService.remove(id);
		
		return "redirect:/SysOperLog/listSysOperLog";
	}
	@RequestMapping(value="/saveSysOperLog")
	public String update(HttpServletRequest request,@ModelAttribute("sysOperLog") SysOperLog sysOperLog ,ModelMap moddel)throws Exception{
		if(sysOperLog.getId()!=null){
		
				 sysOperLogService.modify(sysOperLog);
			}else{
			     sysOperLogService.create(sysOperLog);
			}
		
		return "redirect:/SysOperLog/listSysOperLog";
	}
	
}
