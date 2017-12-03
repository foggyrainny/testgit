package timmy.train.dao.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import timmy.train.dao.PdTypeDao;
import timmy.train.entity.PdType;

@Repository
public class PdTypeDaoImpl  implements PdTypeDao{

	private static final Logger logger = Logger.getLogger(PdTypeDaoImpl.class);
	private static final String PREFIX = "timmy.train.dao.mapper.PdTypeMapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insert(PdType pdType) {
	   
		int a= sqlSessionTemplate.insert(PREFIX + "insert",pdType);
		return a;
	}

	@Override
	public int update(PdType pdType) {
		int a= sqlSessionTemplate.update(PREFIX + "update",pdType);
		return a;
	}

	@Override
	public int delete(Long model_id) {
		int a=sqlSessionTemplate.delete(PREFIX + "delete",model_id);
		return a;
	}

	@Override
	public PdType getPdType(Long model_id) {
		
		PdType pdType=sqlSessionTemplate.selectOne(PREFIX +"getOne",model_id);
		return pdType;
	}

	@Override
	public List<PdType> getPdTypeList(PdType pdType) {
		logger.info("===========getPdInfoList============");
		List<PdType> pdTypeList=sqlSessionTemplate.selectList(PREFIX +"getList",pdType);
		logger.info("pdInfoList="+pdTypeList);
		return pdTypeList;
	}

	@Override
	public int getPdTypeCount(PdType pdType) {
		logger.info("===========getPdInfoCount============");
		int a =sqlSessionTemplate.selectOne(PREFIX +"getCount",pdType);
		
		return a;
	}

	@Override
	public List<PdType> getPdTypePaginatedList(PdType pdType, int first, int count) {
		logger.info("===========getPaginatedList============");
		List<PdType> pdTypeList=sqlSessionTemplate.selectList(PREFIX +"getPaginatedList",pdType);
		
		return pdTypeList;
	}

	
}
