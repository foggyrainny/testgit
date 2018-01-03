package timmy.train.dao;


import java.util.List;

import timmy.train.entity.SysOperLog;

public interface SysOperLogDao {

	
	public int insert(SysOperLog sysOperLog);
	
	public int update(SysOperLog sysOperLog);
	
	public int delete(Long id);
	
	public SysOperLog getSysOperLog(Long id);
	
	public List<SysOperLog> getSysOperLogList(SysOperLog sysOperLog);
	
	public int getSysOperLogCount(SysOperLog sysOperLog);
	
	public List<SysOperLog> getSysOperLogPaginatedList(SysOperLog sysOperLog, int first, int count);

}
