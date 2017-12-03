package timmy.train.dao;

import java.util.List;

import timmy.train.entity.EntpMain;

public interface EntpMainDao {

	
	public int insert(EntpMain entpMain);
	
	public int update(EntpMain entpMain);
	
	public int delete(Long entp_id);
	
	public EntpMain getEntpMain(Long entp_id);
	
	public List<EntpMain> getEntpMainList(EntpMain entpMain);
	
	public int getEntpMainCount(EntpMain entpMain);
	
	public List<EntpMain> getEntpMainPaginatedList(EntpMain entpMain, int first, int count);

}
