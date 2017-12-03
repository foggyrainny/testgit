package raky.train.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raky.train.dao.TeacherDao;
import raky.train.domain.Teacher;
import raky.train.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherDao TeacherDao ;

	@Override
	public void create(Teacher teacher) throws Exception {
		TeacherDao.insert(teacher);	
	}

	@Override
	public void modify(Teacher teacher) throws Exception {
		TeacherDao.update(teacher);
	}

	@Override
	public void remove(Integer id) throws Exception {
		TeacherDao.delete(id);
	}

	@Override
	public Teacher getOne(Integer id) throws Exception {
		return TeacherDao.getOne(id);
	}

	@Override
	public List<Teacher> getList(Teacher teacher) throws Exception {
		return TeacherDao.getList(teacher);
	}

	@Override
	public int exists(Teacher teacher) throws Exception {
		return getList(teacher).size();
	}

	@Override
	public int getCount(Teacher teacher) throws Exception {
	
		return TeacherDao.getCount(teacher);
	}

	@Override
	public List<Teacher> getPaginatedList(Teacher teacher, int first, int count) throws Exception {
		return TeacherDao.getPaginatedList(teacher, first, count);
	}

}
