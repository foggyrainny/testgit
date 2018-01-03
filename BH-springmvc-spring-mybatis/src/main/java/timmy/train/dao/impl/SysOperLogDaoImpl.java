package timmy.train.dao.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import timmy.train.dao.SysOperLogDao;
import timmy.train.entity.SysOperLog;

@Repository
public class SysOperLogDaoImpl  implements SysOperLogDao{

	private static final Logger logger = Logger.getLogger(SysOperLogDaoImpl.class);
	private static final String PREFIX = "timmy.train.dao.mapper.SysOperLogMapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insert(SysOperLog sysOperLog) {
	   
		int a= sqlSessionTemplate.insert(PREFIX + "insert",sysOperLog);
		return a;
	}

	@Override
	public int update(SysOperLog sysOperLog) {
		int a= sqlSessionTemplate.update(PREFIX + "update",sysOperLog);
		return a;
	}

	@Override
	public int delete(Long id) {
		int a=sqlSessionTemplate.delete(PREFIX + "delete",id);
		return a;
	}

	@Override
	public SysOperLog getSysOperLog(Long id) {
		
		SysOperLog sysOperLog=sqlSessionTemplate.selectOne(PREFIX +"getOne",id);
		return sysOperLog;
	}

	@Override
	public List<SysOperLog> getSysOperLogList(SysOperLog sysOperLog) {
		logger.info("===========getPdInfoList============");
		List<SysOperLog> sysOperLogList=sqlSessionTemplate.selectList(PREFIX +"getList",sysOperLog);
		logger.info("pdInfoList="+sysOperLogList);
		return sysOperLogList;
	}

	@Override
	public int getSysOperLogCount(SysOperLog sysOperLog) {
		logger.info("===========getPdInfoCount============");
		int a =sqlSessionTemplate.selectOne(PREFIX +"getCount",sysOperLog);
		
		return a;
	}

	@Override
	public List<SysOperLog> getSysOperLogPaginatedList(SysOperLog sysOperLog, int first, int count) {
		logger.info("===========getPaginatedList============");
		List<SysOperLog> sysOperLogList=sqlSessionTemplate.selectList(PREFIX +"getPaginatedList",sysOperLog);
		
		return sysOperLogList;
	}

	
}
