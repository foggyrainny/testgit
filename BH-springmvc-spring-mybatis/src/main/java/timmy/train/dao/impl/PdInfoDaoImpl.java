package timmy.train.dao.impl;

import java.math.BigInteger;
import java.util.List;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import timmy.train.dao.PdInfoDao;
import timmy.train.entity.PdInfo;
@Repository
public class PdInfoDaoImpl  implements PdInfoDao{

	private static final Logger logger = Logger.getLogger(PdInfoDaoImpl.class);
	private static final String PREFIX = "timmy.train.dao.mapper.PdInfoMapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insert(PdInfo pdInfo) {
	   
		int a= sqlSessionTemplate.insert(PREFIX + "insert",pdInfo);
		return a;
	}

	@Override
	public int update(PdInfo pdInfo) {
		int a= sqlSessionTemplate.update(PREFIX + "update",pdInfo);
		return a;
	}

	@Override
	public int delete(BigInteger pd_id) {
		int a=sqlSessionTemplate.delete(PREFIX + "delete",pd_id);
		return a;
	}

	@Override
	public PdInfo getPdInfo(BigInteger pd_id) {
		
		PdInfo pdinfo=sqlSessionTemplate.selectOne(PREFIX +"getOne",pd_id);
		return pdinfo;
	}

	@Override
	public List<PdInfo> getPdInfoList(PdInfo pdInfo) {
		logger.info("===========getPdInfoList============");
		List<PdInfo> pdInfoList=sqlSessionTemplate.selectList(PREFIX +"getList",pdInfo);
		logger.info("pdInfoList="+pdInfoList);
		return pdInfoList;
	}

	@Override
	public int getPdInfoCount(PdInfo pdInfo) {
		logger.info("===========getPdInfoCount============");
		int a =sqlSessionTemplate.selectOne(PREFIX +"getCount",pdInfo);
		
		return a;
	}

	@Override
	public List<PdInfo> getPdInfoPaginatedList(PdInfo pdInfo, int first, int count) {
		logger.info("===========getPaginatedList============");
		List<PdInfo> pdInfoList=sqlSessionTemplate.selectList(PREFIX +"getPaginatedList",pdInfo);
		
		return pdInfoList;
	}

}
