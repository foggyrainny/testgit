package timmy.train.dao;


import java.util.List;

import timmy.train.entity.PdModel;



public interface PdModelDao {

	
	public int insert(PdModel pdModel);
	
	public int update(PdModel pdModel);
	
	public int delete(Long model_id);
	
	public PdModel getPdModel(Long model_id);
	
	public List<PdModel> getPdModelList(PdModel pdModel);
	
	public int getPdModelCount(PdModel pdModel);
	
	public List<PdModel> getPdModelPaginatedList(PdModel pdModel, int first, int count);

}
