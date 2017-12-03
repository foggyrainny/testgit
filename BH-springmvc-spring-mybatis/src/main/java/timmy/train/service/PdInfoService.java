package timmy.train.service;

import java.math.BigInteger;
import java.util.List;

import timmy.train.entity.PdInfo;

public interface PdInfoService {
	
	public int create(PdInfo pdInfo) throws Exception;
	
	public int modify(PdInfo pdInfo) throws Exception;
	
	public int remove(BigInteger pd_id)throws Exception;
	
	public PdInfo getOne(BigInteger pd_id)throws Exception;
	
	public List<PdInfo> getList(PdInfo pdInfo)throws Exception;
	
	public int getCount(PdInfo pdInfo) throws Exception;
		
	public List<PdInfo> getPaginatedList(PdInfo pdInfo, int first, int count) throws Exception;

}