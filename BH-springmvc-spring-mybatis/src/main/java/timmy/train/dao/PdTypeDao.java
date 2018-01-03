package timmy.train.dao;


import java.util.List;

import timmy.train.entity.PdType;



public interface PdTypeDao {

	
	public int insert(PdType pdType);
	
	public int update(PdType pdType);
	
	public int delete(Long pd_type);
	
	public PdType getPdType(Long pd_type);
	
	public List<PdType> getPdTypeList(PdType pdType);
	
	public int getPdTypeCount(PdType pdType);
	
	public List<PdType> getPdTypePaginatedList(PdType pdType, int first, int count);

}
