/**
 * @类功能说明：
 * @类修改者：yulong
 * @修改日期：2014-12-26
 * @修改说明：
 * @公司名称：杭州龙骞科技有限公司
 * @作者：yulong
 * @创建时间：2014-12-26
 * @版本：
 */
package com.hzdracom.manager.service.sys.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hzdracom.manager.bean.form.SyslogForm;
import com.hzdracom.manager.bean.sys.PageMsg;
import com.hzdracom.manager.bean.sys.SysLog;
import com.hzdracom.manager.dao.sys.SysLogDao;
import com.hzdracom.manager.dao.sys.SysLogDao;
import com.hzdracom.manager.service.sys.ISysLogService;


/**
 * @类功能说明：
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公司名称：杭州龙骞科技有限公司
 * @作者：yl
 * @创建时间：2014-12-26
 * @版本：
 */
@Service("sysLogService")
public class SysLogServiceImpl implements ISysLogService{
	/*@Resource
	private SysLogDao  sysLogDao;*/
	
	@Resource
	private SysLogDao dao;
	
	
	

	@Override
	public PageMsg<SysLog> getSyslogList(SyslogForm form) throws Exception {
		 List<SysLog> list = dao.getSyslogList(form);
		 PageMsg<SysLog> pageObj = new PageMsg<SysLog>();
		 pageObj.setListResult(list);
		 pageObj.setTotalNum(dao.getSyslogListTotal(form));
		 pageObj.setPageNum(form.getSize());
		 pageObj.setPage(form.getCurr());
		 pageObj.setTotalPage();
		 return pageObj;
	}
	
	
	
	@Override
	public void addSysLog(SysLog log) throws Exception {
		dao.insertLog(log);
	}






	
}
