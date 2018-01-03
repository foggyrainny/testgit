package timmy.train.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import timmy.train.dao.PdModelDao;
import timmy.train.entity.PdModel;
import timmy.train.service.PdModelService;

@Service
public class PdModelServiceImpl implements PdModelService {
	@Autowired
	private PdModelDao pdModelDao;

	@Override
	public int create(PdModel pdModel) throws Exception {
		int a=	pdModelDao.insert(pdModel);
		return a;
	}

	@Override
	public int modify(PdModel pdModel) throws Exception {
		
 		int a=	pdModelDao.update(pdModel);
		return a;
	}

	@Override
	public int remove(Long model_id) throws Exception {
		int a=	pdModelDao.delete(model_id);
		return a;
	}

	@Override
	public PdModel getOne(Long model_id) throws Exception {
		PdModel pdModel=pdModelDao.getPdModel(model_id);
		return pdModel;
	}

	@Override
	public List<PdModel> getList(PdModel pdModel) throws Exception {
		List<PdModel> pdModelList=pdModelDao.getPdModelList(pdModel);
		return pdModelList;
	}

	@Override
	public int getCount(PdModel pdModel) throws Exception {
		int a=pdModelDao.getPdModelCount(pdModel);
		return a;
	}

	@Override
	public List<PdModel> getPaginatedList(PdModel pdModel, int first, int count) throws Exception {
		List<PdModel> pdModelList=pdModelDao.getPdModelPaginatedList(pdModel, first, count);
		return pdModelList;
	}

	
	
}
