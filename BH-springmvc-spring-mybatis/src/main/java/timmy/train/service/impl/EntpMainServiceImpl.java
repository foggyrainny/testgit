package timmy.train.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import timmy.train.dao.EntpMainDao;
import timmy.train.entity.EntpMain;
import timmy.train.service.EntpMainService;

@Service
public class EntpMainServiceImpl implements EntpMainService {
	@Autowired
	private EntpMainDao entpMainDao;

	@Override
	public int create(EntpMain entpMain) throws Exception {
		int a=	entpMainDao.insert(entpMain);
		return a;
	}

	@Override
	public int modify(EntpMain entpMain) throws Exception {
		
 		int a=	entpMainDao.update(entpMain);
		return a;
	}

	@Override
	public int remove(Long entp_id) throws Exception {
		int a=	entpMainDao.delete(entp_id);
		return a;
	}

	@Override
	public EntpMain getOne(Long entp_id) throws Exception {
		EntpMain entpMain=entpMainDao.getEntpMain(entp_id);
		return entpMain;
	}

	@Override
	public List<EntpMain> getList(EntpMain entpMain) throws Exception {
		List<EntpMain> entpMainList=entpMainDao.getEntpMainList(entpMain);
		return entpMainList;
	}

	@Override
	public int getCount(EntpMain entpMain) throws Exception {
		int a=entpMainDao.getEntpMainCount(entpMain);
		return a;
	}

	@Override
	public List<EntpMain> getPaginatedList(EntpMain entpMain, int first, int count) throws Exception {
		List<EntpMain> entpMainList=entpMainDao.getEntpMainPaginatedList(entpMain, first, count);
		return entpMainList;
	}

	
	
}
