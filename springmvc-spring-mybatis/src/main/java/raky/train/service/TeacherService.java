package raky.train.service;

import java.util.List;
import raky.train.domain.Teacher;

public interface TeacherService {
	
	public void create(Teacher teacher) throws Exception;

	public void modify(Teacher teacher) throws Exception;
	
	public void remove(Integer id)throws Exception;
	
	public Teacher getOne(Integer id)throws Exception;
	
	public List<Teacher> getList(Teacher teacher)throws Exception;
	
	public int getCount(Teacher teacher) throws Exception;
	
	public List<Teacher> getPaginatedList(Teacher teacher, int first, int count) throws Exception;
	
	public int exists(Teacher teacher) throws Exception;
}
