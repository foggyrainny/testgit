package timmy.dao;


import java.util.List;

import timmy.daomain.Users;

public interface UsersDao {
	
	
public void add(Users users) throws Exception;
	
	public void update(Users users) throws Exception;
	
	public void delete(Long id)throws Exception;
	
	public Users getUsersById(Long id)throws Exception;
	
	public List<Users> getList(Users users)throws Exception;
	
   
	}