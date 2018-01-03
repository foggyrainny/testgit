package timmy.train.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import timmy.train.dao.EntpUserDao;
import timmy.train.entity.EntpUser;
import timmy.train.service.EntpUserService;

@Service
public class EntpUserServiceImpl implements EntpUserService {
	@Autowired
	private EntpUserDao entpUserDao;

	@Override
	public int create(EntpUser entpUser) throws Exception {
		int a=	entpUserDao.insert(entpUser);
		return a;
	}

	@Override
	public int modify(EntpUser entpUser) throws Exception {
		
 		int a=	entpUserDao.update(entpUser);
		return a;
	}

	@Override
	public int remove(Long user_id) throws Exception {
		int a=	entpUserDao.delete(user_id);
		return a;
	}

	@Override
	public EntpUser getOne(Long user_id) throws Exception {
		EntpUser entpUser=entpUserDao.getEntpUser(user_id);
		return entpUser;
	}

	@Override
	public List<EntpUser> getList(EntpUser entpUser) throws Exception {
		List<EntpUser> entpUserList=entpUserDao.getEntpUserList(entpUser);
		return entpUserList;
	}

	@Override
	public int getCount(EntpUser entpUser) throws Exception {
		int a=entpUserDao.getEntpUserCount(entpUser);
		return a;
	}

	@Override
	public List<EntpUser> getPaginatedList(EntpUser entpUser, int first, int count) throws Exception {
		List<EntpUser> entpUserList=entpUserDao.getEntpUserPaginatedList(entpUser, first, count);
		return entpUserList;
	}

	
	
}
