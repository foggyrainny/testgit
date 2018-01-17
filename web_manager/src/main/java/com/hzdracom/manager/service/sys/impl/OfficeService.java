package com.hzdracom.manager.service.sys.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hzdracom.manager.bean.Selector;
import com.hzdracom.manager.bean.sys.Office;
import com.hzdracom.manager.bean.sys.Role;
import com.hzdracom.manager.dao.sys.OfficeDao;
import com.hzdracom.manager.service.sys.IOfficeService;

/**
 * @title: OfficeService.java
 * @pacjage: com.hzdracom.manager.service.sys.impl
 * @description: TODO
 * @author: 高辉
 * @date: 2017年3月20日 下午5:39:32
 */
@Service
public class OfficeService implements IOfficeService {
	@Resource
	private OfficeDao dao;

	@Override
	public List<Office> getOfficeList(Map<String, Object> parameMap)
			throws Exception {
		Integer parentId = Integer.valueOf(parameMap.get("parentId").toString());
		return dao.getOfficeList(parentId);
	}

	@Override
	public boolean doAddOrUpdate(Office office) throws Exception {
		if (dao.isOffice(office).size() > 0) {
			return false;
		}
		int i = 0;
		if (office.getId() > 0) i = dao.updateOffice(office);
		else i = dao.addOffice(office);
		dao.updateOfficeParents();
		return i > 0;
	}

	@Override
	public Office getOffice(int id) throws Exception {
		return dao.getOffice(id);
	}

	@Override
	public boolean delete(int id) throws Exception {
		List<Office> list = dao.getParent(id);
		if (list.size() > 0) return false;
		int i = dao.delete(id);
		dao.updateOfficeParents();
		return i > 0;
	}

	@Override
	public List<Office> getOfficeTreeDown(String type) {
		Role role = new Role();//临时数据，，，，，等待登录功能修改后移除，，，换session下ROLE
		role.setId(1);
		role.setRoleName("超级管理员");
		role.setOfficeId(1);
		
		int parentId = 0;
		if (type.equals("all")) {
			parentId = 0;
		} else if (type.equals("role")) {
			parentId = role.getOfficeId();
		}
		return dao.getOfficeTreeDown();
	}

	@Override
	public List<Office> getOfficeTree() throws Exception {
		return dao.getOfficeTree();
	}

	@Override
	public List<Selector> queryOfficeSelector(String type, Integer roleId) {
		return dao.queryOfficeSelector(type, roleId);
	}

}
