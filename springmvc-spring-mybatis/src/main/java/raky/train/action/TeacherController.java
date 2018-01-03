package raky.train.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import freemarker.log.Logger;
import raky.train.domain.Teacher;
import raky.train.service.TeacherService;
import raky.train.util.Pager;
@Controller
public class TeacherController  {

	private static final Logger logger = Logger.getLogger(TeacherController.class.getName());
	
	
	@InitBinder  
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }  
	
	@Resource
	private TeacherService teacherService ;
	
	private Pager pager =new Pager();	
	
	@RequestMapping("/listTeacher")
	public String list(HttpServletRequest request,	@ModelAttribute("teacher") Teacher teacher,ModelMap model)throws Exception{	
		
		
		int recordCount = teacherService.getCount(teacher);	
		
		logger.info("Action¼ÇÂ¼×ÜÊý"+recordCount);
	
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		
		teacher.getMap().put("first", pager.getFirstRow().toString());
		teacher.getMap().put("count", pager.getRowCount().toString());
		
		List<Teacher> teacherList = teacherService.getPaginatedList(teacher, pager.getFirstRow(), pager.getRowCount());
		model.addAttribute("teacherList", teacherList);
		model.addAttribute("pager", pager);
		model.addAttribute("teacher", teacher);
		model.addAttribute("requestPage", pager.getRequestPage());
		
		return "list";
	}
	
	@RequestMapping("/addTeacher")
	public String add()throws Exception{
		logger.info("=============add==============");
		return "input";
	}
	
	@RequestMapping("/inputTeacher")
	public String input(HttpServletRequest request,@RequestParam Integer id,ModelMap model)throws Exception{
		logger.info("=============input(add/edit)==============");
		if(null != id){
			model.addAttribute("teacher", teacherService.getOne(id));	
		}
		return "input";
	}
	
	@RequestMapping("/editTeacher")
	public String edit(HttpServletRequest request,@RequestParam Integer id,ModelMap model)throws Exception{
		logger.info("=============edit==============");
		model.addAttribute("teacher", teacherService.getOne(id));
		return "input";
	}	
	@RequestMapping("/saveTeacher")
	public String save(HttpServletRequest request,	@ModelAttribute("teacher") Teacher teacher,ModelMap model)throws Exception{
		logger.info("=============save==============");
		if(teacher.getId() != null){ // update
			teacherService.modify(teacher);
		} else { //add			
			teacher.setHiredate(new Date());
			teacherService.create(teacher);
		}
		return "redirect:listTeacher";
	}
	
	@RequestMapping("/deleteTeacher")
	public String delete(HttpServletRequest request,@RequestParam Integer id,ModelMap model)throws Exception{
		logger.info("=============delete==============");
		teacherService.remove(id);
		return "redirect:listTeacher";
	}

}
