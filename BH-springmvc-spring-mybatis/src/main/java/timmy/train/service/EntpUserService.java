package timmy.train.service;

import java.util.List;

import timmy.train.entity.EntpUser;

public interface EntpUserService {
	
	public int create(EntpUser entpUser) throws Exception;
	
	public int modify(EntpUser entpUser) throws Exception;
	
	public int remove(Long user_id)throws Exception;
	
	public EntpUser getOne(Long user_id)throws Exception;
	
	public List<EntpUser> getList(EntpUser entpUser)throws Exception;
	
	public int getCount(EntpUser entpUser) throws Exception;
		
	public List<EntpUser> getPaginatedList(EntpUser entpUser, int first, int count) throws Exception;

}