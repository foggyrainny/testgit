package timmy.service.impl;

import java.util.List;

import timmy.dao.impl.UsersDaoImpl;
import timmy.daomain.Users;
import timmy.service.UsersService;

public class UsersServiceImpl implements UsersService{
	
	UsersDaoImpl dao=new UsersDaoImpl();
	@Override
	public void add(Users users) throws Exception {
		
		dao.add(users);
	}

	@Override
	public void delete(Long id) throws Exception {
		
		dao.delete(id);
		
	}

	@Override
	public void update(Users users) throws Exception {
		dao.update(users);
		
	}

	@Override
	public Users getUsers(Long id) throws Exception {
		
		return dao.getUsersById(id);
	}

	@Override
	public List<Users> getList(Users users) throws Exception {
	
		return dao.getList(users);
	}

	@Override
	public int getUser(Users users) throws Exception {
		
		return dao.getList(users).size();
	}

	
	
	}

	
	

