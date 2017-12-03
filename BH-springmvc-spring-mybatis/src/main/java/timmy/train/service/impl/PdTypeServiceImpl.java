package timmy.train.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import timmy.train.dao.PdTypeDao;
import timmy.train.entity.PdType;
import timmy.train.service.PdTypeService;

@Service
public class PdTypeServiceImpl implements PdTypeService {
	@Autowired
	private PdTypeDao pdTypeDao;

	@Override
	public int create(PdType pdType) throws Exception {
		int a=	pdTypeDao.insert(pdType);
		return a;
	}

	@Override
	public int modify(PdType pdType) throws Exception {
		
 		int a=	pdTypeDao.update(pdType);
		return a;
	}

	@Override
	public int remove(Long pd_type) throws Exception {
		int a=	pdTypeDao.delete(pd_type);
		return a;
	}

	@Override
	public PdType getOne(Long pd_type) throws Exception {
		PdType pdType=pdTypeDao.getPdType(pd_type);
		return pdType;
	}

	@Override
	public List<PdType> getList(PdType pdType) throws Exception {
		List<PdType> pdTypeList=pdTypeDao.getPdTypeList(pdType);
		return pdTypeList;
	}

	@Override
	public int getCount(PdType pdType) throws Exception {
		int a=pdTypeDao.getPdTypeCount(pdType);
		return a;
	}

	@Override
	public List<PdType> getPaginatedList(PdType pdType, int first, int count) throws Exception {
		List<PdType> pdTypeList=pdTypeDao.getPdTypePaginatedList(pdType, first, count);
		return pdTypeList;
	}

	
	
}
