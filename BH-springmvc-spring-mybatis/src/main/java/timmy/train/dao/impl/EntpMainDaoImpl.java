package timmy.train.dao.impl;


import java.util.List;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import timmy.train.dao.EntpMainDao;
import timmy.train.entity.EntpMain;

@Repository
public class EntpMainDaoImpl  implements EntpMainDao{

	private static final Logger logger = Logger.getLogger(EntpMainDaoImpl.class);
	private static final String PREFIX = "timmy.train.dao.mapper.EntpMainMapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insert(EntpMain entpMain) {
	   
		int a= sqlSessionTemplate.insert(PREFIX + "insert",entpMain);
		return a;
	}

	@Override
	public int update(EntpMain entpMain) {
		int a= sqlSessionTemplate.update(PREFIX + "update",entpMain);
		return a;
	}

	@Override
	public int delete(Long entp_id) {
		int a=sqlSessionTemplate.delete(PREFIX + "delete",entp_id);
		return a;
	}

	@Override
	public EntpMain getEntpMain(Long entp_id) {
		
		EntpMain entpMain=sqlSessionTemplate.selectOne(PREFIX +"getOne",entp_id);
		return entpMain;
	}

	@Override
	public List<EntpMain> getEntpMainList(EntpMain entpMain) {
		logger.info("===========getPdInfoList============");
		List<EntpMain> entpMainList=sqlSessionTemplate.selectList(PREFIX +"getList",entpMain);
		logger.info("pdInfoList="+entpMainList);
		return entpMainList;
	}

	@Override
	public int getEntpMainCount(EntpMain entpMain) {
		logger.info("===========getPdInfoCount============");
		int a =sqlSessionTemplate.selectOne(PREFIX +"getCount",entpMain);
		
		return a;
	}

	@Override
	public List<EntpMain> getEntpMainPaginatedList(EntpMain entpMain, int first, int count) {
		logger.info("===========getPaginatedList============");
		List<EntpMain> EntpMainList=sqlSessionTemplate.selectList(PREFIX +"getPaginatedList",entpMain);
		
		return EntpMainList;
	}

	
}
