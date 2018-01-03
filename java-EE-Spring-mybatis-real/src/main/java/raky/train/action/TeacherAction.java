package raky.train.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import freemarker.log.Logger;
import raky.train.domain.Teacher;
import raky.train.service.TeacherService;
import raky.train.util.Pager;

public class TeacherAction extends ActionSupport {

	private static final long serialVersionUID = 8120148364324851L;
	private static final Logger logger = Logger.getLogger(TeacherAction.class.getName());
	
	@Autowired
	private TeacherService teacherService ;
	
	
	private Pager pager = new Pager();
	
	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	

	private Teacher teacher = new Teacher();
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public String list()throws Exception{	
		int recordCount = teacherService.getCount(teacher);	
		
		logger.info("Action¼ÇÂ¼×ÜÊý"+recordCount);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());

		teacher.getMap().put("first", pager.getFirstRow().toString());
		teacher.getMap().put("count", pager.getRowCount().toString());
		
		ActionContext.getContext().put("teacherList", teacherService.getPaginatedList(teacher, pager.getFirstRow(), pager.getRowCount()));
		ServletActionContext.getRequest().setAttribute("requestPage", pager.getRequestPage());
		return "list";
	}

	public String add()throws Exception{
		logger.info("=============add==============");
		return "input";
	}
	
	public String input()throws Exception{
		logger.info("=============input(add/edit)==============");
		if(null != this.getTeacher().getId()){
			ServletActionContext.getRequest().setAttribute("teacher", teacherService.getOne(teacher.getId()));	
		}
		return "input";
	}
	
	public String edit()throws Exception{
		logger.info("=============edit==============");
		ServletActionContext.getRequest().setAttribute("teacher", teacherService.getOne(teacher.getId()));
		return "input";
	}	
	
	public String save()throws Exception{
		logger.info("=============save==============");
		if(teacher.getId() != null){ // update
			teacherService.modify(teacher);
		} else { //add			
			teacher.setHiredate(new Date());
			teacherService.create(teacher);
		}
		return "save";
	}

	public String delete()throws Exception{
		logger.info("=============delete==============");
		teacherService.remove(teacher.getId());
		return "save";
	}

}
