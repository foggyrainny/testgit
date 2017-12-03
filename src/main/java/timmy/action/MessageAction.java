package timmy.action;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import timmy.daomain.MessageForm;
import timmy.daomain.Users;
import timmy.service.impl.UsersServiceImpl;

public class MessageAction extends DispatchAction{
	private static final Logger logger =Logger.getLogger(Message.class.getName());
	public ActionForward  unspecified(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
	logger.info("====================默认方法=============================");	
	
	String name=request.getParameter("name");
	String sex=request.getParameter("sex");
	String mobile=request.getParameter("mobile");
	ActionForward forward=null;
	MessageForm messageForm=(MessageForm)form;
	messageForm.setMessage("default method");
	
	UsersServiceImpl dao =new UsersServiceImpl();
	Users users=new Users();
	if(name!=null&&name.length()>0){
		users.setName(name);
		}
		if(sex!=null&&sex.length()>0){
			
			users.setSex(Integer.valueOf(sex));
		}
		if(mobile!=null&&mobile.length()>0){
			users.setMobile(mobile);
		}
	try {
		List<Users> usersList =dao.getList(users);
		request.setAttribute("usersList", usersList);
		request.setAttribute("users", users);
		forward=mapping.findForward("list");
	} catch (Exception e) {
		
		e.printStackTrace();
	}
		
	return forward;

	}
	public ActionForward add(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("=============执行add方法===============");
		MessageForm messageForm = (MessageForm)form;
		messageForm.setMessage("add method");
		return mapping.findForward("add");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("=============执行edit方法===============");
		MessageForm messageForm = (MessageForm)form;
		messageForm.setMessage("edit method");
		ActionForward forward=null;
		
		String id=request.getParameter("id");
		UsersServiceImpl dao=new UsersServiceImpl();
		try {
		
			Users users=dao.getUsers(Long.valueOf(id));
			logger.info("----edit-----");
			logger.info(users.getName());
			request.setAttribute("users", users);
			forward=mapping.findForward("edit");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return forward;
	}
		
	
	public ActionForward save(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("=============执行save方法===============");
		MessageForm messageForm = (MessageForm)form;
		messageForm.setMessage("save method");
ActionForward forward=null;
		
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String password =request.getParameter("password");
		String sex=request.getParameter("sex");
		String mobile=request.getParameter("mobile");
		String meto=request.getParameter("meto");
		String address=request.getParameter("address");
		
		UsersServiceImpl dao=new UsersServiceImpl();
		if(id==null || id.length()==0){
		Users users=new Users();
		users.setName(name);
		users.setPassword(password);
		users.setAddress(address);
	    users.setSex(Integer.valueOf(sex));//转换有风险
	    users.setAdd_time(new Date());
		users.setMobile(mobile);
		users.setMeto(meto);
		try {
			dao.add(users);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		}else{
			Users users;
			try {
				users = dao.getUsers(Long.valueOf(id));
				users.setName(name);
				users.setPassword(password);
				users.setAddress(address);
			    users.setSex(Integer.valueOf(sex));
			    users.setAdd_time(new Date());
				users.setMobile(mobile);
				users.setMeto(meto);
				dao.update(users);
			} catch (Exception e) {
				
				e.printStackTrace();
			}	
		}
		
		forward=mapping.findForward("save");
		return forward;
		
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("=============执行delete方法===============");
		MessageForm messageForm = (MessageForm)form;
		messageForm.setMessage("delete method");
		
		ActionForward forward=null;
		UsersServiceImpl dao =new  UsersServiceImpl();
		String id =request.getParameter("id");
		try {
			dao.delete(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		forward=mapping.findForward("delete");
		return forward;
		
	}

	
	public ActionForward list(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("=============执行list方法===============");
		MessageForm messageForm = (MessageForm)form;
		messageForm.setMessage("list method");
		
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String mobile=request.getParameter("mobile");
		ActionForward forward=null;
		
		UsersServiceImpl dao =new UsersServiceImpl();
		Users users=new Users();
		if(name!=null&&name.length()>0){
			users.setName(name);
			}
			if(sex!=null&&sex.length()>0){
				
				users.setSex(Integer.valueOf(sex));
			}
			if(mobile!=null&&mobile.length()>0){
				users.setMobile(mobile);
			}
		try {
			List<Users> usersList =dao.getList(users);
			request.setAttribute("usersList", usersList);
			request.setAttribute("users", users);
			forward=mapping.findForward("list");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			
		return forward;
				
	}
	
	
	
	
	
	
	}
