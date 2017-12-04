package raky.train.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import raky.train.dao.TeacherDao;
import raky.train.domain.Teacher;

@Repository
public class TeacherDaoImpl implements TeacherDao {
	
	private static final Logger logger = Logger.getLogger(TeacherDaoImpl.class);
	
	@Autowired 
	private SqlSessionTemplate sqlSessionTemplate ;
	
	public SqlSessionTemplate getSqlSessionTemplate(){
		 logger.info(this.sqlSessionTemplate);
		return this.sqlSessionTemplate;
	  }
	
	public int insert(Teacher teacher) throws Exception{
		
		return this.getSqlSessionTemplate().insert("insert",teacher);
	}
	
	public int update(Teacher teacher) throws Exception{
		
		
		return this.getSqlSessionTemplate().update("update",teacher);
		
	}
	
	public int delete(Integer id)throws Exception{
		
		
		return this.getSqlSessionTemplate().delete("delete",id);
	}
	
	public Teacher getOne(Integer id)throws Exception{
		
		return this.getSqlSessionTemplate().selectOne("getOne",id);
	}
	
	public List<Teacher> getList(Teacher teacher)throws Exception{
		logger.info("getlist");
		return this.getSqlSessionTemplate().selectList("getList",teacher);
	}


	@Override
	public int getCount(Teacher teacher) throws Exception {
		int count=this.getSqlSessionTemplate().selectOne("getCount",teacher);
		logger.info("总数"+count);
		return count;
	}

	@Override
	public List<Teacher> getPaginatedList(Teacher teacher, int first, int count) throws Exception {
		logger.info("list总数"+count);
		
		return this.getSqlSessionTemplate().selectList("getPaginatedList", teacher);
	}
	
	
}
