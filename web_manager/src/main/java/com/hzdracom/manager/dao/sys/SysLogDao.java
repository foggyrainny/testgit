package com.hzdracom.manager.dao.sys;

import java.util.List;

import com.hzdracom.manager.bean.form.SyslogForm;
import com.hzdracom.manager.bean.sys.SysLog;

/**
 * @title: SysLogDao2.java
 * @pacjage: com.hzdracom.manager.dao.sys
 * @description: TODO
 * @author: 高辉
 * @date: 2017年4月10日 下午3:55:30
 */
public interface SysLogDao {
	
	public List<SysLog> getSyslogList(SyslogForm form);
	
	public int getSyslogListTotal(SyslogForm form);
	
	public int insertLog(SysLog form);
}
