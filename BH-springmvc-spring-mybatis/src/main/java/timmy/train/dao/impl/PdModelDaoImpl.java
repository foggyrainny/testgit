package timmy.train.dao.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import timmy.train.dao.PdModelDao;
import timmy.train.entity.PdModel;

@Repository
public class PdModelDaoImpl  implements PdModelDao{

	private static final Logger logger = Logger.getLogger(PdModelDaoImpl.class);
	private static final String PREFIX = "timmy.train.dao.mapper.PdModelMapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insert(PdModel pdModel) {
	   
		int a= sqlSessionTemplate.insert(PREFIX + "insert",pdModel);
		return a;
	}

	@Override
	public int update(PdModel pdModel) {
		int a= sqlSessionTemplate.update(PREFIX + "update",pdModel);
		return a;
	}

	@Override
	public int delete(Long model_id) {
		int a=sqlSessionTemplate.delete(PREFIX + "delete",model_id);
		return a;
	}

	@Override
	public PdModel getPdModel(Long model_id) {
		
		PdModel pdModel=sqlSessionTemplate.selectOne(PREFIX +"getOne",model_id);
		return pdModel;
	}

	@Override
	public List<PdModel> getPdModelList(PdModel pdModel) {
		logger.info("===========getPdInfoList============");
		List<PdModel> pdModelList=sqlSessionTemplate.selectList(PREFIX +"getList",pdModel);
		logger.info("pdInfoList="+pdModelList);
		return pdModelList;
	}

	@Override
	public int getPdModelCount(PdModel PdModel) {
		logger.info("===========getPdInfoCount============");
		int a =sqlSessionTemplate.selectOne(PREFIX +"getCount",PdModel);
		
		return a;
	}

	@Override
	public List<PdModel> getPdModelPaginatedList(PdModel PdModel, int first, int count) {
		logger.info("===========getPaginatedList============");
		List<PdModel> pdModelList=sqlSessionTemplate.selectList(PREFIX +"getPaginatedList",PdModel);
		
		return pdModelList;
	}

	
}
