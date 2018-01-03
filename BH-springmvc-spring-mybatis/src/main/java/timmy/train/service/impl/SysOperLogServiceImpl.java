package timmy.train.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import timmy.train.dao.SysOperLogDao;
import timmy.train.entity.SysOperLog;
import timmy.train.service.SysOperLogService;

@Service
public class SysOperLogServiceImpl implements SysOperLogService {
	@Autowired
	private SysOperLogDao sysOperLogDao;

	@Override
	public int create(SysOperLog sysOperLog) throws Exception {
		int a=	sysOperLogDao.insert(sysOperLog);
		return a;
	}

	@Override
	public int modify(SysOperLog sysOperLog) throws Exception {
		
 		int a=	sysOperLogDao.update(sysOperLog);
		return a;
	}

	@Override
	public int remove(Long id) throws Exception {
		int a=	sysOperLogDao.delete(id);
		return a;
	}

	@Override
	public SysOperLog getOne(Long id) throws Exception {
		SysOperLog sysOperLog=sysOperLogDao.getSysOperLog(id);
		return sysOperLog;
	}

	@Override
	public List<SysOperLog> getList(SysOperLog sysOperLog) throws Exception {
		List<SysOperLog> sysOperLogList=sysOperLogDao.getSysOperLogList(sysOperLog);
		return sysOperLogList;
	}

	@Override
	public int getCount(SysOperLog sysOperLog) throws Exception {
		int a=sysOperLogDao.getSysOperLogCount(sysOperLog);
		return a;
	}

	@Override
	public List<SysOperLog> getPaginatedList(SysOperLog sysOperLog, int first, int count) throws Exception {
		List<SysOperLog> sysOperLogList=sysOperLogDao.getSysOperLogPaginatedList(sysOperLog, first, count);
		return sysOperLogList;
	}

	
	
}
