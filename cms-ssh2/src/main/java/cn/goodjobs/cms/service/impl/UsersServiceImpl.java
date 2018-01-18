package cn.goodjobs.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.goodjobs.cms.dao.UsersDao;
import cn.goodjobs.cms.entity.Users;
import cn.goodjobs.cms.service.UsersService;
import raky.ssh.service.impl.CoreServiceImpl;

@Service
public class UsersServiceImpl extends CoreServiceImpl<Users> implements UsersService {

	@Autowired
	private UsersDao usersDao;
	
	@Override
	public List<Users> getList(Users users) {
		return usersDao.getList(users);
	}

	@Override
	public int getCount(Users t) {
		return usersDao.getCount(t);

	}

	@Override
	public List<Users> getPaginatedList(Users t) {
		return usersDao.getPaginatedList(t);
	}
}