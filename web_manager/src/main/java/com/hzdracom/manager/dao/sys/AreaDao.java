/**
 * 
 */
package com.hzdracom.manager.dao.sys;

import java.util.List;

import com.hzdracom.manager.bean.sys.Area;

/** 
 *  Title: com.hzdracom.manager.dao.sys
 *  Description: 
 *  Company: 杭州龙骞科技有限公司 
 *  @author  panke
 *  @date 2017年3月23日 
 */
public interface AreaDao {

	public List<Area>  queryAreaNames(Area form);
	
	public List<Area>  getAreaTreeDown();
	
	public List<Area> getAreaList(Area area);
}
