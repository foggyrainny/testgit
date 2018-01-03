package raky.train.dao;

import java.util.List;

import raky.train.domain.Teacher;

public interface TeacherDao {
	
	public int insert(Teacher teacher) throws Exception;
	
	public int update(Teacher teacher) throws Exception;
	
	public int delete(Integer id)throws Exception;
	
	public Teacher getOne(Integer id)throws Exception;
	
	public List<Teacher> getList(Teacher teacher)throws Exception;
	
	public int getCount(Teacher teacher) throws Exception;
	
	public List<Teacher> getPaginatedList(Teacher teacher, int first, int count) throws Exception;

}
