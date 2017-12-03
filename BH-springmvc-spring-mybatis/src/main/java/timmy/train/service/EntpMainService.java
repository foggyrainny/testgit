package timmy.train.service;

import java.util.List;

import timmy.train.entity.EntpMain;

public interface EntpMainService {
	
	public int create(EntpMain entpMain) throws Exception;
	
	public int modify(EntpMain entpMain) throws Exception;
	
	public int remove(Long entp_id)throws Exception;
	
	public EntpMain getOne(Long entp_id)throws Exception;
	
	public List<EntpMain> getList(EntpMain entpMain)throws Exception;
	
	public int getCount(EntpMain entpMain) throws Exception;
		
	public List<EntpMain> getPaginatedList(EntpMain entpMain, int first, int count) throws Exception;

}