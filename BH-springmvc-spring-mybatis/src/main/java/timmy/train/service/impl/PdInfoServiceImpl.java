package timmy.train.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import timmy.train.dao.PdInfoDao;
import timmy.train.entity.PdInfo;
import timmy.train.service.PdInfoService;

@Service
public class PdInfoServiceImpl implements PdInfoService {
	@Autowired
	private PdInfoDao pdInfoDao;

	@Override
	public int create(PdInfo pdInfo) throws Exception {
		int a=	pdInfoDao.insert(pdInfo);
		return a;
	}

	@Override
	public int modify(PdInfo pdInfo) throws Exception {
		
 		int a=	pdInfoDao.update(pdInfo);
		return a;
	}

	@Override
	public int remove(BigInteger pd_id) throws Exception {
		int a=	pdInfoDao.delete(pd_id);
		return a;
	}

	@Override
	public PdInfo getOne(BigInteger pd_id) throws Exception {
		PdInfo pdInfo=pdInfoDao.getPdInfo(pd_id);
		return pdInfo;
	}

	@Override
	public List<PdInfo> getList(PdInfo pdInfo) throws Exception {
		List<PdInfo> pdInfoList=pdInfoDao.getPdInfoList(pdInfo);
		return pdInfoList;
	}

	@Override
	public int getCount(PdInfo pdInfo) throws Exception {
		int a=pdInfoDao.getPdInfoCount(pdInfo);
		return a;
	}

	@Override
	public List<PdInfo> getPaginatedList(PdInfo pdInfo, int first, int count) throws Exception {
		List<PdInfo> pdInfoList=pdInfoDao.getPdInfoPaginatedList(pdInfo, first, count);
		return pdInfoList;
	}

	
	
}
