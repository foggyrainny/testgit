package cn.goodjobs.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import cn.goodjobs.cms.domain.Users;
import cn.goodjobs.cms.service.UsersService;
import cn.goodjobs.cms.utils.Pager;

public class UsersAction {

	private static final long serialVersionUID = -7433669033389953697L;
	
	Pager pager = new Pager();

	private Users users = new Users();	

	private UsersService usersServices;
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	public String query()throws Exception{	
		HttpServletRequest request = null;
		String requestPage = request.getParameter("pager.requestPage");

		int recordCount = usersServices.getUsersCount(users);
		pager.init(recordCount, pager.getPageSize(), requestPage);
		
		List<Users> usersList = usersServices.getUsersPaginatedList(users, pager.getFirstRow(), pager.getRowCount());
//		ActionContext.getContext().put("usersList", usersList);	
		request.setAttribute("pager", pager);
		request.setAttribute("users", users);
		
		return "list";
	}

	public String query1()throws Exception{			
//		ActionContext.getContext().put("usersList", usersServices.getUsersList(users));			
		return "list";
	}
	
	@SuppressWarnings("unused")
	public String save()throws Exception{
		Long id = users.getId() == null ? usersServices.createUsers(users) : usersServices.modifyUsers(users);		
		return "save";

	}
	
	public String add()throws Exception{
		return "input";
	}
	
	public String edit()throws Exception{		
		this.users = users.getId() != null ? usersServices.getUsers(users.getId()) : null;	
		return "input";
	}
	
	@SuppressWarnings("unused")
	public String delete()throws Exception{
		Long id = users.getId() != null ? usersServices.removeUsers(users.getId()) : 0;
		return "save";
	}

}
