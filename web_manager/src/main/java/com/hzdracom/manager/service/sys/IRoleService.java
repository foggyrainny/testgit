package com.hzdracom.manager.service.sys;

import java.util.List;

import com.hzdracom.manager.bean.DataAuthority;
import com.hzdracom.manager.bean.form.RoleForm;
import com.hzdracom.manager.bean.sys.Menu;
import com.hzdracom.manager.bean.sys.Office;
import com.hzdracom.manager.bean.sys.PageMsg;
import com.hzdracom.manager.bean.sys.Role;


public interface IRoleService {
	
	
	public PageMsg<Role> getRoleList(RoleForm form) throws Exception;//
	
	public List<Menu> getMenuList(DataAuthority dataAuthority) throws Exception;//
	
	public List<Office> getOfficeList(DataAuthority dataAuthority) throws Exception;//
	
	public boolean doAddOrUpdate(Role role) throws Exception;//
	
	public Role getRole(int id) throws Exception;//
	
	/**
	 *  查询数据权限
	 */
	public List<Integer> queryRoleOfficeIds(Integer roleId);
	
	public boolean delete(String roleId) throws Exception;//
}
