package com.hzdracom.manager.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hzdracom.manager.bean.Selector;
import com.hzdracom.manager.bean.sys.Area;
import com.hzdracom.manager.bean.sys.Office;

/**
 * @title: OfficeDao.java
 * @pacjage: com.hzdracom.manager.dao.sys
 * @description: TODO
 * @author: 高辉
 * @date: 2017年3月20日 下午5:49:32
 */
@Repository
public interface OfficeDao {
	
	public List<Office> getOfficeList(Integer parentId) throws Exception;
	
	public List<Area> getAreaList() throws Exception;
	
	List<Office> isOffice(Office office);
	
	public int addOffice(Office office) throws Exception;
	
	public int updateOffice(Office office) throws Exception;
	
	public Office getOffice(int id) throws Exception;
	
	List<Office> getParent(int id);
	
	public int delete(int id) throws Exception;
	
	public void updateOfficeParents() throws Exception;
	
	public List<Office> getOfficeTreeDown();
	
	public List<Office> getOfficeTree();
	
	public List<Selector> queryOfficeSelector(@Param("type")String type,@Param("roleId")Integer roleId);
}
