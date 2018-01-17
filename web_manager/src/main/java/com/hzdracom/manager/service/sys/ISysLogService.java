package com.hzdracom.manager.service.sys;

import com.hzdracom.manager.bean.form.SyslogForm;
import com.hzdracom.manager.bean.sys.PageMsg;
import com.hzdracom.manager.bean.sys.SysLog;

/**
 * @类功能说明：系统日志业务接口
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公司名称：杭州龙骞科技有限公司
 * @作者：
 * @创建时间：
 * @版本：V1.0
 */
public interface ISysLogService {


	void addSysLog(SysLog log) throws Exception;
	
	public PageMsg<SysLog> getSyslogList(SyslogForm form) throws Exception;
}
