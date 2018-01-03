package timmy.train.service;

import java.util.List;

import timmy.train.entity.PdModel;

public interface PdModelService {
	
	public int create(PdModel pdModel) throws Exception;
	
	public int modify(PdModel pdModel) throws Exception;
	
	public int remove(Long model_id)throws Exception;
	
	public PdModel getOne(Long model_id)throws Exception;
	
	public List<PdModel> getList(PdModel pdModel)throws Exception;
	
	public int getCount(PdModel pdModel) throws Exception;
		
	public List<PdModel> getPaginatedList(PdModel pdModel, int first, int count) throws Exception;

}