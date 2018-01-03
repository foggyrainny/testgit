package timmy.train.service;

import java.util.List;

import timmy.train.entity.SysOperLog;

public interface SysOperLogService {
	
	public int create(SysOperLog sysOperLog) throws Exception;
	
	public int modify(SysOperLog sysOperLog) throws Exception;
	
	public int remove(Long id)throws Exception;
	
	public SysOperLog getOne(Long id)throws Exception;
	
	public List<SysOperLog> getList(SysOperLog sysOperLog)throws Exception;
	
	public int getCount(SysOperLog sysOperLog) throws Exception;
		
	public List<SysOperLog> getPaginatedList(SysOperLog sysOperLog, int first, int count) throws Exception;

}