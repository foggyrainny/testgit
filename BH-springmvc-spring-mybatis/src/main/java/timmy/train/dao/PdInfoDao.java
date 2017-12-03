package timmy.train.dao;

import java.math.BigInteger;
import java.util.List;

import timmy.train.entity.PdInfo;

public interface PdInfoDao {

	
	public int insert(PdInfo pdInfo);
	
	public int update(PdInfo pdInfo);
	
	public int delete(BigInteger pd_id);
	
	public PdInfo getPdInfo(BigInteger pd_id);
	
	public List<PdInfo> getPdInfoList(PdInfo pdInfo);
	
	public int getPdInfoCount(PdInfo pdInfo);
	
	public List<PdInfo> getPdInfoPaginatedList(PdInfo pdInfo, int first, int count);

}
