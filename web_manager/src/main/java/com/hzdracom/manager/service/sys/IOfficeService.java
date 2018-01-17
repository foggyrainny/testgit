package com.hzdracom.manager.service.sys;

import java.util.List;
import java.util.Map;

import com.hzdracom.manager.bean.Selector;
import com.hzdracom.manager.bean.sys.Office;

/**
 * @title: IOfficeService.java
 * @pacjage: com.hzdracom.manager.service.sys
 * @description: TODO
 * @author: 高辉
 * @date: 2017年3月20日 下午5:35:02
 */
public interface IOfficeService {
	/**
	 * 获取
	 * @description: 
	 * @date: 2017年3月20日 下午5:36:18
	 */
	public List<Office> getOfficeList(Map<String, Object> parameMap) throws Exception;
	
	
	public List<Office> getOfficeTreeDown(String type);
	
	
	public boolean doAddOrUpdate(Office office) throws Exception;
	
	
	public Office getOffice(int id) throws Exception;
	
	
	public boolean delete(int id) throws Exception;
	
	public List<Office> getOfficeTree() throws Exception;
	
	public List<Selector> queryOfficeSelector(String type,Integer roleId);
}
