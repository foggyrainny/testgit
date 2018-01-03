package timmy.service;

import java.util.List;

import timmy.daomain.Users;

public interface UsersService {

	public int getUser(Users user) throws Exception;
	
	public void add(Users users) throws Exception;
	
	public void delete(Long id) throws Exception;
	
	public void update(Users users) throws Exception;
	
	public Users getUsers(Long id) throws Exception;
		
	public List<Users> getList(Users users) throws Exception;
   
}
