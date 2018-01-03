package timmy.train.dao.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import timmy.train.dao.EntpUserDao;
import timmy.train.entity.EntpUser;

@Repository
public class EntpUserDaoImpl  implements EntpUserDao{

	private static final Logger logger = Logger.getLogger(EntpUserDaoImpl.class);
	private static final String PREFIX = "timmy.train.dao.mapper.EntpUserMapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insert(EntpUser entpUser) {
	   logger.info("--------------insert-------------------------");
		int a= sqlSessionTemplate.insert(PREFIX + "insert",entpUser);
		return a;
	}

	@Override
	public int update(EntpUser entpUser) {
		int a= sqlSessionTemplate.update(PREFIX + "update",entpUser);
		return a;
	}

	@Override
	public int delete(Long user_id) {
		int a=sqlSessionTemplate.delete(PREFIX + "delete",user_id);
		return a;
	}

	@Override
	public EntpUser getEntpUser(Long user_id) {
		
		EntpUser entpUser=sqlSessionTemplate.selectOne(PREFIX +"getOne",user_id);
		return entpUser;
	}

	@Override
	public List<EntpUser> getEntpUserList(EntpUser entpUser) {
		logger.info("===========getPdInfoList============");
		List<EntpUser> entpUserList=sqlSessionTemplate.selectList(PREFIX +"getList",entpUser);
		logger.info("pdInfoList="+entpUserList);
		return entpUserList;
	}

	@Override
	public int getEntpUserCount(EntpUser entpUser) {
		logger.info("===========getPdInfoCount============");
		int a =sqlSessionTemplate.selectOne(PREFIX +"getCount",entpUser);
		
		return a;
	}

	@Override
	public List<EntpUser> getEntpUserPaginatedList(EntpUser entpUser, int first, int count) {
		logger.info("===========getPaginatedList============");
		List<EntpUser> entpUserList=sqlSessionTemplate.selectList(PREFIX +"getPaginatedList",entpUser);
		
		return entpUserList;
	}

	
}
