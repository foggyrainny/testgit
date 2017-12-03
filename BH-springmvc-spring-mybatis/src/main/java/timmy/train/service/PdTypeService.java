package timmy.train.service;

import java.util.List;

import timmy.train.entity. PdType;

public interface PdTypeService {
	
	public int create( PdType  pdType) throws Exception;
	
	public int modify( PdType  pdType) throws Exception;
	
	public int remove(Long pd_type)throws Exception;
	
	public  PdType getOne(Long pd_type)throws Exception;
	
	public List< PdType> getList( PdType  pdType)throws Exception;
	
	public int getCount( PdType  pdType) throws Exception;
		
	public List< PdType> getPaginatedList( PdType  pdType, int first, int count) throws Exception;

}