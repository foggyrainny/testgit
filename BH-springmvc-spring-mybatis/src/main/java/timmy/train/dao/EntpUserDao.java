package timmy.train.dao;


import java.util.List;

import timmy.train.entity.EntpUser;



public interface EntpUserDao {

	
	public int insert(EntpUser entpUser);
	
	public int update(EntpUser entpUser);
	
	public int delete(Long user_id);
	
	public EntpUser getEntpUser(Long user_id);
	
	public List<EntpUser> getEntpUserList(EntpUser entpUser);
	
	public int getEntpUserCount(EntpUser entpUser);
	
	public List<EntpUser> getEntpUserPaginatedList(EntpUser entpUser, int first, int count);

}
